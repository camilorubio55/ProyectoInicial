package com.example.poryectoinicial.model.Tareas

import android.util.Log
import com.example.poryectoinicial.model.APIService
import com.example.poryectoinicial.model.APIUtils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TareaRepositorioImpl {

    fun getallTareasAPI(usuid: Int, completion: (MutableList<Tarea>) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("usuid", usuid)
        val mAPIService: APIService
        mAPIService = APIUtils.apiService
        try {
            mAPIService.getalldeproyectos(jsonObject).enqueue(object :
                Callback<ArrayList<Tarea>> {
                override fun onFailure(call: Call<ArrayList<Tarea>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Tarea>>, response: Response<ArrayList<Tarea>>) {
                    val tarea = response.body() as MutableList<Tarea>
                    completion(tarea)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

}