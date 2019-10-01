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

    fun getallTareas(usuid: Int){
        tareaRepositorioImpl?.getallTareasAPI(usuid,{
            this.tareas.value = it
        })
    }

    fun getTareas(): LiveData<MutableList<Tarea>> = this.tareas
}