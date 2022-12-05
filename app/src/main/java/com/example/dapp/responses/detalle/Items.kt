package com.example.dapp.responses.detalle

data class Items(
    val articulo: Articulo,
    val cantidad: Int,
    val pedido: Pedido
)
