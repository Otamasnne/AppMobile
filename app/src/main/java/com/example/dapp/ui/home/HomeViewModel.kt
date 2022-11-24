package com.example.dapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dapp.network.Resource
import com.example.dapp.repository.AuthRepository
import com.example.dapp.responses.ArticulosResponseItem
import kotlinx.coroutines.launch

class HomeViewModel(
    //Temporalmente asignamos AuthRepository
    private val repository: AuthRepository
) : ViewModel() {



    //MutableLiveData where we can put the value
    private val _articulosResponse : MutableLiveData<Resource<ArticulosResponseItem>> = MutableLiveData()
    //LiveData que accede desde afuera de la clase
    val articulosResponse: LiveData<Resource<ArticulosResponseItem>>
        get() = _articulosResponse

    //funcion para pegarle a la api con ayuda del repositorio llamando a la suspend fun login
    // en authRepository. Por eso necesitamos usar corutineScope
    //LLamamos esta funcion en LoginFragment dentro de onView
    fun getArticulos() = viewModelScope.launch{

      //  _articulosResponse.value = repository.getArticulos()
    }


}