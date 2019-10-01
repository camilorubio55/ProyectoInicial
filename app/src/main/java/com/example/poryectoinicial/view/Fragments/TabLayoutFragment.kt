package com.example.poryectoinicial.view.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.poryectoinicial.R

class TabLayoutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        habilitarFloatActionB()
    }

    private fun habilitarFloatActionB(){
        val btfloat = activity?.findViewById<View>(R.id.BtFloatAction)
        val tab = activity?.findViewById<View>(R.id.tabs_main)
        val vpager = activity?.findViewById<View>(R.id.viewpager_main)
        val appB = activity?.findViewById<View>(R.id.app_bar)
        btfloat?.visibility = View.VISIBLE
        appB?.visibility = View.VISIBLE
        tab?.visibility = View.VISIBLE
        vpager?.visibility = View.VISIBLE
    }

    companion object {
        var usuid: Int = 0
        const val TAG = "TabLayoutFragment"
        fun newInstance(bundle: Bundle? = null): TabLayoutFragment {
            val fragment = TabLayoutFragment()
            if (bundle != null) {
                fragment.arguments = bundle
            }
            return fragment
        }
    }
}
