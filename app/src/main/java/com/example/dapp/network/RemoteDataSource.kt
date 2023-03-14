package com.example.dapp.network

import androidx.viewbinding.BuildConfig

//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create


class RemoteDataSource {
    companion object {
        private const val WEB_URL = "http://depotapp-env.eba-p7aerjjc.sa-east-1.elasticbeanstalk.com/"
        private const val BASE_URL = "http://192.168.18.99:8080/"
        private const val URL_EMU = "http://10.0.2.2:8080/"
    }

    /**
    //     * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
    //     */
//    fun <Api> buildApi(
//        api : Class<Api>
//    ) : Api{
//
//        return Retrofit.Builder()
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .client(
//                OkHttpClient.Builder().also { client ->
//                    if(BuildConfig.DEBUG) {
//                        val loggin = HttpLoggingInterceptor();
//                        loggin.setLevel(HttpLoggingInterceptor.Level.BODY)
//                        client.addInterceptor(loggin)
//                    }
//                }.build()
//            )
//            .build()
//
//            .create(api)
//    }


//
//        fun <Api> buildApi(
//        api : Class<Api>
//    ) : Api {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(api)
//    }

    fun <Api> buildApi(
        api : Class<Api>
    ) : Api {
        return Retrofit.Builder()
            .baseUrl(WEB_URL)
            .client(
                OkHttpClient.Builder().also { client ->
                    if(BuildConfig.DEBUG) {
                        val loggin = HttpLoggingInterceptor();
                        loggin.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(loggin)
                    }
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }


    /**
    //     * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
    //     */
//    private val moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()
//
//    fun <Api> buildApi(
//        api : Class<Api>
//    ) : Api{
//
//        return Retrofit.Builder()
//            .addConverterFactory(MoshiConverterFactory.create(moshi))
//            .baseUrl(BASE_URL)
//            .build()
//            .create(api)
//    }






//

    /**
     * The retrofit object with the Moshi converter.
     */
//    private val retrofit = Retrofit.Builder()
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .baseUrl(BASE_URL)
//        .build()

//    fun<Api> buildApi(
//        api: Class<Api>
//    ) : Api {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory()
//            .build()
//            .create(api)
//    }
}