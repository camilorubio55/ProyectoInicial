package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.poryectoinicial.R
import com.example.poryectoinicial.view.Adapters.AdapterTareas
import com.example.poryectoinicial.viewmodel.TareasViewModel
import kotlinx.android.synthetic.main.fragment_tareas.*

class TareasFragment : Fragment() {

    private lateinit var tareasViewModel: TareasViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adaptador: AdapterTareas
    private var rta = 0

    fun manejador(){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tareasViewModel.getTareas().observe(this, Observer {
            adaptador.
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tareas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btfloat = activity?.findViewById<View>(R.id.BtFloatAction)
        rta = LoginFragment.usuid
        iniRecycler()
    }

    private fun iniRecycler(){
        adaptador = AdapterTareas()
        RcTareas.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        RcTareas.layoutManager = layoutManager
        RcTareas.adapter = adaptador
    }

    private fun consultarTareas(){
        tareasViewModel
    }
}
