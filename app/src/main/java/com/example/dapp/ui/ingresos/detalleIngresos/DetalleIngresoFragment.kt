package com.example.dapp.ui.ingresos.detalleIngresos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dapp.databinding.FragmentDetalleIngresoBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.repository.IngresoRepository
import com.example.dapp.responses.ingreso.Ingreso
import com.example.dapp.ui.base.BaseFragment

class DetalleIngresoFragment : BaseFragment<
        DetalleIngresoViewModel,
        FragmentDetalleIngresoBinding,
        IngresoRepository> () {

    private lateinit var codigoIngreso: String

    companion object {
        const val CODIGO = "codigo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            codigoIngreso = it.getString(CODIGO).toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = binding.recyclerViewDetalleIngreso
        viewModel.getArticulosIngreso(codigoIngreso)

        viewModel.ingresoDetalleResponse.observe(viewLifecycleOwner, Observer { articulos ->
            recycler.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = DetalleIngresoAdapter(articulos)
            }
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
            viewModel.completarIngreso(codigoIngreso)
        }
    }



    override fun getViewModel(): Class<DetalleIngresoViewModel> = DetalleIngresoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) =  FragmentDetalleIngresoBinding.inflate(inflater,container, false)

    override fun getFragmentRepository() = IngresoRepository (
        remoteDataSource.buildApi(AuthApi::class.java)
            )

}