package com.example.dapp.repository

import com.example.dapp.network.AuthApi

class DetalleRepository (
    private val api: AuthApi
        ) : BaseRepository() {

    suspend fun getArticulos() = apiRequest {
        api.getArticulos()
    }
}