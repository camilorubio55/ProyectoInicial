package com.example.poryectoinicial.model.Proyecto

import android.util.Log
import com.example.poryectoinicial.model.APIService
import com.example.poryectoinicial.model.APIUtils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProyectoRepositorioImpl {

    fun getallProyectosAPI(usuid: Int, completion: (MutableList<Proyecto>) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("usuid", usuid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.getallproyectos(jsonObject).enqueue(object :
                Callback<ArrayList<Proyecto>> {
                override fun onFailure(call: Call<ArrayList<Proyecto>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Proyecto>>, response: Response<ArrayList<Proyecto>>) {
                    val proyect = response.body() as MutableList<Proyecto>
                    completion(proyect)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun getdataProyectoAPI(proyectoid : Int, completion: (Proyecto) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("proyectoid", proyectoid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.getdataproyecto(jsonObject).enqueue(object :
                Callback<ArrayList<Proyecto>> {
                override fun onFailure(call: Call<ArrayList<Proyecto>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Proyecto>>, response: Response<ArrayList<Proyecto>>) {
                    val proyect = response.body()!![0]
                    completion(proyect)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun eliminarProyectoAPI(proyectoid : Int, completion: (MutableList<Proyecto>) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("proyectoid", proyectoid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.eliminarproyecto(jsonObject).enqueue(object :
                Callback<ArrayList<Proyecto>> {
                override fun onFailure(call: Call<ArrayList<Proyecto>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Proyecto>>, response: Response<ArrayList<Proyecto>>) {
                    val proyect = response.body() as MutableList<Proyecto>
                    completion(proyect)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun actualizarProyectoAPI(proyecto : Proyecto, completion: (Proyecto) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("proyectoid", proyecto.proyectoid)
        jsonObject.addProperty("titulo", proyecto.titulo)
        jsonObject.addProperty("descripcion", proyecto.descripcion)
        jsonObject.addProperty("fecestimada", proyecto.fecestimada)
        jsonObject.addProperty("fecentrega", proyecto.fecentrega)
        jsonObject.addProperty("horas", proyecto.horas)
        jsonObject.addProperty("usuid", proyecto.usuid)
        val mAPIService: APIService?
        mAPIService = APIUtils.apiService
        try {
            mAPIService.actualizarproyecto(jsonObject).enqueue(object :
                Callback<ArrayList<Proyecto>> {
                override fun onFailure(call: Call<ArrayList<Proyecto>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Proyecto>>, response: Response<ArrayList<Proyecto>>) {
                    val proyect = response.body()!![0]
                    completion(proyect)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun insertarProyectoAPI(proyecto : Proyecto, completion: (Proyecto) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("titulo", proyecto.titulo)
        jsonObject.addProperty("descripcion", proyecto.descripcion)
        jsonObject.addProperty("fecestimada", proyecto.fecestimada)
        jsonObject.addProperty("fecentrega", proyecto.fecentrega)
        jsonObject.addProperty("horas", proyecto.horas)
        jsonObject.addProperty("usuid", proyecto.usuid)
        val mAPIService: APIService = APIUtils.apiService
        try {
            mAPIService.insertarproyecto(jsonObject).enqueue(object :
                Callback<ArrayList<Proyecto>> {
                override fun onFailure(call: Call<ArrayList<Proyecto>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Proyecto>>, response: Response<ArrayList<Proyecto>>) {
                    val proyect = response.body()!![0]
                    completion(proyect)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }
}