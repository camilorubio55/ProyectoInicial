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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager

import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Usuarios.Usuario
import com.example.poryectoinicial.view.Activities.EditActivity
import com.example.poryectoinicial.view.Adapters.AdapterUsuarios
import com.example.poryectoinicial.viewmodel.UsuariosViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_usuarios.*
import org.jetbrains.anko.support.v4.act
import org.jetbrains.anko.support.v4.alert

class UsuariosFragment : Fragment() {

    private lateinit var usuariosViewModel: UsuariosViewModel
    private lateinit var adaptador: AdapterUsuarios
    private var rta = 0

    private fun manejador(lista: MutableList<Usuario>){
        adaptador.clearData()
        adaptador.setData(lista)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuariosViewModel = ViewModelProviders.of(activity!!).get(UsuariosViewModel(activity!!.application)::class.java)
        usuariosViewModel.getUsuarios().observe(this, Observer {
            if(it != null)
                manejador(it)
        })
        usuariosViewModel.geteliminarusuario().observe(this, Observer {
            if(it != null)
                mostrarmensaje(it.mensaje)
        })
        usuariosViewModel.getinsertarUsuario().observe(this, Observer {
            if (it != null)
                mostrarmensaje(it.mensaje)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_usuarios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rta = LoginFragment.usuid
        iniRecycler()
        SwRefreshUsuarios.setOnRefreshListener {
            SwRefreshUsuarios.isRefreshing = true
            consultarusuarios(rta)
            SwRefreshUsuarios.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        consultarusuarios(rta)
        if(!userVisibleHint){
            return
        }
        val btfloatusuarios = activity?.findViewById<FloatingActionButton>(R.id.BtFloatAction)
        btfloatusuarios?.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.colorFloatActionBUsuarios))
        btfloatusuarios?.setImageResource(R.drawable.ic_followers)
        btfloatusuarios?.setOnClickListener{
            navEditUsuario(0)
        }
    }

    override fun setUserVisibleHint(visible: Boolean) {
        super.setUserVisibleHint(visible)
        if (visible && isResumed) {
            onResume()
        }
    }

    private fun consultarusuarios(usuid: Int){
        usuariosViewModel.getallUsuarios(usuid)
    }

    private fun eliminarusuario(usuid: Int, index: Int){
        alert {
            title = "Esta seguro de eliminar usuario $usuid ?"
            message = "Al eliminar usuario no podra ser recuperada"
            positiveButton ( "Si") {
                usuariosViewModel.eliminarUsuario(rta, usuid)
                adaptador.deleteItem(index)
            }
            negativeButton ( "No" ) {
            }
        }.show()
    }

    fun navEditUsuario(usuid: Int){
        Intent(activity, EditActivity::class.java).run {
            putExtra("ITEM", 3)
            putExtra("USUID", usuid.toString())
            startActivity(this)
        }
    }

    fun mostrarmensaje(mensaje: String){
        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
    }

    private fun iniRecycler(){
        adaptador = AdapterUsuarios(clickClosure = {
            navEditUsuario(it.usuid.toInt())
        }, longClickClosure = {it, index ->
            eliminarusuario(it.usuid.toInt(), index)
        })
        RcUsuarios.setHasFixedSize(true)
        RcUsuarios.adapter = adaptador
    }
}
