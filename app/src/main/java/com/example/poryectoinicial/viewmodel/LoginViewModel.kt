package com.example.poryectoinicial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.poryectoinicial.model.Login.LoginRepositorioImpl

class LoginViewModel (application: Application) : AndroidViewModel(application){

    private  var loginRepositorioImpl: LoginRepositorioImpl? = LoginRepositorioImpl()
    private var usuid = MutableLiveData<Int>()

    fun loginUsuario(username: String, pass: String){
        loginRepositorioImpl!!.loginAPI(username,pass){
            this.usuid.value = it
        }
    }

    fun getUsuid(): MutableLiveData<Int> = this.usuid

}