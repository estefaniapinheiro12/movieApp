package br.com.movieapp.search_movie_feature_domain_source

import br.com.movieapp.modelDomain.MovieSearchPaging
import br.com.movieapp.paging.MovieSearchPagingSource
import br.com.movieapp.response.SearchResponse

interface MovieSearchRemoteDataSource {
    fun getSearchMoviePagingSource(query: String): MovieSearchPagingSource // método para buscar filmes,
    // query é a string de busca, MovieSearchPagingSource é uma classe que representa a fonte de dados para a paginação de filmes
    suspend fun getSearchMovies(page: Int, query: String): MovieSearchPaging // função para buscar
    // filmes, page é o número da página e query é a string de busca, SearchResponse é uma classe que representa a resposta da busca de filmes



}
/*
🎥🌈 História: O Contrato Secreto da Fada dos Filmes 🧚‍♀️📜

Era uma vez, no Reino dos Códigos, um contrato mágico chamado MovieSearchRemoteDataSource.
Esse contrato não era feito de papel, mas de regras invisíveis que diziam o que toda fada dos filmes tinha que fazer! ✨

Esse contrato dizia assim:
📜 Primeira Regra: O Mapa da Busca 🔍

    “Toda fada dos filmes tem que saber entregar um mapa mágico para o Robô Buscador seguir uma trilha de filmes.
    Quando alguém disser o que está procurando (tipo: ‘dragões coloridos’ 🐉🌈), a fada precisa entregar esse mapa usando o método chamado getSearchMoviePagingSource!”

📞 Segunda Regra: Ligar pro Reino dos Filmes 📡👑

    “Toda fada também precisa saber ligar pro Rei dos Filmes e dizer:
    ‘Majestade, me envie a página 3 com os filmes sobre "cachorros espaciais", por favor!’ 🐶🚀
    Isso é feito usando o método getSearchMovies, e ela deve receber uma resposta com os filmes, chamada SearchResponse.”

🧙 O Contrato é Mágico Porque...

Esse contrato é como um acordo entre mundos!
Ele não faz as coisas sozinho, mas obriga quem o assinar a seguir essas regras certinhas!

Então, quando a nossa fada especial (MovieSearchRemoteDataSourceImpl) assinou esse contrato, ela prometeu:

    “Eu vou sempre entregar o mapa e ligar pro Rei dos Filmes do jeitinho que o contrato mandou!”

🌟 Moral da história:

    A interface MovieSearchRemoteDataSource é um contrato mágico!
    Ela diz tudo o que uma ajudante (como a fada dos filmes) tem que fazer:

        Dar o mapa da busca pro robô 🗺️

        Falar com o Reino dos Filmes pra trazer resultados 📞🎬

Sem esse contrato, o reino viraria uma bagunça! 😲
* */