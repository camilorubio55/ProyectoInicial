package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.view.Adapters.AdapterProyectos
import com.example.poryectoinicial.viewmodel.ProyectosViewModel
import kotlinx.android.synthetic.main.fragment_proyectos.*
import androidx.lifecycle.Observer
import com.example.poryectoinicial.model.Proyecto.Proyecto

class ProyectosFragment : Fragment() {

    private var proyectosViewModel: ProyectosViewModel? = ProyectosViewModel()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adaptador: AdapterProyectos? = null
    private var rta = 0

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
        proyectosViewModel?.getallProyectos(rta)
        proyectosViewModel?.getproyectos()?.observe(this, Observer {proyecto: ArrayList<Proyecto> ->
            RcProyectos?.setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            RcProyectos?.layoutManager = layoutManager
            adaptador = AdapterProyectos(context, proyecto)
            RcProyectos.adapter = adaptador
        })
    }
}
