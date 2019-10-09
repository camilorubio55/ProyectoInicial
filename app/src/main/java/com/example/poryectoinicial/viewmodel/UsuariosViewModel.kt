package com.example.poryectoinicial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.poryectoinicial.model.Usuarios.Usuario
import com.example.poryectoinicial.model.Usuarios.UsuarioRepositorioImpl

class UsuariosViewModel(application: Application): AndroidViewModel(application) {

    private var usuarioRepositorioImpl: UsuarioRepositorioImpl = UsuarioRepositorioImpl()
    private var usuarios = MutableLiveData<MutableList<Usuario>>()

    fun getallUsuarios(usuid: Int){
       usuarioRepositorioImpl.getallUsuariosAPI(usuid){
            this.usuarios.value = it
       }
    }

    fun getUsuario(): LiveData<MutableList<Usuario>> = this.usuarios

}