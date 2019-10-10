package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Usuarios.Usuario
import com.example.poryectoinicial.viewmodel.UsuariosViewModel
import kotlinx.android.synthetic.main.fragment_edit_usuarios.*

class EditUsuariosFragment : Fragment() {

    private lateinit var usuariosViewModel: UsuariosViewModel
    private var usuario = Usuario()
    private var rta = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usuariosViewModel = ViewModelProviders.of(activity!!).get(UsuariosViewModel(activity!!.application)::class.java)
        usuariosViewModel.getUsuario().observe(this, Observer {
            if(it != null)
                setData(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_usuarios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rta = arguments!!.getString("USUID")!!.toInt()
        val usuidsesion = LoginFragment.usuid
        if(rta != 0)
            consultarUsuarios(usuidsesion)
        else
            limpiarCampos()

        BtGuardarUsuario.setOnClickListener {

        }
    }

    fun consultarUsuarios(usuidsesion: Int){
        usuariosViewModel.getdataUsuario(usuidsesion,rta)
    }

    fun limpiarCampos(){
        EdUsuario.setText("")
        EdNombre.setText("")
        EdEmail.setText("")
        EdTelefono.setText("")
        EdContrasena.setText("")
    }

    fun setData(usuario: Usuario){
        EdUsuario.setText(usuario.codigo)
        EdNombre.setText(usuario.nombre)
        EdEmail.setText(usuario.email)
        EdTelefono.setText(usuario.telefono)
        EdContrasena.setText(usuario.contrasena)
    }

    fun construirobjeto(): Usuario{
        usuario.usuidsesion = LoginFragment.usuid.toString()
        usuario.codigo = EdUsuario.text.toString()
        usuario.nombre = EdNombre.text.toString()
        usuario.email = EdEmail.text.toString()
        usuario.telefono = EdTelefono.text.toString()
        usuario.contrasena = EdContrasena.text.toString()
        usuario.rol = ChkAdmin.text.toString()

        return  usuario
    }

    companion object{
        const val TAG = "EditUsuariosFragment"

        fun newInstance(bundle: Bundle? = null): EditUsuariosFragment{
            val fragment = EditUsuariosFragment()
            if(bundle != null){
                fragment.arguments = bundle
            }
            return fragment
        }
    }

}
