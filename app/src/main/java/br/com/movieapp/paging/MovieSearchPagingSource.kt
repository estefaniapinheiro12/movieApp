package br.com.movieapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.modelDomain.MovieSearch
import br.com.movieapp.search_movie_feature_domain_source.MovieSearchRemoteDataSource
import retrofit2.HttpException
import java.io.IOException
import br.com.movieapp.search_movie_feature_data_mapper.toMovieSearch

class MovieSearchPagingSource(
    private val query: String, // string de busca
    private val remoteDataSource: MovieSearchRemoteDataSource // fonte de dados remota
) : PagingSource<Int, MovieSearch>() { // classe que representa a fonte de dados para a paginação de filmes, Int é o tipo de dado que representa o número da página e MovieSearch é o tipo de dado que representa um filme
    override fun getRefreshKey(state: PagingState<Int, MovieSearch>): Int? { // método para obter a chave de atualização, state é o estado atual da paginação
        return state.anchorPosition?.let { anchorPosition -> // retorna a posição âncora, que é a posição do item mais próximo da tela
            val anchorPage = state.closestPageToPosition(anchorPosition) // obtém a página mais próxima da posição âncora
            anchorPage?.prevKey?.plus(LIMIT) // retorna a chave anterior, que é a página anterior à posição âncora
                ?: anchorPage?.nextKey?.minus(LIMIT) // retorna a chave seguinte, que é a página seguinte à posição âncora
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieSearch> { // método para carregar os dados, params é o parâmetro de carregamento
        return try{
            val pageNumber = params.key ?: 1 // obtém o número da página, se não houver chave, retorna 1, a primeira vez que estiver carregando os dados vai ser nulo
            val response = remoteDataSource.getSearchMovies(page = pageNumber, query = query) // chama o método getSearchMovies da fonte de dados remota, passando o número da página e a string de busca que o usuário digitou

            val movies = response.movies // obtém a lista de filmes da resposta
            val totalPages = response.totalPages

            LoadResult.Page(
                data = movies,
                prevKey = if (pageNumber == 1) null else pageNumber - 1, // se o número da página for 1, não há página anterior, caso contrário, retorna a página anterior
                nextKey = if (pageNumber == totalPages) null else pageNumber + 1 // se a lista de filmes estiver vazia, não há página seguinte, caso contrário, retorna a próxima página
            )

        }catch(exception: IOException) {
            LoadResult.Error(exception)
        }
    }
    companion object {
        private const val LIMIT = 20 // limite de itens por página
    }
}
/*
🌟 Histórinha: A Aventura do Robô Buscador de Filmes 🎬🤖

Era uma vez um robô muito curioso chamado MovieSearchPagingSource.

Esse robô tinha uma missão muito importante:

    Buscar filmes que as pessoas estavam procurando e trazer de pouquinho em pouquinho!
    Ele não podia trazer todos de uma vez senão ele se cansava muito! 🥵

Então ele pegava uma página de cada vez, tipo um livrinho de figurinhas! 📖✨
🧭 O que ele tinha nas mãos?

👀 O robô tinha:

    Um mapa com uma palavra mágica: query, que dizia o que as pessoas estavam procurando ("princesas", "dragões", "super-heróis"...).

    Um telefone para ligar pro planeta dos filmes (isso é o remoteDataSource).

🧠 Parte 1: “Qual página eu recomeço se algo der errado?”

    O robô perguntava:
    “Hmm… Se alguém girar o celular ou sair da tela… por onde eu recomeço?”

Ele usava uma bússola (getRefreshKey) pra descobrir qual página mostrar de novo sem repetir tudo do zero. 🧭
📦 Parte 2: “Buscar os filmes”

O robô então fazia o que ele mais gostava! No load:

    Ele dizia:

        “Deixa eu ver… qual página eu tô mesmo? Ah, se ninguém me contou, começo da página 1!”

    Ele ligava pro planeta dos filmes e dizia:

        “Oi! Me manda a página número X de filmes sobre ‘dinossauros dançantes’, por favor!”

    Quando chegavam os filmes, ele colocava todos numa caixinha mágica chamada LoadResult.Page.

    Mas o robô também era muito inteligente, então ele dizia:

        “Se essa é a primeira página, não tem página antes 🧭❌”

        “Se acabaram os filmes, então não tem próxima página também 📪❌”

    Se algo desse errado, tipo a internet cair ou o planeta dos filmes estiver fechado, ele chorava um pouquinho 😢 (LoadResult.Error) e dizia:

        “Deu erro! Mas tá tudo bem, vamos tentar de novo depois!”

✨ Moral da história

    MovieSearchPagingSource é um robô que busca os filmes aos poucos, página por página, sempre sabendo onde está e cuidando pra não se perder nem cansar.
    Ele ajuda a mostrar muitos filmes sem deixar o app pesado e lento!
* */