package com.sukma.data_store.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.sukma.data_store.R
import com.sukma.data_store.data.DataStoreManager
import com.sukma.data_store.databinding.ActivityLoginBinding
import com.sukma.data_store.ui.MainActivity
import com.sukma.data_store.ui.register.RegisterActivity
import com.sukma.data_store.utils.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: ViewModelLogin
    private lateinit var pref: DataStoreManager
    private var _binding:ActivityLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setOnclickListener()
        checkuserLogin()
    }



    private fun setupViewModel() {
        pref = DataStoreManager(this)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref)) [ViewModelLogin::class.java]
    }

    private fun setOnclickListener() {
        binding.register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }
        binding.btnLogin.setOnClickListener {
            if (validate()) {
                val username = binding.etUsername.text.toString()
                val password = binding.TiPassword.text.toString()

                viewModel.getUser().observe(this) {
                    if (it.username == username && it.password ==password) {
                        viewModel.setUserLogin(true)

                        startActivity(Intent(this,MainActivity::class.java).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        })
                    } else {
                        Toast.makeText(this,"username atau password salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
    private fun checkuserLogin() {
        viewModel.getUserLogin().observe(this) {
            Log.d("loginstate", it.toString())
            if (it) {
                startActivity(Intent(this, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                })
                finish()
            }
        }
    }
//
//    private fun checkUserStatus() {
//        viewModel.getUserLogin().observe(this) {
//            Log.d("loginstate", it.toString())
//            if (it) {
//                startActivity(Intent(this, MainActivity::class.java).apply {
//                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
//                })
//                finish()
//            }
//        }
//    }



    private fun validate() :Boolean{
        var isValid = true
        val username = binding.etUsername.text.toString()
        val password = binding.TiPassword.text.toString()
        if (username.isEmpty()) {
            isValid = false
            binding.etUsername.error = "Username Kosong"
        }
        if (password.isEmpty()) {
            isValid = false
            binding.TiPassword.error = "Password Kosong"
        }
        return isValid
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}