package br.com.movieapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.movie_popula_feature_data_mapper.toMovie
import coil.network.HttpException

class MovieSimiliarPagingSource( // classe para carregar os filmes similares
    private val remoteDataSource: MovieDetailsRemoteDataSource, // fonte de dados remota
    private val movieId: Int // id do filme
): PagingSource<Int, Movie> (){ // herança da classe PagingSource com os tipos Int e Movie
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? { // função para obter a chave de atualização
        return state.anchorPosition?.let { anchorPosition -> // verifica se a posição âncora não é nula
            val anchorPage = state.closestPageToPosition(anchorPosition) // obtém a página mais próxima da posição âncora
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT) // retorna a chave anterior ou próxima da página âncora

        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> { // função para carregar os dados
        return try { // tenta executar o bloco de código
            val pageNumber = params.key ?: 1 // obtém o número da página ou define como 1 se for nulo
            val response = remoteDataSource // chama a fonte de dados remota
                .getMoviesSimilar(page = pageNumber, movieId = movieId) // obtém os filmes similares
            val movies = response.movies    // obtém os resultados dos filmes
            val totalPages = response.totalPages


            LoadResult.Page ( // cria uma nova página de resultados
                data = movies, // converte os resultados dos filmes para o tipo Movie
                prevKey = if (pageNumber == 1) null else pageNumber - 1, // define a chave anterior como nula se o número da página for 1, caso contrário, subtrai 1
                nextKey = if (pageNumber == totalPages) null else pageNumber + 1 // define a chave próxima como nula se a lista de filmes estiver vazia, caso contrário, adiciona 1
            )
        } catch (exception: Exception) { // captura qualquer exceção
            LoadResult.Error(exception)
        }
    }
    companion object{ // objeto companheiro para definir constantes
        private const val LIMIT = 20 // limite de resultados por página
    }

}
/*
O que está acontecendo aqui?

Essa classe MovieSimiliarPagingSource tem um papel importante no seu app: ela é responsável por
carregar os filmes similares aos poucos (de forma paginada), à medida que o usuário vai rolando a tela.

Imagina que você está vendo uma lista de filmes, e o app vai mostrando mais filmes conforme você
vai descendo. Esse comportamento de carregar mais filmes conforme a necessidade é o que a classe faz aqui.

Vamos explicar por partes:
1. Herança da PagingSource:

A classe MovieSimiliarPagingSource herda de uma classe chamada PagingSource. Isso significa que ela
vai se comportar como uma "fonte de dados" que vai carregar as informações em páginas.
Ela vai trabalhar com dois tipos de dados:

    Int (para o número da página).

    Movie (para os filmes que você vai mostrar).

Ela tem que seguir um padrão de como carregar esses filmes e de como lidar com as páginas.

2. Construtor da Classe:

O construtor da classe recebe dois parâmetros:

    remoteDataSource: Esse é o lugar de onde ela vai pegar as informações. Aqui, ela está recebendo
    a interface MovieDetailsRemoteDataSource (que já vimos antes). Esse objeto vai ser usado para
     pedir à API os filmes semelhantes.

    movieId: O ID do filme. Isso é necessário porque queremos pegar filmes similares ao filme de ID
     que foi passado.

     3. Função getRefreshKey:

Essa função serve para obter a chave de atualização quando o usuário tenta voltar ou atualizar a lista.

    Quando a pessoa rola a lista para cima ou para baixo, a função usa a posição âncora (onde a tela
    estava no momento) e tenta descobrir qual é a página mais próxima daquela posição.

    Ela vai usar a chave anterior ou próxima da página para carregar a nova lista.

4. Função load:

A função mais importante! 😎

Essa função é a que vai carregar os filmes. Ela é chamada quando o usuário começa a rolar a tela e
precisa ver mais filmes.

Aqui está o que ela faz:

    Número da Página: Ela pega o número da página a partir do parâmetro params.key (ou começa com a
    página 1 se não houver valor).

    Chama a API: Com o remoteDataSource, ela chama a função getMoviesSimilar passando o movieId e o
     número da página. Isso vai trazer os filmes semelhantes.

    Criação da Página de Resultados: Depois de receber os filmes, ela cria um novo objeto
    LoadResult.Page, que vai conter:

        data: Uma lista de filmes convertidos para o modelo Movie usando a função toMovie().

        prevKey: A chave da página anterior. Se a página for a 1, não tem página anterior (nula).

        nextKey: A chave da próxima página. Se a lista de filmes estiver vazia, não existe próxima
        página.

Tudo isso é feito dentro de um bloco try-catch para garantir que se houver um erro, ele será tratado
e não vai travar o app.

5. O que é o companion object e o LIMIT?

Dentro da classe, existe um companion object, que é como se fosse uma "parte estática" da classe onde
você pode guardar constantes que não mudam.

    O LIMIT é o número máximo de filmes por página. Aqui, é definido como 20. Isso significa que cada
     vez que você pedir filmes similares, vai receber até 20 filmes por vez.

Resumo do que a classe faz:

    A classe MovieSimiliarPagingSource é uma fonte de dados paginada para buscar filmes semelhantes.

    Ela vai carregar filmes aos poucos, conforme o usuário rola a tela para baixo.

    Quando o usuário quer ver mais filmes, ela vai:

        Chamar a API para pegar os filmes semelhantes.

        Converter esses filmes para o formato Movie.

        Mostrar os resultados de forma paginada, com a chave para as páginas anteriores e seguintes.

    E ela vai garantir que tudo aconteça de forma suave, tratando possíveis erros que podem acontecer
    na comunicação com a internet.

Por que tudo isso é importante?

Essa classe permite que o app mostre filmes similares aos poucos, sem sobrecarregar a memória do
dispositivo e sem travar o app. Quando o usuário chega no final da lista, ele pode continuar vendo
mais filmes sem perceber o carregamento acontecendo.

* */