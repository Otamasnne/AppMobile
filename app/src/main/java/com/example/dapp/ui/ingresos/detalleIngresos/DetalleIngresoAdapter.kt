package com.example.dapp.ui.ingresos.detalleIngresos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dapp.R
import com.example.dapp.databinding.RecyclerviewDetalleIngresoBinding
import com.example.dapp.responses.detalle.Items
import com.example.dapp.ui.pedidos.detallePedido.RecyclerViewDPClickListener

class DetalleIngresoAdapter (
    private val articulos: List<Items>,
    private val listener: RecyclerViewDPClickListener
        ) : RecyclerView.Adapter<DetalleIngresoAdapter.DetalleIngresoViewHolder>() {

    inner class DetalleIngresoViewHolder(
        val recyclerViewDetalleIngresoBinding: RecyclerviewDetalleIngresoBinding
    ): RecyclerView.ViewHolder(recyclerViewDetalleIngresoBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DetalleIngresoViewHolder (
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_detalle_ingreso,
            parent,
            false
        )
            )

    override fun onBindViewHolder(holder: DetalleIngresoViewHolder, position: Int) {


        holder.recyclerViewDetalleIngresoBinding.articulo = articulos[position]

        holder.recyclerViewDetalleIngresoBinding.root.setOnClickListener {
            listener.onRecyclerViewItemClick(holder.recyclerViewDetalleIngresoBinding.root, articulos[position])
        }
    }

    override fun getItemCount() = articulos.size


}