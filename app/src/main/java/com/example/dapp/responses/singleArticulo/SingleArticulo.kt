package com.example.dapp.responses.singleArticulo

data class SingleArticulo(
    val codigo: Int,
    val datanucleusVersionTimestamp: Long,
    val descripcion: String,
    val estado: String,
    val logicalTypeName: String,
    val objectIdentifier: String,
    val proveedor: Proveedor,
    val stock: Int,
    val ubicacion: Ubicacion
)