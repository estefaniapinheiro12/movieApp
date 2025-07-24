package br.com.movieapp.movie_popula_feature_data_presentation.state

import androidx.paging.PagingData
import br.com.movieapp.modelDomain.MovieSearch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MovieSearchState( // data classe para gerenciar o estado da tela de pesquisa
    val query: String = "", // string que representa a consulta de pesquisa
    val movies: Flow<PagingData<MovieSearch>> = emptyFlow() // fluxo de dados que representa a lista de filmes pesquisados

)

/*
🌟 Título: "O Livro de Estado da Busca"

Era uma vez, num castelo encantado chamado movie_popula_feature_data_presentation.state, um livro
mágico chamado MovieSearchState 📖✨

Esse não era um livro comum! Ele era um livro de registros usado pelo Guardião da Busca (lembra do
ViewModel?) para anotar tudo o que o usuário estava fazendo na busca de filmes 🔍🎬
📌 O que tinha escrito nesse livro?

Esse livro tinha duas páginas principais:
1. 📄 Página da Consulta (query)

Era onde o livro anotava o que o usuário escreveu na caixinha de busca.
Se a pessoa escreveu “Shrek”, o livro anotava:
🖊️ query = "Shrek"

Se ninguém escreveu nada ainda, a página ficava em branco, assim:
query = ""
2. 🎞️ Página dos Filmes Encontrados (movies)

Essa era uma página mágica que mostrava a lista de filmes encontrados!
Mas como os filmes vinham de longe, em páginas, o livro usava um encantamento chamado
Flow<PagingData> — era como uma fonte que jorrava os filmes aos poucos, conforme o usuário descia a tela 📜✨

Quando o livro estava vazio, ele usava um feitiço chamado emptyFlow() que significava:

    "Ainda não temos filmes! 🫣"

📒 Por que esse livro era uma data class?

Porque o Guardião queria copiar e atualizar só algumas partes do livro sem precisar reescrever tudo.
Por exemplo:

    "Ah, o usuário digitou outro nome? Beleza, copia o livro, mas troca só a query!" 📝🔄

🌈 Moral da história:

O MovieSearchState é o livro onde tudo que acontece na tela de busca é registrado:

    O que o usuário está procurando (query)

    E os filmes que apareceram (movies)

É com ele que o app sabe o que mostrar e como reagir ao toque de cada dedo do usuário! 📲✨
* */