package com.example.dapp.ui.ingresos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dapp.R
import com.example.dapp.databinding.FragmentIngresoBinding
import com.example.dapp.databinding.RecyclerviewIngresoBinding
import com.example.dapp.responses.ingreso.Ingreso

class IngresoAdapter (
    private val ingresos: List<Ingreso>
) : RecyclerView.Adapter<IngresoAdapter.IngresoViewHolder>() {

    inner class IngresoViewHolder(
        val recyclerViewIngresoBinding: RecyclerviewIngresoBinding
    ): RecyclerView.ViewHolder(recyclerViewIngresoBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IngresoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recyclerview_ingreso,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: IngresoViewHolder, position: Int) {
        holder.recyclerViewIngresoBinding.ingreso = ingresos[position]
    }

    override fun getItemCount() = ingresos.size
}