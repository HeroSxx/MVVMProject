package com.example.networkmodule.rxjava;


import android.content.Context;


import com.example.networkmodule.constant.Constant;
import com.example.networkmodule.gsonfactory.ApiException;
import com.example.networkmodule.gsonfactory.BaseResult;
import com.example.networkmodule.presenter.MvpView;
import com.example.networkmodule.util.ApkUtil;
import com.example.networkmodule.util.LogUtils;
import com.example.networkmodule.util.NetworkUtil;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

/**
 * class_name: BaseObserver
 * package_name: com.i5d5.basemodule.RX
 * author: lijun
 * time: 2017/3/30 14:30
 *
 * @author 金石
 */

public abstract class BaseObserver<T> implements Observer<T> {

    private MvpView mvpView;
    private Context context;
    //服务器开小差页面是否显示的标志位
    private boolean showServerBusy = false;

    public BaseObserver(MvpView mvpView, Context context) {
        this.mvpView = mvpView;
        this.context = context;
    }

    public BaseObserver(MvpView mvpView, Context context, boolean showServerBusy) {
        this.mvpView = mvpView;
        this.context = context;
        this.showServerBusy = showServerBusy;
    }

    @Override
    public void onSubscribe(Disposable d) {
        onPmSubScribe(d);
    }

    @Override
    public void onNext(T t) {
        onPmNext(t);
        try {
            BaseResult baseResult = (BaseResult) t;
            if (!baseResult.getResult() &&!baseResult.getCode().equals("1000")) {
                ApiException error = new ApiException(Integer.valueOf(baseResult.getCode()), baseResult.getMsg());
                handleErrorCode(error);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable e) {
        try {
            LogUtils.e("===onError", e.toString());
            if (!NetworkUtil.isNetworkAvailable(context)) {
                RxBus.getInstance().post(new EventObject(EventObject.NETWORK_DISCONNECT, "NETWORK_DISCONNECT"));
                return;
            }

            if (e instanceof HttpException) {
                HttpException error = (HttpException) e;
                ErrorModel errorModel = new ErrorModel();
                errorModel.setCode(error.code());
                mvpView.showErrorInfo(errorModel);
                verifyServerIsAvailable();

            } else if (e instanceof ConnectException) {
                if (NetworkUtil.isNetworkAvailable(context)) {
                    verifyServerIsAvailable();
                } else {
                    RxBus.getInstance().post(new EventObject(EventObject.NETWORK_DISCONNECT, "NETWORK_DISCONNECT"));
                }
            } else if (e instanceof SocketTimeoutException) {
                ErrorModel errorModel = new ErrorModel();
                errorModel.setCode(0);
                errorModel.setMessage("服务连接超时");
                mvpView.showErrorInfo(errorModel);
                verifyServerIsAvailable();
            } else if (e instanceof UnknownHostException) {
                RxBus.getInstance().post(new EventObject(EventObject.SEVER_ERROR, "SEVER_ERROR"));
            }
        } catch (Exception ex) {
            e.printStackTrace();
        }

    }

    //统一处理后台返回错误码
    private void handleErrorCode(ApiException e) {
        LogUtils.e("===error===",e.toString());
        String errorMessage = "";
        errorMessage=e.getMessage();
        if (e.getErrorCode() == 8007) {
            errorMessage = "您的账号已在其他终端登录";
            RxBus.getInstance().post(new EventObject(EventObject.OTHER_LOGIN, "已在其他终端登录"));
        } else if (e.getErrorCode() >= 8000 && e.getErrorCode() < 9000) {
            //token过期，重新登录
            errorMessage = "账号过期或未登录，请登录";
            RxBus.getInstance().post(new EventObject(EventObject.TOKEN_OUT_DATA, "请账号登录后使用"));
        }
        ErrorModel errorModel = new ErrorModel();
        errorModel.setCode(e.getErrorCode());
        errorModel.setMessage(errorMessage);
        mvpView.showErrorInfo(errorModel);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onPmNext(T t);

    public abstract void onPmSubScribe(Disposable d);

    /**
     * 当接口不可用的时候请求升级接口验证服务器是否可用，
     * 不可用弹出系统升级中弹窗，
     * 否则弹出服务器开小差页面
     */
    public void verifyServerIsAvailable() {

        String url = "";
        if (ApkUtil.isApkInDebug(context)) {
            url = Constant.TEST_UPDATE_URL;
        } else {
            url = Constant.OFFICIAL_UPDATE_URL;
        }
        Rx2AndroidNetworking.post(url)
                .addPathParameter("code", ApkUtil.getVersionName(context))
                .build()
                .getObjectObservable(BaseResult.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult baseResult) {
                        LogUtils.e("Rx2AndroidNetworking onNext :" + baseResult.toString());
                        if (baseResult.getResult()) {
                            if (showServerBusy) {
                                mvpView.onServerBusy();
                            }
                        } else {
                            RxBus.getInstance().post(new EventObject(EventObject.SEVER_ERROR, ""));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("Rx2AndroidNetworking onError :" + e.toString());
                        RxBus.getInstance().post(new EventObject(EventObject.SEVER_ERROR, ""));
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
