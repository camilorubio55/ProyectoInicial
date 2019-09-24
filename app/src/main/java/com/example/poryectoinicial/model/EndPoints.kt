package com.example.poryectoinicial.model

import com.example.poryectoinicial.model.Login.Login
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface APIService {
    @POST("codigophp/API2/Controller/Login.php")
    fun login(@Body login: JsonObject?): Call<ArrayList<Login>>

    @POST("codigophp/API2/Controller/GetAllProyecto.php")
    fun getallproyectos(@Body proyectos: JsonObject?): Call<ArrayList<Proyecto>>

    @POST("codigophp/API2/Controller/GetDataProyecto.php")
    fun getdataproyecto(@Body proyecto: JsonObject?): Call<ArrayList<Proyecto>>
}

object APIUtils{
    val BaseUrl = "http://192.168.0.71:81/"
    val apiService: APIService
        get() = RetrofitClient.getClient(BaseUrl)!!.create(APIService::class.java)
}

object RetrofitClient{

    var retrofit: Retrofit? = null

    fun  getClient(baseUrl: String): Retrofit?{
        if(retrofit == null){
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}