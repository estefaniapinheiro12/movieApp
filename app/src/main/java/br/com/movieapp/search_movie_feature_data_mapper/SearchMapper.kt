package br.com.movieapp.search_movie_feature_data_mapper

import br.com.movieapp.modelDomain.MovieSearch
import br.com.movieapp.response.SearchResult
import br.com.movieapp.util.toPostUrl

fun SearchResult.toMovieSearch() = // função de extensão que converte um SearchResult em um MovieSearch
    MovieSearch( // cria uma nova instância de MovieSearch
        id = id, // atributo id do filme
        imageUrl = poster_path.toPostUrl(), // atributo imageUrl do filme, chama a função de extensão toPostUrl para converter o caminho da imagem em uma URL completa
        voteAverage = vote_average // atributo voteAverage do filme
    )

fun List<SearchResult>.toMovieSearch() =
    map { searchResult -> // função de extensão que converte uma lista de SearchResult em uma lista de MovieSearch
        MovieSearch( // cria uma nova instância de MovieSearch
            id = searchResult.id, // atributo id do filme
            imageUrl = searchResult.poster_path.toPostUrl(), // atributo imageUrl do filme, chama a função de extensão toPostUrl para converter o caminho da imagem em uma URL completa
            voteAverage = searchResult.vote_average
        )
    }

/*

🎬✨ História: O Feitiço da Transformação dos Filmes 🔮

No reino das buscas, existia um Feiticeiro da Transformação, chamado toMovieSearch, que tinha o poder de transformar os filmes em filmes incríveis para os viajantes do reino! 🧙‍♂️✨
🧠 Passo 1: A Tarefa do Feiticeiro

O Feiticeiro tinha uma tarefa mágica muito especial. Ele recebia uma lista de filmes (chamados SearchResult) e precisava transformar esses filmes em algo ainda mais mágico! 🎥✨

Esses filmes que ele recebia tinham algumas informações importantes, como:

    Id: Um número mágico que identificava o filme.

    ImageUrl: O caminho para uma imagem do filme, como uma porta secreta para a imagem de um filme.

    VoteAverage: A média dos votos das pessoas, que mostrava o quanto o filme era amado pelos espectadores.

Mas o Feiticeiro queria algo mais organizado, mais claro e mais prático! 🌟
🧙‍♂️ Passo 2: A Mágica da Transformação

O Feiticeiro então começou a sua mágica de transformação. Ele pegava cada filme da lista e transformava cada um deles de forma única! 🔮

    O Id se tornava uma chave mágica que abria os segredos do filme.

    A Imagem passava por uma porta secreta, chamada toPostUrl, que pegava um caminho simples e o transformava em uma URL completa que levava a imagem mágica do filme!

    A Média de Votos continuava do jeito que estava, mas agora estava em um formato mais bonito.

Assim, o Feiticeiro criava uma nova lista de filmes que eram muito mais poderosos e fáceis de serem usados! ✨🎬
📦 Passo 3: O Resultado Mágico

Depois de passar por esse feitiço, o Feiticeiro entregava uma lista novinha e transformada chamada MovieSearch, cheia de filmes prontos para serem assistidos e amados! 💖📽
📘 Moral da História:

O Feiticeiro da Transformação tem o poder de pegar uma lista simples de filmes e transformá-la em uma lista mágica e cheia de informações que os viajantes do reino podem usar facilmente. Ele converte o caminho da imagem para um endereço mágico e mantém todas as informações dos filmes, criando algo mais organizado e pronto para brilhar! 🌟🎬

* */