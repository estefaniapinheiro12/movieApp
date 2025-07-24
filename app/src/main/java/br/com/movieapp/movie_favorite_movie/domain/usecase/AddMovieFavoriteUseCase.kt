package br.com.movieapp.movie_favorite_movie.domain.usecase

import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_favorite_movie.domain.repository.MovieFavoriteRepository
import br.com.movieapp.util.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


interface AddMovieFavoriteUseCase { // Define um contrato: qualquer classe que implementar essa interface deve dizer como adicionar um filme como favorito.
    suspend operator fun invoke(params: Params): Flow<ResultData<Unit>> //  é uma função suspensa, pode ser chamada dentro de corrotinas.
    // Ou seja, ela pode ser usada para fazer coisas demoradas — como acessar a internet, ler um banco de dados, ou esperar alguma resposta — sem travar o app.
    // operator fun invoke(...): isso permite chamar o caso de uso como se fosse uma função: useCase(params)
    // params: Params: recebe um Params, que é um data class contendo o filme.
    // Flow<ResultData<Unit>>: retorna um Flow que emite um ResultData, indicando sucesso ou erro.
    data class Params(val movie: Movie) // Define os parâmetros da operação.
}

class AddMovieFavoriteUseCaseImpl @Inject constructor( // Classe concreta que implementa a interface AddMovieFavoriteUseCase.
    // @Inject constructor(...): diz ao Hilt que pode injetar o MovieFavoriteRepository automaticamente.
    private val movieFavoriteRepository: MovieFavoriteRepository // dependência usada para acessar o banco/local/dados.
): AddMovieFavoriteUseCase {
    override suspend fun invoke(params: AddMovieFavoriteUseCase.Params): Flow<ResultData<Unit>> { // Sobrescreve a função da interface. a funçãoa acima
        return flow { // Cria um Flow, ou seja, uma emissão assíncrona de dados (boa prática para manipulação de estados e reatividade).
            try {
                val insert = movieFavoriteRepository.insert(params.movie) // Chama o repositório para inserir o filme nos favoritos.
                // params.movie: pega o filme passado como argumento.
                emit(ResultData.Success(insert)) // Emite um resultado de sucesso, encapsulando o retorno da operação.
                // Isso é útil para ViewModel saber se deu certo ou não.L
            } catch (e: Exception) {
                emit(ResultData.Failure(e))
            }
        }.flowOn(Dispatchers.IO) // Garante que a execução desse Flow será feita em uma thread de I/O, que é ideal para chamadas de banco ou disco (evita travar a UI).
    }
}
