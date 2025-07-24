package br.com.movieapp.core.domain.model

import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.modelDomain.MoviePaging
import br.com.movieapp.modelDomain.MovieSearch
import br.com.movieapp.modelDomain.MovieSearchPaging

class MovieSearchPagingFactory {

    fun create( ) = MovieSearchPaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            MovieSearch (
                id = 1,
                voteAverage = 8.0,
                imageUrl = "url1"
            ),
            MovieSearch(
                id = 2,
                voteAverage = 8.0,
                imageUrl = "url2"
            )
        )
    )
}