package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.poryectoinicial.R
import com.example.poryectoinicial.viewmodel.ProyectosViewModel
import kotlinx.android.synthetic.main.cardview_proyectos.*
import kotlinx.android.synthetic.main.fragment_edit_proyectos.*

class EditProyectosFragment : Fragment() {

    private var proyectosViewModel: ProyectosViewModel? = ProyectosViewModel()
    private var rta = 0

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
        proyectosViewModel?.getdataProyectos(rta)
        proyectosViewModel?.geteditproyectos()?.observe(this, Observer {
            EdTituloProyecto.setText(it.get(0).titulo)
            EdDescripcionProyecto.setText(it.get(0).descripcion)
            EdFechaEnt.setText(it.get(0).fecentrega)
            EdFechaEst.setText(it.get(0).fecestimada)
            EdHoras.setText(it.get(0).horas)
        })
    }
}
