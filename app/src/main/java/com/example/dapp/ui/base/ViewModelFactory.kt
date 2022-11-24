package com.example.dapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dapp.repository.AuthRepository
import com.example.dapp.repository.BaseRepository
import com.example.dapp.ui.auth.AuthViewModel
import com.example.dapp.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

// single viewModelFactory que es responsable de proveer todos los viewModels
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
   // Cada ves que necesitamos crear un repositorio extendemos BaseRepo
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    /*
    Esta funcion va a crear viewModel
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            // Si es asignable desde AuthViewModel entonces creo AuthViewModel pero para esto
            //tenemos que pasar AuthRepository
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> AuthViewModel(repository as AuthRepository) as T
            //Otros ViewModel Aca
            //prueba
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as AuthRepository) as T
            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}