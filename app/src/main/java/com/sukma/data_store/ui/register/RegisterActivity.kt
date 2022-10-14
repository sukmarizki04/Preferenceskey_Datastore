package com.sukma.data_store.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.sukma.data_store.R
import com.sukma.data_store.data.DataStoreManager
import com.sukma.data_store.databinding.ActivityRegisterBinding
import com.sukma.data_store.utils.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var pref: DataStoreManager
    private var _binding: ActivityRegisterBinding?= null
    private val binding get() = _binding!!
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListener()
        setupViewModel()

    }

    private fun setOnClickListener() {
        binding.btnRegister.setOnClickListener {
            if (validateInput()) {
                val username = binding.etUsername.text.toString()
                val password = binding.regPassword.text.toString()
                viewModel.saveUser(username, password)

                finish()
            }
        }
    }

    private fun validateInput(): Boolean {
        var isValid = true
        val username = binding.etUsername.text.toString()
        val password = binding.regPassword.text.toString()

        if (username.isEmpty()) {
            isValid = false
            binding.etUsername.error = "Username or password must not be empty"
        }
        if (password.isEmpty()) {
            isValid = false
            binding.etUsername.error = "Password must not be empty"
        }
        return isValid
    }
    private fun setupViewModel() {

        pref = DataStoreManager(this)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[RegisterViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
