package com.example.projetoipo.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoipo.R

class AdapterApoio(private val apoioList: List<ItemApoio>, private val context: android.content.Context) : RecyclerView.Adapter<AdapterApoio.ApoioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApoioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_apoio, parent, false)
        return ApoioViewHolder(view)
    }

    override fun getItemCount(): Int {
        return apoioList.size
    }

    override fun onBindViewHolder(holder: ApoioViewHolder, position: Int) {
        val apoioItem = apoioList[position]
        holder.bind(apoioItem)
    }

    override fun onViewRecycled(holder: ApoioViewHolder) {
        holder.ckBox.setOnCheckedChangeListener(null)
        super.onViewRecycled(holder)
    }

    inner class ApoioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ckBox: CheckBox = itemView.findViewById(R.id.ckBox)

        fun bind(apoioItem: ItemApoio) {
            ckBox.text = apoioItem.item
            ckBox.isChecked = apoioItem.isChecked

            // Remover listener antigo antes de definir o estado do checkbox
            ckBox.setOnCheckedChangeListener(null)
            ckBox.isChecked = apoioItem.isChecked

            ckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // Verifica quantos itens estão marcados
                    val checkedCount = apoioList.count { it.isChecked }
                    if (checkedCount >= 5) {
                        // Mostra uma mensagem ao usuário
                        Toast.makeText(context, "Você só pode selecionar até 5 itens.", Toast.LENGTH_SHORT).show()
                        // Desmarca o CheckBox
                        ckBox.isChecked = false
                    } else {
                        apoioItem.isChecked = true
                    }
                } else {
                    apoioItem.isChecked = false
                }
            }
        }
    }
}
