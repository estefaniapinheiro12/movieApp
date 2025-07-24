package br.com.movieapp.movie_detail_feature.data.mapper

import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.modelDomain.MovieDetails

fun MovieDetails.toMovie(): Movie { // extensão de função para converter MovieDetails em Movie
    return Movie( // cria um novo objeto Movie
        id = id,
        title = title,
        imageUrl = backdropPathUrl.toString(),
        voteAverage = voteAverage
    )
}