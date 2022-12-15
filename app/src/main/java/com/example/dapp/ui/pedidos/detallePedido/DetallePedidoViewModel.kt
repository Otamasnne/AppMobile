package com.example.dapp.ui.pedidos.detallePedido

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dapp.repository.DetalleRepository
import com.example.dapp.responses.detalle.Items
import kotlinx.coroutines.launch

class DetallePedidoViewModel (
    private val repository: DetalleRepository
        ) : ViewModel() {

    private val _detalleResponse: MutableLiveData<List<Items>> = MutableLiveData()

    val detalleResponse : LiveData<List<Items>>
        get() = _detalleResponse



    fun getArticulos() = viewModelScope.launch {
        try {
            _detalleResponse.value = repository.getArticulos()
            //_detalleResponse.value = repository.getArticulos().filter { it.pedido.title == "Pedido " + "3" }
        } catch (e: Exception){
            _detalleResponse.value = listOf()
        }
    }
}