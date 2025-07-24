package br.com.movieapp.movie_detail_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.modelDomain.MovieDetails
import br.com.movieapp.movie_detail_feature.domain.repository.MoviesDetailsRepository
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.paging.MovieSimiliarPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesDetailsRepositoryImpl @Inject constructor( // classe que implementa a interface MoviesDetailsRepository
    private val remoteDataSource: MovieDetailsRemoteDataSource // injetando a dependência MovieDetailsRemoteDataSource
): MoviesDetailsRepository { // implementando a interface MoviesDetailsRepository
    override suspend fun getMovieDetails(movieId: Int): MovieDetails { // função que retorna os detalhes do filme
        return remoteDataSource.getMovieDetails(movieId = movieId) // chama a função getMovieDetails do remoteDataSource
    }

    override  fun getMovieSimiliar( // função que retorna os filmes similares
        movieId: Int // id do filme
    ): PagingSource<Int, Movie> { // retorna um fluxo de dados paginados
        return MovieSimiliarPagingSource(movieId = movieId, remoteDataSource = remoteDataSource) // cria uma instância de MovieSimiliarPagingSource

    }
}

/*
Essa classe MoviesDetailsRepositoryImpl tem a função de fornecer os dados relacionados a filmes para
o seu app. Ela pega as informações sobre filmes a partir de uma fonte de dados remota (lá da internet)
 e as disponibiliza de maneira eficiente.

Além disso, ela trabalha com paginamento para carregar os dados aos poucos, o que é muito útil para
evitar que o app fique lento.

1. Herança da interface MoviesDetailsRepository

Essa classe MoviesDetailsRepositoryImpl implementa uma interface chamada MoviesDetailsRepository.

    A interface define o que a classe precisa fazer, ou seja, o que o app espera dela.

    A classe MoviesDetailsRepositoryImpl vai cumprir essas expectativas, trazendo os detalhes do filme e filmes similares.

 classe tem um construtor que recebe um parâmetro:

    remoteDataSource: Esse é o local de onde a classe vai buscar os dados. Ele é um objeto que já
     sabemos que se comunica com a internet para buscar informações sobre filmes.
     (É o MovieDetailsRemoteDataSource que você já viu antes.)

O construtor é marcado com @Inject. Isso significa que a injeção de dependência vai acontecer
automaticamente, ou seja, o sistema vai passar o remoteDataSource para essa classe sem você precisar
 fazer isso manualmente.

Funções da classe:

Agora vamos ver as duas funções importantes que essa classe implementa.

getMovieDetails(movieId: Int): MovieDetails

Essa função vai buscar os detalhes de um filme específico.

    Ela recebe o movieId, que é o identificador do filme que você quer saber mais detalhes.

    Depois, ela chama a função getMovieDetails do remoteDataSource, que é quem vai fazer o trabalho
    de buscar os detalhes na internet.

    No final, ela retorna os detalhes do filme (no formato MovieDetails) para quem pediu.

 getMovieSimiliar(movieId: Int, pagingConfig: PagingConfig): Flow<PagingData<Movie>>

Essa função é responsável por buscar filmes similares ao filme que foi passado.

Ela funciona com paginamento — ou seja, ela vai pegar os filmes aos poucos, conforme o usuário for
 precisando (rolando a tela para baixo).

    Ela recebe dois parâmetros:

        movieId: O ID do filme que você quer filmes semelhantes.

        pagingConfig: A configuração de como os filmes devem ser carregados (quantos por página, por exemplo).

    O que ela faz?

        Ela cria um Pager, que é responsável por carregar os filmes em páginas.

        O Pager vai usar a função getSimiluarMoviesPagingSource do remoteDataSource, que já vimos em
        outro código, para obter os filmes semelhantes em formato de páginas.

    A função retorna um Flow<PagingData<Movie>>:

        Flow: Significa que os dados vão ser carregados de maneira assíncrona e reativa. Ou seja, a
        interface pode mostrar os filmes conforme eles são carregados.

        PagingData<Movie>: Os filmes são carregados de forma paginada, com cada página de resultados
        sendo entregue quando o usuário rolar para baixo.

4. Por que usar o Pager?

O Pager é a chave para garantir que os filmes sejam carregados de maneira eficiente, sem travar o app.

    Em vez de carregar todos os filmes de uma vez, o Pager vai buscar apenas uma página de filmes por vez.

    Isso ajuda a evitar que o app fique muito lento e que os dados ocupem muita memória.

Quando o usuário rola para baixo, o Pager pede a próxima página de filmes e a exibe sem sobrecarregar o dispositivo.

Resumo da Classe MoviesDetailsRepositoryImpl:

    O que ela faz?

        Essa classe é responsável por pegar os dados sobre os filmes. Ela pega os detalhes de um filme específico e também os filmes semelhantes a ele.

        Ela utiliza o MovieDetailsRemoteDataSource para buscar esses dados na internet.

    Como ela trabalha?

        Quando você pede os detalhes de um filme, ela chama a função que pega os detalhes da internet e retorna a resposta.

        Quando você pede filmes similares, ela usa o paginamento para buscar os filmes aos poucos, conforme o usuário vai rolando a tela.

A classe MoviesDetailsRepositoryImpl conecta o seu app com a fonte de dados externa (a internet) para pegar os detalhes de filmes e filmes semelhantes de maneira eficiente, usando o paginamento para evitar sobrecarga no app.

    Ela usa o remoteDataSource para buscar os dados.

    Ela usa o Pager para carregar os filmes similares aos poucos, conforme a necessidade.

    Ela trabalha com Flow para garantir que os dados sejam carregados de forma assíncrona e fluida.
* */