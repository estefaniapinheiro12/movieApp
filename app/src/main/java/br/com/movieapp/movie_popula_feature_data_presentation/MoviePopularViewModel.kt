package br.com.movieapp.movie_popula_feature_data_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import br.com.movieapp.movie_popula_feature_data_domain_usecase.GetPopularMoviesUseCase
import br.com.movieapp.movie_popula_feature_data_presentation.state.MoviePopularState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviePopularViewModel @Inject constructor( // injeção de dependência do ViewModel
    getPopularMoviesUseCase: GetPopularMoviesUseCase // injeção de dependência do caso de uso
): ViewModel() { // ViewModel é uma classe do Android que armazena e gerencia dados relacionados à interface do usuário
    var uiState by mutableStateOf(MoviePopularState()) // uiState é uma variável que armazena o estado da interface do usuário
        private set // set privado para que não possa ser alterado fora do ViewModel

    init { // bloco de inicialização do ViewModel
        val movies = getPopularMoviesUseCase.invoke(
            GetPopularMoviesUseCase.Params( // parâmetros necessários para o caso de uso
                pagingConfig = pagingConfig() // configuração de paginação definida no estado da interface do usuário
            )
        ) // chama o caso de uso para obter os filmes populares
            .cachedIn(viewModelScope) // armazenamento em cache dos dados obtidos
        uiState = uiState.copy(movies = movies) // copia o estado atual da interface do usuário e atualiza a lista de filmes
    }
    private fun pagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
        )
    }

}

/*
🧠 O que é essa classe MoviePopularViewModel?

Ela é como o “cérebro da tela de filmes populares”.
É quem busca os dados, guarda o que foi buscado e avisa a tela quando algo novo acontecer.
Vamos quebrar por partes como se fosse uma historinha:
🍿 1. class MoviePopularViewModel(...) : ViewModel()

    A classe ViewModel é tipo um chefe de memória que não se perde quando você gira o celular ou fecha e volta pra tela.

    Ela cuida da lógica e protege os dados da interface.

💉 2. @HiltViewModel e @Inject constructor(...)

    Essas anotações servem pra dizer:

    “Hilt, me ajuda a construir essa classe! Você já sabe onde pegar o que eu preciso!”

    No caso, ele precisa do GetPopularMoviesUseCase, que é quem sabe como buscar os filmes populares.

📦 3. var uiState by mutableStateOf(MoviePopularState())

    uiState é onde guardamos o estado da tela.

    mutableStateOf é tipo um baú mágico: sempre que algo mudar nele, a tela se atualiza sozinha!

    private set quer dizer: "só o ViewModel pode mudar esse baú".

🚀 4. init { ... } → Bloco de inicialização

Quando essa classe é criada, ela automaticamente faz o seguinte:

val movies = getPopularMoviesUseCase.invoke()

    Isso chama o caso de uso, que vai buscar os filmes lá na internet ou banco de dados.

.cachedIn(viewModelScope)

    Isso diz: “Guarda os resultados em cache enquanto o ViewModel existir, pra não ficar buscando tudo de novo toda hora.”

🎁 5. uiState = uiState.copy(movies = movies)

    Aqui ele atualiza o uiState dizendo:

    “Ei tela, agora temos filmes! Pode mostrar!”

Como a tela está observando esse estado, ela se atualiza automaticamente.
👶 Explicando pra uma criança:

    “Essa classe é como um entregador de pipoca 🍿.
    Ele vai buscar os filmes que estão fazendo sucesso, guarda direitinho em uma caixa (o uiState) e fala pra tela:

    — Olha, chegou filme novo! Mostra aí pros espectadores!”
* */