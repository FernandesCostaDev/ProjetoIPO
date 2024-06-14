package com.example.projetoipo.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoipo.R
import com.example.projetoipo.ui.delUsuario.DelUsuario

class AdapterMain(private val listaNomes: List<Usuario>, private val listener: DelUsuario): RecyclerView.Adapter<AdapterMain.NomesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NomesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nome, parent, false)
        return NomesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaNomes.size
    }

    override fun onBindViewHolder(holder: NomesViewHolder, position: Int) {
        val itemCurrent = listaNomes[position]
        holder.bind(itemCurrent)
    }

    inner class NomesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Usuario) {
            /*Usado para inflar o android.R.layout.simple_list_item_1
            val tvNome = itemView as TextView
            val nome = item.nome
            tvNome.text = nome*/

            val tvNome: TextView = itemView.findViewById(R.id.txt_nomes)
            tvNome.setText(item.nome)

            val imgDelete: ImageView = itemView.findViewById(R.id.img_delete)

            imgDelete.setOnLongClickListener {
                listener.onLongClick(adapterPosition,item)
                true
            }

            tvNome.setOnClickListener {
                listener.onClick(item.id, item.nome)
            }
        }
    }
}










