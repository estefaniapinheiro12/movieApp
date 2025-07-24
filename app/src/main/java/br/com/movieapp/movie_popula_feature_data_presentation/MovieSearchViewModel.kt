package br.com.movieapp.movie_popula_feature_data_presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import br.com.movieapp.movie_popula_feature_data_presentation.state.MovieSearchState
import br.com.movieapp.search_movie_feature_domain_usecases.GetMovieSearchUseCase
import br.com.movieapp.search_movie_feature_presentation.MovieSearchEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel // Anotação HiltViewModel para indicar que esta classe é um ViewModel gerenciado pelo Hilt
class MovieSearchViewModel @Inject constructor( // Injeção de dependência do ViewModel, Inject serve para indicar que o Hilt deve fornecer uma instância dessa classe
    private val getMovieSearchUseCase: GetMovieSearchUseCase // variavel que recebe o caso de uso GetMovieSearchUseCase, o GetMovieSearchUseCase é uma classe que contém a lógica de negócios para buscar filmes
) : ViewModel() {

    var uiState by mutableStateOf(MovieSearchState()) // uiState é uma variável de estado que representa
        // o estado atual da interface do usuário. O modificador by mutableStateOf cria uma propriedade
        // observável que notifica a interface do usuário quando seu valor muda. O valor inicial é um objeto MovieSearchState vazio.
        private set // Variável de estado que representa o estado atual da interface do usuário, inicializada
// com um objeto MovieSearchState vazio. O modificador private set impede que o estado seja modificado fora desta classe.

    fun fetch(query: String = "") { // Função fetch que busca filmes com base na consulta fornecida. Se nenhuma consulta for fornecida, a consulta padrão é uma string vazia.
        val movies = getMovieSearchUseCase.invoke( // variável movies recebe o resultado da chamada do caso de uso getMovieSearchUseCase, invoke é um método que executa a lógica de negócios do caso de uso.
            params = GetMovieSearchUseCase.Params( // parâmetros necessários para a execução do caso de uso, encapsulados em um objeto Params.
                query = query, // atributo query do objeto Params recebe o valor da consulta fornecida como argumento para a função fetch.
               pagingConfig =  pagingConfig()
            )
        ).cachedIn(viewModelScope) // O resultado do caso de uso é armazenado em cache no escopo do ViewModel, permitindo que os dados sejam reutilizados sem precisar buscar novamente.
        uiState = uiState.copy(movies = movies) // Atualiza o estado da interface do usuário com a nova lista de filmes, usando o método copy para criar uma cópia do estado atual e substituir o atributo movies.
    }
    fun event(event: MovieSearchEvent) { // Essa função serve para lidar com eventos relacionados à busca de filmes, como a entrada de uma nova consulta.
        uiState = when(event) { // Quando um evento é recebido, o estado da interface do usuário é atualizado com base no tipo de evento.
            is MovieSearchEvent.EnteredQuery -> { // Se o evento for do tipo EnteredQuery, que representa a entrada de uma nova consulta.
                uiState.copy( query = event.value) // Atualiza o estado da interface do usuário, copiando o estado atual e substituindo o atributo query pelo valor da nova consulta.
            }
        }
    }
    private fun pagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 20,
            initialLoadSize = 20
        )
    }
}
/*
🌟 Título: "O Guardião das Buscas de Filmes"

Era uma vez, em uma terra chamada App dos Filmes 🍿📱, um personagem muito importante chamado
MovieSearchViewModel. Ele era como um guardião inteligente que ajudava as pessoas a encontrarem seus
 filmes favoritos só com um pedido! 🎩✨
🧙‍♂️ O guardião mágico

Esse guardião foi criado com uma poção especial chamada @HiltViewModel, que dava poderes mágicos para
ele viver dentro do aplicativo. A poção foi feita por um mago chamado Hilt, que dizia:

    “Toda vez que precisarem de um guardião de buscas, eu crio um direitinho pra vocês!” 🧪🔮

📚 O Livro de Instruções

O guardião tinha um livro de instruções chamado GetMovieSearchUseCase. Era nele que estava toda a
sabedoria sobre como buscar filmes pelo mundo. Então, o guardião carregava esse livro pra todo lado 📖✨
🎛️ O Estado da Tela

O guardião também tinha uma tela mágica chamada uiState. Nela, ele escrevia o que estava acontecendo:

    Qual é o nome do filme que as pessoas estão procurando? 🎬

    Já buscaram? Já apareceu a lista de filmes? 🧾

Mas só o guardião podia mexer nessa tela, porque ela era muito especial e delicada. Então, ele
trancava com um cadeado que dizia private set 🔐🧼
🛎️ Alguém pediu um filme?

Quando alguém digitava "Quero ver um filme!", o guardião usava um feitiço chamado fetch(query).

Ele abria o livro de instruções (getMovieSearchUseCase) e dizia:

    “Ei, livro mágico, encontre todos os filmes que têm esse nome aqui!” 📣

O livro fazia a mágica e entregava uma lista de filmes novinha! O guardião, então, colava essa lista
na sua tela mágica (uiState) pra todo mundo ver ✨📜

Ah, e ele guardava isso tudo num baú com tampa (cachedIn(viewModelScope)) pra não precisar procurar
de novo se alguém pedisse o mesmo filme de novo! 📦🧠
💌 Ouvindo os pedidos das pessoas

Às vezes, as pessoas não clicavam em buscar. Só digitavam o nome do filme.

Aí, o guardião escutava com a orelha afiada e dizia:

    "Ah! Alguém acabou de escrever ‘Harry Potter’! Vou guardar esse nome pra usar depois!" 🧙‍♀️

Ele fazia isso com a magia chamada event(event: MovieSearchEvent). Quando via que era um evento do
tipo EnteredQuery, ele escrevia o nome na sua tela mágica também.
🌈 Moral da história:

MovieSearchViewModel é o guardião que:

    escuta as pessoas digitando os nomes dos filmes 🗣️

    pede para o livro mágico (o caso de uso) buscar os filmes 📖✨

    atualiza a tela com a lista de filmes encontrados 🎥📋

Ele cuida de tudo com carinho, sempre usando suas magias de forma organizada e silenciosa, pra
deixar o app funcionando lindamente 💜🌟
* */