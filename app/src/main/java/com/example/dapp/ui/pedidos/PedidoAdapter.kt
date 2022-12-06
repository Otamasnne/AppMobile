package com.example.dapp.ui.pedidos

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dapp.R
import com.example.dapp.databinding.RecyclerviewPedidoBinding
import com.example.dapp.network.Resource
import com.example.dapp.responses.login.User
import com.example.dapp.responses.pedidoModel.Pedido

class PedidoAdapter(
    private val pedidos: List<Pedido>,
    private var listener: RecyclerViewClickListener
) : RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>(){

    val estado = false

    override fun getItemCount() = pedidos.size

    inner class PedidoViewHolder(
        val recyclerviewPedidoBinding: RecyclerviewPedidoBinding
    ): RecyclerView.ViewHolder(recyclerviewPedidoBinding.root) {


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PedidoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_pedido,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {

            holder.recyclerviewPedidoBinding.pedido = pedidos[position]

            holder.recyclerviewPedidoBinding.root.setOnClickListener {
                listener.onRecyclerViewItemClick(holder.recyclerviewPedidoBinding.root, pedidos[position])
            }

            if (pedidos[position].procesando == 0) {
                //holder.recyclerviewPedidoBinding.layoutItem.visibility= View.GONE
                holder.recyclerviewPedidoBinding.cardView.visibility = View.GONE
            }







    }

    //override fun getItemCount() = pedidos.size
}