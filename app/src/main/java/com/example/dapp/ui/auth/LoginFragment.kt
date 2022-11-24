package com.example.dapp.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.dapp.databinding.FragmentLoginBinding
import com.example.dapp.network.AuthApi
import com.example.dapp.network.Resource
import com.example.dapp.repository.AuthRepository
import com.example.dapp.responses.login.Password
import com.example.dapp.responses.login.UserDto
import com.example.dapp.responses.login.Username
import com.example.dapp.ui.base.BaseFragment


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//    }


    //propio

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //we need to observe login response live data
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when(it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_LONG).show()
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()

                    //Navegando hacia inicioFragment
                    //action_loginFragment_to_inicioFragment
                    //findNavController().navigate(R.id.action_loginFragment_to_inicioFragment)

                }

            }
        })

        //accedemos a nuestor viewModel
        binding.btnIngresar.setOnClickListener {
            val usernameFrom = binding.etUser.text.toString().trim()
            val passwordFrom = binding.etPassword.text.toString().trim()

            val username = Username(usernameFrom)
            val password = Password(passwordFrom)
            val userData = UserDto(password,username)

            //hit API
            //@todo add input validations
            viewModel.login(userData)
        }

    }



    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}