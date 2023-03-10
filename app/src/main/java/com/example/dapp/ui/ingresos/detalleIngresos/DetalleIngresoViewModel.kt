package com.example.dapp.ui.ingresos.detalleIngresos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dapp.network.Resource
import com.example.dapp.repository.ArticuloRepository
import com.example.dapp.repository.IngresoRepository
import com.example.dapp.responses.completar.Completar
import com.example.dapp.responses.detalle.Items
import com.example.dapp.responses.singleArticulo.SingleArticulo
import kotlinx.coroutines.launch

class DetalleIngresoViewModel (
    private val repository: IngresoRepository
        ) : ViewModel() {

    private val _ingresoDetalleResponse: MutableLiveData<List<Items>> = MutableLiveData()
    val ingresoDetalleResponse: LiveData<List<Items>>
        get() = _ingresoDetalleResponse

    //Completar Ingreso
    private val _completarResponse: MutableLiveData<Resource<Completar>> = MutableLiveData()
    val completarResponse : LiveData<Resource<Completar>>
        get() = _completarResponse

    //Get single articulo
    private val _articuloResponse : MutableLiveData<Resource<SingleArticulo>> = MutableLiveData()
    val articuloResponse : LiveData<Resource<SingleArticulo>>
        get() = _articuloResponse

    fun getArticulosIngreso(id: String) = viewModelScope.launch {
        try {
            _ingresoDetalleResponse.value = repository.getArticulosIngreso(id)
        } catch (e: Exception) {
            _ingresoDetalleResponse.value = listOf()
        }
    }

    fun completarIngreso(codigo: String) = viewModelScope.launch {

        _completarResponse.value = repository.completarIngreso(codigo)
    }

    fun getSingleArticulo(href: String) = viewModelScope.launch {
        _articuloResponse.value = repository.getSingleArticulo(href)
    }


}