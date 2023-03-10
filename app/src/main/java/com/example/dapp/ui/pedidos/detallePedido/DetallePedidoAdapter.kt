package com.example.dapp.ui.pedidos.detallePedido

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dapp.R
import com.example.dapp.databinding.FragmentDetallePedidoBinding
import com.example.dapp.databinding.RecyclerviewDetallePedidoBinding
import com.example.dapp.responses.detalle.Items

class DetallePedidoAdapter (
    private val articulos: List<Items>,
    private val listener: RecyclerViewDPClickListener
        ) : RecyclerView.Adapter<DetallePedidoAdapter.DetallePedidoViewHolder>(){

            inner class  DetallePedidoViewHolder(
                val recviewDetallePedidoBinding: RecyclerviewDetallePedidoBinding
            ): RecyclerView.ViewHolder(recviewDetallePedidoBinding.root){}

    //private lateinit var articulosFilter : List<Items>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DetallePedidoViewHolder (
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_detalle_pedido,
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: DetallePedidoViewHolder, position: Int) {

        //articulosFilter = articulos.filter { it.pedido.title == "Pedido " + codigoPedido }
        //holder.recviewDetallePedidoBinding.articulo = articulosFilter[position]



//        if(articulos[position].pedido.title == codigoPedido) {
//            holder.recviewDetallePedidoBinding.articulo = articulos[position]
//        }
        //holder.recviewDetallePedidoBinding.pruebaArgs.text = codigoPedido


//        if(articulos[position].pedido.title != "Pedido $codigoPedido"){
//            holder.recviewDetallePedidoBinding.cardViewDetalle.visibility = View.GONE
//        }
        holder.recviewDetallePedidoBinding.articulo = articulos[position]

        holder.recviewDetallePedidoBinding.root.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recviewDetallePedidoBinding.root, articulos[position])
        }

    }

    override fun getItemCount() = articulos.size
}