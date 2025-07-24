package br.com.movieapp.movie_popula_feature_data_repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_popula_feature_data_domain_repository.MoviePopularRepository
import br.com.movieapp.movie_popula_feature_data_domain_source.MoviePopularRemoteDataSource
import br.com.movieapp.paging.MoviePagingSource
import kotlinx.coroutines.flow.Flow

class MoviePopularRepositoryImpl constructor( // Essa classe implementa a interface MoviePopularRepository
    private val remoteDataSource: MoviePopularRemoteDataSource // Essa classe recebe o remoteDataSource como parâmetro
) : MoviePopularRepository { // Essa classe implementa a interface MoviePopularRepository
    override fun getPopularMovies(): PagingSource<Int, Movie> { // Essa função retorna um Flow de PagingData de Movie
       return MoviePagingSource(remoteDataSource)
    }

}
// Essa classe serve para implementar a lógica de negócios da aplicação, que é a lógica que define como os dados são processados e manipulados

/*
📦 O que essa classe faz?

Imagina que o aplicativo é uma locadora de filmes online, e tem um lugar na internet com uma lista
 gigante de filmes populares (isso é o que chamamos de fonte remota ou remoteDataSource).

Mas… se você tentar pegar todos os filmes de uma vez, o celular pode ficar lento. Então a gente faz o seguinte:
🎲 Essa classe funciona como:

Um pegador de filmes aos pouquinhos, tipo páginas de um livro.

💡 Explicando passo a passo:

A classe se chama MoviePopularRepositoryImpl → um nome chique pra dizer que ela é quem pega os filmes populares.

Ela usa um ajudante especial chamado remoteDataSource → que sabe onde os filmes estão guardados na internet.

A função getPopularMovies diz:

“Me dá os filmes em pedacinhos, por página, pra não travar tudo de uma vez.”

Pra isso, ela usa uma ferramenta mágica chamada Pager que:

Divide os filmes em páginas (como páginas de um caderno).

Pega essas páginas uma por vez.

E devolve tudo em forma de um fluxo de filmes (o Flow<PagingData<Movie>>).

🧠 Em resumo bem simples:

Essa classe:

Vai buscar os filmes populares de pouquinho em pouquinho, do jeito certo, e entrega eles
organizadinhos pro app mostrar na tela. Ela é tipo um entregador que traz uma caixa de cada vez, em
vez de trazer tudo de uma vez só e deixar cair no chão.*/
