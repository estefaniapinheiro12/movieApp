package br.com.movieapp.search_movie_feature_data_repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.movieapp.modelDomain.MovieSearch
import br.com.movieapp.paging.MovieSearchPagingSource
import br.com.movieapp.search_movie_feature_domain_repository.MovieSearchRepository
import br.com.movieapp.search_movie_feature_domain_source.MovieSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieSearchRepositoryImpl @Inject constructor( // construtor da classe, @Inject é uma anotação que indica que a classe pode ser injetada em outras classes
    private val remoteDataSource: MovieSearchRemoteDataSource // fonte de dados remota
): MovieSearchRepository { // MovieSearchRepository é uma interface que define os métodos para acessar a API de filmes
    override fun getSearchMovies( // método para buscar filmes populares, query é a string de busca e pagingConfig é a configuração de paginação
        query: String,
    ): PagingSource<Int, MovieSearch> { // Flow é um tipo de fluxo que emite dados de forma assíncrona,
        // pagingData é um tipo de dado que representa uma lista de dados paginados, MovieSearch é o tipo de dado que representa um filme
       return MovieSearchPagingSource(query, remoteDataSource) // retorna uma instância de MovieSearchPagingSource, que é uma classe que implementa PagingSource
    }
}
/*

🎬📦 História: O Cofrinho dos Filmes Mágicos 🧚‍♂️✨

Era uma vez, em um mundo encantado de filmes, uma criatura muito especial chamada Repositório dos Filmes — o MovieSearchRepositoryImpl.
Ele era como um cofrinho mágico, que sabia organizar os filmes em fileiras, pra ninguém ficar cansado de procurar tudo de uma vez. 📚🎥
🌟 Como funcionava esse cofrinho mágico?

Quando uma criança (ou um app! 🤭) dizia:

    “Eu quero ver filmes sobre dinossauros voadores!” 🦖🕊️

O cofrinho não corria desesperado juntando tudo!
Ele fazia assim:
🧠 Passo 1: Chamava o Fadinho da Busca

O repositório dizia:

    “Ei, Fadinho da Busca (remoteDataSource)!
    Você pode me dar um mapa que mostre como buscar filmes de pouquinho em pouquinho?”
    E o Fadinho respondia com um mapa mágico de busca, chamado getSearchMoviePagingSource.

📦 Passo 2: Organizava tudo em Caixinhas

Agora com o mapa na mão e a ideia do que procurar (query), o Repositório usava um feitiço chamado Pager.

Esse feitiço dizia assim:

🧙‍♂️

    “Vamos buscar os filmes aos pouquinhos, em páginas.
    Cada vez que alguém rolar a tela, mostramos mais um pouco!”

E ele usava um plano de organização chamado PagingConfig, que dizia quantos filmes mostrar por vez, como se fossem caixinhas empilhadas, organizadinhas! 📦📦📦
💧 Passo 3: Criava um Rio de Filmes!

Por fim, ele criava um rio de filmes, chamado Flow, onde os filmes vinham boiando, página por página, como se estivessem em folhas de papel flutuando na água. 🌊🎞️

Assim, toda vez que alguém queria mais filmes, ele só precisava esperar o próximo barquinho passar com a nova leva de histórias. ⛵🍿
📘 Moral da história:

    O MovieSearchRepositoryImpl é um cofrinho mágico que sabe onde buscar filmes,
    sabe como empacotar eles direitinho, e entrega tudo isso como um rio encantado de páginas de filmes!

    Assim, ninguém se perde, ninguém se cansa, e todos se divertem com as histórias. 💫

* */