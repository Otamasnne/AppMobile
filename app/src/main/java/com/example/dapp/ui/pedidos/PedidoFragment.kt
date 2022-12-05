package com.example.dapp.ui.pedidos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dapp.R
import com.example.dapp.databinding.FragmentPedidoBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.repository.PedidoRepository
import com.example.dapp.responses.pedidoModel.Pedido
import com.example.dapp.ui.base.BaseFragment

class PedidoFragment : BaseFragment<PedidoViewModel, FragmentPedidoBinding, PedidoRepository>() {

    //var recycler: RecyclerView? = null




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = binding.recyclerViewPedidos
        //recycler = view.findViewById(R.id.recycler_view_pedidos)
        viewModel.getPedido()
        //val pedsize = viewModel.pedidoResponse.value.

        viewModel.pedidoResponse.observe(viewLifecycleOwner, Observer { pedidos ->
            recycler.also {
                it?.layoutManager = LinearLayoutManager(requireContext())
                it?.setHasFixedSize(true)
                it?.adapter = PedidoAdapter(pedidos)
            }

            if (pedidos.isEmpty()) {
                Toast.makeText(requireContext(), "No hay tareas pendientes", Toast.LENGTH_SHORT).show()
            }
        })



    }


    override fun getViewModel() = PedidoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPedidoBinding.inflate(inflater,container,false)

    override fun getFragmentRepository() = PedidoRepository(
        remoteDataSource.buildApi(AuthApi::class.java)
    )



}