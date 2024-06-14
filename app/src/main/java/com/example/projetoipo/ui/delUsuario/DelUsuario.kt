package com.example.projetoipo.ui.delUsuario

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetoipo.R
import com.example.projetoipo.databinding.FragmentDelUsuarioBinding
import com.example.projetoipo.model.App
import com.example.projetoipo.model.AdapterMain
import com.example.projetoipo.model.OnListClickListener
import com.example.projetoipo.model.Usuario

class DelUsuario : Fragment(), OnListClickListener {
    private lateinit var binding: FragmentDelUsuarioBinding
    private lateinit var lista: MutableList<Usuario>
    private lateinit var adapter: AdapterMain
    private lateinit var rv: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDelUsuarioBinding.inflate(inflater,container,false)
     return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.lista = mutableListOf<Usuario>()

        adapter = AdapterMain(lista,this)

        rv =  binding.rvRegistro
        rv.layoutManager = LinearLayoutManager((requireContext()))
        rv.adapter = adapter

        Thread{
            val app = requireActivity().application as App
            val dao = app.db.dao()
            val resultado = dao.buscarRegistro()

            requireActivity().runOnUiThread(){
                lista.addAll(resultado)
                adapter.notifyDataSetChanged()
            }
        }.start()

    }


    override fun onClick(id: Int, type: String) {
    }

    override fun onLongClick(position: Int, usuario: Usuario) {

        AlertDialog.Builder(requireContext())
            .setMessage(getString(R.string.msg_deletar))
            .setNegativeButton(android.R.string.cancel){
                    dialog,which->
            }
            .setPositiveButton(android.R.string.ok){
                    dialog,which->
                Thread{
                    val app = requireActivity().application as App
                    val dao = app.db.dao()
                    val resultado = dao.deletar(usuario)

                    if (resultado > 0){
                        requireActivity().runOnUiThread(){
                            lista.removeAt(position)
                            adapter.notifyItemRemoved(position)
                        }
                    }

                }.start()
            }
            .create()
            .show()
    }

}