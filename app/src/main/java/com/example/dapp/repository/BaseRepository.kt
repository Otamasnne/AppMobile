package com.example.dapp.repository

import com.example.dapp.network.Resource
import com.example.dapp.responses.pedidoModel.Pedido
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

abstract class BaseRepository {

    /**
     * Esta funcion va a ejecutar la llamada a la api
     * le pasamos un parametro que es nuestra llamada a la api
     */
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ) : Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                /**
                 * si successful ponemos el resultado en nuestra clase Resource
                 */
                Resource.Success(apiCall.invoke())
            }catch (throwable: Throwable) {
                when(throwable){
                    is HttpException -> {
                        Resource.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Resource.Failure(true, null, null)
                    }
                }
            }
        }
    }



    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>) : T {

        val response = call.invoke()
        if(response.isSuccessful){
            return response.body()!!
        } else {
            //@Todo handle api exception
//            val error = response.errorBody()?.toString()
//            val message = StringBuilder()
//            error?.let {
//                try {
//                    message.append(JSONObject(it).getString("message"))
//
//                }catch (e: JSONException){ }
//                message.append("\n")
//            }
//
//            message.append("Error code: ${response.code()}")
            throw Exception(response.code().toString())
            //return response.body()!!
        }
    }

    class ApiException(message: String) : IOException(message)


}