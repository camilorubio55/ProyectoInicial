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
import androidx.navigation.Navigation
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.view.Interfaces.ClickListener

class ProyectosFragment : Fragment() {

    private var proyectosViewModel: ProyectosViewModel? = ProyectosViewModel()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adaptador: AdapterProyectos? = null
    private var rta = 0

    fun iniRecycler(){
        adaptador = AdapterProyectos()
        RcProyectos.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        RcProyectos.layoutManager = layoutManager
        RcProyectos.adapter = adaptador
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
        proyectosViewModel?.getallProyectos(rta)
        consultarProyectos()
        SwRefresh.setOnRefreshListener {
            SwRefresh.isRefreshing = true
            consultarProyectos()
            SwRefresh.isRefreshing = false
        }
    }

    fun consultarProyectos(){
        proyectosViewModel?.getproyectos()?.observe(this, Observer {
            manejador(it)
        })
    }

    fun manejador(listdata: MutableList<Proyecto>){
        adaptador?.setData(listdata, object : ClickListener {
            override fun onClick(vista: View, index: Int) {
                navEditProyectos(vista, listdata.get(index).proyectoid.toInt())
            }
        })
    }

    fun navEditProyectos(view: View, proyectoid: Int?){
        val bundle = Bundle()
        bundle.putString("PROYECTOID",proyectoid.toString())
        Navigation.findNavController(view).navigate(R.id.action_proyectosFragment_to_editProyectosFragment, bundle)
    }
}
