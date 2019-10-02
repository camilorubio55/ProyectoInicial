package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Tareas.Tarea
import com.example.poryectoinicial.view.Adapters.AdapterTareas
import com.example.poryectoinicial.viewmodel.TareasViewModel
import kotlinx.android.synthetic.main.fragment_tareas.*

class TareasFragment : Fragment() {

    private lateinit var tareasViewModel: TareasViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adaptador: AdapterTareas
    private var rta = 0

    fun manejador(listdata: MutableList<Tarea>){
        adaptador.setdata(listdata)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tareasViewModel = ViewModelProviders.of(activity!!).get(TareasViewModel(activity!!.application)::class.java)
        tareasViewModel.getTareas().observe(this, Observer {
            manejador(it)
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
        SwRefreshTareas.setOnRefreshListener {
            SwRefreshTareas.isRefreshing = true
            consultarTareas()
            SwRefreshTareas.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        consultarTareas()
    }

    private fun iniRecycler(){
        adaptador = AdapterTareas()
        RcTareas.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        RcTareas.layoutManager = layoutManager
        RcTareas.adapter = adaptador
    }

    private fun consultarTareas(){
        tareasViewModel.getallTareas(rta)
    }
}
