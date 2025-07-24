package br.com.movieapp.search_movie_feature_data_source

import br.com.movieapp.modelDomain.MovieSearchPaging
import br.com.movieapp.paging.MovieSearchPagingSource
import br.com.movieapp.remote.MovieService
import br.com.movieapp.response.SearchResponse
import br.com.movieapp.search_movie_feature_data_mapper.toMovieSearch
import br.com.movieapp.search_movie_feature_domain_source.MovieSearchRemoteDataSource
import javax.inject.Inject

class MovieSearchRemoteDataSourceImpl @Inject constructor( // construtor da classe, @Inject é uma anotação que indica que a classe pode ser injetada em outras classes
    private val service: MovieService // serviço de filmes, MovieService é uma interface que define os métodos para acessar a API de filmes
): MovieSearchRemoteDataSource { // classe que implementa a interface MovieSearchRemoteDataSource, que define os métodos para acessar a API de filmes
    override fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource { // método para buscar filmes,
        // query é a string de busca, MovieSearchPagingSource é uma classe que representa a fonte de dados para a paginação de filmes
        return MovieSearchPagingSource(query = query, remoteDataSource = this) // retorna uma nova instância de MovieSearchPagingSource,
    // passando a string de busca e a fonte de dados remota
    }

    override suspend fun getSearchMovies(page: Int, query: String): MovieSearchPaging { // método para buscar filmes, page é o número da página e query é a string de busca
        val response = service.searchMovie(page = page, query = query)

        return MovieSearchPaging(
            page = response.page,
            totalPages = response.total_pages,
            totalResults = response.total_results,
            movies = response.searchResults.map { it.toMovieSearch() } // converte os resultados da busca para o modelo MovieSearch
        )
    }
}
/*

🧚‍♀️ Histórinha: A Fada dos Filmes e o Robô Mensageiro 🎬🧙

Era uma vez uma fada muito esperta chamada MovieSearchRemoteDataSourceImpl.
Ela morava numa nuvem mágica chamada internet ☁️📡

A missão da Fadinha era simples:

    Ajudar o Robô Buscador (lembra do MovieSearchPagingSource?) a encontrar os filmes certos pra mostrar! 🍿📽️

💌 Parte 1: Recebendo o Pedido

Um dia, o Robô Buscador chegou e disse:

    “Fadinhaaa! Eu preciso de ajuda! Alguém tá procurando filmes sobre ‘unicórnios dançantes’!
    Você pode me dar um mapa pra começar a busca?”

E a Fadinha respondeu com um sorrisinho:

    “Claro que sim! Aqui está seu mapa mágico!”
    (A Fada usou o método getSearchMoviePagingSource, que cria e devolve um MovieSearchPagingSource!)

📞 Parte 2: Fazendo a ligação pro Reino dos Filmes

O Robô começou a seguir o mapa, mas pra achar os filmes, ele precisava falar com o Rei dos Filmes, lá no Reino da API 👑🎞️

Então o Robô usava o telefone da Fadinha, que era chamado de MovieService.

Sempre que ele queria uma página nova de filmes, ele dizia:

    “Alô? Me manda a página 2 com filmes sobre unicórnios dançantes, por favor!”

E a Fadinha cuidava disso usando o método getSearchMovies, que fazia exatamente isso: 📬 Mandava o pedido pro Rei e 💌 recebia a lista de filmes de volta (SearchResponse).
🔧 Bastidores Mágicos

Ah, e sabe aquela palavrinha estranha, @Inject? Ela é como um sinal mágico que diz:

    “Essa fada pode ser chamada automaticamente por qualquer um que precise dela, sem precisar ficar repetindo o nome dela toda hora!”

Tipo uma chave mestra 🗝️ que abre portas em todo o castelo do app!
🌟 Moral da história:

    A MovieSearchRemoteDataSourceImpl é a fada ajudante do Robô Buscador!
    Ela entrega o mapa da busca (PagingSource) e também liga pro Reino dos Filmes pra pegar os resultados que o robô precisa! 📞🎥
    Sem ela, o robô estaria perdido! 😅

* */