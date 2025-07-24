package br.com.movieapp.movie_detail_feature.domain.source

import br.com.movieapp.modelDomain.MovieDetails
import br.com.movieapp.modelDomain.MoviePaging
import br.com.movieapp.paging.MovieSimiliarPagingSource
import br.com.movieapp.response.MovieResponse

interface MovieDetailsRemoteDataSource { // interface para o data source remoto

    suspend fun getMovieDetails(movieId: Int): MovieDetails // função para pegar os detalhes do filme
    suspend fun getMoviesSimilar(page: Int,  movieId: Int): MoviePaging // função para pegar os filmes similares
    fun getSimiluarMoviesPagingSource(movieId: Int): MovieSimiliarPagingSource // função para pegar os filmes similares com paginação

}
/*
O que está acontecendo aqui?

Este código tem a ver com como o seu app fala com a internet para pegar informações sobre filmes,
como o detalhe de um filme ou filmes similares.

Aqui está o que cada parte faz:
1. A interface MovieDetailsRemoteDataSource:

A interface é como um contrato ou promessa. Ela diz: "Eu sou um conjunto de regras para acessar
informações sobre filmes de algum lugar (geralmente a internet), mas quem vai realmente pegar esses
dados sou outra parte do código."
Parece meio complicado, né? Vou explicar mais fácil.
Então, a interface é como um manual de instruções dizendo o que o app precisa poder fazer para pegar
as informações de filmes.
2. Funções dentro da interface:

Dentro dessa interface, existem três funções. Elas são como tarefas que o app pode pedir para o
"manual" fazer:

    getMovieDetails(movieId: Int): MovieDetails

    Essa função vai pegar os detalhes de um filme. Por exemplo, se você quisesse saber o nome do
    filme, a descrição, o elenco, essas coisas, você daria o ID do filme para essa função. Ela vai
    trazer essas informações de volta.

    Exemplo: "Me dá os detalhes do filme com o ID 42."

    getMoviesSimilar(page: Int, movieId: Int): MovieResponse

    Essa função vai trazer uma lista de filmes similares ao que você está vendo. Se você está
    assistindo um filme de ação, essa função pode trazer filmes que também sejam de ação. Ela recebe
    dois parâmetros:

        A página (page), que indica que parte da lista de filmes você quer. Por exemplo, "Eu quero
        a segunda página dos filmes".

        O ID do filme (movieId), para saber quais filmes são semelhantes ao que você está olhando.

    getSimiluarMoviesPagingSource(movieId: Int): MovieSimiliarPagingSource

    Essa função é um pouquinho diferente. Ela não vai só trazer os filmes similares de uma vez, mas
    vai configurar tudo para que o app carregue os filmes aos poucos (paginando). Ela retorna um
    "paginador" (MovieSimiliarPagingSource), que vai pedir os filmes de maneira eficiente, carregando
    mais conforme o usuário rola a tela. É tipo um assistente que vai buscando mais filmes enquanto
    você vai pedindo.

3. Por que é importante isso?

Essas funções vão permitir que o seu app comunique-se com a internet e consiga mostrar as informações
de filmes de forma organizada e eficiente. E elas são chamadas remotas porque a informação vem de
algum lugar na internet (o servidor, no caso).
Resumindo:

    A interface é o manual que diz o que precisa ser feito.

    As funções são as tarefas que o app pode pedir para o "manual" fazer, como pegar detalhes de
     filmes ou filmes similares.

    O app vai usar essas funções para mostrar as informações sobre filmes, de uma forma bem organizada!
* */