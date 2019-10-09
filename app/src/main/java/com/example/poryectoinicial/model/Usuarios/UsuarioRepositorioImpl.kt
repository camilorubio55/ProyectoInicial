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
                    val tarea = response.body() as MutableList<Usuario>
                    completion(tarea)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }
}