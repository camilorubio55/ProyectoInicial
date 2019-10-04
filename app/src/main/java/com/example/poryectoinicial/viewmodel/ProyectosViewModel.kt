package com.example.poryectoinicial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.model.Proyecto.ProyectoRepositorioImpl

class ProyectosViewModel(application: Application) : AndroidViewModel(application) {

    private var proyectoRepositorioImpl: ProyectoRepositorioImpl? = ProyectoRepositorioImpl()
    private val proyectos = MutableLiveData<List<Proyecto>>()
    private var editproyecto = MutableLiveData<Proyecto>()
    private var eliminarproyecto = MutableLiveData<MutableList<Proyecto>>()
    private var actualizarproyecto = MutableLiveData<Proyecto>()
    private var insertarproyecto = MutableLiveData<Proyecto>()

    fun getallProyectos(usuid: Int){
        proyectoRepositorioImpl!!.getallProyectosAPI(usuid){
            this.proyectos.value = it
        }/*,{
            val list = mutableListOf<Proyecto>()
            list.addAll(it)
            this.proyectos.value = list
        })*/
    }

    fun getproyectos(): MutableLiveData<List<Proyecto>> = this.proyectos

    fun getdataProyectos(proyectoid: Int){
        proyectoRepositorioImpl!!.getdataProyectoAPI(proyectoid){
            this.editproyecto.value = it
        }
    }

    fun geteditproyectos(): LiveData<Proyecto> = this.editproyecto

    fun eliminarProyecto(proyectoid: Int){
        proyectoRepositorioImpl!!.eliminarProyectoAPI(proyectoid){
            this.eliminarproyecto.value = it
        }
    }

    fun geteliminaproyecto(): LiveData<MutableList<Proyecto>> = this.eliminarproyecto

    fun actualizarProyecto(proyecto: Proyecto){
        proyectoRepositorioImpl!!.actualizarProyectoAPI(proyecto){
            this.actualizarproyecto.value = it
        }
    }

    fun getactualizarproyecto(): LiveData<Proyecto> = this.actualizarproyecto

    fun insertarProyecto(proyecto: Proyecto){
        proyectoRepositorioImpl!!.insertarProyectoAPI(proyecto){
            this.insertarproyecto.value = it
        }
    }

    fun getinsertarproyecto(): LiveData<Proyecto> = this.insertarproyecto

    fun limpiarObjetos(){
        this.insertarproyecto.value = null
        this.actualizarproyecto.value = null
    }

/*    fun eliminaitem(){
        this.proyectos.value = null
    }*/
}