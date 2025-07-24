package br.com.movieapp.modelDomain

data class MovieDetails ( // Essa classe representa os detalhes de um filme com suas propriedades
    val id: Int,
    val title: String,
    val genres: List<String>,
    val overview: String?,
    val backdropPathUrl: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val duration: Int = 0,
    val voteCount: Int = 0
)