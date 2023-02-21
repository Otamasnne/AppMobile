package com.example.dapp.repository

import com.example.dapp.network.AuthApi

class IngresoRepository (
    private val api: AuthApi
        ) : BaseRepository() {

    suspend fun getIngresos() = apiRequest {
        api.getIngresos()
    }

    suspend fun getArticulosIngreso(codigo: String) = apiRequest {
        api.getArticulosIngreso(codigo)
    }

    suspend fun completarIngreso(codigo: String) = safeApiCall {
        api.completarIngreso(codigo)
    }

    suspend fun getSingleArticulo(href: String) = safeApiCall {
        api.getSingleArticulo(href)
    }
}