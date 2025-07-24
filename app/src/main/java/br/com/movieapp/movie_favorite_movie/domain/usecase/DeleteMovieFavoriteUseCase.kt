package br.com.movieapp.movie_favorite_movie.domain.usecase

import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_favorite_movie.domain.repository.MovieFavoriteRepository
import br.com.movieapp.util.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


interface DeleteMovieFavoriteUseCase {
    // Define um contrato: qualquer classe que implementar essa interface deve fornecer a lógica para deletar um filme dos favoritos.

    suspend operator fun invoke(params: Params): Flow<ResultData<Unit>>
    // É uma função suspensa: pode ser chamada dentro de corrotinas.
    // Isso significa que ela pode executar operações demoradas (como acessar banco de dados ou rede) sem travar a UI.
    // operator fun invoke(...): permite chamar o caso de uso como se fosse uma função: useCase(params).
    // params: Params é um data class contendo os dados necessários para a operação — neste caso, o filme.
    // Flow<ResultData<Unit>>: retorna um Flow que emite um ResultData indicando o sucesso ou erro da operação.

    data class Params(val movie: Movie)
    // Classe que encapsula os parâmetros da operação. Aqui, apenas um filme.
}

class DeleteMovieFavoriteUseCaseImpl @Inject constructor(
    // Implementação concreta do caso de uso DeleteMovieFavoriteUseCase.
    // @Inject permite que o Hilt injete a dependência automaticamente via o construtor.

    private val movieFavoriteRepository: MovieFavoriteRepository
    // Repositório responsável por acessar a fonte de dados (banco local, API etc.).
) : DeleteMovieFavoriteUseCase {

    override suspend fun invoke(params: DeleteMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> {
        // Sobrescreve a função definida na interface. É chamada para deletar o filme dos favoritos.
        return flow {
            // Cria um Flow: uma stream assíncrona que emite valores de forma reativa.
            try {
                val delete = movieFavoriteRepository.delete(params.movie)
                // Chama o repositório para deletar o filme dos favoritos.
                // params.movie acessa o filme passado dentro do objeto Params.
                emit(ResultData.Success(delete))
                // Emite um resultado de sucesso com o retorno da operação.
                // A ViewModel pode usar esse resultado para atualizar a UI.
            }catch (e: Exception) {
                emit(ResultData.Failure(e))
                // Emite um resultado de falha se ocorrer uma exceção durante a operação.
                // Isso permite que a ViewModel trate o erro e informe o usuário.
            }
        }.flowOn(Dispatchers.IO)
        // Define que o Flow será executado na thread de I/O (entrada/saída), ideal para tarefas de banco ou rede.
    }
}
