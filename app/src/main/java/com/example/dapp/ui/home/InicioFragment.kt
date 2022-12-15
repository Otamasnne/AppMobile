package com.example.dapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dapp.R
import com.example.dapp.databinding.FragmentInicioBinding
import com.example.dapp.databinding.FragmentLoginBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.repository.AuthRepository
import com.example.dapp.repository.PedidoRepository
import com.example.dapp.ui.auth.AuthViewModel
import com.example.dapp.ui.base.BaseFragment

class InicioFragment : BaseFragment<HomeViewModel, FragmentInicioBinding, PedidoRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPedidos()
        //we need to observe login response live data
        viewModel.pedidosResponse.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()){
                binding.btnfetchData.text = "No hay pedidos"
            } else {
                binding.btnfetchData.text = it.size.toString()
                //binding.btnfetchData.text = it.get()
            }
        })

        //accedemos a nuestor viewModel
        binding.btnfetchData.setOnClickListener {
//            val email = binding.etUser.text.toString().trim()
//            val password = binding.etPassword.text.toString().trim()

            findNavController().navigate(R.id.action_inicioFragment_to_pedidoFragment)
            //hit API
            //@todo add input validations
            //viewModel.getArticulos()
        }

        binding.txtSalir.setOnClickListener {
            findNavController().navigate(R.id.action_inicioFragment_to_loginFragment)
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentInicioBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = PedidoRepository(remoteDataSource.buildApi(AuthApi::class.java))

}