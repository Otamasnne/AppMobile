package com.example.dapp.ui.ingresos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dapp.repository.IngresoRepository
import com.example.dapp.responses.ingreso.Ingreso
import kotlinx.coroutines.launch

class IngresoViewModel (
    private val repository: IngresoRepository
        ) : ViewModel() {

    private val _ingresoResponse: MutableLiveData<List<Ingreso>> = MutableLiveData()
    val ingresoResponse : LiveData<List<Ingreso>>
        get() = _ingresoResponse

    fun getIngreso() = viewModelScope.launch {
        
        try {
            _ingresoResponse.value = repository.getIngresos()
        }catch (e:Exception) {
            _ingresoResponse.value = listOf()
        }
    }


}