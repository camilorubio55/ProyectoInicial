package com.example.poryectoinicial.model.Usuarios

import android.util.Log
import com.example.poryectoinicial.model.APIService
import com.example.poryectoinicial.model.APIUtils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioRepositorioImpl {

    fun getallUsuariosAPI(usuid: Int, completion: (MutableList<Usuario>) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("usuid", usuid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.getallusuario(jsonObject).enqueue(object :
                Callback<ArrayList<Usuario>> {
                override fun onFailure(call: Call<ArrayList<Usuario>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Usuario>>, response: Response<ArrayList<Usuario>>) {
                    val usuario = response.body() as MutableList<Usuario>
                    completion(usuario)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun getdataUsuarioAPI(usuidsesion:Int, usuid: Int, completion: (Usuario) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("usuidsesion", usuidsesion)
        jsonObject.addProperty("usuid", usuid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.getdatausuario(jsonObject).enqueue(object :
                Callback<ArrayList<Usuario>> {
                override fun onFailure(call: Call<ArrayList<Usuario>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Usuario>>, response: Response<ArrayList<Usuario>>) {
                    val usuario = response.body()!![0]
                    completion(usuario)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun eliminarUsuarioAPI(usuidsesion: Int, usuid: Int, completion: (Usuario) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("usuidsesion", usuidsesion)
        jsonObject.addProperty("usuid", usuid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.eliminarusuario(jsonObject).enqueue(object :
                Callback<ArrayList<Usuario>> {
                override fun onFailure(call: Call<ArrayList<Usuario>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Usuario>>, response: Response<ArrayList<Usuario>>) {
                    val usuario = response.body()!![0]
                    completion(usuario)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun insertarUsuarioAPI(usuario: Usuario, completion: (Usuario) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("codigo", usuario.codigo)
        jsonObject.addProperty("contrasena", usuario.contrasena)
        jsonObject.addProperty("nombre", usuario.nombre)
        jsonObject.addProperty("rol", usuario.rol)
        jsonObject.addProperty("usuidsesion", usuario.usuidsesion)
        jsonObject.addProperty("email", usuario.email)
        jsonObject.addProperty("telefono", usuario.telefono)
        jsonObject.addProperty("inactivo", usuario.inactivo)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.insertarusuario(jsonObject).enqueue(object :
                Callback<ArrayList<Usuario>> {
                override fun onFailure(call: Call<ArrayList<Usuario>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Usuario>>, response: Response<ArrayList<Usuario>>) {
                    val usuario = response.body()!![0]
                    completion(usuario)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun actualizarUsuarioAPI(usuario: Usuario, completion: (Usuario) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("usuidsesion", usuario.usuidsesion)
        jsonObject.addProperty("usuid", usuario.usuid)
        jsonObject.addProperty("codigo", usuario.codigo)
        jsonObject.addProperty("contrasena", usuario.contrasena)
        jsonObject.addProperty("nombre", usuario.nombre)
        jsonObject.addProperty("rol", usuario.rol)
        jsonObject.addProperty("email", usuario.email)
        jsonObject.addProperty("telefono", usuario.telefono)
        jsonObject.addProperty("inactivo", usuario.inactivo)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.actualizarusuario(jsonObject).enqueue(object :
                Callback<ArrayList<Usuario>> {
                override fun onFailure(call: Call<ArrayList<Usuario>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Usuario>>, response: Response<ArrayList<Usuario>>) {
                    val usuario = response.body()!![0]
                    completion(usuario)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }
}