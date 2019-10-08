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
import kotlinx.android.synthetic.main.cardview_proyectos.view.*
import kotlinx.android.synthetic.main.cardview_tareas.view.*
import kotlinx.android.synthetic.main.cardview_tareas.view.EdTitulo

class AdapterTareas(
    val clickClosure: (Tarea) -> Unit,
    val eliminaritemClosure: (Tarea, Int) -> Unit
): RecyclerView.Adapter<AdapterTareas.ViewHolder>() {

    var items: MutableList<Tarea> = mutableListOf()

    fun setdata(items: MutableList<Tarea>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun cleardata(){
        this.items.clear()
    }

    fun deleteItem(index: Int){
        this.items.removeAt(index)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.cardview_tareas, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Tarea, position: Int){
            itemView.EdTitulo.text = item.titulo
            itemView.EdFecha.text = item.fecha

            itemView.setOnClickListener {
                clickClosure(item)
            }

            itemView.setOnLongClickListener {
                eliminaritemClosure(item, position)
                true
            }
        }
    }
}