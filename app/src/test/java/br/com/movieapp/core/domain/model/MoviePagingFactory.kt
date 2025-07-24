package br.com.movieapp.core.domain.model

import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.modelDomain.MoviePaging

class MoviePagingFactory {

    fun create( ) = MoviePaging(
        page = 1,
        totalPages = 1,
        totalResults = 2,
        movies = listOf(
            Movie (
                id = 1,
                title = "Avengers: Endgame",
                voteAverage = 8.0,
                imageUrl = "url1"
            ),
            Movie(
                id = 2,
                title = "John Wick: Chapter 3 - Parabellum",
                voteAverage = 8.0,
                imageUrl = "url2"
            )
        )
    )
}