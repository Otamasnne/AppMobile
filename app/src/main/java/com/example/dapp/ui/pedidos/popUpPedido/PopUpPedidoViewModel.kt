package com.example.dapp.ui.pedidos.popUpPedido

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dapp.network.Resource
import com.example.dapp.repository.ArticuloRepository
import com.example.dapp.responses.singleArticulo.SingleArticulo
import kotlinx.coroutines.launch

class PopUpPedidoViewModel (
    private val repository: ArticuloRepository
        ) : ViewModel() {

    private val _articuloResponse : MutableLiveData<Resource<SingleArticulo>> = MutableLiveData()
    val articuloResponse : LiveData<Resource<SingleArticulo>>
        get() = _articuloResponse

    fun getSingleArticulo(href: String) = viewModelScope.launch {

        _articuloResponse.value = repository.getSingleArticulo(href)
    }



}