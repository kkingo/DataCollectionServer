package com.datacollcet.Util;

/**
 * Created by 郭诗韬 on 2017/10/12.
 */
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class MyX509TrustManager implements X509TrustManager {
    public MyX509TrustManager() {
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
