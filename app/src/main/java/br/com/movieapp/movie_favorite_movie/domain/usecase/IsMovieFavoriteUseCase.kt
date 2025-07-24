package br.com.movieapp.movie_favorite_movie.domain.usecase

// Importações necessárias para acessar o modelo de filme, o repositório, utilitários de resultado e suporte a corrotinas.
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_favorite_movie.domain.repository.MovieFavoriteRepository
import br.com.movieapp.util.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


interface IsMovieFavoriteUseCase {
    // Define o contrato do caso de uso: verifica se um filme é favorito ou não.

    suspend operator fun invoke(params: Params): Flow<ResultData<Boolean>>
    // Função suspensa: pode rodar dentro de uma corrotina (ideal para operações demoradas como acessar banco de dados).
    // operator fun: permite chamar o caso de uso como uma função normal, por exemplo: useCase(params).
    // params: recebe um objeto Params, que contém o ID do filme a ser verificado.
    // Flow<ResultData<Boolean>>: retorna um fluxo que emite um resultado (sucesso ou erro),
    // contendo um Boolean (true = é favorito, false = não é).

    data class Params(val movieId: Int)
    // Classe usada para passar o ID do filme como parâmetro do caso de uso.
}


class IsMovieFavoriteUseCaseImpl @Inject constructor(
    // Implementação concreta da interface IsMovieFavoriteUseCase.
    // @Inject diz ao Hilt para injetar automaticamente a dependência via o construtor.

    private val movieFavoriteRepository: MovieFavoriteRepository
    // O repositório que fornece acesso aos dados (como banco de dados local).
) : IsMovieFavoriteUseCase {

    override suspend fun invoke(params: IsMovieFavoriteUseCase.Params): Flow<ResultData<Boolean>> {
        return flow {
            // Cria um Flow: uma sequência reativa de dados que será executada quando coletada.

            try {
                // Tenta executar o código dentro do bloco try.
                val isFavorite = movieFavoriteRepository.isFavorite(params.movieId)
                // Chama o método do repositório para verificar se o filme (por ID) está marcado como favorito.
                // Retorna um Boolean.
                emit(ResultData.Success(isFavorite))
                // Emite um resultado de sucesso contendo o valor Boolean retornado.
            } catch (e: Exception) {
                // Se ocorrer uma exceção, emite um resultado de falha com a exceção.
                emit(ResultData.Failure(e))
            }
        }.flowOn(Dispatchers.IO)
        // Garante que esse flow será executado em uma thread de I/O (ideal para acessar disco ou banco de dados).
    }
}
