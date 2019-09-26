package com.example.poryectoinicial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.model.Proyecto.ProyectoRepositorioImpl

class ProyectosViewModel(application: Application) : AndroidViewModel(application) {

    private  var proyectoRepositorioImpl: ProyectoRepositorioImpl? = ProyectoRepositorioImpl()
    private var proyectos = MutableLiveData<MutableList<Proyecto>>()
    private var editproyecto = MutableLiveData<MutableList<Proyecto>>()
    private var eliminarproyecto = MutableLiveData<MutableList<Proyecto>>()

    fun getallProyectos(usuid: Int){
        proyectoRepositorioImpl!!.getallProyectosAPI(usuid){
            this.proyectos.value = it
        }
    }

    fun getproyectos():  LiveData<MutableList<Proyecto>> = this.proyectos

    fun getdataProyectos(proyectoid: Int){
        proyectoRepositorioImpl!!.getdataProyectoAPI(proyectoid){
            this.editproyecto.value = it
        }
        //this.editproyecto.postValue(editproyecto)
    }

    fun geteditproyectos(): LiveData<MutableList<Proyecto>> = this.editproyecto

    fun eliminarProyecto(proyectoid: Int){
        val eliminarproyecto: MutableList<Proyecto> = proyectoRepositorioImpl!!.eliminarProyectoAPI(proyectoid)
        this.eliminarproyecto.postValue(eliminarproyecto)
    }

    fun geteliminaproyecto(): LiveData<MutableList<Proyecto>> = this.eliminarproyecto
}