package br.com.movieapp.search_movie_feature_domain_usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.modelDomain.MovieSearch
import br.com.movieapp.search_movie_feature_domain_repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

interface GetMovieSearchUseCase { // interface para o caso de uso de busca de filmes
    operator fun invoke(params: Params): Flow<PagingData<MovieSearch>> // método para buscar filmes, que recebe os parâmetros e retorna um fluxo de dados paginados
    data class Params(
        val query: String,
        val pagingConfig: PagingConfig
    ) // classe interna que representa os parâmetros de busca, que contém a string de busca
}


class GetMovieSearchUseCaseImpl @Inject constructor( //
    private val repository: MovieSearchRepository // injetando o repositório de busca de filmes
) : GetMovieSearchUseCase { // implementando a interface GetMovieSearchUseCase
    override fun invoke(params: GetMovieSearchUseCase.Params): Flow<PagingData<MovieSearch>> { // implementando
        // o método invoke, onde params é a string de busca, Flow é um tipo de fluxo que emite dados
        //  de forma assíncrona, pagingData é um tipo de dado que representa uma lista de dados paginados, MovieSearch é o tipo de dado que representa um filme
        return try {
            val pagingSource = repository.getSearchMovies(params.query)

            return Pager(
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
🌟 Título da história: "A Busca pelos Filmes Perdidos"

Era uma vez, no mundo mágico dos aplicativos de filmes, um ajudante muito esperto chamado
GetMovieSearchUseCaseImpl. Ele era conhecido como o resolvedor de buscas! Toda vez que alguém dizia:

    "Quero ver filmes sobre dragões!" 🐉

Esse ajudante sabia exatamente o que fazer.
📩 O Pedido Especial

Mas ele nunca fazia isso sozinho. Ele recebia um bilhetinho mágico chamado Params, que dizia:

    "Aqui está a palavrinha que a pessoa quer procurar: 'dragões'"

Esse bilhetinho vinha dentro de uma interface mágica chamada GetMovieSearchUseCase, que dizia:

    "Toda vez que alguém te chamar, você precisa olhar esse bilhetinho e trazer uma lista de filmes."

🧙‍♂️ O Repositório, o Sábio dos Filmes

O ajudante então corria até um sábio muito sábio chamado MovieSearchRepository. Esse sábio sabia
como procurar os filmes, mas precisava que alguém o chamasse com as palavras certas.

Então o ajudante dizia:

    "Ó grande repositório, aqui está o pedido: queremos filmes de dragões, mas entregue 20 por vez, tudo bem?"

O sábio respondia:

    "Pode deixar! Eu vou entregar essa lista aos poucos, como um rio que vai trazendo um balde de cada vez." ⛲

Esse rio de dados era chamado de Flow<PagingData<MovieSearch>>, que quer dizer:

    "Uma lista mágica de filmes que vai chegando devagarinho, página por página."

📦 Como funciona a entrega?

O ajudante também dizia:

    "Quero que venham 20 filmes agora, e depois mais 20, e depois mais 20… até terminar!" 🧺🧺🧺

Ele fazia isso usando um pacote chamado PagingConfig, que dizia:

    pageSize = 20: “Mande 20 filmes por vez.”

    initialLoadSize = 20: “Comece com 20 também.”

🧚 E assim termina a história…

Toda vez que alguém procurava um filme, o ajudante GetMovieSearchUseCaseImpl pegava o bilhetinho,
chamava o sábio repositório e trazia a lista mágica de filmes, devagarinho, para a pessoa ver no celular 📱✨

E todos viveram felizes assistindo seus filmes favoritos! 🎬💖
* */