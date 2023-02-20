package com.example.dapp.ui.pedidos.popUpPedido

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.dapp.R
import com.example.dapp.databinding.FragmentPopUpPedidoBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.repository.ArticuloRepository
import com.example.dapp.ui.base.BaseFragment
import com.example.dapp.ui.home.HomeViewModel





class PopUpPedidoFragment : BaseFragment<
        PopUpPedidoViewModel,
        FragmentPopUpPedidoBinding,
        ArticuloRepository>(){

    private lateinit var href: String

    companion object{
        const val HREF = "href"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            href = it.getString(HREF).toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSingleArticulo(href)

        viewModel.articuloResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    binding.txtPop.text =  it.value.descripcion
                    binding.txtUbicacionTitle.text = "Ubicacion: " + it.value.ubicacion.title
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Operacion Fallida", Toast.LENGTH_SHORT).show()

                }

            }
        })
    }

    override fun getViewModel() = PopUpPedidoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPopUpPedidoBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = ArticuloRepository (remoteDataSource.buildApi(AuthApi::class.java))


}