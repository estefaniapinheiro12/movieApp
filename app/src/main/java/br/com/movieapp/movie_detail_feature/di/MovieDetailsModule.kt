package br.com.movieapp.movie_detail_feature.di

import br.com.movieapp.movie_detail_feature.data.repository.MoviesDetailsRepositoryImpl
import br.com.movieapp.movie_detail_feature.data.source.MovieDetailsRemoteDataSourceImpl
import br.com.movieapp.movie_detail_feature.domain.repository.MoviesDetailsRepository
import br.com.movieapp.movie_detail_feature.domain.source.MovieDetailsRemoteDataSource
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCase
import br.com.movieapp.movie_detail_feature.domain.usecase.GetMovieDetailsUseCaseImpl
import br.com.movieapp.remote.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Essa anotação indica que essa classe é um módulo do Dagger Hilt, ou seja, ela fornece dependências.
@InstallIn(SingletonComponent::class) // Essa anotação indica que o módulo deve ser instalado no
// componente Singleton, o que significa que as dependências fornecidas terão um ciclo de vida igual ao do aplicativo.
object MovieDetailsModule { // Essa classe é um módulo do Dagger Hilt que fornece dependências relacionadas aos detalhes de filmes.

    @Provides // Essa anotação indica que o método fornece uma dependência. O Provides é usado pelo Dagger Hilt para saber como criar a instância da dependência.
    @Singleton // Essa anotação indica que a dependência fornecida terá um ciclo de vida igual ao do aplicativo.
    fun provideMovieDetailsDataSource(service: MovieService): MovieDetailsRemoteDataSource { // Esse método fornece uma instância de MovieDetailsRemoteDataSource.
        return MovieDetailsRemoteDataSourceImpl(service = service) // Retorna uma nova instância de MovieDetailsRemoteDataSourceImpl, passando o serviço MovieService como parâmetro.
    }

    @Provides
    @Singleton
    fun provideMovieDetailsRepository(remoteDataSource: MovieDetailsRemoteDataSource): MoviesDetailsRepository { // Esse método fornece uma instância de MoviesDetailsRepository.
        return MoviesDetailsRepositoryImpl(remoteDataSource = remoteDataSource) // Retorna uma nova instância de MoviesDetailsRepositoryImpl, passando o MovieDetailsRemoteDataSource como parâmetro.
    }

    @Provides
    @Singleton
    fun provideGetMovieDetailsUseCase(repository: MoviesDetailsRepository): GetMovieDetailsUseCase { // Esse método fornece uma instância de GetMovieDetailsUseCase.
        return GetMovieDetailsUseCaseImpl(repository = repository) // Retorna uma nova instância de GetMovieDetailsUseCaseImpl, passando o MoviesDetailsRepository como parâmetro.
    }

}