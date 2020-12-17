package com.udacity.shoestore.ui.login

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.util.extensions.*

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.loginFormState.observe(viewLifecycleOwner, Observer {
            val loginState = it ?: return@Observer

            binding.loginBtn.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                binding.username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                binding.password.error = getString(loginState.passwordError)
            }
        })

        viewModel.loginResult.observe(viewLifecycleOwner, Observer {result ->
            binding.loading.gone()
            if (result.error != null) {
                showLoginFailed(result.error)
            }
            if (result.success != null) {
                updateUiWithUser(result.success)
                clearForm()
                findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
                viewModel.onLoginFinish()
            }
        })

        binding.username.afterTextChanged {
            viewModel.loginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.password.apply {
            afterTextChanged {
                viewModel.loginDataChanged(
                    binding.username.text.toString(),
                    binding.password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        viewModel.login(
                            binding.username.text.toString(),
                            binding.password.text.toString()
                        )
                }
                false
            }

            binding.loginBtn.setOnClickListener {
                binding.loading.visible()
                viewModel.login(binding.username.text.toString(), binding.password.text.toString())
            }
        }

        return binding.root
    }

    private fun clearForm() {
        binding.username.text = null
        binding.password.text = null
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        longToast("$welcome $displayName")
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        toast(errorString)
    }
}