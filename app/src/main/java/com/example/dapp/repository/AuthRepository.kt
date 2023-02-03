package com.example.dapp.repository

import com.example.dapp.network.AuthApi
import com.example.dapp.responses.login.UserDto

class AuthRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun login(userDto: UserDto) = safeApiCall {
        api.login(userDto)
    }


    //prueba
//    suspend fun getArticulos() = safeApiCall {
//        api.getArticulos()
//    }

}