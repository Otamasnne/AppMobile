package com.example.dapp.repository

import com.example.dapp.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

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
}