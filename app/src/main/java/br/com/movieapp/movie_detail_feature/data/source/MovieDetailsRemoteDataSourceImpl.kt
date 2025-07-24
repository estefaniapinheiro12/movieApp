package br.com.movieapp.movie_detail_feature.data.source

import br.com.movieapp.modelDomain.MovieDetails
import br.com.movieapp.modelDomain.MoviePaging
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.movie_popula_feature_data_mapper.toMovie
import br.com.movieapp.paging.MovieSimiliarPagingSource
import br.com.movieapp.remote.MovieService
import br.com.movieapp.response.MovieResponse
import br.com.movieapp.util.toBackdropUrl
import javax.inject.Inject

class MovieDetailsRemoteDataSourceImpl @Inject constructor( // classe que implementa a interface MovieDetailsRemoteDataSource
    private val service: MovieService // injeção de dependência do serviço MovieService
): MovieDetailsRemoteDataSource { // implementa a interface MovieDetailsRemoteDataSource

    override suspend fun getMovieDetails(movieId: Int): MovieDetails { // função que busca os detalhes do filme
        val response = service.getMovie(movieId) // chama o serviço para buscar os detalhes do filme
        val genres = response.genres.map { it.name } // mapeia as categorias do filme para uma lista de strings
        return MovieDetails( // retorna um objeto MovieDetails
            id = response.id, // id do filme
            title = response.title, // título do filme
            overview = response.overview, // sinopse do filme
            genres = genres, // categorias do filme
            releaseDate = response.release_date, // data de lançamento do filme
            backdropPathUrl = response.backdrop_path.toBackdropUrl(), // URL do fundo do filme
            voteAverage = response.vote_average, // média de votos do filme
            duration = response.runtime, // duração do filme
            voteCount = response.vote_count // contagem de votos do filme
        )
    }

    override suspend fun getMoviesSimilar(page: Int, movieId: Int): MoviePaging { // função que busca filmes similares
        val response =  service.getMoviesSimilar(page = page, movieId = movieId) // chama o serviço para buscar filmes similares

        return MoviePaging(
            page = response.page, // página atual dos resultados
           totalPages = response.totalPages,
            totalResults = response.totalResults,
            movies = response.movieResults.map { it.toMovie() }
        )
    }

    override fun getSimiluarMoviesPagingSource(movieId: Int): MovieSimiliarPagingSource { // função que retorna um objeto MovieSimiliarPagingSource
        return MovieSimiliarPagingSource(this, movieId = movieId) // cria um novo objeto MovieSimiliarPagingSource
    }
}
/*
O que está acontecendo aqui?

Esse código está fazendo a implementação do manual de instruções (a interface) que você viu no código
anterior. Lembra que a interface dizia o que o app deveria ser capaz de fazer? Agora, nesse código,
você tem a implementação real de como fazer essas tarefas.
O que a classe MovieDetailsRemoteDataSourceImpl faz?

A classe MovieDetailsRemoteDataSourceImpl é como se fosse o funcionário que vai seguir as instruções
que a interface MovieDetailsRemoteDataSource definiu. Então, enquanto a interface diz "o que precisa
ser feito", essa classe é a que realmente vai buscar os dados da internet e trazer as informações de
volta para o app.

E ela faz isso com ajuda do MovieService, que é um serviço (provavelmente uma conexão com a internet)
que sabe como pedir essas informações de um servidor.
Como ela faz isso?

    Construtor da classe (@Inject constructor):

        A classe recebe um serviço (MovieService) que é injetado (isto é, o serviço é fornecido por
        um lugar especial do código, usando uma técnica chamada injeção de dependência). Esse serviço
         vai ser usado para fazer as requisições à internet.

    As funções:

    Agora, vamos ver cada uma das funções que a classe implementa (que vêm da interface MovieDetailsRemoteDataSource):

1. getMovieDetails(movieId: Int): MovieDetails:

    O que ela faz?

        Essa função é responsável por buscar os detalhes de um filme.

        Ela usa o MovieService para ir até o servidor e buscar os detalhes do filme com o ID que foi
        passado.

    Como funciona?

        O service.getMovie(movieId) vai chamar a API que retorna os detalhes do filme.

        Depois, ela pega essas informações e organiza tudo de uma maneira que o app consegue entender
        (no formato MovieDetails).

    O que é retornado?

        Um objeto MovieDetails, que é o detalhe do filme, como nome, sinopse, data de lançamento, etc.

2. getMoviesSimilar(page: Int, movieId: Int): MovieResponse:

    O que ela faz?

        Essa função vai buscar filmes similares ao filme que o usuário está vendo.

        Assim como a função anterior, ela usa o MovieService para buscar esses filmes.

    Como funciona?

        O service.getMoviesSimilar(page = page, movieId = movieId) chama a API que retorna os filmes
         semelhantes ao filme com o ID que foi passado.

        Ela recebe dois parâmetros:

            page: que é a página de filmes que você quer pegar.

            movieId: o ID do filme ao qual os outros filmes serão semelhantes.

    O que é retornado?

        Um objeto MovieResponse, que contém uma lista de filmes similares.

3. getSimiluarMoviesPagingSource(movieId: Int): MovieSimiliarPagingSource:

    O que ela faz?

        Essa função retorna um paginador (algo que vai ajudar a carregar os filmes aos poucos),
         que vai pegar os filmes similares de forma eficiente. Ela não pega tudo de uma vez, mas vai
          carregando à medida que o usuário vai pedindo mais.

    Como funciona?

        O MovieSimiliarPagingSource é o responsável por carregar os filmes de forma paginada, e ele
        vai ser configurado com o movieId do filme que você está buscando filmes semelhantes.

    O que é retornado?

        Um objeto MovieSimiliarPagingSource, que vai gerenciar a páginação dos filmes semelhantes.

Como funciona tudo junto?

Então, essa classe, MovieDetailsRemoteDataSourceImpl, está implementando a interface (o manual de
instruções) para buscar detalhes do filme e filmes similares, usando o MovieService para se comunicar
com o servidor e trazer as informações para o app.

    Quando você quer os detalhes do filme, a função getMovieDetails chama a API e traz as informações.

    Quando você quer filmes similares, a função getMoviesSimilar chama a API e traz os filmes semelhantes.

    E quando você quer carregar os filmes de maneira eficiente, com páginação, a
    função getSimiluarMoviesPagingSource cria o paginador que vai buscar os filmes aos poucos.

Resumo:

    A interface diz o que o app deve fazer (pegar detalhes de filmes, filmes semelhantes, etc.).

    A classe MovieDetailsRemoteDataSourceImpl é a responsável por fazer essas tarefas de verdade, buscando dados na internet.

    Ela usa o MovieService para pedir os dados do servidor.

    Ela organiza tudo e retorna as informações no formato certo para o app.
* */