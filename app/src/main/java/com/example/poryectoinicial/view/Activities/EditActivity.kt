package com.example.poryectoinicial.view.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.poryectoinicial.R
import com.example.poryectoinicial.view.Fragments.EditProyectosFragment

class EditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        intent.extras.let {
            decisionFragment(it!!.getString("PROYECTOID", "PROYECTOID"))
        }
    }

    fun decisionFragment(proyectoid : String){
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        val bundle = Bundle()
        bundle.putString("PROYECTOID",proyectoid)
        fragmentTransaction.replace(R.id.containeredit, EditProyectosFragment.newInstance(bundle), EditProyectosFragment.TAG)
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
