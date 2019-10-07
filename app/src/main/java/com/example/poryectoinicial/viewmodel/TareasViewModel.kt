package com.example.poryectoinicial.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.poryectoinicial.model.Tareas.Tarea
import com.example.poryectoinicial.model.Tareas.TareaRepositorioImpl

class TareasViewModel(application: Application): AndroidViewModel(application) {

    private var tareaRepositorioImpl: TareaRepositorioImpl? = TareaRepositorioImpl()
    private var tareas = MutableLiveData<MutableList<Tarea>>()
    private var tarea = MutableLiveData<Tarea>()
    private var insertartarea = MutableLiveData<Tarea>()

    fun getallTareas(usuid: Int){
        tareaRepositorioImpl?.getallTareasAPI(usuid){
            this.tareas.value = it
        }
    }

    fun getTareas(): LiveData<MutableList<Tarea>> = this.tareas

    fun getdataTarea(deproyectoid : Int){
        tareaRepositorioImpl?.getdataTareasAPI(deproyectoid){
            this.tarea.value = it
        }
    }

    fun geteditTareas(): LiveData<Tarea> = this.tarea

    fun insertarTarea(tarea: Tarea){
        tareaRepositorioImpl?.insertarTareaAPI(tarea){
            this.insertartarea.value = it
        }
    }

    fun getinsertartarea(): LiveData<Tarea> = this.insertartarea

}