package com.example.networkmodule.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

public class NetworkUtil {
    /**
     * 检测当的网络（WLAN、3G/2G）状态
     *
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    static HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            return true;
        }
    };


//        String url1="https://1.1.1.1:2017";//测试https
//        String url2="http://1.1.1.1:2017";//测试http
//        String url3="1.1.1.1";//测试普通ip
//        System.out.println(url1+":"+netStateUtil.connectingAddress(url1));
//        System.out.println(url2+":"+netStateUtil.connectingAddress(url2));
//        System.out.println(url3+":"+netStateUtil.connectingAddress(url3));

    public static boolean connectingAddress(String remoteInetAddr) {
        boolean flag = false;
        String tempUrl = remoteInetAddr.substring(0, 5);//取出地址前5位
        if (tempUrl.contains("http")) {//判断传过来的地址中是否有http
            if (tempUrl.equals("https")) {//判断服务器是否是https协议
                try {
                    trustAllHttpsCertificates();//当协议是https时
                } catch (Exception e) {
                    e.printStackTrace();
                }
                HttpsURLConnection.setDefaultHostnameVerifier(hv);//当协议是https时
            }
            flag = isConnServerByHttp(remoteInetAddr);
        } else {//传过来的是IP地址
            flag = isReachable(remoteInetAddr);
        }
        return flag;
    }

    /**
     *      * 传入需要连接的IP，返回是否连接成功
     *      *
     *      * @param remoteInetAddr
     *      * @return
     *     
     */


    public static boolean isReachable(String remoteInetAddr) {// IP地址是否可达，相当于Ping命令
        boolean reachable = false;
        try {
            InetAddress address = InetAddress.getByName(remoteInetAddr);
            reachable = address.isReachable(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reachable;
    }

    // 服务器是否开启
    public static boolean isConnServerByHttp(String serverUrl) {
        boolean connFlag = false;
        URL url;
        HttpURLConnection conn = null;
        try {
            url = new URL(serverUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(3 * 1000);
            if (conn.getResponseCode() == 200) {// 如果连接成功则设置为true
                connFlag = true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return connFlag;
    }
    /*以下是Https适用*/

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(
                sc.getSocketFactory());
    }


    static class miTM implements javax.net.ssl.TrustManager,
            javax.net.ssl.X509TrustManager

    {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                java.security.cert.X509Certificate[] certs) {
            return true;
        }


        public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType) throws java.security.cert.CertificateException

        {
            return;
        }

        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }

    public static String getIP(Context context){

        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
                {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address))
                    {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        }
        catch (SocketException ex){
            ex.printStackTrace();
        }
        return null;
    }

}
