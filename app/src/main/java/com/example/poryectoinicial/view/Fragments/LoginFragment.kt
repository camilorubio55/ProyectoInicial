package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.poryectoinicial.R
import com.example.poryectoinicial.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private var loginViewModel: LoginViewModel? = LoginViewModel()
    //private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProviders.of(activity!!).get(LoginViewModel(activity!!.application)::class.java)
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BtIngresar.setOnClickListener {
            val username= EdUsuarioLogin.text.toString()
            val pass = EdPasswordLogin.text.toString()
            if(!username.isNullOrEmpty() && !pass.isNullOrEmpty()){
                loginViewModel?.loginUsuario(username, pass)
                val usuid = loginViewModel?.getUsuid()
                if(usuid != 0)
                    navProyectos(view,usuid)
                else
                    Toast.makeText(context,"No se encontro usuario", Toast.LENGTH_SHORT).show()
            }
            else
                Toast.makeText(context,"Debe llenar los campos", Toast.LENGTH_SHORT).show()
        }
    }

    fun navProyectos(view: View, usuid: Int?){
        val bundle = Bundle()
        bundle.putString("USUID",usuid.toString())
        Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_proyectosFragment, bundle)
    }


}
