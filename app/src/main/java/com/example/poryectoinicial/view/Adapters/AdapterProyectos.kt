package com.example.poryectoinicial.view.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Proyecto.Proyecto

class AdapterProyectos(items: ArrayList<Proyecto>): RecyclerView.Adapter<AdapterProyectos.ViewHolder>() {

    var items: ArrayList<Proyecto>? = null

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProyectos.ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.cardview_proyectos, parent, false)
        //val viewHolder
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: AdapterProyectos.ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(){


    }
}