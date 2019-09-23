package com.example.poryectoinicial.model.Proyecto

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.poryectoinicial.model.APIService
import com.example.poryectoinicial.model.APIUtils
import com.example.poryectoinicial.model.Login.Login
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProyectoRepositorioImpl {

    private var proyectos = MutableLiveData<ArrayList<Proyecto>>()

    fun getallProyectosAPI(usuid: Int): MutableLiveData<ArrayList<Proyecto>>{

        var jsonObject: JsonObject = JsonObject()
        jsonObject.addProperty("usuid", usuid)
        var mAPIService: APIService?

        mAPIService = APIUtils.apiService
        try {
            mAPIService!!.getallproyectos(jsonObject).enqueue(object :
                Callback<ArrayList<Proyecto>> {
                override fun onFailure(call: Call<ArrayList<Proyecto>>, t: Throwable) {
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Proyecto>>, response: Response<ArrayList<Proyecto>>) {
                   var proyect = response.body()
                   proyectos.value = proyect
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
        return proyectos
    }
}