package com.sukma.data_store.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sukma.data_store.data.DataStoreManager
import kotlinx.coroutines.launch

class RegisterViewModel (private val pref: DataStoreManager):ViewModel(){
    fun saveUser(name: String, password: String) {
        viewModelScope.launch {
            pref.setUser(name, password)
        }
    }
}