package com.example.poryectoinicial.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.model.Proyecto.ProyectoRepositorioImpl

class ProyectosViewModel {

    private  var proyectoRepositorioImpl: ProyectoRepositorioImpl? = ProyectoRepositorioImpl()
    private var proyectos = MutableLiveData<ArrayList<Proyecto>>()

    fun getallProyectos(usuid: Int){
        proyectoRepositorioImpl?.getallProyectosAPI(usuid)
    }
}