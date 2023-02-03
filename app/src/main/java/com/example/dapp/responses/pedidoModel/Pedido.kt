package com.example.dapp.responses.pedidoModel

data class Pedido(
    val codigo: String,
    val descripcion: String,
    val estadoOperativo: String,
    val procesando: Int
)
