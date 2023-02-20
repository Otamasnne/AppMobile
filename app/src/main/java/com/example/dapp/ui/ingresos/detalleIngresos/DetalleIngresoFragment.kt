package com.example.dapp.ui.ingresos.detalleIngresos

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dapp.R
import com.example.dapp.databinding.FragmentDetalleIngresoBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.repository.IngresoRepository
import com.example.dapp.responses.ingreso.Ingreso
import com.example.dapp.ui.base.BaseFragment
import com.example.dapp.ui.pedidos.detallePedido.DetallePedidoFragment.Companion.CODIGO

class DetalleIngresoFragment : BaseFragment<
        DetalleIngresoViewModel,
        FragmentDetalleIngresoBinding,
        IngresoRepository> () {

    private lateinit var codigoIngreso: String

    //Crear AlertDialog
    private lateinit var builder: AlertDialog.Builder

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
                    //Toast.makeText(requireContext(), "Operacion Exitosa", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_detalleIngresoFragment_to_inicioFragment)
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Operacion Fallida", Toast.LENGTH_SHORT).show()
                }
            }
        })

        //Are you sure
        builder = AlertDialog.Builder(context)

        binding.btnCompletar.setOnClickListener {
            //viewModel.completarIngreso(codigoIngreso)
            //binding.btnCompletar.visibility = View.INVISIBLE
            //binding.btnAreSureY.visibility = View.VISIBLE

            builder.setTitle("Atencion!")
                .setMessage("Queres completar este ingreso?")
                .setCancelable(true)
                .setPositiveButton("Si"){dialogInterface, it ->
                    viewModel.completarIngreso(codigoIngreso)
                    Toast.makeText(context, "Ingreso Enviado",Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){dialogInterface, it ->
                    dialogInterface.cancel()
                }
            //show the builder
                .show()
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