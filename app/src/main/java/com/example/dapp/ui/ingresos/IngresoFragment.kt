package com.example.dapp.ui.ingresos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.dapp.databinding.FragmentIngresoBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.repository.IngresoRepository
import com.example.dapp.ui.base.BaseFragment
import com.example.dapp.ui.pedidos.PedidoViewModel

class IngresoFragment : BaseFragment<IngresoViewModel, FragmentIngresoBinding, IngresoRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = binding.recyclerViewIngresos
        viewModel.getIngreso()
        viewModel.ingresoResponse.observe(viewLifecycleOwner, Observer { ingresos ->

        })
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