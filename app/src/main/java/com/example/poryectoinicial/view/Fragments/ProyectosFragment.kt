package com.example.poryectoinicial.view.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.view.Adapters.AdapterProyectos
import com.example.poryectoinicial.viewmodel.ProyectosViewModel
import kotlinx.android.synthetic.main.fragment_proyectos.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.view.Activities.EditActivity
import com.example.poryectoinicial.view.Interfaces.ClickListener
import com.example.poryectoinicial.view.Interfaces.LongClickListener
import org.jetbrains.anko.support.v4.alert

class ProyectosFragment : Fragment() {

    private lateinit var proyectosViewModel: ProyectosViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adaptador: AdapterProyectos
    private var rta = 0
    private lateinit var listdata: MutableList<Proyecto>

    private fun manejador(listdata: List<Proyecto>){
        //this.listdata = listdata
        adaptador.clearData()
        adaptador.setData(listdata, object : ClickListener {
            override fun onClick(vista: View, index: Int) {
                navEditProyectos(listdata[index].proyectoid.toInt())
            }
        }, object : LongClickListener {
            override fun longClick(vista: View, index: Int) {
                eliminarProyecto(listdata[index].proyectoid.toInt(), index)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        proyectosViewModel = ViewModelProviders.of(activity!!).get(ProyectosViewModel(activity!!.application)::class.java)
        proyectosViewModel.getproyectos().observe(this, Observer {
            manejador(it)
        })

        proyectosViewModel.geteliminaproyecto().observe(this, Observer {
            respuestaEliminarProyecto(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_proyectos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btfloat = activity?.findViewById<View>(R.id.BtFloatAction)
        rta = LoginFragment.usuid
        iniRecycler()
        SwRefresh.setOnRefreshListener {
            SwRefresh.isRefreshing = true
            //proyectosViewModel.getproyectos().value?.clear()
            consultarProyectos()
            SwRefresh.isRefreshing = false
        }
        btfloat?.setOnClickListener{
            navEditProyectos(0)
        }
    }

    override fun onResume() {
        super.onResume()
        consultarProyectos()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed){
            consultarProyectos()
        }
    }

    private fun iniRecycler(){
        adaptador = AdapterProyectos()
        RcProyectos.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        RcProyectos.layoutManager = layoutManager
        RcProyectos.adapter = adaptador
    }

    private fun consultarProyectos(){
        proyectosViewModel.getallProyectos(rta)
    }

    private fun navEditProyectos(proyectoid: Int?){
        Intent(activity, EditActivity::class.java).run {
            putExtra("PROYECTOID", proyectoid.toString())
            startActivity(this)
        }
    }

    private fun eliminarProyecto(proyectoid: Int, index: Int){
        alert {
            title = "Eliminar proyecto $proyectoid ?"
            message = "Al eliminar el proyecto se eliminaran las tareas relacionadas"
            positiveButton ( "Si") {
                proyectosViewModel.eliminarProyecto(proyectoid)
                adaptador.deleteItem(index)
                proyectosViewModel.getproyectos().value?.removeAt(index)
            }
            negativeButton ( "No" ) {
            }
        }.show ()
    }

    private fun respuestaEliminarProyecto(respuesta: MutableList<Proyecto>){
        Toast.makeText(context, respuesta[0].mensaje, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "ProyectosFragment"
    }
}
