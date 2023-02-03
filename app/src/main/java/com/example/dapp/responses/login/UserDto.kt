package com.example.dapp.responses.login

import com.google.gson.annotations.SerializedName

data class UserDto(
    val password: Password,
    val username: Username
)