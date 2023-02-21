package com.example.dapp.repository

import com.example.dapp.network.AuthApi

class DetalleRepository (
    private val api: AuthApi
        ) : BaseRepository() {

    suspend fun getArticulos(id: String) = apiRequest {
        api.getArticulos(id)
    }

    suspend fun completarPedido(codigo: String) = safeApiCall {
        api.completarPedido(codigo)
    }

    suspend fun getSingleArticulo(href: String) = safeApiCall {
        api.getSingleArticulo(href)
    }
}