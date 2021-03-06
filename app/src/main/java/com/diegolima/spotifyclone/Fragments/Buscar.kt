package com.diegolima.spotifyclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.spotifyclone.R
import com.diegolima.spotifyclone.model.Secao
import com.diegolima.spotifyclone.model.dados
import kotlinx.android.synthetic.main.fragment_buscar.*
import kotlinx.android.synthetic.main.secao_item.view.*

class Buscar : Fragment() {

    private lateinit var secaoAdapter: SecaoAdapter

    companion object {
        fun newInstance(): Buscar {
            val fragmentHome = Buscar()
            val argumentos = Bundle()
            fragmentHome.arguments = argumentos
            return fragmentHome
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buscar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        secaoAdapter = SecaoAdapter(dados())
        recycler_view_secao.adapter = secaoAdapter
        recycler_view_secao.layoutManager = GridLayoutManager(context, 2)

    }

    private inner class SecaoAdapter(private val secoes: MutableList<Secao>) :
        RecyclerView.Adapter<SecaoHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecaoHolder {
            return SecaoHolder(layoutInflater.inflate(R.layout.secao_item, parent, false))
        }

        override fun getItemCount(): Int = secoes.size

        override fun onBindViewHolder(holder: SecaoHolder, position: Int) {
            val secao = secoes[position]
            holder.bind(secao)
        }
    }

    private inner class SecaoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(secao: Secao) {
            itemView.imagem_secao.setImageResource(secao.secao)
            itemView.nomeSecao.text = secao.nomeSecao
        }
    }

}