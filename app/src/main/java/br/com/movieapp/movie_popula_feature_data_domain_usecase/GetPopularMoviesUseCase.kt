package br.com.movieapp.movie_popula_feature_data_domain_usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_popula_feature_data_domain_repository.MoviePopularRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface GetPopularMoviesUseCase { // Interface que define o caso de uso para obter filmes populares
    operator fun invoke(params: Params): Flow<PagingData<Movie>> // Função suspensa que retorna um fluxo de dados paginados de filmes
    data class Params(val pagingConfig: PagingConfig)
}

class GetPopularMoviesUseCaseImpl @Inject constructor( // Classe que implementa o caso de uso para obter filmes populares
    private val repository: MoviePopularRepository // Repositório que fornece os dados dos filmes populares
) : GetPopularMoviesUseCase { // Implementação do caso de uso
    override fun invoke(params: GetPopularMoviesUseCase.Params): Flow<PagingData<Movie>> {
        return try {
            val pagingSource = repository.getPopularMovies()
            Pager(
                config = params.pagingConfig,
                pagingSourceFactory = {
                    pagingSource
                }
            ).flow
        } catch (e: Exception) {
            emptyFlow()
        }
    }

}
/*
🎒 Imaginando como se fosse para uma criança:

Imagina que você tem um livro mágico de filmes, mas ele é tão grande que você só consegue ver um
pedacinho por vez (como uma página de cada vez).

Então você chama uma ajudante chamada GetPopularMoviesUseCase e diz:

"Ei, me mostra os filmes populares, mas só um pedacinho por vez pra eu não me perder, tá?"

Ela responde:

"Claro! Vou pegar os filmes lá na estante do repositório e vou te mostrar 20 por vez. E começo com
 20 também, combinado?" 📚🎞️

🔍 Agora parte por parte do código

interface GetPopularMoviesUseCase {
    suspend operator fun invoke(): Flow<PagingData<Movie>>
}

🧠 Interface (contrato):
Essa parte define o que a ajudante deve fazer.
Ela promete que vai te dar um fluxo de filmes (pouco a pouco) sempre que você chamar ela com invoke().

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviePopularRepository
)

🏗️ Classe real da ajudante:
Aqui é onde a ajudante é construída de verdade.
Ela recebe o repositório de filmes (que é tipo uma grande estante onde os filmes ficam guardados).

override suspend fun invoke(): Flow<PagingData<Movie>> {
    return repository.getPopularMovies(
        pagingConfig = PagingConfig (
            pageSize = 20,
            initialLoadSize = 20
        )
    )
}

📦 Função invoke:
Essa função é como quando você diz “Mostra os filmes!” e ela:

Vai no repositório 🏃‍♀️

Pega os filmes 🎥

Te entrega 20 por vez em uma sequência chamada Flow (como uma fita de filmes passando aos poucos na sua frente).

🎈 Como se fosse um teatrinho:

Você: "Quero filmes populares!"
UseCase: "Tá bom! Espera um pouco…"
(ela vai até o repositório, pega os filmes, configura pra te mostrar 20 por vez)
UseCase: "Aqui estão! Mas só 20 por vez, pra não te sobrecarregar, beleza?"

✅ Resumo simples:

GetPopularMoviesUseCase é como um ajudante que sabe buscar os filmes pra você.

Ela vai lá no repositório e diz: “Me dá os filmes populares, mas em partes pequenas!”

Ela usa um configurador de páginas (PagingConfig) pra trazer 20 filmes por vez.

E ela devolve tudo isso em um Flow, que é tipo uma fita que vai desenrolando devagarzinho no seu app.*/
