package com.example.dapp.ui.pedidos

import android.view.View
import com.example.dapp.responses.pedidoModel.Pedido

interface RecyclerViewClickListener {

    fun onRecyclerViewItemClick (view: View, pedido: Pedido){}
}