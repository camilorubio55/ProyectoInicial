package com.example.poryectoinicial.view.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poryectoinicial.R
import com.example.poryectoinicial.view.Adapters.ViewPagerAdapter
import com.example.poryectoinicial.view.Fragments.ProyectosFragment
import com.example.poryectoinicial.view.Fragments.TareasFragment
import kotlinx.android.synthetic.main.activity_base.*

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        configurarViewPager()
        tabs_main.setupWithViewPager(viewpager_main)
    }

    fun configurarViewPager(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ProyectosFragment(), "Proyectos")
        adapter.addFragment(TareasFragment(), "Tareas")
        viewpager_main.adapter = adapter
    }
}
