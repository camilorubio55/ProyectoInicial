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
    private var usuario = MutableLiveData<Usuario>()
    private var eliminarusuario = MutableLiveData<Usuario>()
    private var insertarusuario = MutableLiveData<Usuario>()
    private var actualizarusuario = MutableLiveData<Usuario>()

    fun getallUsuarios(usuid: Int){
       usuarioRepositorioImpl.getallUsuariosAPI(usuid){
            this.usuarios.value = it
       }
    }

    fun getUsuarios(): LiveData<MutableList<Usuario>> = this.usuarios

    fun getdataUsuario(usuidsesion: Int, usuid: Int){
        usuarioRepositorioImpl.getdataUsuarioAPI(usuidsesion, usuid){
            this.usuario.value = it
        }
    }

    fun getUsuario(): LiveData<Usuario> = this.usuario

    fun eliminarUsuario(usuidsesion: Int, usuid: Int){
        usuarioRepositorioImpl.eliminarUsuarioAPI(usuidsesion, usuid){
            this.eliminarusuario.value = it
        }
    }

    fun geteliminarusuario(): LiveData<Usuario> = this.eliminarusuario

    fun insertarUsuario(usuario: Usuario){
        usuarioRepositorioImpl.insertarUsuarioAPI(usuario){
            this.insertarusuario.value = it
        }
    }

    fun getinsertarUsuario(): LiveData<Usuario> = this.insertarusuario

    fun actualizarUsuario(usuario: Usuario){
        usuarioRepositorioImpl.actualizarUsuarioAPI(usuario){
            this.actualizarusuario.value = it
        }
    }

    fun getactualizarUsuario(): LiveData<Usuario> = this.actualizarusuario
}