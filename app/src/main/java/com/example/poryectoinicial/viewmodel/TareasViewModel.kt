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
    private var actualizartarea = MutableLiveData<Tarea>()
    private var eliminartarea = MutableLiveData<Tarea>()

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

    fun actualizarTarea(tarea: Tarea){
        tareaRepositorioImpl?.actualizarTareaAPI(tarea){
            this.actualizartarea.value = it
        }
    }

    fun getactualizartarea(): LiveData<Tarea> = this.actualizartarea

    fun eliminarTarea(deproyectoid: Int){
        tareaRepositorioImpl?.eliminarTareaAPI(deproyectoid){
            this.eliminartarea.value = it
        }
    }

    fun geteliminartarea(): LiveData<Tarea> = this.eliminartarea
}