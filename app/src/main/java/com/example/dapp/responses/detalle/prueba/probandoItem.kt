package com.example.dapp.responses.detalle.prueba

data class probandoItem(
    val articulo: Articulo,
    val cantidad: Int,
    val pedido: Pedido
)