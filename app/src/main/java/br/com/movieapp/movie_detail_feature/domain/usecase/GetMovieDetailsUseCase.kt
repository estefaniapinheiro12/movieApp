package br.com.movieapp.movie_detail_feature.domain.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.modelDomain.MovieDetails
import br.com.movieapp.movie_detail_feature.domain.repository.MoviesDetailsRepository
import br.com.movieapp.util.ResultData
import coil.network.HttpException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okio.IOException
import javax.inject.Inject

interface  GetMovieDetailsUseCase { // Caso de uso para obter detalhes de um filme, casos de uso servem para abstrair a lógica de negócio
     suspend operator fun invoke(params: Params): ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>> // Invoke é um operador que permite chamar a classe como se fosse uma função
    data class Params(val movieId: Int, val pagingConfig: PagingConfig) // Classe interna que representa os parâmetros necessários para obter os detalhes do filme
}
class GetMovieDetailsUseCaseImpl @Inject constructor( // Implementação da interface GetMovieDetailsUseCase
    private val repository: MoviesDetailsRepository // Injeção de dependência do repositório que fornece os detalhes do filme
): GetMovieDetailsUseCase { // Implementa a interface GetMovieDetailsUseCase
    override suspend fun invoke(params: GetMovieDetailsUseCase.Params): ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>> {
        return withContext(Dispatchers.IO) {
            ResultData.Loading
            try {
                val pagingSource = repository.getMovieSimiliar(params.movieId)
                val movieDetails = repository.getMovieDetails(params.movieId)
                val pager = Pager (
                    config = params.pagingConfig,
                    pagingSourceFactory = {
                        pagingSource
                    }
                ).flow
                ResultData.Success(pager to movieDetails)
            } catch (e: Exception) {
                ResultData.Failure(e)
            }
        }
    }
}
/*
interface: Define um contrato para as classes que implementarem essa interface. No caso, a interface
define a funcionalidade que vai buscar os detalhes do filme.

operator fun invoke: Define o método invoke como um operador, o que permite que a interface seja chamada
como uma função. Ou seja, em vez de chamar o método como useCase.invoke(params), podemos chamar
diretamente useCase(params), o que torna o código mais limpo.

Flow<ResultData<Pair<Flow<PagingData<Movie>>, MovieDetails>>>: Retorna um Fluxo (Flow) com um
Resultado (ResultData) que contém uma dupla (Pair). A primeira parte da dupla é outro Fluxo com
dados paginados de filmes (PagingData<Movie>), e a segunda parte são os detalhes do filme (MovieDetails).

data class Params: Define uma classe de dados que contém o parâmetro necessário para buscar os
detalhes do filme. Aqui, é necessário um ID do filme (movieId), que será passado como parâmetro.

class GetMovieDetailsUseCaseImpl: Define a classe que implementa a interface GetMovieDetailsUseCase.

@Inject constructor: A anotação @Inject indica que a dependência MoviesDetailsRepository será injetada
automaticamente pelo framework de injeção de dependências (provavelmente Dagger ou Hilt).

private val repository: A variável repository é do tipo MoviesDetailsRepository, e ela é usada para
obter as informações do filme e filmes semelhantes.

override fun invoke: Implementa o método invoke da interface GetMovieDetailsUseCase.

flow: Cria um fluxo (ou stream) que emite valores ao longo do tempo. Ele permite que o código seja assíncrono e reativo.

try: Inicia um bloco de código que tenta executar as instruções e trata possíveis erros.

emit(ResultData.Loading): Emite um valor representando o estado de "carregamento". Isso pode ser
usado para indicar que os dados estão sendo buscados.

movieDetails: Obtém os detalhes do filme chamando o método getMovieDetails do repositório. O movieId é passado como parâmetro.

movieSimiliar: Obtém filmes semelhantes ao filme solicitado, com paginação configurada para 20 itens por página.

emit(ResultData.Success): Emite um resultado de sucesso, contendo tanto os filmes semelhantes quanto os detalhes do filme como uma dupla (Pair).

catch (e: HttpException): Se ocorrer uma exceção HTTP (por exemplo, erro de rede), um resultado de falha é emitido, contendo a exceção.

catch (e: IOException): Se ocorrer uma exceção de entrada/saída (por exemplo, erro de leitura/escrita), também será emitido um resultado de falha.

flowOn(Dispatchers.IO): Define que o fluxo será executado no dispatcher de IO (entrada/saída). Isso é
importante para garantir que a busca por dados (rede, banco de dados) seja feita de forma assíncrona e fora da thread principal.

Resumo: Esse código define um caso de uso para buscar os detalhes de um filme e filmes semelhantes,
 com o uso de fluxos assíncronos para lidar com a rede de forma eficiente. Ele emite estados como
 "carregando", "sucesso" e "falha", e faz uso de paginação para obter filmes semelhantes. Ele também
  lida com exceções de rede e de entrada/saída de forma apropriada.
* */
