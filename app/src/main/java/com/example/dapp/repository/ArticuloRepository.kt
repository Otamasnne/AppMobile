package com.example.dapp.repository

import com.example.dapp.network.AuthApi

class ArticuloRepository (
    private val api: AuthApi
        ) : BaseRepository() {
            suspend fun getSingleArticulo(href: String) = safeApiCall {
                api.getSingleArticulo(href)
            }
}