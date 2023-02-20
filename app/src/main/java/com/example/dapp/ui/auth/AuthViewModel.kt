package com.example.dapp.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dapp.network.Resource
import com.example.dapp.repository.AuthRepository
import com.example.dapp.responses.login.LoginRespondePH
import com.example.dapp.responses.login.User
import com.example.dapp.responses.login.UserDto
import kotlinx.coroutines.launch


class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel(){

    //MutableLiveData where we can put the value
    private val _loginResponse : MutableLiveData<Resource<User>> = MutableLiveData()
    //LiveData que accede desde afuera de la clase
    val loginResponse: LiveData<Resource<User>>
        get() = _loginResponse

    //funcion para pegarle a la api con ayuda del repositorio llamando a la suspend fun login
    // en authRepository. Por eso necesitamos usar corutineScope
    //LLamamos esta funcion en LoginFragment dentro de onView
    fun login(userDto: UserDto) = viewModelScope.launch{

        _loginResponse.value = repository.login(userDto)
    }

}