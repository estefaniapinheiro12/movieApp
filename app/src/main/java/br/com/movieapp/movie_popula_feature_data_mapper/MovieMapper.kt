package br.com.movieapp.movie_popula_feature_data_mapper

import br.com.movieapp.model.MovieResult
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.util.toPostUrl

fun MovieResult.toMovie(): Movie {
    return Movie (
        id = id, // A propriedade id do Movie é preenchida com o id do MovieResult
        title = title, // A propriedade title do Movie é preenchida com o title do MovieResult
        voteAverage = vote_average, // A propriedade voteAverage do Movie é preenchida com o vote_average do MovieResult
        imageUrl = poster_path?.toPostUrl() ?: "" // A propriedade imageUrl do Movie é preenchida com o poster_path do MovieResult
    )
}


fun List<MovieResult>.toMovie () = map { movieResult -> // Essa função converte uma lista de MovieResult
    Movie( // para um objeto Movie, que é um modelo de dados
        id = movieResult.id, // A propriedade id do Movie é preenchida com o id do MovieResult
        title = movieResult.title, // A propriedade title do Movie é preenchida com o title do MovieResult
        voteAverage = movieResult.vote_average, // A propriedade voteAverage do Movie é preenchida com o vote_average do MovieResult
        imageUrl = movieResult.poster_path?.toPostUrl() ?: "", // A propriedade imageUrl do Movie é preenchida com o poster_path do MovieResult
    )
}
/*

🧩 O que essa classe faz?

Essa função é como uma fábrica mágica que pega um monte de caixinhas de filme que vieram do servidor
 (MovieResult) e transforma em caixinhas organizadinhas para o app entender (Movie). 🎁✨
🧒 Explicando como se fosse para uma criança:

Imagina que você pediu vários filmes pelo correio 📦.
Esses filmes chegaram em caixas meio bagunçadas (os MovieResult)...
Algumas com nome, outras com imagem faltando, nota em outro lugar...

Aí vem essa função e diz:

“Deixa comigo! Vou arrumar tudo isso do jeitinho que o app gosta!”

Ela vai:

Abrir cada caixa bagunçada (cada MovieResult)

Pegar as informações certas

Colocar dentro de uma nova caixinha bem organizada (o Movie)

E devolver a lista de caixinhas prontas pra usar no app 🧼🎬

📦 Parte por parte:

fun List<MovieResult>.toMovie() = map { movieResult ->

    Essa linha diz:
    "Ei, peguei uma lista de resultados do servidor (MovieResult). Agora vou passar um por um na fábrica e transformá-los."

    Movie(
        id = movieResult.id,
        title = movieResult.title,
        voteAverage = movieResult.vote_average,
        imageUrl = movieResult.poster_path?.toPostUrl() ?: ""
    )

    Aqui ele monta o novo filme certinho (Movie), pegando as partes do filme bagunçado:

    id: número do filme

    title: o nome do filme

    voteAverage: nota que o filme recebeu

    imageUrl: a imagem, mas ela vem como um pedaço da URL e precisa ser completada com a função toPostUrl()
    (Se não tiver imagem, ele coloca um texto vazio "" pra não quebrar o app)

    ✨ Como se fosse uma receita de bolo:

    Pegue uma lista de ingredientes bagunçados (MovieResult)

    Para cada um:

    Pegue o nome, nota, ID e imagem (se tiver)

    Monte um bolo certinho (Movie)

    No final, você tem uma bandeja cheia de bolinhos prontos para servir no app! 🧁

    📌 Resumo desenhado (imaginário):

    ANTES (dados crus do servidor):
    📦📦📦 MovieResult, MovieResult, MovieResult...

    ⬇️ passa pela função mágica toMovie()

    DEPOIS (dados lindinhos pro app):
    🎬🎬🎬 Movie, Movie, Movie...*/
