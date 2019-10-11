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
        usuariosViewModel.getinsertarUsuario().observe(this, Observer {
            if(it != null){
                mostrarmensaje(it.mensaje)
                regresaraGrid()
            }
        })
        usuariosViewModel.getactualizarUsuario().observe(this, Observer {
            if(it != null)
                mostrarmensaje(it.mensaje)
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
            if(validarCampos()){
                val objeto = construirobjeto()
                if(rta != 0)
                    actualizarUsuario(objeto)
                else
                    insertarUsuario(objeto)
            }
        }
    }

    private fun consultarUsuarios(usuidsesion: Int){
        usuariosViewModel.getdataUsuario(usuidsesion,rta)
    }

    private fun insertarUsuario(usuario: Usuario){
        usuariosViewModel.insertarUsuario(usuario)
    }

    private fun actualizarUsuario(usuario: Usuario){
        usuariosViewModel.actualizarUsuario(usuario)
    }

    fun mostrarmensaje(mensaje: String){
        Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show()
    }

    private fun regresaraGrid(){
        activity?.onBackPressed()
    }

    private fun limpiarCampos(){
        EdUsuario.setText("")
        EdNombre.setText("")
        EdEmail.setText("")
        EdTelefono.setText("")
        EdContrasena.setText("")
    }

    private fun validarCampos() : Boolean{
        when {
            EdUsuario.text.isNullOrEmpty() -> {
                EdUsuario.error = "El usuario es obligatorio"
                return false
            }
            EdNombre.text.isNullOrEmpty() -> {
                EdNombre.error = "El nombre es obligatorio"
                return false
            }
            EdEmail.text.isNullOrEmpty() -> {
                EdEmail.error = "El email es obligatorio"
                return false
            }
            EdContrasena.text.isNullOrEmpty() -> {
                EdContrasena.error = "La contraseña es obligatoria"
                return false
            }
            EdTelefono.text.isNullOrEmpty() -> {
                EdTelefono.error = "El telefono es obligatorio"
                return false
            }
            else -> return true
        }

    }

    private fun setData(usuario: Usuario){
        EdUsuario.setText(usuario.codigo)
        EdNombre.setText(usuario.nombre)
        EdEmail.setText(usuario.email)
        EdTelefono.setText(usuario.telefono)
        EdContrasena.setText(usuario.contrasena)
        ChkAdmin.isChecked = usuario.rol == "1"
    }

    private fun construirobjeto(): Usuario{
        var checkeado = "0"
        if (ChkAdmin.isChecked)
            checkeado = "1"

        usuario.usuid = rta.toString()
        usuario.usuidsesion = LoginFragment.usuid.toString()
        usuario.codigo = EdUsuario.text.toString()
        usuario.nombre = EdNombre.text.toString()
        usuario.email = EdEmail.text.toString()
        usuario.telefono = EdTelefono.text.toString()
        usuario.contrasena = EdContrasena.text.toString()
        usuario.rol = checkeado

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
