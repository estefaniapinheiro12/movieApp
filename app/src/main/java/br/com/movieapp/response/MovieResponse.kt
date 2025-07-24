package br.com.movieapp.response

import br.com.movieapp.model.MovieResult
import com.google.gson.annotations.SerializedName

data class MovieResponse( // Essa classe representa a resposta da API de filmes
    @SerializedName("page") // Número da página atual
    val page: Int, // Essa variável representa o número da página atual
    @SerializedName("results") // Lista de resultados de filmes
    val movieResults: List<MovieResult>, // Essa variável representa a lista de resultados de filmes
    @SerializedName("total_pages") // Número total de páginas
    val totalPages: Int, // Essa variável representa o número total de páginas
    @SerializedName("total_results") // Número total de resultados
    val totalResults: Int // Essa variável representa o número total de resultados
)

/*
🧒 Como se fosse pra uma criança:

Imagina que você entrou numa loja de filmes online e pediu:

“Ei, me mostra os filmes populares?”

A loja (que é a API) te entrega uma caixa de resposta com as seguintes coisas dentro:

📄 Um número dizendo em que página de filmes você está (tipo página 1 de um álbum)

🎞️ Uma lista de filmes (os resultados que você pediu)

📚 Um número total de páginas, pra você saber se tem mais pra ver

🔢 Um número total de filmes disponíveis no geral

Essa caixa de resposta é o que a gente chama de MovieResponse.
📦 Parte por parte da caixinha:

data class MovieResponse(
    @SerializedName("page")
    val page: Int,

    📖 page: é a página atual que a API está te mostrando.
    Ex: "Você está vendo a página 1."

@SerializedName("results")
val movieResults: List<MovieResult>,

🎥 movieResults: é a lista de filmes que vieram nessa página.
Cada um é um filme com nome, imagem, nota etc.

@SerializedName("total_pages")
val totalPages: Int,

📚 totalPages: é o número total de páginas que existem.
Tipo: “Tem 20 páginas de filmes pra você ver!”

@SerializedName("total_results")
val totalResults: Int
)

🔢 totalResults: é o número total de filmes em todas as páginas.
Tipo: “No total, temos 400 filmes pra mostrar!”
📽️ Imaginação de como isso funciona:

Você → 📲 pede “Filmes populares”

API → 📦 te entrega:

📄 Página: 1
🎞️ Resultados: [Filme A, Filme B, Filme C...]
📚 Total de páginas: 20
🔢 Total de filmes: 400

Tudo isso está dentro de um envelope chamado MovieResponse.
O app lê esse envelope e fala: “Beleza, entendi tudinho. Vamos mostrar os filmes pro usuário!” 💁‍♀️✨
✨ Resumindo bem simples:

A MovieResponse é uma caixa de resposta da API que diz:

“Você está na página tal,”

“Aqui estão os filmes,”

“Tem tantas páginas ao todo,”

“E tantos filmes no total.”
*/

