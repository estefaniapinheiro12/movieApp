package br.com.movieapp.movie_detail_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_detail_feature.presentation.state.MovieDetailState
import br.com.movieapp.movie_favorite_movie.domain.usecase.AddMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_movie.domain.usecase.DeleteMovieFavoriteUseCase
import br.com.movieapp.movie_favorite_movie.domain.usecase.IsMovieFavoriteUseCase
import br.com.movieapp.util.Constants
import br.com.movieapp.util.ResultData
import br.com.movieapp.util.UtilFunctions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel // anotação HiltViewModel indica que essa classe é um ViewModel que será injetado pelo Hilt
class MovieDetailViewModel @Inject constructor( // classe MovieDetailViewModel que representa o ViewModel, o Inject é usado para injeção de dependência, o construtor recebe uma instância de GetMovieDetailsUseCase
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase, // o GetMovieDetailsUseCase é uma classe que contém a lógica de negócios para obter os detalhes do filme
    private val addMovieFavoriteUseCase: AddMovieFavoriteUseCase, // o AddMovieFavoriteUseCase é uma classe que contém a lógica de negócios para adicionar um filme aos favoritos
    private val deleteMovieFavoriteUseCase: DeleteMovieFavoriteUseCase, // o DeleteMovieFavoriteUseCase é uma classe que contém a lógica de negócios para remover um filme dos favoritos
    private val isMovieFavoriteUseCase: IsMovieFavoriteUseCase, // o IsMovieFavoriteUseCase é uma classe que contém a lógica de negócios para verificar se um filme é favorito
    savedStateHandle: SavedStateHandle // o SavedStateHandle é uma classe que armazena o estado da tela, o savedStateHandle é passado como parâmetro para o construtor

) : ViewModel() {// ViewModel é uma classe do Android Architecture Components que armazena e gerencia dados relacionados à interface do usuário de forma consciente ao ciclo de vida

    var uiState by mutableStateOf(MovieDetailState()) // uiState é uma variável que armazena o estado
        // atual da interface do usuário, inicializada com MovieDetailState(), que provavelmente contém propriedades como isLoading, movieDetails e error
        private set // o modificador private set significa que o valor só pode ser alterado dentro da classe MovieDetailViewModel

    private val movieId =
        savedStateHandle.get<Int>(key = Constants.MOVIE_DETAIL_ARGUMENT_KEY) // variavel privada que armazena o id do filme, obtido do SavedStateHandle, o SavedStateHandle é uma classe que armazena o estado da tela, o key é a chave usada para obter o valor do estado salvo

    init { // bloco init é chamado quando a classe é instanciada
        movieId?.let { safeMovieId -> // verifica se o movieId não é nulo, se não for nulo, atribui o valor a safeMovieId
            checkedFavorite(MovieDetailEvent.CheckedFavorite(safeMovieId)) // chama a função checkedFavorite passando o evento CheckedFavorite, que verifica se o filme é favorito
            getMovieDetail(MovieDetailEvent.GetMovieDetail(safeMovieId)) // chama a função getMovieDetail passando o evento GetMovieDetail, que obtém os detalhes do filme
        }
    }

    private fun checkedFavorite(checkedFavorite: MovieDetailEvent.CheckedFavorite) { // função  que recebe um evento
        event(checkedFavorite) // chama a função event passando o evento recebido
    }

    private fun getMovieDetail(getMovieDetail: MovieDetailEvent.GetMovieDetail) { // função  que recebe um evento do
        // tipo MovieDetailEvent.GetMovieDetail, o MovieDetailEvent é uma classe selada que representa eventos relacionados aos detalhes do filme
        event(getMovieDetail) // chama a função event passando o evento recebido
    }

    fun onAddFavorite(movie: Movie) { // função pública que recebe um filme do tipo Movie
        if (uiState.iconColor == Color.White) { // verifica se a cor do ícone é branca
            event(MovieDetailEvent.AddFavorites(movie = movie)) // chama a função event passando o evento de adicionar favorito
        } else { // se a cor do ícone não for branca
            event(MovieDetailEvent.RemoveFavorites(movie = movie)) // chama a função event passando o evento de remover favorito
        }
    }

    private fun event(event: MovieDetailEvent) { // função privada que recebe um evento do tipo MovieDetailEvent
        when (event) { // quando o evento for do tipo MovieDetailEvent
            is MovieDetailEvent.AddFavorites -> { // Se o evento for de adicionar favorito
                viewModelScope.launch { // lança uma nova corrotina no escopo do ViewModel, corrotina serve para executar tarefas assíncronas, assicrona quer dizer que não bloqueia a thread principal
                    addMovieFavoriteUseCase.invoke( // chama a função invoke do addMovieFavoriteUseCase, que é uma função suspensa
                        params = AddMovieFavoriteUseCase.Params( // passa os parâmetros necessários para a função
                            movie = event.movie // o filme é obtido do evento
                        )
                    )
                        .collectLatest { result -> // coleta o resultado da função invoke, que retorna um Flow de ResultData
                            when (result) { // quando o resultado for do tipo ResultData
                                is ResultData.Success -> { // se o resultado for do tipo Success
                                    uiState =
                                        uiState.copy(iconColor = Color.Red) // cria uma nova instância de MovieDetailState com a cor do ícone vermelha
                                }

                                is ResultData.Failure -> { // se o resultado for do tipo Failure
                                    UtilFunctions.logError(
                                        "DETAIL",
                                        "Erro ao cadastrar filme"
                                    ) // chama a função logError para registrar o erro
                                }

                                is ResultData.Loading -> {} // se o resultado for do tipo Loading, não faz nada
                            }
                        }
                }
            }

            is MovieDetailEvent.CheckedFavorite -> { // se o evento for do tipo CheckedFavorites
                viewModelScope.launch { // lança uma nova corrotina no escopo do ViewModel
                    isMovieFavoriteUseCase.invoke( // chama a função invoke do isMovieFavoriteUseCase, que é uma função suspensa
                        params = IsMovieFavoriteUseCase.Params( // passa os parâmetros necessários para a função
                            movieId = event.movieId // o movieId é obtido do evento
                        )
                    )
                        .collectLatest { result -> // coleta o resultado da função invoke, que retorna um Flow de ResultData
                            when (result) { // quando o resultado for do tipo ResultData
                                is ResultData.Success -> { // se o resultado for do tipo Success
                                    uiState =
                                        if (result.data == true) { // verifica se o resultado é verdadeiro
                                            uiState.copy(iconColor = Color.Red) // cria uma nova instância de MovieDetailState com a cor do ícone vermelha
                                        } else { // se o resultado for falso
                                            uiState.copy(iconColor = Color.White) // cria uma nova instância de MovieDetailState com a cor do ícone branca
                                        }
                                }

                                is ResultData.Failure -> { // se o resultado for do tipo Failure
                                    UtilFunctions.logError(
                                        "DETAIL",
                                        "Erro ao cadastrar filme"
                                    ) // chama a função logError para registrar o erro
                                }

                                is ResultData.Loading -> {} // se o resultado for do tipo Loading, não faz nada
                            }
                        }
                }
            }

            is MovieDetailEvent.RemoveFavorites -> { // se o evento for do tipo RemoveFavorites
                viewModelScope.launch { // lança uma nova corrotina no escopo do ViewModel
                    deleteMovieFavoriteUseCase.invoke( // chama a função invoke do deleteMovieFavoriteUseCase, que é uma função suspensa
                        params = DeleteMovieFavoriteUseCase.Params( // passa os parâmetros necessários para a função
                            movie = event.movie // o filme é obtido do evento
                        )
                    )
                        .collectLatest { result -> // coleta o resultado da função invoke, que retorna um Flow de ResultData
                            when (result) { // quando o resultado for do tipo ResultData
                                is ResultData.Success -> { // se o resultado for do tipo Success
                                    uiState =
                                        uiState.copy(iconColor = Color.White) // cria uma nova instância de MovieDetailState com a cor do ícone branca
                                }

                                is ResultData.Failure -> { // se o resultado for do tipo Failure
                                    UtilFunctions.logError(
                                        "DETAIL",
                                        "Erro ao cadastrar filme"
                                    ) // chama a função logError para registrar o erro
                                }

                                is ResultData.Loading -> {} // se o resultado for do tipo Loading, não faz nada
                            }
                        }
                }
            }

            is MovieDetailEvent.GetMovieDetail -> { // se o evento for do tipo GetMovieDetail
                viewModelScope.launch { // lança uma nova corrotina no escopo do ViewModel
                    val resultData =
                        getMovieDetailsUseCase.invoke( // chama a função invoke do getMovieDetailsUseCase, que é uma função suspensa
                            params = GetMovieDetailsUseCase.Params( // passa os parâmetros necessários para a função
                                movieId = event.movieId, // o movieId é obtido do evento
                                pagingConfig = pagingConfig()
                            )
                        )// coleta o resultado da função invoke, que retorna um Flow de ResultData
                    when (resultData) { // quando o resultado for do tipo ResultData
                        is ResultData.Success -> { // se o resultado for do tipo Success
                            uiState = uiState.copy(
                                // cria uma nova instância de MovieDetailState com os dados do resultado
                                isLoading = false, // define isLoading como false
                                movieDetails = resultData.data?.second, // obtém os detalhes do filme do resultado,
                                // o resulData.data é um par, onde o primeiro elemento é o resultado e o segundo elemento é a lista de resultados
                                results = resultData.data?.first
                                    ?: emptyFlow(), // obtém a lista de resultados do resultado, se for nulo, define como um Flow vazio

                            )
                        }

                        is ResultData.Failure -> { // se o resultado for do tipo Failure
                            uiState =
                                uiState.copy( // cria uma nova instância de MovieDetailState com os dados do resultado
                                    isLoading = false, // define isLoading como false
                                    error = resultData.e?.message.toString() // obtém a mensagem de erro do resultado
                                )
                            UtilFunctions.logError( // chama a função logError para registrar o erro
                                "DETAIL-ERROR", // registra o erro com a tag "DETAIL-ERROR"
                                resultData.e?.message.toString() // obtém a mensagem de erro do resultado
                            )
                        }

                        is ResultData.Loading -> { // se o resultado for do tipo Loading
                            uiState =
                                uiState.copy( // cria uma nova instância de MovieDetailState com os dados do resultado
                                    isLoading = true // define isLoading como true
                                )
                        }
                    }
                }
            }
        }
    }
}

private fun pagingConfig(): PagingConfig {
    return PagingConfig(
        pageSize = 20,
        initialLoadSize = 20
    )
}
