package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.poryectoinicial.R
import com.example.poryectoinicial.viewmodel.ProyectosViewModel

class ProyectosFragment : Fragment() {

    private var proyectosViewModel: ProyectosViewModel? = ProyectosViewModel()
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
    }
}
