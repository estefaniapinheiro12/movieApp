package br.com.movieapp.movie_favorite_movie.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.movieapp.movie_favorite_movie.domain.usecase.GetMoviesFavoriteUseCase
import br.com.movieapp.movie_favorite_movie.presentation.state.MovieFavoriteState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel // Anotação HiltViewModel para indicar que esta classe é um ViewModel gerenciado pelo Hilt, que serve para injeção de dependências.
class MovieFavoriteViewModel @Inject constructor( // classe MovieFavoriteViewModel que serve como ViewModel para a tela de favoritos de filmes.
// Injet constructor é usado para injeção de dependências.
    private val getMoviesFavoriteUseCase: GetMoviesFavoriteUseCase // Dependência do caso de uso para obter filmes favoritos.
) : ViewModel() { // Herança da classe ViewModel do Android, que fornece a lógica de negócios para a interface do usuário.
    // Serve para armazenar e gerenciar dados relacionados à interface do usuário de forma que sobreviva a mudanças de configuração, como rotações de tela.
    var uiState by mutableStateOf(MovieFavoriteState()) // uiState é uma propriedade observável que representa o estado atual da interface do usuário.
        private set // Define o setter como privado para que ele não possa ser modificado fora da classe.

    init { // Bloco init é chamado quando a classe é instanciada.
        fetch() // Chama o método fetch() para buscar os filmes favoritos assim que o ViewModel é criado.
    }

    private fun fetch() { // Método privado fetch() que inicia a coleta de filmes favoritos.
        viewModelScope.launch { // Lança uma nova coroutine no escopo do ViewModel, que é cancelada automaticamente quando o ViewModel é destruído.
            val movies = getMoviesFavoriteUseCase.invoke()
            uiState = uiState.copy(movies = movies) // Atualiza o estado da interface do usuário com a lista de filmes favoritos coletados.
        }
    }
}
