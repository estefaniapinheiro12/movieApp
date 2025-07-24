package br.com.movieapp.search_movie_feature_domain_repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.movieapp.modelDomain.MovieSearch
import kotlinx.coroutines.flow.Flow

interface MovieSearchRepository { // interface para o repositório de busca de filmes

    fun getSearchMovies(query: String): PagingSource<Int, MovieSearch> // método para
// buscar filmes populares, query é a string de busca e pagingConfig é a configuração de paginação, Flow é um tipo de
// fluxo que emite dados de forma assíncrona, pagingData é um tipo de dado que representa uma lista de dados paginados, MovieSearch é o tipo de dado que representa um filme


}
/*

🎬📚 História: O Mago das Buscas de Filmes 🧙‍♂️✨

Em um reino distante, existia um Mago das Buscas de Filmes, chamado MovieSearchRepository.
Esse mago não sabia fazer feitiços comuns, como os outros magos. Ele tinha um poder muito especial: organizar e buscar filmes para todos de forma mágica e prática! 🧙‍♂️✨
🧠 Passo 1: O Magia do "Buscar Filmes"

Quando alguém precisava encontrar um filme (tipo um filme de super-heróis ou de mistério!), o mago não começava a procurar tudo de uma vez. Não! Ele tinha um método muito organizado e eficiente. Ele usava um feitiço que recebia duas coisas importantes:

    O Pedido: O nome do filme ou do tipo de filme que as pessoas queriam ver, como "Filmes de Aventura!" ou "Filmes com Dragões!" (Esse pedido era chamado de query).

    A Varinha de Paginamento: O mago usava uma varinha mágica chamada PagingConfig. Ela ajudava a organizar tudo, fazendo com que o mago não trouxesse filmes de uma vez, mas sim aos pouquinhos, para ninguém se cansar de ver todos ao mesmo tempo!

📦 Passo 2: Organizando tudo em Páginas

Com o feitiço de organização, o mago transformava a busca em uma série de caixinhas mágicas (chamadas PagingData), cada uma com uma porção de filmes, e as pessoas podiam abrir uma caixinha de cada vez, sem pressa! ⏳📦

Essas caixinhas mágicas não apareciam de uma vez. Elas chegavam uma por uma, e o mago mandava as caixinhas pelo rio encantado que flui sem parar! Esse rio se chamava Flow.
💧 Passo 3: O Rio Mágico de Filmes

O Rio Mágico era muito especial. Ele levava páginas de filmes, uma por uma, para onde quer que a pessoa estivesse. Nunca acabava! As pessoas só precisavam seguir o rio, que sempre tinha uma nova caixinha de filmes chegando.
📘 Moral da história:

O Mago das Buscas de Filmes é muito esperto. Ele não joga tudo de uma vez, mas sabe organizar as buscas para que todos vejam os filmes aos poucos, sem se perder. Ele usa uma varinha mágica chamada PagingConfig, que cria caixinhas de filmes e as envia pelo rio encantado chamado Flow, fazendo com que a busca nunca pare e os filmes cheguem de forma organizada e prática. 💫🎥

* */