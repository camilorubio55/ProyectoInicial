package com.example.poryectoinicial.view.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.view.Interfaces.ClickListener
import kotlinx.android.synthetic.main.cardview_proyectos.view.*

class AdapterProyectos: RecyclerView.Adapter<AdapterProyectos.ViewHolder>() {

    var items: MutableList<Proyecto> = mutableListOf()
    var listener: ClickListener? = null

    fun setData(items: MutableList<Proyecto>, listener: ClickListener){
        this.items.clear()
        this.items.addAll(items)
        //this.items = items
        this.listener = listener
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.cardview_proyectos, parent, false)
        val viewHolder = ViewHolder(vista, listener!!)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.titulo?.text = item?.titulo
        holder.descripcion?.text = item?.descripcion
        holder.numero?.text = item?.proyectoid
    }

    class ViewHolder(vista: View, listener: ClickListener): RecyclerView.ViewHolder(vista), View.OnClickListener{

        var vista = vista
        var titulo: TextView? = null
        var descripcion: TextView? = null
        var numero: TextView? = null
        var listener: ClickListener? = null

        init {
            titulo = vista.EdTitulo
            descripcion = vista.EdDescripcion
            numero = vista.EdNumero
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            this.listener?.onClick(p0!!, adapterPosition)
        }

    }
}