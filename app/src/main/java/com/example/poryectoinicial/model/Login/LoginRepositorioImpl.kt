package com.example.poryectoinicial.model.Login

import android.util.Log
import com.example.poryectoinicial.model.APIService
import com.example.poryectoinicial.model.APIUtils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositorioImpl {

    fun loginAPI(username: String, pass: String, completion: (Int) -> Unit){
        val jsonObject = JsonObject()
        jsonObject.addProperty("codigo",username)
        jsonObject.addProperty("contrasena",pass)
        val mAPIService: APIService?
        mAPIService = APIUtils.apiService
        try {
            mAPIService.login(jsonObject).enqueue(object :
                Callback<ArrayList<Login>> {
                override fun onFailure(call: Call<ArrayList<Login>>, t: Throwable) {
                    val login = 0
                    completion(login)
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Login>>, response: Response<ArrayList<Login>>) {
                    val login = response.body()?.get(0)?.usuid?.toInt()
                    completion(login!!.toInt())
                }
            })
        }catch (e: Exception){
            e.stackTrace
        }
    }
}