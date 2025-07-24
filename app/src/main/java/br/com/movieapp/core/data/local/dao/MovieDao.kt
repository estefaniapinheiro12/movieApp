package br.com.movieapp.core.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.movieapp.core.data.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao // Dao significa que é um objeto de acesso a dados
interface MovieDao { // interface é uma classe abstrata que não pode ser instanciada

    @Query("SELECT * FROM Movies ORDER BY movieId") // SELECT * FROM Movies significa que vai selecionar todos os filmes
    fun getMovies(): Flow<List<MovieEntity>> // Flow é um tipo de dado que permite trabalhar com dados
    // assíncronos, ou seja, dados que podem chegar a qualquer momento. List<MovieEntity> é uma lista
    // de objetos MovieEntity, que representam os filmes. O Flow<List<MovieEntity>> significa que a
    // função vai retornar um fluxo de dados que vai emitir uma lista de filmes.

    @Query("SELECT * FROM Movies WHERE movieId = :movieId") // SELECT * FROM Movies WHERE movieId = :movieId significa que vai selecionar todos os filmes
    suspend fun getMovie(movieId: Int): MovieEntity? // movieId: Int é um inteiro que representa o id do filme que vai ser selecionado.

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE) // onConflict = OnConflictStrategy.REPLACE significa que se já existir um filme com o mesmo id.
    suspend fun insertMovie(movieEntity: MovieEntity) // suspend fun significa que a função é uma função
    // suspensa, ou seja, pode ser chamada de forma assíncrona, assicrona significa que a função pode
    // ser chamada de forma que não bloqueia a thread principal. movieEntity: MovieEntity é um objeto
    // MovieEntity, que representa o filme que vai ser inserido no banco de dados.

    @Query("SELECT * FROM Movies WHERE movieId = :movieId") // SELECT * FROM Movies WHERE movieId = :movieId significa que vai selecionar todos os filmes
    suspend fun isFavorite(movieId: Int): MovieEntity? // movieId: Int é um inteiro que representa o id do filme que vai ser selecionado.

    @Delete // @Delete significa que a função vai deletar um filme do banco de dados
    suspend fun deleteMovie(movieEntity: MovieEntity) // movieEntity: MovieEntity é um objeto MovieEntity, que representa o filme que vai ser deletado do banco de dados.

}