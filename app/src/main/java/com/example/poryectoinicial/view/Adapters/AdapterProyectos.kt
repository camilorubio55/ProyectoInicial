package com.example.poryectoinicial.view.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Proyecto.Proyecto
import kotlinx.android.synthetic.main.cardview_proyectos.view.*

class AdapterProyectos(context: Context?, items: ArrayList<Proyecto>): RecyclerView.Adapter<AdapterProyectos.ViewHolder>() {

    var items: ArrayList<Proyecto>? = null

    init {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.cardview_proyectos, parent, false)
        val viewHolder = ViewHolder(vista)
        return  viewHolder
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

    class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){

        var vista = vista
        var titulo: TextView? = null
        var descripcion: TextView? = null
        var numero: TextView? = null

        init {
            titulo = vista.EdTitulo
            descripcion = vista.EdDescripcion
            numero = vista.EdNumero
        }
    }
}