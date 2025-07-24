package br.com.movieapp.movie_favorite_movie.domain.usecase

// Importações necessárias para usar o modelo de filme, repositório, retorno de resultado e fluxo de dados assíncronos.
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_favorite_movie.domain.repository.MovieFavoriteRepository
import br.com.movieapp.util.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

// Interface que define o contrato do caso de uso de obter filmes favoritos.
interface GetMoviesFavoriteUseCase {
    // Define uma função suspensa: pode ser pausada e retomada dentro de corrotinas.
    suspend operator fun invoke(): Flow<List<Movie>>
    // operator fun invoke(): permite que esse caso de uso seja chamado como uma função comum: useCase()
    // Flow<List<Movie>>: retorna um Flow que emite uma lista de filmes favoritos.
    // Ou seja, ao "coletar" esse Flow, você receberá atualizações (se houverem) da lista de filmes favoritos.
}
// Implementação concreta da interface acima.
class GetMoviesFavoriteUseCaseImpl @Inject constructor(
    // O @Inject permite que o Hilt forneça automaticamente o repositório ao criar essa classe.
    private val movieFavoriteRepository: MovieFavoriteRepository
    // O repositório fornece os dados (neste caso, os filmes favoritos) de alguma fonte (banco local, por exemplo).
) : GetMoviesFavoriteUseCase {

    override suspend fun invoke(): Flow<List<Movie>> {
        return  try {
            movieFavoriteRepository.getMovies()
        } catch (e: Exception){
            emptyFlow()
        }
    }
}
