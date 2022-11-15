package com.matter.otp.client;


import java.net.InetSocketAddress;
        import java.net.Proxy;
        import java.security.KeyManagementException;
        import java.security.NoSuchAlgorithmException;
        import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
        import javax.net.ssl.TrustManager;
        import javax.net.ssl.X509TrustManager;

import com.matter.otp.model.SmsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class SmsClient {

    static final Logger LOGGER = LoggerFactory.getLogger(SmsClient.class);

  //  @Value("${sms.url}")
    private String smsUrl;

   // @Value("${general.proxy}")
    private String generalProxy;

    public String sendSms(SmsVo requestData) {
        LOGGER.info("SEND SMS");
        String respuesta = "";
        try {

            OkHttpClient.Builder builder = prepareBuilder();
            builder.connectTimeout(2, TimeUnit.MINUTES);
            builder.readTimeout(2, TimeUnit.MINUTES);
            builder.writeTimeout(2, TimeUnit.MINUTES);

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(generalProxy, 8080));

            OkHttpClient okHttpClient = builder.proxy(proxy).build();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType,
                    "{\"usrClient\":\"FarWalmart\",\"pasClient\":\"eikd.20!DS2\",\"sender\":\"Walmart\"," + "\"gsm\":\"52"
                            + requestData.getNumber() + "\",\"text\":\"" + requestData.getMessage() + "\"}");
            Request request = new Request.Builder()
                    .url(smsUrl).post(body)
                    .addHeader("content-type", "application/json")
                    .build();

            Response response = okHttpClient.newCall(request).execute();
            respuesta = String.valueOf(response.code());

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            respuesta = "503";
        }

        return respuesta;
    }

    private OkHttpClient.Builder prepareBuilder() throws NoSuchAlgorithmException, KeyManagementException {
        final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws CertificateException {
                //implementation will be done when required
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType)
                    throws CertificateException {
                //implementation will be done when required
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }
        } };

        // Install the all-trusting trust manager


        final SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(2, TimeUnit.MINUTES);
        builder.readTimeout(2, TimeUnit.MINUTES);
        builder.writeTimeout(2, TimeUnit.MINUTES);
        builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
        builder.hostnameVerifier((hostname, session) -> hostname.equalsIgnoreCase(session.getPeerHost()));
        return builder;
    }

}
