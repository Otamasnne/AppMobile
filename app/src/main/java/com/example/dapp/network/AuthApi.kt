package com.example.dapp.network

import com.example.dapp.responses.ArticulosResponseItem
import com.example.dapp.responses.completar.Completar
import com.example.dapp.responses.detalle.Items
import com.example.dapp.responses.ingreso.Ingreso
import com.example.dapp.responses.login.LoginRespondePH
import com.example.dapp.responses.login.User
import com.example.dapp.responses.login.UserDto
import com.example.dapp.responses.pedidoModel.Pedido
import com.example.dapp.responses.singleArticulo.SingleArticulo
import retrofit2.Response
import retrofit2.http.*

interface AuthApi {

//    //@FormUrlEncoded
//    @Headers(
//        "Authorization: Basic c3ZlbjpwYXNz",
//        "Content-Type: application/json",
//        //"Content-Length: <calculated when request is sent>",
//        //"Host: <calculated when request is sent>",
//        "Accept: application/json;profile=urn:org.apache.isis/v2;suppress=all")
//    @POST("restful/services/simple.Usuarios/actions/userValidation/invoke")
//    suspend fun login(
//        userDto: UserDto
//    ): LoginRespondePH

    @Headers(
        "Authorization: Basic c3ZlbjpwYXNz",
        "Content-Type: application/json",
        "Accept: application/json;profile=urn:org.apache.isis/v2;suppress=all")
    @POST("restful/services/depotapp.Usuarios/actions/userValidation/invoke")
    suspend fun login(@Body userData: UserDto) : User

    @Headers(
        "Authorization: Basic c3ZlbjpwYXNz",
        "Content-Type: application/json",
        "Accept: application/json")
    @POST("restful/objects/depotapp.Pedido/{codigo}/actions/completar/invoke")
    suspend fun completarPedido(@Path("codigo") codigo: String) : Completar

    //pruebas

//    @Headers(
//        "Authorization: Basic c3ZlbjpwYXNz",
//        "Accept: application/json;profile=urn:org.apache.isis/v2;suppress=all")
//    @GET("restful/services/depotapp.Pedidos/actions/listAll/invoke/")
//    suspend fun getPedidos() : Response<List<Pedido>>

    @Headers(
        "Authorization: Basic c3ZlbjpwYXNz",
        "Accept: application/json;profile=urn:org.apache.isis/v2;suppress=all")
    @GET("restful/services/depotapp.Pedidos/actions/listProcesando/invoke")
    suspend fun getPedidos() : Response<List<Pedido>>

    @Headers(
        "Authorization: Basic c3ZlbjpwYXNz",
        "Accept: application/json;profile=urn:org.apache.isis/v2;suppress=all")
    @GET("restful/services/depotapp.Pedidos/actions/listProcesando/invoke")
    suspend fun getPedidoInicio() : List<Pedido>



    @Headers(
        "Authorization: Basic c3ZlbjpwYXNz",
        "Accept: application/json;profile=urn:org.apache.isis/v2;suppress=all")
    @GET("restful/services/depotapp.Pedidos/actions/listItems/invoke")
    suspend fun getArticulos(@Query("codigo") codigo: String) : Response<List<Items>>

    @GET
    suspend fun getSingleArticulo(@Url href: String) : SingleArticulo


    //Ingresos

    @Headers(
        "Authorization: Basic c3ZlbjpwYXNz",
        "Accept: application/json;profile=urn:org.apache.isis/v2;suppress=all")
    suspend fun getIngresos() : Response<List<Ingreso>>


//    @Headers(
//        "Authorization: Basic c3ZlbjpwYXNz",
//        "Accept: application/json;profile=urn:org.apache.isis/v2;suppress=all")
//    @GET("restful/services/depotapp.Pedidos/actions/listItems/invoke/")
//    suspend fun getArticulos() : Response<List<Items>>

    //suspend fun getSingleArt(): Response<>

    /*
    * Para id por paramentro en la url:
    * @GET("url/{id}")
    * fun blabla(@Path("id") id:String: Call<asda>*/

//    @Headers(
//        "Authorization: Basic c3ZlbjpwYXNz",
//        "Accept: application/json;profile=urn:org.apache.isis/v2;suppress=all")
//    @GET("restful/services/depotapp.Articulos/actions/listAll/invoke/")
//    suspend fun getArticulos () : ArticulosResponseItem

}