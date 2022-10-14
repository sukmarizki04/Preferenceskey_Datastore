package com.sukma.data_store.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sukma.data_store.data.DataStoreManager
import com.sukma.data_store.ui.login.ViewModelLogin
import com.sukma.data_store.ui.register.RegisterViewModel

class ViewModelFactory(private val prif: DataStoreManager) :ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)){
            return RegisterViewModel(prif) as T
        }
        if (modelClass.isAssignableFrom(ViewModelLogin::class.java)) {
            return  ViewModelLogin(prif) as T
        }
        throw IllegalArgumentException("Unknow View Model class:" + modelClass.name)
    }

}