package br.com.movieapp.core.domain.model

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.modelDomain.MovieDetails


class MovieDetailsFactory {
    fun create(poster: Poster) = when(poster) {
        Poster.Avengers -> {
            MovieDetails (
                id = 1,
                title = "Avengers: Endgame",
                voteAverage = 7.1,
                genres = listOf("Aventura", "Ação"),
                overview = LoremIpsum(100).values.first(),
                backdropPathUrl = "Url",
                releaseDate = "2023-10-10",
                duration = 143,
                voteCount = 7
            )
        }
        Poster.JohnWick -> {
            MovieDetails (
                id = 1,
                title = "John Wick: Chapter 4",
                voteAverage = 8.1,
                genres = listOf("Aventura", "Ação"),
                overview = LoremIpsum(100).values.first(),
                backdropPathUrl = "Url",
                releaseDate = "2023-11-10",
                duration = 123,
                voteCount = 9
            )
        }
    }
    sealed class Poster {
        object Avengers: Poster()
        object JohnWick: Poster()
    }
}