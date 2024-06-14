package com.example.projetoipo.model



interface OnListClickListener {
    fun onClick(id: Int, type: String)
    fun onLongClick(position: Int, usuario: Usuario)
}