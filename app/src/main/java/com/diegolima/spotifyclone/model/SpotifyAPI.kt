package com.diegolima.spotifyclone.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SpotifyAPI {
    @GET("nelson.pd.json.json?alt=media&token=95d79652-a874-4711-8f39-5d86b7372aa5")
    fun ListCategorias():Call<Categorias>
}

fun retrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://firebasestorage.googleapis.com/v0/b/spotify-86390.appspot.com/o/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()