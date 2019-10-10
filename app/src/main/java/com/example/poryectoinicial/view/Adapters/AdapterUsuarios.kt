package com.example.poryectoinicial.view.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.poryectoinicial.R
import com.example.poryectoinicial.model.Usuarios.Usuario
import kotlinx.android.synthetic.main.cardview_usuarios.view.*

class AdapterUsuarios(val clickClosure: (Usuario) -> Unit, val longClickClosure: (Usuario, index: Int) -> Unit): RecyclerView.Adapter<AdapterUsuarios.ViewHolder>() {

    private var items: MutableList<Usuario> = mutableListOf()

    fun setData(items: MutableList<Usuario>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearData(){
        this.items.clear()
    }

    fun deleteItem(item: Int){
        this.items.removeAt(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.cardview_usuarios, parent, false)
        return ViewHolder(vista)
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Usuario, index: Int){
            itemView.EdNomUsuario.text = item.nombre

            itemView.setOnClickListener {
                clickClosure(item)
            }

            itemView.setOnLongClickListener {
                longClickClosure(item,index)
                true
            }
        }
    }
}