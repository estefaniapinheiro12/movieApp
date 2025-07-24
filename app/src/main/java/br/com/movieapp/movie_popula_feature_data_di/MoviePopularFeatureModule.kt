package br.com.movieapp.movie_popula_feature_data_di

import br.com.movieapp.movie_popula_feature_data_domain_repository.MoviePopularRepository
import br.com.movieapp.movie_popula_feature_data_domain_source.MoviePopularRemoteDataSource
import br.com.movieapp.movie_popula_feature_data_domain_usecase.GetPopularMoviesUseCase
import br.com.movieapp.movie_popula_feature_data_domain_usecase.GetPopularMoviesUseCaseImpl
import br.com.movieapp.movie_popula_feature_data_repository.MoviePopularRepositoryImpl
import br.com.movieapp.movie_popula_feature_data_source.MoviePopularRemoteDataSourceImpl
import br.com.movieapp.remote.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Essa anotação indica que essa classe é um módulo Dagger, que fornece dependências
@InstallIn(SingletonComponent::class) // Essa anotação indica que o módulo será instalado no componente
// Singleton, o que significa que as dependências fornecidas por esse módulo terão um ciclo de vida igual ao da aplicação
object MoviePopularFeatureModule {

    @Provides // Essa anotação fornece a instância de MoviePopularRemoteDataSourceImpl e depende de um objeto MovieService
    @Singleton // Essa anotação indica que a instância de MoviePopularRemoteDataSourceImpl será única durante o ciclo de vida da aplicação
    fun provideMovieDataSource(service: MovieService): MoviePopularRemoteDataSource { // Essa função fornece a instância de MoviePopularRemoteDataSourceImpl
        return MoviePopularRemoteDataSourceImpl(service = service) // Retorna uma nova instância de MoviePopularRemoteDataSourceImpl
    }

    @Provides
    @Singleton
    fun provideMovieRepository(remoteDataSource: MoviePopularRemoteDataSource): MoviePopularRepository { // Essa função fornece a instância de MoviePopularRepositoryImpl
        return MoviePopularRepositoryImpl(remoteDataSource = remoteDataSource) // Retorna uma nova instância de MoviePopularRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideGetMoviesPopularUseCase(moviePopularRepository: MoviePopularRepository): GetPopularMoviesUseCase { // Essa função fornece a instância de GetPopularMoviesUseCaseImpl
        return GetPopularMoviesUseCaseImpl(repository = moviePopularRepository) // Retorna uma nova instância de GetPopularMoviesUseCaseImpl
    }
}
/*
🏭 A fábrica (o MoviePopularFeatureModule)

Pensa que essa classe é uma fábrica mágica que faz tudo o que você precisa pra trabalhar com filmes populares no seu app. Ela monta as peças certinhas pra você, pra você não ter que fazer tudo com a mão toda vez!
🧩 As pecinhas que a fábrica monta:

    DataSource (Fonte de dados)

        Como se fosse um entregador que vai até a internet buscar os filmes populares.

    MoviePopularRemoteDataSourceImpl(service = service)

    📦 Ele usa um serviço chamado MovieService que é quem fala com a API (o lugar onde estão os filmes!).

    Repositório (Organizador)

        Esse é o ajudante que pega o que o entregador trouxe e organiza pra ficar mais fácil de usar no app.

    MoviePopularRepositoryImpl(remoteDataSource = remoteDataSource)

    🗂️ Ele sabe como guardar, organizar e devolver os filmes do jeito que você precisa.

    Use Case (Comando)

        Esse é o botão mágico: quando você aperta, ele fala com o repositório pra pegar os filmes!

    GetPopularMoviesUseCaseImpl(repository = moviePopularRepository)

    💡 O botão sabe exatamente o que fazer quando você quiser buscar os filmes populares.

🧙 E o que é o Dagger Hilt?

Imagina que é um mago que vê tudo o que você precisa no seu app e fala:

    “Ah! Você precisa de um entregador, um organizador e um botão mágico? Deixa que eu crio tudinho pra você, sozinho!”

Pra isso funcionar, você só precisa dizer:

@Inject lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase

E PÁ! o mago entrega a peça na sua mão, pronta pra usar. ✨


* */