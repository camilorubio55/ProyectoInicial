package com.example.poryectoinicial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.model.Proyecto.ProyectoRepositorioImpl

class ProyectosViewModel : ViewModel() {

    private  var proyectoRepositorioImpl: ProyectoRepositorioImpl? = ProyectoRepositorioImpl()
    private var proyectos = MutableLiveData<MutableList<Proyecto>>()
    private var editproyecto = MutableLiveData<ArrayList<Proyecto>>()

    fun getallProyectos(usuid: Int){
        val proyectos: MutableLiveData<MutableList<Proyecto>> = proyectoRepositorioImpl!!.getallProyectosAPI(usuid)
        this.proyectos = proyectos
    }

    fun getproyectos():  MutableLiveData<MutableList<Proyecto>> = proyectos

    fun getdataProyectos(proyectoid: Int){
        val editproyecto: MutableLiveData<ArrayList<Proyecto>> = proyectoRepositorioImpl!!.getdataProyectoAPI(proyectoid)
        this.editproyecto = editproyecto
    }

    fun geteditproyectos(): MutableLiveData<ArrayList<Proyecto>> = editproyecto
}