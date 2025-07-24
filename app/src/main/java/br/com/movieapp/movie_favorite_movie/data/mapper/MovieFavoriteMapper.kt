package br.com.movieapp.movie_favorite_movie.data.mapper

import br.com.movieapp.core.data.local.entity.MovieEntity
import br.com.movieapp.modelDomain.Movie

fun List<MovieEntity>.toMovies() = map { movieEntity -> // fun List<MovieEntity>.toMovies(): função
    // de extensão para converter uma lista de MovieEntity em uma lista de Movie.
    // map { movieEntity -> ... }: percorre cada item da lista e transforma. Dentro do map, ele cria um objeto Movie com:
    Movie(
        id = movieEntity.movieId,  // id = movieEntity.movieId: o ID da entidade vira o ID do domínio.
        title = movieEntity.title, // title = movieEntity.title: o título é copiado.
        imageUrl = movieEntity.imageUrl // imageUrl = movieEntity.imageUrl: o caminho da imagem é copiado também.
    )
}
fun Movie.toMovieEntity(): MovieEntity { // fun Movie.toMovieEntity(): função de extensão que
    // converte um objeto do tipo Movie (domínio) para MovieEntity (entidade do banco).
    // Cria um novo MovieEntity com os dados do Movie:
    return MovieEntity(
        movieId = id, // movieId = id: mapeia o ID.
        title = title, // title = title: mapeia o título.
        imageUrl = imageUrl // imageUrl = imageUrl: mapeia a URL da imagem.
    )
}