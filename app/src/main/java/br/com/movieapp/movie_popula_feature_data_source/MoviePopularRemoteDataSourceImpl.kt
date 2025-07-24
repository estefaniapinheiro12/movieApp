package br.com.movieapp.movie_popula_feature_data_source

import br.com.movieapp.modelDomain.MoviePaging
import br.com.movieapp.movie_popula_feature_data_domain_source.MoviePopularRemoteDataSource
import br.com.movieapp.movie_popula_feature_data_mapper.toMovie
import br.com.movieapp.paging.MoviePagingSource
import br.com.movieapp.remote.MovieService
import javax.inject.Inject

class MoviePopularRemoteDataSourceImpl @Inject constructor( // essa classe serve para implementar a interface
    private val service: MovieService // a classe MovieService é uma interface que define os métodos de rede
): MoviePopularRemoteDataSource { // a classe MoviePopularRemoteDataSourceImpl implementa a interface MoviePopularRemoteDataSource

    override fun getPopularMoviesPagingSource(): MoviePagingSource { // essa função retorna um objeto do tipo MoviePagingSource
        return MoviePagingSource(this) // retorna um objeto do tipo MoviePagingSource, passando a si mesmo como parâmetro
    }

    override suspend fun getPopularMovies(page: Int): MoviePaging { // essa função retorna um objeto do tipo MovieResponse
        val response = service.getPopularMovies(page)
        return MoviePaging(
            page = response.page,
            totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.movieResults.map { it.toMovie() // converte a resposta da API para o modelo de domínio
            }
        )
    }
}

/*
📦 O que essa classe faz?

Imagina que o app quer buscar filmes famosos na internet.
Essa classe aqui é como um mensageiro do app, que sabe falar com a internet pra pegar essas informações.
🧒 Explicando como se fosse uma historinha:

O app quer saber:

“Ei, internet, quais são os filmes populares?”

Mas o app não sabe falar com a internet direto.
Então ele pede ajuda pra essa classe: MoviePopularRemoteDataSourceImpl.

Essa classe vai até um lugar chamado MovieService (que é quem realmente fala com a internet) e pergunta:

“Me dá os filmes da página número X?”

Depois, ela devolve a resposta pro app num pacotinho chamado MovieResponse.

🚪 Essa classe tem duas portas (funções):

getPopularMoviesPagingSource()
👉 Cria um pegador de filmes página por página (tipo uma colherzinha que pega um pedacinho de sopa por vez).
Ela devolve um MoviePagingSource, que é quem vai puxar os filmes da internet aos pouquinhos.

getPopularMovies(page: Int)
👉 Vai até a internet e diz:

“Me dá os filmes da página 1 (ou 2, ou 3...)”
Usa o MovieService pra isso.

🧠 Em resumo bem simples:

Essa classe:

É o tradutor e mensageiro do app. Ela sabe como falar com a internet pra buscar os filmes populares,
e também ajuda a montar as páginas com os filmes, uma de cada vez.*/
