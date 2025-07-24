package br.com.movieapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_popula_feature_data_domain_source.MoviePopularRemoteDataSource
import br.com.movieapp.movie_popula_feature_data_mapper.toMovie
import coil.network.HttpException

class MoviePagingSource( // Essa classe é responsável por carregar os dados de filmes populares
    private val remoteDataSource: MoviePopularRemoteDataSource // Essa private variável é uma instância de MoviePopularRemoteDataSource
) : PagingSource<Int, Movie>() { // Essa classe herda de PagingSource, que é uma classe do Android Paging Library

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->// Essa função retorna a chave de atualização
        val anchorPage = state.closestPageToPosition(anchorPosition)
        anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
    }
}

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> { // Essa função carrega os dados de filmes populares
        return try { // Essa função tenta carregar os dados
            val pageNumber = params.key ?: 1 // Se a chave for nula, define como 1
            val moviePaging = remoteDataSource.getPopularMovies(page = pageNumber) // Chama a função getPopularMovies do remoteDataSource
            val movies = moviePaging.movies // Pega a lista de filmes da resposta
            val totalPages = moviePaging.totalPages

            LoadResult.Page( // Cria uma instância de LoadResult.Page, que é uma classe que representa uma página de dados
                data = movies, // Converte a lista de filmes para o modelo Movie
                prevKey = if (pageNumber == 1) null else pageNumber - 1, // Se a página for 1, a chave anterior é nula, caso contrário, é a página atual - 1
                nextKey = if (pageNumber == totalPages) null else pageNumber + 1 // Se a lista de filmes estiver vazia, a chave seguinte é nula, caso contrário, é a página atual + 1
            )
        } catch (exception: Exception) { // Essa função captura qualquer exceção que ocorra durante o carregamento dos dados
             LoadResult.Error(exception) // Retorna um LoadResult.Error com a exceção
        }
    }
    companion object { // Essa classe contém constantes e funções auxiliares
        private const val LIMIT = 20 // Número máximo de itens por página
    }

}

/*
📦 O que essa classe faz?

Essa classe é quem vai buscar os filmes populares pedacinho por pedacinho, ou melhor, página por
 página, como se fosse uma biblioteca com várias prateleiras de filmes. Ela é quem vai lá e pega os
 filmes certos cada vez que o app pede.
🧒 Explicando como se fosse uma criança curiosa:

Imagina que o app quer mostrar os filmes famosos...
Mas não dá pra pegar todos de uma vez, né? Seria pesado demais! 🧱
Então ele faz assim:

“Me dá só 20 de cada vez, por favor?”

E essa classe MoviePagingSource responde:

“Beleza, vou lá buscar!”

🎬 Como funciona passo a passo:

O app diz:

“Quero a página 1 de filmes!”

Essa classe pergunta pra remoteDataSource:

“Me dá os filmes da página 1!”

O remoteDataSource traz os filmes (20, por exemplo).

A MoviePagingSource organiza:

Data = os filmes que vieram

prevKey = qual é a página anterior (se for a primeira, não tem)

nextKey = qual é a próxima página (se não vier mais nada, acabou)

Se der algum erro no caminho:

“Opa! Deu erro! 😢” E ela avisa o app direitinho.

🧩 E os pedaços do código?

PagingSource<Int, Movie> = Essa classe faz parte do sistema de paginação. O primeiro Int é o número
da página, e o Movie é o que ela vai buscar.

load(...) = é como ela vai lá buscar os dados da página.

params.key ?: 1 = se ninguém disser qual página, ela começa pela página 1.

movies.toMovie() = ela converte os dados brutos pra algo que o app entende (tipo traduzir pra linguagem do app).

prevKey e nextKey = ajudam o app a saber se tem mais antes ou depois.

LoadResult.Error(...) = se algo deu errado, ela avisa certinho com o erro.

🔁 E o getRefreshKey()?

Ele serve pra dizer:

“Se o usuário puxar pra atualizar, por onde recomeçamos?”
Mas ainda tá com um TODO, então você vai implementar depois! 😉

📌 Resumo como se fosse um desenho:

App: “Me dá uma página de filmes?”

MoviePagingSource: “Claro! Espera que vou buscar.”

Vai no remoteDataSource, pega os filmes da página.

Volta e fala: “Toma, aqui estão os filmes da página X!”

Se der erro, ela avisa com carinho.*/
