package com.diegolima.spotifyclone.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.spotifyclone.R
import com.diegolima.spotifyclone.model.Album
import com.diegolima.spotifyclone.model.Categoria
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.categoria_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*

class Home : Fragment() {

    /**
     * construindo as categorias
     */

    private lateinit var categoriaAdapter: CategoriaAdapter

    companion object {
        fun newInstance(): Home {
            val fragmentHome = Home()
            val argumentos = Bundle()
            fragmentHome.arguments = argumentos
            return fragmentHome

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val categorias: MutableList<Categoria> = ArrayList()
        for (c in 0..4) { //'c' de categorias

            val categoria = Categoria()
            categoria.titulo = "Categoria$c" //modelo de dados criado sendo add o titulo



            val albuns:MutableList<Album> = ArrayList()
            for(a in 0..19) { //'a' de albuns
                val album = Album()
                //album.album = R.drawable.spotify
                albuns.add(album) //modelo de dados criado sendo add o album
            }

            categoria.albuns = albuns
            categorias.add(categoria)

        }

        categoriaAdapter = CategoriaAdapter(categorias)
        recycler_view_categorias.adapter = categoriaAdapter
        recycler_view_categorias.layoutManager = LinearLayoutManager(context)
    }

    private inner class CategoriaAdapter(private val categorias: MutableList<Categoria>) :
        RecyclerView.Adapter<CategoriaHolder>() { //classe interna

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaHolder {
            return CategoriaHolder(layoutInflater.inflate(R.layout.categoria_item, parent, false))
        }

        override fun getItemCount(): Int = categorias.size

        override fun onBindViewHolder(holder: CategoriaHolder, position: Int) {
            val categoria = categorias[position]
            holder.bind(categoria)
        }
    }

    private inner class CategoriaHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoria: Categoria) {
            itemView.text_titulo.text = categoria.titulo
            itemView.recycler_albuns.adapter = AlbunsAdapter(categoria.albuns)
            itemView.recycler_albuns.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    /**
     * construindo os albuns
     */

    private inner class AlbunsAdapter(private val albuns:MutableList<Album>):RecyclerView.Adapter<AlbunsHolders>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbunsHolders {
            return AlbunsHolders(layoutInflater.inflate(R.layout.album_item, parent, false))
        }

        override fun getItemCount(): Int = albuns.size

        override fun onBindViewHolder(holder: AlbunsHolders, position: Int) {
            val album = albuns[position]
            holder.bind(album)
        }
    }

    private inner class AlbunsHolders(itemView:View): RecyclerView.ViewHolder(itemView){
        fun bind(album: Album){
            //itemView.image_album.setImageResource(album.album)
        }
    }

}


