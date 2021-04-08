package com.diegolima.spotifyclone

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.diegolima.spotifyclone.Fragments.Home
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes.*

class Detalhes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes)


        intent.extras?.let {
            var album = it.getString("album")
            var titulos = it.getString("titulos")

            Picasso.get().load(album).into(detalhe_album)
            titulo_album.setText(titulos)
        }


        window.statusBarColor = Color.LTGRAY


        //funciona do mesmo jeito que criado direto no xml pela navigationIcon
        //toobar.setNavigationIcon(getDrawable(R.drawable.ic_back))
        toobar.title = null
        toobar.setNavigationOnClickListener {
            intent = Intent(this, Home::class.java)
            startActivities(intent)
            finish()

        }
    }

    private fun startActivities(intent: Intent) {

    }
}