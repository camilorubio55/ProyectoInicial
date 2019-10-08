package com.example.poryectoinicial.view.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.poryectoinicial.R
import com.example.poryectoinicial.view.Fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container, LoginFragment.newInstance(), LoginFragment.TAG)
        fragmentTransaction.addToBackStack(TAG)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        finish()
    }

    companion object {
        const val TAG = "MainActivity"
    }
}
