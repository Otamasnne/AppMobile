package com.example.dapp.ui.pedidos

import android.app.AlertDialog
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.repository.PedidoRepository
import com.example.dapp.responses.pedidoModel.Pedido
import kotlinx.coroutines.launch

class PedidoViewModel (
    private val repository: PedidoRepository
        ) : ViewModel() {



    private val _pedidoResponse : MutableLiveData<List<Pedido>> = MutableLiveData()

    val pedidoResponse : LiveData<List<Pedido>>
        get() = _pedidoResponse

    //private val _pedidoResponse: MutableLiveData<>

    

    fun getPedido() = viewModelScope.launch {

        //_pedidoResponse.value = repository.getPedidos()
        try {
            _pedidoResponse.value = repository.getPedidos()
            //_pedidoResponse.value = repository.getPedidos().filter { it.procesando == 1 }

        } catch(e:Exception) {

            _pedidoResponse.value = listOf()
        }

    }


}