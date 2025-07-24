package br.com.movieapp.movie_detail_feature.domain.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.modelDomain.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesDetailsRepository { // interface para o repository

    suspend fun getMovieDetails(movieId: Int): MovieDetails // função para pegar os detalhes do filme
    fun getMovieSimiliar(movieId: Int): PagingSource<Int, Movie> // função para pegar os filmes similares

}

/*
O que é uma interface?

Uma interface é como um contrato. Ela diz o que a classe precisa fazer, mas não diz como fazer.
Ou seja, ela apenas define as funções que qualquer classe que a implementar deve ter. É como uma
lista de tarefas que a classe precisa cumprir!

Vamos analisar a interface MoviesDetailsRepository:

Essa interface está dizendo que qualquer classe que implementar ela deve fazer duas coisas:

    Pegar os detalhes de um filme.

    Pegar filmes similares a um filme.

Essas são as duas funções que ela define.

Função getMovieDetails(movieId: Int): MovieDetails

Essa função deve pegar os detalhes de um filme específico. Ela recebe o ID do filme (um número único
que identifica o filme) e vai retornar um objeto com todos os detalhes do filme (como título, sinopse,
 categorias, etc.).

    Exemplo: Quando você quer saber mais sobre um filme, como quem são os atores, qual é a sinopse,
    e outras informações, você usa essa função.

Função getMovieSimiliar(movieId: Int, pagingConfig: PagingConfig): Flow<PagingData<Movie>>

Essa função deve pegar filmes similares ao filme com o movieId passado. Ela recebe dois parâmetros:

    movieId: O ID do filme que você está interessado.

    pagingConfig: A configuração de como os filmes devem ser carregados em páginas (quantos filmes
    por vez, por exemplo).

Ela vai retornar um fluxo de dados paginados (Flow<PagingData<Movie>>), ou seja, ela vai entregar
os filmes aos poucos, para que o usuário possa ir rolando a tela e vendo mais filmes.

    Exemplo: Se você assistiu um filme e quer ver mais filmes parecidos, essa função vai te mostrar
    esses filmes de forma organizada em páginas, sem travar o app.

 O que a interface não faz?

A interface não diz como essas funções serão implementadas. Ela apenas diz o que deve ser feito.
 Quem vai definir como as funções vão buscar os dados (na internet, por exemplo) e como os dados
 serão organizados, é a classe que implementar essa interface. A classe é como um "operário" que
 segue o "contrato" da interface para fazer o trabalho.

Por que usar uma interface?

Usar uma interface é uma boa prática porque ela ajuda a organizar o código. Ela deixa claro o que o
 "contrato" entre o app e a lógica de negócios deve ser, sem se preocupar com os detalhes de como
 as funções são implementadas.

Por exemplo, você pode ter várias implementações dessa interface (talvez uma que pegue os dados de
uma API e outra que use um banco de dados local) e todas elas terão as mesmas funções definidas na
interface. Isso torna o código mais flexível e fácil de testar.

Resumo da interface MoviesDetailsRepository:

    O que ela define?

        A interface define que qualquer classe que a implementar vai ter que ter duas funções:

            Uma para pegar os detalhes de um filme.

            Outra para pegar filmes similares de maneira paginada (aos poucos).

    Por que ela é útil?

        Ela organiza o que deve ser feito, mas deixa que outra classe defina o como fazer.

        Ela ajuda a manter o código organizado e flexível, permitindo diferentes maneiras de obter
        os dados (API, banco de dados, etc.).

* */