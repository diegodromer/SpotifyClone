package com.diegolima.spotifyclone.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diegolima.spotifyclone.Detalhes
import com.diegolima.spotifyclone.R
import com.diegolima.spotifyclone.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.album_item.view.*
import kotlinx.android.synthetic.main.categoria_item.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        val categorias = arrayListOf<Categoria>()
        categoriaAdapter = CategoriaAdapter(categorias)
        recycler_view_categorias.adapter = categoriaAdapter
        recycler_view_categorias.layoutManager = LinearLayoutManager(context)

        retrofit().create(SpotifyAPI::class.java)
            .ListCategorias()
            .enqueue(object : Callback<Categorias> {
                override fun onFailure(call: Call<Categorias>, t: Throwable) {
                    Toast.makeText(context, "Erro no servidor", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            categoriaAdapter.categorias.clear()
                            categoriaAdapter.categorias.addAll(it.categorias)
                            categoriaAdapter.notifyDataSetChanged()
                        }
                    }
                }
            })

    }

    private inner class CategoriaAdapter(internal val categorias: MutableList<Categoria>) : //internal - fun de uso interna apenas
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
            itemView.recycler_albuns.adapter = AlbunsAdapter(categoria.albuns) { album ->

                val intent = Intent(context, Detalhes::class.java)
                intent.putExtra("album", album.album)
                intent.putExtra("titulos", titulos[album.id])
                startActivity(intent)
            }
            itemView.recycler_albuns.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
    }

    /**
     * construindo os albuns
     */

    private inner class AlbunsAdapter(
        private val albuns: List<Album>,
        private val listener: ((Album) -> Unit)?
    ) : RecyclerView.Adapter<AlbunsHolders>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbunsHolders = AlbunsHolders(
            layoutInflater.inflate(
                R.layout.album_item, parent, false
            ),
            listener
        )

        override fun getItemCount(): Int = albuns.size

        override fun onBindViewHolder(holder: AlbunsHolders, position: Int) {
            val album = albuns[position]
            holder.bind(album)
        }
    }

    private inner class AlbunsHolders(itemView: View, val onClick: ((Album) -> Unit)?) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(album: Album) {
            Picasso.get().load(album.album).placeholder(R.drawable.placeholder).fit()
                .into(itemView.image_album) // fit() -> para as imagens se alinhar corretamente no quadrado, pra nao ficar uma imagem nem maior, nem menor que a outra
            itemView.image_album.setOnClickListener {
                onClick?.invoke(album)
            }
        }
    }

}


