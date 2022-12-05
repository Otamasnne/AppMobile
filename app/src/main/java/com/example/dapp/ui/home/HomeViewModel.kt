package com.example.dapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dapp.network.Resource
import com.example.dapp.repository.AuthRepository
import com.example.dapp.repository.PedidoRepository
import com.example.dapp.responses.ArticulosResponseItem
import com.example.dapp.responses.pedidoModel.Pedido
import kotlinx.coroutines.launch

class HomeViewModel(
    //Temporalmente asignamos AuthRepository
    private val repository: PedidoRepository
) : ViewModel() {



    //MutableLiveData where we can put the value
    private val _pedidosResponse : MutableLiveData<List<Pedido>> = MutableLiveData()
    //LiveData que accede desde afuera de la clase
    val pedidosResponse: LiveData<List<Pedido>>
        get() = _pedidosResponse

    //funcion para pegarle a la api con ayuda del repositorio llamando a la suspend fun login
    // en authRepository. Por eso necesitamos usar corutineScope
    //LLamamos esta funcion en LoginFragment dentro de onView
    fun getPedidos() = viewModelScope.launch{

        _pedidosResponse.value = repository.getPedidos()
    }


}