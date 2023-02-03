package com.example.dapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dapp.repository.*
import com.example.dapp.responses.ingreso.Ingreso
import com.example.dapp.ui.auth.AuthViewModel
import com.example.dapp.ui.home.HomeViewModel
import com.example.dapp.ui.ingresos.IngresoViewModel
import com.example.dapp.ui.ingresos.detalleIngresos.DetalleIngresoViewModel
import com.example.dapp.ui.pedidos.PedidoViewModel
import com.example.dapp.ui.pedidos.detallePedido.DetallePedidoViewModel
import com.example.dapp.ui.pedidos.popUpPedido.PopUpPedidoViewModel
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
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository as PedidoRepository) as T
            //else -> throw IllegalArgumentException("ViewModelClass Not Found")

            //pedidos
            modelClass.isAssignableFrom(PedidoViewModel::class.java) -> PedidoViewModel(repository as PedidoRepository) as T

            //pedidos detalle
            modelClass.isAssignableFrom(DetallePedidoViewModel::class.java) -> DetallePedidoViewModel(repository as DetalleRepository) as T

            //Ingresos
            modelClass.isAssignableFrom(IngresoViewModel::class.java) -> IngresoViewModel(repository as IngresoRepository) as T

            //ingresos detalle
            modelClass.isAssignableFrom(DetalleIngresoViewModel::class.java) -> DetalleIngresoViewModel(repository as IngresoRepository) as T

            //PopUp
            modelClass.isAssignableFrom(PopUpPedidoViewModel::class.java) -> PopUpPedidoViewModel(repository as ArticuloRepository) as T

            else -> throw IllegalArgumentException("ViewModelClass Not Found")
        }
    }

}