package br.com.movieapp.modelDomain

data class MovieSearchPaging (
    val page: Int,
    val totalPages: Int,
    val totalResults: Int,
    val movies: List<MovieSearch>
)