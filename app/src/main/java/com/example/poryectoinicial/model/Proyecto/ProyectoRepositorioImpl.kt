package com.example.poryectoinicial.model.Proyecto

import android.util.Log
import androidx.annotation.UiThread
import androidx.lifecycle.MutableLiveData
import com.example.poryectoinicial.model.APIService
import com.example.poryectoinicial.model.APIUtils
import com.example.poryectoinicial.model.Login.Login
import com.google.gson.JsonObject
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProyectoRepositorioImpl {

    private var proyectos = mutableListOf<Proyecto>()
    private var editproyecto = mutableListOf<Proyecto>()
    private var eliminarproyecto = mutableListOf<Proyecto>()

    fun getallProyectosAPI(usuid: Int, completion: (MutableList<Proyecto>) -> Unit){
        var jsonObject = JsonObject()
        jsonObject.addProperty("usuid", usuid)
        var mAPIService: APIService?
        mAPIService = APIUtils.apiService

        try {
            mAPIService.getallproyectos(jsonObject).enqueue(object :
                Callback<ArrayList<Proyecto>> {
                override fun onFailure(call: Call<ArrayList<Proyecto>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Proyecto>>, response: Response<ArrayList<Proyecto>>) {
                    var proyect = response.body() as MutableList<Proyecto>
                    completion(proyect)
                    //proyectos.addAll(proyect)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun getdataProyectoAPI(proyectoid : Int, completion: (MutableList<Proyecto>) -> Unit){
        var jsonObject = JsonObject()
        jsonObject.addProperty("proyectoid", proyectoid)
        var mAPIService: APIService?
        mAPIService = APIUtils.apiService
        try {
            mAPIService.getdataproyecto(jsonObject).enqueue(object :
                Callback<ArrayList<Proyecto>> {
                override fun onFailure(call: Call<ArrayList<Proyecto>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Proyecto>>, response: Response<ArrayList<Proyecto>>) {
                    var proyect = response.body() as MutableList<Proyecto>
                    completion(proyect)
                    //editproyecto.addAll(proyect)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }

    fun eliminarProyectoAPI(proyectoid : Int): MutableList<Proyecto>{
        var jsonObject = JsonObject()
        jsonObject.addProperty("proyectoid", proyectoid)
        var mAPIService: APIService?
        mAPIService = APIUtils.apiService
        try {
            mAPIService.eliminarproyecto(jsonObject).enqueue(object :
                Callback<ArrayList<Proyecto>> {
                override fun onFailure(call: Call<ArrayList<Proyecto>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Proyecto>>, response: Response<ArrayList<Proyecto>>) {
                    var proyect = response.body() as MutableList<Proyecto>
                    eliminarproyecto.addAll(proyect)
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
        return eliminarproyecto
    }
}