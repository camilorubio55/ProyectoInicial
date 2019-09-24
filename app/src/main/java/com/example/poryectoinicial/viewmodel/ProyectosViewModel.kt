package com.example.poryectoinicial.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.model.Proyecto.ProyectoRepositorioImpl

class ProyectosViewModel : ViewModel() {

    private  var proyectoRepositorioImpl: ProyectoRepositorioImpl? = ProyectoRepositorioImpl()
    private var proyectos = MutableLiveData<ArrayList<Proyecto>>()

    fun getallProyectos(usuid: Int){
        val proyectos: MutableLiveData<ArrayList<Proyecto>> = proyectoRepositorioImpl!!.getallProyectosAPI(usuid)
        this.proyectos = proyectos
    }

    fun getproyectos():  MutableLiveData<ArrayList<Proyecto>> = proyectos
}