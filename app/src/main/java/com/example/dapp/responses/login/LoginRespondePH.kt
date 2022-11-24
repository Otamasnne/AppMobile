package com.example.dapp.responses.login

import com.google.gson.annotations.SerializedName

data class LoginRespondePH (
    @SerializedName("apellido") val apellido: String,
    @SerializedName("email")  val email: String,
    @SerializedName("nombre")  val nombre: String,
    @SerializedName("password")  val password: String,
    @SerializedName("username")  val username: String,
    @SerializedName("telefono")  val telefono: String
        )