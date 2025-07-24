package br.com.movieapp.di

import android.content.Context
import androidx.room.Room
import br.com.movieapp.core.data.local.MovieDatabase
import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.util.Constants.MOVIE_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // marca essa classe como um módulo Hilt que provê dependências.
@InstallIn(SingletonComponent::class) // define que essas dependências vivem durante o ciclo de vida global da aplicação.
object RoomModule { // Usamos um objeto Kotlin (singleton) porque não precisamos instanciar essa classe.

    @Provides // indica que essa função fornece uma dependência.
    @Singleton // garante que só haverá uma instância única de MovieDatabase durante a vida da aplicação.
    fun provideAppDatabase(
        @ApplicationContext context: Context //  Hilt injeta automaticamente o contexto da aplicação aqui.
    ) = Room.databaseBuilder( // cria a instância do banco de dados Room, com o nome definido em MOVIE_DATABASE_NAME.
        context,
        MovieDatabase::class.java,
        MOVIE_DATABASE_NAME
    ).build()

    @Provides
    @Singleton // Também é anotada com @Singleton, pois o DAO pode ser reutilizado com segurança.
    fun provideMovieDao(database: MovieDatabase): MovieDao { // Fornece uma instância de MovieDao usando o banco de dados criado acima.
        return database.movieDao()
    }
}

/*
🧠 Resumo geral

Este módulo está dizendo para o Dagger Hilt:

    Como criar o banco de dados MovieDatabase com Room.

    Como obter o DAO MovieDao desse banco de dados.

    Que essas instâncias devem ser únicas durante toda a vida da aplicação.
* */