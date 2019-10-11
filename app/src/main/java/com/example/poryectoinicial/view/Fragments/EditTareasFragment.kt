package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Tareas.Tarea
import com.example.poryectoinicial.viewmodel.TareasViewModel
import kotlinx.android.synthetic.main.fragment_edit_tareas.*

class EditTareasFragment : Fragment() {

    private lateinit var tareasViewModel: TareasViewModel
    private var tarea: Tarea = Tarea()
    private var rta = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tareasViewModel = ViewModelProviders.of(activity!!).get(TareasViewModel(activity!!.application)::class.java)
        tareasViewModel.geteditTareas().observe(this, Observer {
            if (it != null)
                setData(it)
        })
        tareasViewModel.getinsertartarea().observe( this, Observer {
            if (it != null){
                mostrarmensaje(it.mensaje)
                regresaraGrid()
            }
        })
        tareasViewModel.getactualizartarea().observe(this, Observer {
            if(it != null)
                mostrarmensaje(it.mensaje)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_tareas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rta = arguments?.getString("DEPROYECTOID")!!.toInt()
        if(rta != 0)
            consultarDetalleTarea()
        else
            limpiarcampos()
        BtGuardarTarea.setOnClickListener {
            if (validarCampos()) {
                val objeto = construirobjeto()
                if (rta != 0)
                    actualizarTarea(objeto)
                else
                    insertarTarea(objeto)
            }
        }
    }

    private fun regresaraGrid(){
        activity?.onBackPressed()
    }

    private fun consultarDetalleTarea(){
        tareasViewModel.getdataTarea(rta)
    }

    private fun insertarTarea(tarea: Tarea){
        tareasViewModel.insertarTarea(tarea)
    }

    private fun actualizarTarea(tarea: Tarea){
        tareasViewModel.actualizarTarea(tarea)
    }

    private fun construirobjeto(): Tarea{
        tarea.deproyectoid = rta.toString()
        tarea.proyectoid = EdNumProyecto.text.toString()
        tarea.titulo = EdTituloTarea.text.toString()
        tarea.descripcion = EdDescripcionTarea.text.toString()
        tarea.fecha = EdFecha.text.toString()
        tarea.usuid = LoginFragment.usuid.toString()

        return tarea
    }

    private fun setData(tarea: Tarea){
        if(tarea.toString().isNotEmpty())
            EdTituloTarea.setText(tarea.titulo)
            EdDescripcionTarea.setText(tarea.descripcion)
            EdFecha.setText(tarea.fecha)
            EdNumProyecto.setText(tarea.proyectoid)
    }

    private fun mostrarmensaje(mensaje : String){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun limpiarcampos(){
        EdTituloTarea.setText("")
        EdDescripcionTarea.setText("")
        EdFecha.setText("")
        EdNumProyecto.setText("")
    }

    private fun validarCampos(): Boolean{
        return when{
            EdTituloTarea.text.isNullOrEmpty() -> {
                EdTituloTarea.error = "El titulo es obligatorio"
                false
            }
            EdDescripcionTarea.text.isNullOrEmpty() -> {
                EdDescripcionTarea.error = "La descripcion es obligatoria"
                false
            }
            EdFecha.text.isNullOrEmpty() -> {
                EdFecha.error = "La fecha es obligatoria"
                false
            }
            else -> true
        }
    }

    companion object {
        const val TAG = "EditTareaFragment"
        fun newInstance(bundle: Bundle? = null): EditTareasFragment {
            val fragment = EditTareasFragment()
            if (bundle != null) {
                fragment.arguments = bundle
            }
            return fragment
        }
    }
}
