package com.example.poryectoinicial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.poryectoinicial.model.Login.LoginRepositorioImpl

class LoginViewModel/*(application: Application) : AndroidViewModel(application)*/{

    private  var loginRepositorioImpl: LoginRepositorioImpl? = LoginRepositorioImpl()
    private var usuid: Int? = 0

    fun loginUsuario(username: String, pass: String){
        val usuid: Int? = loginRepositorioImpl?.loginAPI(username,pass)
        this.usuid = usuid
    }

    fun getUsuid(): Int? = usuid

}