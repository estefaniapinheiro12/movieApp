package br.com.movieapp.movie_popula_feature_data_domain_source

import br.com.movieapp.modelDomain.MoviePaging
import br.com.movieapp.paging.MoviePagingSource
import br.com.movieapp.response.MovieResponse

interface MoviePopularRemoteDataSource { // Essa interface é a que vai ser injetada no repository, para que o repository não tenha que conhecer a implementação

    fun getPopularMoviesPagingSource(): MoviePagingSource // Essa função retorna o MoviePagingSource, que é a classe que vai fazer a paginação dos filmes populares

    suspend fun getPopularMovies(page: Int): MoviePaging // Essa função retorna a resposta da API com os filmes populares, ela é chamada pelo MoviePagingSource

}