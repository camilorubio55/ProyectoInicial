package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.viewmodel.ProyectosViewModel
import kotlinx.android.synthetic.main.cardview_proyectos.*
import kotlinx.android.synthetic.main.fragment_edit_proyectos.*

class EditProyectosFragment : Fragment() {

    private lateinit var proyectosViewModel: ProyectosViewModel
    private var rta = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        proyectosViewModel = ViewModelProviders.of(activity!!).get(ProyectosViewModel(activity!!.application)::class.java)
        proyectosViewModel?.geteditproyectos()?.observe(this, Observer {
            setData(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_proyectos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let {
            rta = getArguments()?.getString("PROYECTOID")!!.toInt()
        }
        if(!rta.equals(0)){
            consultarDetalleProyecto()
        }
        //BtGuardarProyecto.setOnLongClickListener {

        //}
    }

    fun consultarDetalleProyecto(){
        proyectosViewModel?.getdataProyectos(rta)
    }

    fun setData(detalleproyecto: MutableList<Proyecto>){
        if(!detalleproyecto.isNullOrEmpty()){
            EdTituloProyecto.setText(detalleproyecto.get(0).titulo)
            EdDescripcionProyecto.setText(detalleproyecto.get(0).descripcion)
            EdFechaEnt.setText(detalleproyecto.get(0).fecentrega)
            EdFechaEst.setText(detalleproyecto.get(0).fecestimada)
            EdHoras.setText(detalleproyecto.get(0).horas)
        }
    }
}
