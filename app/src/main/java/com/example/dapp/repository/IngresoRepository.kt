package com.example.dapp.repository

import com.example.dapp.network.AuthApi

class IngresoRepository (
    private val api: AuthApi
        ) : BaseRepository() {

    suspend fun getIngresos() = apiRequest {
        api.getIngresos()
    }
}