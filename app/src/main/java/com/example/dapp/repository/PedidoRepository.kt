package com.example.dapp.repository

import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.responses.pedidoModel.Pedido

class PedidoRepository (
    private val api: AuthApi
        ) : BaseRepository() {

    suspend fun getPedidos() = apiRequest{
        api.getPedidos()
    }

    suspend fun getPedidosSafe() = safeApiCall{
        api.getPedidoInicio()
    }



//    suspend fun getCharacter(characterId: String): Result<Character> {
//        return try {
//            val response = service.getCharacter(characterId)
//            if (response.code == "200") {
//                val result = response.toCharacter()
//                Result.Success(result)
//            } else {
//                Result.Error(response.status)
//            }
//        } catch (e: Exception) {
//            Result.Error(e.localizedMessage ?: e.toString())
//        }
//    }
//    suspend fun getPedidoDos(): Resource<Pedido>{
//        return try {
//            val response = api.getPedidos()
//            if(response)
//        }
//    }


}