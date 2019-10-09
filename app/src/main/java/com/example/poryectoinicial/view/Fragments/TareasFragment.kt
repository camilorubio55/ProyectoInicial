package com.example.poryectoinicial.view.Fragments


import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.R.id.BtFloatAction
import com.example.poryectoinicial.model.Tareas.Tarea
import com.example.poryectoinicial.view.Activities.EditActivity
import com.example.poryectoinicial.view.Adapters.AdapterTareas
import com.example.poryectoinicial.view.Interfaces.ClickListener
import com.example.poryectoinicial.view.Interfaces.LongClickListener
import com.example.poryectoinicial.viewmodel.TareasViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_tareas.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.support.v4.alert

class TareasFragment : Fragment() {

    private lateinit var tareasViewModel: TareasViewModel
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adaptador: AdapterTareas
    private var rta = 0

    fun manejador(listdata: MutableList<Tarea>){
        adaptador.cleardata()
        adaptador.setdata(listdata)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tareasViewModel = ViewModelProviders.of(activity!!).get(TareasViewModel(activity!!.application)::class.java)
        tareasViewModel.getTareas().observe(this, Observer {
            manejador(it)
        })
        tareasViewModel.geteliminartarea().observe(this, Observer {
            if(it != null){
                respuestaEliminarTarea(it.mensaje)
                //consultarTareas()
            }
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
        rta = LoginFragment.usuid
        RcTareas.isMotionEventSplittingEnabled = false
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
        if(!userVisibleHint){
            return
        }
        val btfloattareas = activity?.findViewById<FloatingActionButton>(BtFloatAction)
        btfloattareas?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorFloatActionBTareas))
        btfloattareas?.setImageResource(R.drawable.ic_edit)
        btfloattareas?.setOnClickListener{
            navEditTareas(0)
        }
    }

    override fun setUserVisibleHint(visible: Boolean) {
        super.setUserVisibleHint(visible)
        if (visible && isResumed) {
            onResume()
        }
    }

    private fun navEditTareas(deproyectoid: Int){
        Intent(activity, EditActivity::class.java).run {
            putExtra("DEPROYECTOID", deproyectoid.toString())
            startActivity(this)
        }
    }

    private fun iniRecycler(){
        adaptador = AdapterTareas(clickClosure = {
            navEditTareas(it.deproyectoid.toInt())
        }, eliminaritemClosure = {it, index ->
            eliminarTareas(it.deproyectoid.toInt(), index)
        })
        RcTareas.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        RcTareas.layoutManager = layoutManager
        RcTareas.adapter = adaptador
    }

    private fun consultarTareas(){
        tareasViewModel.getallTareas(rta)
    }

    private fun eliminarTareas(deproyectoid: Int, index : Int){
        alert {
            title = "Esta seguro de eliminar tarea $deproyectoid ?"
            message = "Al eliminar tarea no podra ser recuperada"
            positiveButton ( "Si") {
                tareasViewModel.eliminarTarea(deproyectoid)
                adaptador.deleteItem(index)
            }
            negativeButton ( "No" ) {
            }
        }.show()
    }

    private fun respuestaEliminarTarea(mensaje: String){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }
}
