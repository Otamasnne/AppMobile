package com.example.dapp.ui.ingresos

import android.view.View
import com.example.dapp.responses.ingreso.Ingreso

interface RecyclerViewIngresoClickListener {

    fun onRecyclerViewItemClick (view: View, ingreso: Ingreso){}
}