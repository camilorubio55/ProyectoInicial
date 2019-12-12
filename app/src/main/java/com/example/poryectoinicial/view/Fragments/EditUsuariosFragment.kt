package com.example.poryectoinicial.view.Fragments


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.poryectoinicial.model.Usuarios.Usuario
import com.example.poryectoinicial.viewmodel.UsuariosViewModel
import kotlinx.android.synthetic.main.fragment_edit_usuarios.*
import android.util.Patterns
import androidx.core.app.ActivityCompat
import com.example.poryectoinicial.R

class EditUsuariosFragment : Fragment() {

    private lateinit var usuariosViewModel: UsuariosViewModel
    private var usuario = Usuario()
    private var rta = 0
    private val permisoCamara = android.Manifest.permission.CAMERA
    private val permisoEscribir = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    private val permisoLeer = android.Manifest.permission.READ_EXTERNAL_STORAGE
    private val SOLICITUD_TOMAR_FOTO = 1

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
        ImagePerfil.setOnCreateContextMenuListener{ contextMenu, view, contextMenuInfo ->
            val galeria = contextMenu.add("Galeria")
            val tomarfoto = contextMenu.add("Tomar foto")
            galeria.setOnMenuItemClickListener {
                pedirPermisos()
                true
            }
            tomarfoto.setOnMenuItemClickListener {
                Toast.makeText(context, "Camara", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }

    private fun pedirPermisos(){
        val proveerContexto = ActivityCompat.shouldShowRequestPermissionRationale(activity!!, permisoCamara)
        if (proveerContexto)
            solicitudPermisos()
        else
            solicitudPermisos()
    }

    private fun solicitudPermisos(){
        requestPermissions(arrayOf(permisoCamara,permisoEscribir,permisoLeer), SOLICITUD_TOMAR_FOTO)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            SOLICITUD_TOMAR_FOTO -> {
                if(grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[2] == PackageManager.PERMISSION_GRANTED ){
                    dispararIntentFoto()
                }
                else
                    Toast.makeText(context, "No se encontró permiso para acceder a la camara", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun dispararIntentFoto(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(activity!!.packageManager) != null)
            startActivityForResult(intent, SOLICITUD_TOMAR_FOTO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            SOLICITUD_TOMAR_FOTO -> {
                if(resultCode == Activity.RESULT_OK){
                    val extras = data?.extras
                    val imageBitmap = extras?.get("data") as Bitmap
                    ImagePerfil.setImageBitmap(imageBitmap)
                }
                else
                    Toast.makeText(context, "No se tomo foto", Toast.LENGTH_SHORT).show()
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

    fun validarEmail(email: String): Boolean{
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
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
            !validarEmail(EdEmail.text.toString()) -> {
                EdEmail.error = "No es un email correcto"
                return false
            }
            EdContrasena.text.isNullOrEmpty() -> {
                EdErrorContrasena.error = "La contraseña es obligatoria"
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
        SwitchInactivo.isChecked = usuario.inactivo == "1"
    }

    private fun construirobjeto(): Usuario{
        var checkeado = "0"
        if(ChkAdmin.isChecked)
            checkeado = "1"

        var inactivo = "0"
        if(SwitchInactivo.isChecked)
            inactivo = "1"

        usuario.usuid = rta.toString()
        usuario.usuidsesion = LoginFragment.usuid.toString()
        usuario.codigo = EdUsuario.text.toString()
        usuario.nombre = EdNombre.text.toString()
        usuario.email = EdEmail.text.toString()
        usuario.telefono = EdTelefono.text.toString()
        usuario.contrasena = EdContrasena.text.toString()
        usuario.rol = checkeado
        usuario.inactivo = inactivo
        //usuario.imagen = ImagePerfil.drawable as ByteArray

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
