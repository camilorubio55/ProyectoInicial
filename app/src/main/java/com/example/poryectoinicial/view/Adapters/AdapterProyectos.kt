package com.example.poryectoinicial.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.view.Interfaces.ClickListener
import com.example.poryectoinicial.view.Interfaces.LongClickListener
import kotlinx.android.synthetic.main.cardview_proyectos.view.*

class AdapterProyectos: RecyclerView.Adapter<AdapterProyectos.ViewHolder>() {

    var items: MutableList<Proyecto> = mutableListOf()
    var listener: ClickListener? = null
    var longlistener: LongClickListener? = null

    fun setData(items: List<Proyecto>, listener: ClickListener, longlistener: LongClickListener){
        //this.items.clear()
        this.items.addAll(items)
        this.listener = listener
        this.longlistener = longlistener
        notifyDataSetChanged()
    }

    fun clearData(){
        this.items.clear()
    }

    fun deleteItem(index: Int){
        this.items.removeAt(index)
        notifyItemRemoved(index)
        //notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.cardview_proyectos, parent, false)
        val viewHolder = ViewHolder(vista, listener!!, longlistener!!)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titulo?.text = item.titulo
        holder.descripcion?.text = item.descripcion
        holder.numero?.text = item.proyectoid
    }

    class ViewHolder(vista: View, listener: ClickListener, longlistener: LongClickListener): RecyclerView.ViewHolder(vista), View.OnClickListener, View.OnLongClickListener{

        var titulo: TextView? = null
        var descripcion: TextView? = null
        var numero: TextView? = null
        var listener: ClickListener? = null
        var longlistener: LongClickListener? = null

        init {
            titulo = vista.EdTitulo
            descripcion = vista.EdDescripcion
            numero = vista.EdNumero
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