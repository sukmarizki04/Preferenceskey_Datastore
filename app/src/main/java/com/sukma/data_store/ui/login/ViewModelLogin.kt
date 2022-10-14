package com.sukma.data_store.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.sukma.data_store.data.DataStoreManager
import com.sukma.data_store.data.UserData
import kotlinx.coroutines.launch

class ViewModelLogin(private val pref: DataStoreManager):ViewModel(){
    fun getUser(): LiveData<UserData> {
        return pref.getUser().asLiveData()
    }

    fun setUserLogin(isLogin: Boolean) {
            viewModelScope.launch {
            pref.setUserLogin(isLogin)
        }
    }

    fun getUserLogin(): LiveData<Boolean> {
        return pref.getUserLogin().asLiveData()
    }
}