package br.com.movieapp.search_movie_feature_di

import br.com.movieapp.remote.MovieService
import br.com.movieapp.search_movie_feature_data_repository.MovieSearchRepositoryImpl
import br.com.movieapp.search_movie_feature_data_source.MovieSearchRemoteDataSourceImpl
import br.com.movieapp.search_movie_feature_domain_repository.MovieSearchRepository
import br.com.movieapp.search_movie_feature_domain_source.MovieSearchRemoteDataSource
import br.com.movieapp.search_movie_feature_domain_usecases.GetMovieSearchUseCase
import br.com.movieapp.search_movie_feature_domain_usecases.GetMovieSearchUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Essa anotação indica que essa classe é um módulo do Dagger
@InstallIn(SingletonComponent::class) // Essa anotação indica que o módulo será instalado no componente
// Singleton, ou seja, suas dependências terão um ciclo de vida igual ao do aplicativo, serão a mesma instância durante todo o tempo de execução do aplicativo.
object MovieSearchFeatureModule { // Essa classe é um módulo do Dagger, que fornece as dependências necessárias para a funcionalidade de busca de filmes.

    @Provides // Essa anotação indica que o método fornece uma dependência
    @Singleton // Essa anotação indica que a dependência é um singleton, ou seja, será criada apenas uma vez durante o ciclo de vida do aplicativo.
    fun provideMovieSearchDataSource(service: MovieService): MovieSearchRemoteDataSource { // Esse método fornece a dependência MovieSearchRemoteDataSource.
        return MovieSearchRemoteDataSourceImpl(service = service) // Retorna uma nova instância de MovieSearchRemoteDataSourceImpl, passando o serviço MovieService como parâmetro.
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(remoteDataSource: MovieSearchRemoteDataSource): MovieSearchRepository{ // Esse método fornece a dependência MovieSearchRepository.
        return MovieSearchRepositoryImpl(remoteDataSource = remoteDataSource) // Retorna uma nova instância de MovieSearchRepositoryImpl, passando o MovieSearchRemoteDataSource como parâmetro.
    }
    @Provides
    @Singleton
    fun provideGetMovieSearchUseCase(repository: MovieSearchRepository): GetMovieSearchUseCase { // Esse método fornece a dependência GetMovieSearchUseCase.
        return GetMovieSearchUseCaseImpl(repository = repository) // Retorna uma nova instância de GetMovieSearchUseCaseImpl, passando o MovieSearchRepository como parâmetro.
    }


}
/*
🌟 Título: "A Casinha das Magias que Entregam Filmes"

Era uma vez uma casinha mágica chamada MovieSearchFeatureModule 🏠✨

Essa casinha não era qualquer casinha... ela era cheia de ingredientes mágicos que ajudavam o
aplicativo de filmes a funcionar! Só que ninguém sabia como fazer esses ingredientes sozinhos.
Então, essa casinha falava assim:

    "Pode deixar! Eu sei como fazer tudinho! É só me chamar que eu entrego!" 🍲

🧪 Ingrediente 1: O buscador de filmes

Primeiro, a casinha sabia fazer um ajudante que procurava filmes na internet. Mas pra isso, ela
precisava de uma ferramenta especial chamada MovieService 🛠️

Então a casinha dizia:

    "Com esse MovieService, eu consigo criar o MovieSearchRemoteDataSourceImpl, que é quem sabe ir
    lá fora buscar os filmes." 🚀🍿

Ela chamava isso de:

provideMovieSearchDataSource()

🔍 Ingrediente 2: O guardião dos filmes buscados

Depois, a casinha precisava de um guardião dos filmes: alguém que soubesse guardar e organizar os
 filmes que o buscador encontrava.

Aí ela dizia:

    "Com esse buscador que acabei de fazer, eu consigo criar o MovieSearchRepositoryImpl, que guarda
    os filmes direitinho." 📦🎥

Ela fazia isso com:

provideMovieSearchRepository()

🎬 Ingrediente 3: O entregador dos filmes para o app

Por fim, a casinha preparava um ajudante muito especial chamado GetMovieSearchUseCaseImpl. Ele era
 o entregador oficial dos filmes. Sempre que alguém pedia “quero filmes de dragão”, ele ia buscar
 os filmes com o guardião (repository) e trazia de volta direitinho.

    "Com o guardião de filmes, eu consigo fazer o entregador de buscas funcionar!" 📬🐉

Ela dizia isso aqui:

provideGetMovieSearchUseCase()

🪄 E sabe o que é mais legal?

Todos esses ajudantes viviam para sempre! Isso porque a casinha usava um feitiço chamado @Singleton,
que dizia:

    “Crie só um de cada, e use o mesmo sempre que precisar.” 🧙‍♀️✨

🌈 Moral da história:

Essa casinha MovieSearchFeatureModule é quem cria e entrega todos os ajudantes mágicos do aplicativo.
Sem ela, nada funcionaria direito. Ela é tipo uma fábrica de mágica, que o app usa pra pedir ajuda
quando precisa encontrar filmes! 💫
* */