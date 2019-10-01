package com.example.poryectoinicial.view.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Login.Login
import com.example.poryectoinicial.view.Adapters.ViewPagerAdapter
import com.example.poryectoinicial.view.Fragments.LoginFragment
import com.example.poryectoinicial.view.Fragments.ProyectosFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, LoginFragment.newInstance(), LoginFragment.TAG)
        fragmentTransaction.addToBackStack(TAG)
        fragmentTransaction.commit()

        configurarViewPager()
        tabs_main.setupWithViewPager(viewpager_main)
    }

    fun configurarViewPager(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ProyectosFragment(), "Proyectos")
        adapter.addFragment(ProyectosFragment(), "Tareas")
        viewpager_main.adapter = adapter
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
