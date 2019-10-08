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
        val mAPIService: APIService = APIUtils.apiService
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

    fun getdataTareasAPI(deproyectoid: Int, completion: (Tarea) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("deproyectoid", deproyectoid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.getdatadeproyectos(jsonObject).enqueue(object :
                Callback<ArrayList<Tarea>> {
                override fun onFailure(call: Call<ArrayList<Tarea>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Tarea>>, response: Response<ArrayList<Tarea>>) {
                    val tarea: Tarea = response.body()?.get(0)!!
                    completion(tarea)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun insertarTareaAPI(tarea: Tarea, completion: (Tarea) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("proyectoid", tarea.proyectoid)
        jsonObject.addProperty("titulo", tarea.titulo)
        jsonObject.addProperty("descripcion", tarea.descripcion)
        jsonObject.addProperty("fecha", tarea.fecha)
        jsonObject.addProperty("usuid", tarea.usuid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.insertartarea(jsonObject).enqueue(object :
                Callback<ArrayList<Tarea>> {
                override fun onFailure(call: Call<ArrayList<Tarea>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Tarea>>, response: Response<ArrayList<Tarea>>) {
                    val tarea: Tarea = response.body()?.get(0)!!
                    completion(tarea)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun actualizarTareaAPI(tarea: Tarea, completion: (Tarea) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("deproyectoid", tarea.deproyectoid)
        jsonObject.addProperty("proyectoid", tarea.proyectoid)
        jsonObject.addProperty("titulo", tarea.titulo)
        jsonObject.addProperty("descripcion", tarea.descripcion)
        jsonObject.addProperty("fecha", tarea.fecha)
        jsonObject.addProperty("usuid", tarea.usuid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.actualizartarea(jsonObject).enqueue(object :
                Callback<ArrayList<Tarea>> {
                override fun onFailure(call: Call<ArrayList<Tarea>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Tarea>>, response: Response<ArrayList<Tarea>>) {
                    val tarea: Tarea = response.body()?.get(0)!!
                    completion(tarea)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun eliminarTareaAPI(deproyectoid: Int, completion: (Tarea) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("deproyectoid", deproyectoid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.eliminartarea(jsonObject).enqueue(object :
                Callback<ArrayList<Tarea>> {
                override fun onFailure(call: Call<ArrayList<Tarea>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Tarea>>, response: Response<ArrayList<Tarea>>) {
                    val tarea: Tarea = response.body()?.get(0)!!
                    completion(tarea)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }
}