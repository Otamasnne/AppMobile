package com.example.dapp.ui.pedidos.detallePedido

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dapp.R
import com.example.dapp.databinding.FragmentDetallePedidoBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.repository.DetalleRepository
import com.example.dapp.responses.detalle.Items
import com.example.dapp.ui.base.BaseFragment
import com.example.dapp.ui.pedidos.PedidoAdapter
import com.example.dapp.ui.pedidos.PedidoViewModel
import java.util.Locale.filter

class DetallePedidoFragment : BaseFragment<
        DetallePedidoViewModel,
        FragmentDetallePedidoBinding,
        DetalleRepository>(), RecyclerViewDPClickListener {

    private lateinit var codigoPedido: String
    //Crear AlertDialog
    private lateinit var builder: AlertDialog.Builder

    var alertCount = 0
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
                it?.adapter = DetallePedidoAdapter(articulos,this)
            }

//            if (pedidos.isEmpty()) {
//                Toast.makeText(requireContext(), "No hay tareas pendientes", Toast.LENGTH_SHORT).show()
//            }
        })

        viewModel.completarResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    //Toast.makeText(requireContext(), "Operacion Exitosa", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_detallePedidoFragment_to_inicioFragment)

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
                .setMessage("Queres completar este pedido?")
                .setCancelable(true)
                .setPositiveButton("Si"){dialogInterface, it ->
                    viewModel.completarPedido(codigoPedido)
                    Toast.makeText(context, "Pedido Enviado",Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){dialogInterface, it ->
                    dialogInterface.cancel()
                }
                //show the builder
                .show()
        }

//        binding.btnCompletar.setOnClickListener {
//            viewModel.completarPedido(codigoPedido)
//        }
    }


    override fun onRecyclerViewItemClick(view: View, item: Items) {
        super.onRecyclerViewItemClick(view, item)

        builder = AlertDialog.Builder(context)

        viewModel.getSingleArticulo(item.articulo.href)
        viewModel.articuloResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {

                    if(alertCount == 0){
                        createDialog(it.value.descripcion, it.value.ubicacion.title)
                        alertCount =+ 1
                    }
//                    binding.txtPop.text =  it.value.descripcion
//                    binding.txtUbicacionTitle.text = "Ubicacion: " + it.value.ubicacion.title

//                    builder.setTitle(it.value.descripcion)
//                        .setMessage(" " +  it.value.ubicacion.title)
//                        .setCancelable(true)
//                        .show()
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Operacion Fallida", Toast.LENGTH_SHORT).show()

                }
            }
        })
//        val action = DetallePedidoFragmentDirections.actionDetallePedidoFragmentToPopUpPedidoFragment(item.articulo.href)
//        findNavController().navigate(action)
//        val showPopUp = PopUpPedidoFragment()
//        showPopUp.show((activity as AppCompatActivity).supportFragmentManager, "showPopUp")
    }

    fun createDialog(title: String, msj: String){

        builder = AlertDialog.Builder(context)
        builder.setTitle(title)
            .setMessage(" " +  msj)
            .setCancelable(false)
            .setNegativeButton("Volver"){dialogInterface, it ->
                dialogInterface.cancel()
                alertCount = 0
            }
            .show()
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