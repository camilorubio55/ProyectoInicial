package com.example.poryectoinicial.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Tareas.Tarea
import kotlinx.android.synthetic.main.cardview_tareas.view.*

class AdapterTareas: RecyclerView.Adapter<AdapterTareas.ViewHolder>() {

    var items: MutableList<Tarea> = mutableListOf()

    fun setdata(items: MutableList<Tarea>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.cardview_tareas, parent, false)
        val viewHolder = ViewHolder(vista)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.titulo?.text = item.titulo
        holder.fecha?.text = item.fecha
    }

    class  ViewHolder(vista: View): RecyclerView.ViewHolder(vista){
        var titulo: TextView? = null
        var fecha: TextView? = null

        init {
            titulo = vista.EdTitulo
            fecha = vista.EdFecha
        }
    }
}