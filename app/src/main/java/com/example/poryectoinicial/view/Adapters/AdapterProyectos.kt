package com.example.poryectoinicial.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Proyecto.Proyecto
import com.example.poryectoinicial.view.Interfaces.ClickListener
import com.example.poryectoinicial.view.Interfaces.LongClickListener
import kotlinx.android.synthetic.main.cardview_proyectos.view.*

class AdapterProyectos(
    val clickClosure: (Proyecto) -> Unit,
    val eliminaritemClosure: (Proyecto, Int) -> Unit
): RecyclerView.Adapter<AdapterProyectos.ViewHolder>() {

    var items: MutableList<Proyecto> = mutableListOf()

    fun setData(items: List<Proyecto>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearData(){
        this.items.clear()
    }

    fun deleteItem(index: Int){
        this.items.removeAt(index)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.cardview_proyectos, parent, false)
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
        fun bind(item: Proyecto, position: Int){
            itemView.EdTitulo?.text = item.titulo
            itemView.EdDescripcion?.text = item.descripcion
            itemView.EdNumero?.text = item.proyectoid

            itemView.setOnClickListener {
                clickClosure(item)
            }

            itemView.setOnLongClickListener {
                eliminaritemClosure(item,position)
                true
            }
        }

    }
}