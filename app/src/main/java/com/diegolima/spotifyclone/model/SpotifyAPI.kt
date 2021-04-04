package com.diegolima.spotifyclone.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SpotifyAPI {
    @GET("pd.json.json?alt=media&token=11c94754-77a4-4f7f-9e2e-516ac4a12636")
    fun ListCategorias():Call<Categorias>
}

fun retrofit(): Retrofit = Retrofit.Builder()
    .baseUrl("https://firebasestorage.googleapis.com/v0/b/spotify-86390.appspot.com/o/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()