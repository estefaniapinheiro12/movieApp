package br.com.movieapp.remote

import br.com.movieapp.response.MovieDetailResponse
import br.com.movieapp.response.MovieResponse
import br.com.movieapp.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// Interface serve como um contrato para a implementação de serviços de rede
interface MovieService {

    @GET("movie/popular") // Endpoint da API para buscar filmes populares
    suspend fun getPopularMovies( // Método para buscar filmes populares
        @Query("page") page: Int,
    ): MovieResponse// O movieResponse é um modelo de dados que representa a resposta da API, ele retorna uma lista de filmes populares

    @GET("search/multi") // Endpoint da API para buscar filmes e séries
    suspend fun searchMovie(// Método para buscar filmes
        @Query("page") page: Int, // Número da página
        @Query("query") query: String, // Termo de pesquisa
    ):SearchResponse // O SearchResponse é um modelo de dados que representa a resposta da API, ele retorna uma lista de filmes que correspondem ao termo de pesquisa

    @GET("movie/{movie_id}") // Endpoint da API para buscar detalhes de um filme específico
    suspend fun getMovie( // Método para buscar detalhes de um filme
        @Path("movie_id") movieId: Int, // ID do filme
    ):MovieDetailResponse // O MovieDetailResponse é um modelo de dados que representa a resposta da API, ele retorna os detalhes do filme

    @GET("movie/{movie_id}/similar") // Endpoint da API para buscar filmes semelhantes
    suspend fun getMoviesSimilar( // Método para buscar filmes semelhantes
        @Path("movie_id") movieId: Int, // ID do filme
        @Query("page") page: Int, // Número da página
    ):MovieResponse // O MovieResponse é um modelo de dados que representa a resposta da API, ele retorna uma lista de filmes semelhantes

}