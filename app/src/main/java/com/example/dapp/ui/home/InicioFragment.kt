package com.example.dapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dapp.R
import com.example.dapp.databinding.FragmentInicioBinding
import com.example.dapp.databinding.FragmentLoginBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.repository.AuthRepository
import com.example.dapp.ui.auth.AuthViewModel
import com.example.dapp.ui.base.BaseFragment

class InicioFragment : BaseFragment<HomeViewModel, FragmentInicioBinding, AuthRepository>() {




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //we need to observe login response live data
        viewModel.articulosResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()


                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "fetch failure", Toast.LENGTH_SHORT).show()

                    //Navegando hacia inicioFragment
                    //action_loginFragment_to_inicioFragment
//                    findNavController().navigate(R.id.action_loginFragment_to_inicioFragment)

                }

            }
        })

        //accedemos a nuestor viewModel
        binding.btnfetchData.setOnClickListener {
//            val email = binding.etUser.text.toString().trim()
//            val password = binding.etPassword.text.toString().trim()

            //hit API
            //@todo add input validations
            viewModel.getArticulos()
        }
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentInicioBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))

}