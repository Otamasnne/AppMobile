package com.example.dapp.ui.pedidos.detallePedido

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dapp.databinding.FragmentDetallePedidoBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.repository.DetalleRepository
import com.example.dapp.ui.base.BaseFragment
import com.example.dapp.ui.pedidos.PedidoAdapter
import com.example.dapp.ui.pedidos.PedidoViewModel
import java.util.Locale.filter

class DetallePedidoFragment : BaseFragment<
        DetallePedidoViewModel,
        FragmentDetallePedidoBinding,
        DetalleRepository>() {

    private lateinit var codigoPedido: String


    companion object {
        const val CODIGO = "codigo"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let{
            codigoPedido = it.getString(CODIGO).toString()
            //codigoPedido = it.getInteger(CODIGO)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = binding.recyclerViewDetallePedido

        viewModel.getArticulos(codigoPedido)


        viewModel.detalleResponse.observe(viewLifecycleOwner, Observer { articulos ->
            recycler.also{
                it?.layoutManager = LinearLayoutManager(requireContext())
                it?.setHasFixedSize(true)
                it?.adapter = DetallePedidoAdapter(articulos)
            }

//            if (pedidos.isEmpty()) {
//                Toast.makeText(requireContext(), "No hay tareas pendientes", Toast.LENGTH_SHORT).show()
//            }
        })

        viewModel.completarResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "Operacion Exitosa", Toast.LENGTH_SHORT).show()

                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Operacion Fallida", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.btnCompletar.setOnClickListener {
            viewModel.completarPedido(codigoPedido)
        }
    }



    override fun getViewModel(): Class<DetallePedidoViewModel> = DetallePedidoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetallePedidoBinding = FragmentDetallePedidoBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = DetalleRepository (
        remoteDataSource.buildApi(AuthApi::class.java)
            )


}