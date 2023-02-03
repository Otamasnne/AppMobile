package com.example.dapp.ui.pedidos.detallePedido

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dapp.network.Resource
import com.example.dapp.repository.DetalleRepository
import com.example.dapp.responses.completar.Completar
import com.example.dapp.responses.detalle.Items
import com.example.dapp.responses.singleArticulo.SingleArticulo
import kotlinx.coroutines.launch

class DetallePedidoViewModel (
    private val repository: DetalleRepository
        ) : ViewModel() {

    private val _detalleResponse: MutableLiveData<List<Items>> = MutableLiveData()

    val detalleResponse : LiveData<List<Items>>
        get() = _detalleResponse

    //Completar
    private val _completarResponse: MutableLiveData<Resource<Completar>> = MutableLiveData()
    val completarResponse : LiveData<Resource<Completar>>
        get() = _completarResponse

    //foco articulo

    private val _articuloResponse: MutableLiveData<SingleArticulo> = MutableLiveData()
    val articuloResponse : LiveData<SingleArticulo>
        get() = _articuloResponse


    fun getArticulos(id: String) = viewModelScope.launch {
        try {
            //val idToInt = id.toInt()
            _detalleResponse.value = repository.getArticulos(id)

            //var codigo = _detalleResponse.value!!.get().articulo.href
            //_detalleResponse.value = repository.getArticulos().filter { it.articulo.href == "Pedido " + "3" }

            /* hacer foco en un articulo*/


        } catch (e: Exception){
            _detalleResponse.value = listOf()
        }
    }

    fun completarPedido(codigo: String) = viewModelScope.launch {

        _completarResponse.value = repository.completarPedido(codigo)
    }


}