package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.view.Adapters.AdapterProyectos
import com.example.poryectoinicial.viewmodel.ProyectosViewModel
import kotlinx.android.synthetic.main.fragment_proyectos.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.view.Interfaces.ClickListener
import com.example.poryectoinicial.view.Interfaces.LongClickListener
import org.jetbrains.anko.support.v4.alert

class ProyectosFragment : Fragment() {

    private lateinit var proyectosViewModel: ProyectosViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adaptador: AdapterProyectos
    private var rta = 0

    fun manejador(listdata: MutableList<Proyecto>){
        adaptador.setData(listdata, object : ClickListener {
            override fun onClick(vista: View, index: Int) {
                navEditProyectos(vista, listdata.get(index).proyectoid.toInt())
            }
        }, object : LongClickListener {
            override fun longClick(vista: View, index: Int) {
                eliminarProyecto(listdata.get(index).proyectoid.toInt())
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
        arguments.let {
            rta = getArguments()?.getString("USUID")!!.toInt()
        }
        iniRecycler()
        SwRefresh.setOnRefreshListener {
            SwRefresh.isRefreshing = true
            consultarProyectos()
            SwRefresh.isRefreshing = false
        }
        BtFloatAction.setOnClickListener{view: View ->
            navEditProyectos(view,0)
        }
    }

    override fun onResume() {
        super.onResume()
        consultarProyectos()
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

    private fun navEditProyectos(view: View, proyectoid: Int?){
        val bundle = Bundle()
        bundle.putString("PROYECTOID",proyectoid.toString())
        Navigation.findNavController(view).navigate(R.id.action_proyectosFragment_to_editProyectosFragment, bundle)
    }

    private fun eliminarProyecto(proyectoid: Int){
        alert {
            title = "Eliminar proyecto $proyectoid ?"
            message = "Al eliminar el proyecto se eliminaran las tareas relacionadas"
            positiveButton ( "Si") {
                proyectosViewModel.eliminarProyecto(proyectoid)
                //respuestaEliminarProyecto()
            }
            negativeButton ( "No" ) {
            }
        }.show ()
    }

    private fun respuestaEliminarProyecto(respuesta: MutableList<Proyecto>){
        Toast.makeText(context, respuesta.get(0).mensaje, Toast.LENGTH_SHORT).show()
    }
}
