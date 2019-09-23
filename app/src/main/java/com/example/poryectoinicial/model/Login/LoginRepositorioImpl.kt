package com.example.poryectoinicial.model.Login

import android.util.Log
import com.example.poryectoinicial.model.APIService
import com.example.poryectoinicial.model.APIUtils
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositorioImpl {

    private var login: Int? = 0

    fun loginAPI(username: String, pass: String) : Int?{

        var jsonObject: JsonObject = JsonObject()
        jsonObject.addProperty("codigo",username)
        jsonObject.addProperty("contrasena",pass)
        var mAPIService: APIService?

        mAPIService = APIUtils.apiService
        try {
            mAPIService!!.login(jsonObject).enqueue(object :
                Callback<ArrayList<Login>> {
                override fun onFailure(call: Call<ArrayList<Login>>, t: Throwable) {
                    login = 0
                    Log.d("--- respuestaonF", "onFailure")
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ArrayList<Login>>, response: Response<ArrayList<Login>>) {
                    login = response.body()?.get(0)?.usuid?.toInt()
                }
            })
        }catch (e: Exception){
            e.stackTrace
            return null
        }
        return login!!.toInt()
    }
}