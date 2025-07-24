package br.com.movieapp.modelDomain

data class MovieSearch( // Essa data class representa a pesquisa de um filme com os seguintes atributos:

    val id: Int,
    val voteAverage: Double = 0.0,
    val imageUrl: String = "",

)
