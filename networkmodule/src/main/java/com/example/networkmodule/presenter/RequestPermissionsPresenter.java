package com.example.networkmodule.presenter;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class RequestPermissionsPresenter extends BasePresenter<RequestPermissionsPresenter.RequestPermissionsView> {
    private Activity activity;

    @Inject
    public RequestPermissionsPresenter(Activity context) {
        this.activity = context;
    }

    public void requestPermissions(String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permissions).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (getMvpView() != null && aBoolean != null) {
                    getMvpView().handleRequestPermissionsResult(aBoolean);
                }
            }
        });
    }

    public interface RequestPermissionsView extends MvpView {
        void handleRequestPermissionsResult(boolean result);
    }
}
