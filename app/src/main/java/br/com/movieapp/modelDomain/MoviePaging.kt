package br.com.movieapp.modelDomain

data class MoviePaging(

    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movies: List<Movie>

)
