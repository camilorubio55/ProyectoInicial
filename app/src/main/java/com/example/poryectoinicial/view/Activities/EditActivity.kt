package com.example.poryectoinicial.view.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.poryectoinicial.R
import com.example.poryectoinicial.view.Fragments.EditProyectosFragment
import com.example.poryectoinicial.view.Fragments.EditTareasFragment
import com.example.poryectoinicial.view.Fragments.EditUsuariosFragment

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        intent.extras.let {
            when (it!!.getInt("ITEM")) {
                1 -> proyectoFragment(it.getString("PROYECTOID", "PROYECTOID"))
                2 -> tareaFragment(it.getString("DEPROYECTOID", "DEPROYECTOID"))
                else -> usuarioFragment(it.getString("USUID", "USUID"))
            }
        }
    }

    private fun proyectoFragment(proyectoid : String){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("PROYECTOID",proyectoid)
        fragmentTransaction.replace(R.id.containeredit, EditProyectosFragment.newInstance(bundle), EditProyectosFragment.TAG)
        fragmentTransaction.addToBackStack(TAG)
        fragmentTransaction.commit()
    }

    private fun tareaFragment(deproyectoid : String){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("DEPROYECTOID",deproyectoid)
        fragmentTransaction.replace(R.id.containeredit, EditTareasFragment.newInstance(bundle), EditTareasFragment.TAG)
        fragmentTransaction.addToBackStack(TAG)
        fragmentTransaction.commit()
    }

    private fun usuarioFragment(usuid : String){
        val bundle = Bundle()
        bundle.putString("USUID",usuid)
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.containeredit, EditUsuariosFragment.newInstance(bundle), EditUsuariosFragment.TAG)
        fragmentTransaction.addToBackStack(TAG)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        const val TAG = "EditActivity"
    }
}
