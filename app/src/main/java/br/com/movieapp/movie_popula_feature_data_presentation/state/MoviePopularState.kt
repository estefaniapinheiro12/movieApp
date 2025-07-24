package br.com.movieapp.movie_popula_feature_data_presentation.state

import androidx.paging.PagingData
import br.com.movieapp.modelDomain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MoviePopularState ( // data class para encapsular o estado da tela
    val movies: Flow<PagingData<Movie>> = emptyFlow() // flow vazio para inicializar o estado da tela
)

/*
📦 O que é essa data class MoviePopularState?

Essa classe é como uma caixinha mágica que guarda o estado atual da tela onde aparecem os filmes populares.

Ou seja, ela diz:

    "Ei, aqui tá o que você precisa mostrar na tela agora!"

🧠 Explicando item por item:

data class MoviePopularState(
    val movies: Flow<PagingData<Movie>> = emptyFlow()
)

🔄 Flow<PagingData<Movie>>

    Um Flow é como um rio de dados que vai mandando as coisas aos pouquinhos.

    PagingData<Movie> é um tipo especial que carrega os filmes por partes, tipo:

        Página 1: 10 filmes

        Página 2: mais 10

        E assim vai…

💡 Isso evita travar o app carregando tudo de uma vez.
🕳️ emptyFlow()

    Começa com nenhum dado, ou seja, a tela começa vazia.

    Depois, quando os dados chegam, essa "caixinha" vai se atualizando com os filmes.

🌟 E por que usar essa classe?

Porque no Jetpack Compose (e até no MVVM), a gente gosta de separar bem as responsabilidades. Essa classe:

    📲 Guarda o que vai ser mostrado

    🔄 Permite atualizar a tela automaticamente quando os filmes chegam

    🧼 Começa limpinha, sem erro ou bagunça
* */