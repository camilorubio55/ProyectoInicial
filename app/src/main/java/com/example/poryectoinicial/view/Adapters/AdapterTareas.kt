package com.example.poryectoinicial.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Tareas.Tarea
import com.example.poryectoinicial.view.Interfaces.ClickListener
import com.example.poryectoinicial.view.Interfaces.LongClickListener
import kotlinx.android.synthetic.main.cardview_tareas.view.*

class AdapterTareas: RecyclerView.Adapter<AdapterTareas.ViewHolder>() {

    var items: MutableList<Tarea> = mutableListOf()
    var listener: ClickListener? = null
    var longlistener: LongClickListener? = null

    fun setdata(items: MutableList<Tarea>, listener: ClickListener, longlistener: LongClickListener){
        this.items.clear()
        this.items.addAll(items)
        this.listener = listener
        this.longlistener = longlistener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.cardview_tareas, parent, false)
        val viewHolder = ViewHolder(vista, listener!!, longlistener!!)
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

    class  ViewHolder(vista: View, listener: ClickListener, longlistener: LongClickListener): RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener{
        var titulo: TextView? = null
        var fecha: TextView? = null
        var listener: ClickListener? = null
        var longlistener: LongClickListener? = null

        init {
            titulo = vista.EdTitulo
            fecha = vista.EdFecha
            this.listener = listener
            this.longlistener = longlistener
            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            this.listener?.onClick(p0!!, adapterPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            this.longlistener?.longClick(p0!!,adapterPosition)
            return true
        }
    }
}