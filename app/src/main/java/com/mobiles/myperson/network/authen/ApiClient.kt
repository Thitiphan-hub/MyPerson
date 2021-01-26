package com.mobiles.myperson.network.authen
import android.content.Context
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class ApiClient {

    companion object{
        const val BASE_URL = "https://test-apfamily-api.apthai.com/"
    }

    fun buildRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildOkHttp())
            .build()
    }
        private fun buildOkHttp(): OkHttpClient {
                    val okHttpClient = OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .addNetworkInterceptor { chain ->
                    val originReq = chain.request()
                    val originHttpUrl = originReq.url
                    val originBuilder = originHttpUrl.newBuilder()
                    val modifiedHttpUrl = originBuilder.build()
                    val modifiedReq = originReq.newBuilder().apply {
                        url(modifiedHttpUrl)

                    }
                        chain.proceed(modifiedReq.build())

                    }
            return okHttpClient.build()
        }
    }
