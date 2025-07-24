package br.com.movieapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.movieapp.core.data.local.dao.MovieDao
import br.com.movieapp.core.data.local.entity.MovieEntity

@Database( // Database siginifica que é uma classe que representa o banco de dados
    entities = [MovieEntity::class], // Entidades que representam as tabelas do banco de dados
    version = 1, // Versão do banco de dados, deve ser incrementada quando houver mudanças nas entidades
    exportSchema = false // Exportar o esquema do banco de dados, não é necessário para o desenvolvimento
)

abstract class MovieDatabase: RoomDatabase( ) { // RoomDatabase é uma classe abstrata que representa o banco de dados

    abstract fun movieDao(): MovieDao // Método abstrato que retorna uma instância do DAO (Data Access Object) para acessar os dados do banco de dados
}