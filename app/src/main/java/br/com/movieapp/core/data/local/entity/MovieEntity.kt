package br.com.movieapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movies") // Entity serve para criar a tabela no banco de dados. tableName = "Movies": Define o nome da tabela como Movies.
data class MovieEntity( // Classe que representa a tabela no banco de dados. Essa é a classe de dados usada para:
    // Criar registros.Inserir no banco.Ler os dados da tabela

    @PrimaryKey(autoGenerate = false) // Chave primária da tabela, que vai ser o movieId
    val movieId: Int, // ID do filme
    val title: String, // Título do filme
    val imageUrl: String // URL da imagem do filme

)
/*
| Parte                  | O que faz                           |
| ---------------------- | ----------------------------------- |
| `@Entity`              | Cria a tabela no banco              |
| `tableName = "Movies"` | Dá nome à tabela                    |
| `@PrimaryKey`          | Define a chave única de cada linha  |
| `MovieEntity`          | Representa cada linha da tabela |
| Campos (`val`)         | São as colunas da tabela        |

* */