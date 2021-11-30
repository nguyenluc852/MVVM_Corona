package com.s.basemvvm.repo

import com.s.basemvvm.BuildConfig
import com.s.basemvvm.model.TotalRespone
import com.s.basemvvm.utils.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

object RetrofitFactory {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    private val trustAllCerts =
        arrayOf<TrustManager>(
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                fun checkServerTrusted(
                    arr: Array<X509Certificate>,
                    authType: String,
                    host: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )
    // Install the all-trusting trust manager
    private var sslContext: SSLContext =
        SSLContext.getInstance("SSL").apply {
            init(null, trustAllCerts, SecureRandom())
        }

    // Create an ssl socket factory with our all-trusting manager
    private val sslSocketFactory = sslContext.socketFactory

    private val client by lazy {
        OkHttpClient().newBuilder()
            .addInterceptor(Interceptor {
                val  retquest = it.request().newBuilder()
                    .addHeader("x-rapidapi-host", AppConstant.API_HOST)
                    .addHeader("x-rapidapi-key", AppConstant.API_KEY)
                    .build()
                return@Interceptor it.proceed(retquest)
            })
            .addInterceptor(loggingInterceptor)

            .sslSocketFactory(
                sslSocketFactory,
                (trustAllCerts[0] as X509TrustManager)
            )

            .build()
    }

    val api: ApiService = Retrofit.Builder()
        .client(client)
        .baseUrl(AppConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiService::class.java)
}

suspend fun network(): ApiService = withContext(Dispatchers.IO) {
    RetrofitFactory.api
}