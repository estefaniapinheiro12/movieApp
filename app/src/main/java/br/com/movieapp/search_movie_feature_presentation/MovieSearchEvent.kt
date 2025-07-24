package br.com.movieapp.search_movie_feature_presentation

sealed class MovieSearchEvent { // classe selada que representa os eventos de busca de filmes

    data class EnteredQuery(val value: String) : MovieSearchEvent() // evento de entrada de consulta

}
/*

🌟 Título: "A Mensageira da Busca"

Era uma vez, em um reino chamado search_movie_feature_presentation, uma classe selada mágica chamada
MovieSearchEvent. Ela não era qualquer classe... Ela era uma mensageira oficial do reino! 📜💌
📬 A Mensagem da Busca

Essa mensageira tinha uma missão super importante: avisar o Guardião das Buscas (o MovieSearchViewModel)
 quando alguém escrevia o nome de um filme na caixa de busca 🎯🎬

Ela dizia:

    "Senhor Guardião, acabei de receber uma mensagem de alguém que digitou Frozen! ❄️"

🧾 O tipo da mensagem: EnteredQuery

A mensageira trazia esse tipo de bilhete especial chamado EnteredQuery, que dizia:

    "Alguém acabou de digitar este nome aqui!" ✍️
    E junto com a mensagem, ela trazia o que a pessoa digitou, como:
    🗣️ "Harry Potter", "Moana", "Vingadores"...

Esse bilhete era guardado com carinho, e o guardião usava ele quando fosse lançar o feitiço da busca ✨🔍
🔐 Por que essa classe é selada?

A classe MovieSearchEvent era uma classe selada (sealed class), o que significava que:

    "Só existem os tipos de mensagens que a própria mensageira conhece. Nada além disso!" 🛡️

Ou seja, ela só podia entregar os tipos de evento que estavam ali dentro. No caso, por enquanto,
 só tinha o EnteredQuery. Isso ajudava o guardião a entender direitinho o que fazer com cada tipo
 de mensagem, sem ficar confuso! 🧠🧭
🌈 Moral da história:

MovieSearchEvent é a mensageira que leva bilhetes mágicos até o guardião, dizendo o que o usuário digitou.
Ela ajuda o app a reagir quando alguém escreve o nome de um filme 📝📽️

    Ela é rápida, confiável, e só entrega bilhetes que ela mesma conhece! 💌⚡
* */