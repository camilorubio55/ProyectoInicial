package com.example.poryectoinicial


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_loading_dialog.*

class LoadingDialogFragment : DialogFragment() {

    internal var message = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        GlideApp.with(this).load(R.drawable.wait).into(iconLoading)
    }

    companion object{
        const val  TAG = "LoadingDialogFragment"

        fun newInstance(message: String): LoadingDialogFragment{
            val dialog = LoadingDialogFragment()
            dialog.message = message
            return dialog
        }
    }

}
