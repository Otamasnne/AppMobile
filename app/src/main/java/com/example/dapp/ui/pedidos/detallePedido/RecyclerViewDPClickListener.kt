package com.example.dapp.ui.pedidos.detallePedido

import android.view.View
import com.example.dapp.responses.detalle.Items

interface RecyclerViewDPClickListener {

    fun onRecyclerViewItemClick(view:View, item: Items) {}
}