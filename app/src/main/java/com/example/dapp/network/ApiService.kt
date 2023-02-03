//package com.example.dapp.network
//
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import retrofit2.Retrofit
//import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.http.GET
//
//class ApiService {
//
//    private val BASE_URL = "http://192.168.18.99:8080/"
//        //"http://10.0.2.2:8080/"
//
//
//    /**
//     * Build the Moshi object with Kotlin adapter factory that Retrofit will be using.
//     */
//    private val moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()
//
//    /**
//     * The retrofit object with the Moshi converter.
//     */
//    private val retrofit = Retrofit.Builder()
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .baseUrl(BASE_URL)
//        .build()
//
//    /**
//     * a public interface that exposes the [get] method
//     */
//    interface ApiService {
//
//        @GET("custom/pedidos/")
//        suspend fun  getPedidos(): List<Pedidos>
//    }
//
//    /**
//     * A public Api object that exposes the lazy-initialized Retrofit service.
//     */
//    object Api {
//
//        val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
//    }
//
//
//
//
//}