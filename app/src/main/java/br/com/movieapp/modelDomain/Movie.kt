package br.com.movieapp.modelDomain


data class Movie( // Essa classe representa um filme com suas propriedades

    val id: Int,
    val title: String,
    val voteAverage: Double = 0.0,
    val imageUrl: String = ""

)
