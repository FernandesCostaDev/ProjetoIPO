package com.example.projetoipo.model


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoipo.R
import com.example.projetoipo.Recursos


class AdapterViatura(private val listaViaturas: List<Viaturas>, private val listener: Recursos, private val txtEmpenhoVtr: TextView): RecyclerView.Adapter<AdapterViatura.ViaturaViewHolder>() {
    private val selectedItems = mutableSetOf<Viaturas>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViaturaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_viatura, parent, false)
        return ViaturaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listaViaturas.size
    }

    override fun onBindViewHolder(holder: ViaturaViewHolder, position: Int) {
        val itemCurrent = listaViaturas[position]
        holder.bind(itemCurrent)
    }

    override fun onViewRecycled(holder: AdapterViatura.ViaturaViewHolder) {
        holder.checkBox.setOnCheckedChangeListener(null)
        super.onViewRecycled(holder)
    }

    inner class ViaturaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
        val imgDelete: ImageView = itemView.findViewById(R.id.img_del)

        fun bind(item: Viaturas) {
            checkBox.text = item.prefixo
            checkBox.isChecked = selectedItems.contains(item)

            // Listener para o CheckBox
            checkBox.setOnCheckedChangeListener(null) //Limpa qualquer listener anterior

            checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedItems.add(item)

                } else {
                    selectedItems.remove(item)
                }

                updateTextView()
            }

            imgDelete.setOnLongClickListener {
                listener.onLongClick(adapterPosition,item)
                true
            }
        }
    }

    fun getSelectedItems(): List<Viaturas> {
        return selectedItems.toList()
    }

    private fun updateTextView() {
        val selectedText = selectedItems.joinToString(", ") { it.prefixo }
        txtEmpenhoVtr.text = selectedText
    }
}