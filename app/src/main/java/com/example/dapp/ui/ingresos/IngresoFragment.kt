package com.example.dapp.ui.ingresos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dapp.databinding.FragmentIngresoBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.repository.IngresoRepository
import com.example.dapp.responses.ingreso.Ingreso
import com.example.dapp.ui.base.BaseFragment
import com.example.dapp.ui.pedidos.PedidoFragmentDirections
import com.example.dapp.ui.pedidos.PedidoViewModel

class IngresoFragment : BaseFragment<IngresoViewModel, FragmentIngresoBinding, IngresoRepository>(),
    RecyclerViewIngresoClickListener {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = binding.recyclerViewIngresos
        viewModel.getIngreso()
        viewModel.ingresoResponse.observe(viewLifecycleOwner, Observer { ingresos ->

            recycler.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = IngresoAdapter(ingresos, this)
            }

            if (ingresos.isEmpty()) {
                Toast.makeText(requireContext(), "No hay tareas pendientes", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, ingreso: Ingreso) {
        super.onRecyclerViewItemClick(view, ingreso)

        val action = IngresoFragmentDirections.actionIngresoFragmentToDetalleIngresoFragment(ingreso.codigo)
        findNavController().navigate(action)
    }

    override fun getViewModel() = IngresoViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentIngresoBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = IngresoRepository(
        remoteDataSource.buildApi(AuthApi::class.java)
    )
}