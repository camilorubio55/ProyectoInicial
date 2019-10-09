package com.example.poryectoinicial.view.Fragments


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
import com.example.poryectoinicial.view.Adapters.AdapterUsuarios
import com.example.poryectoinicial.viewmodel.UsuariosViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_usuarios.*
import org.jetbrains.anko.support.v4.act

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
        usuariosViewModel.getUsuario().observe(this, Observer {
            manejador(it)
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
        /*btfloatusuarios?.setOnLongClickListener {
            //Toast.makeText(this, "hola", Toast.LENGTH_SHORT).show()
        }*/
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

    private fun iniRecycler(){
        adaptador = AdapterUsuarios(clickClosure = {

        })
        RcUsuarios.setHasFixedSize(true)
        /*layoutManager = GridLayoutManager(context)
        RcUsuarios.layoutManager = layoutManager*/
        RcUsuarios.adapter = adaptador
    }

}
