package com.example.poryectoinicial.view.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poryectoinicial.view.Adapters.ViewPagerAdapter
import com.example.poryectoinicial.view.Fragments.LoginFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.poryectoinicial.R.layout.activity_main)

        configurarViewPager()
        tabs_main.setupWithViewPager(viewpager_main)
    }

    fun configurarViewPager(){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LoginFragment(), "Proyectos")
        adapter.addFragment(LoginFragment(), "Tareas")

        viewpager_main.adapter = adapter
    }
}
