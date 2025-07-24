package br.com.movieapp.util

sealed class ResultData <out T> { // Classes seladas servem para definir um número fixo de subclasses, o que significa que não podemos criar novas classes que herdem de ResultData fora desse arquivo
    object Loading : ResultData<Nothing>() // Objeto singleton que representa o estado de carregamento
    data class Success<out T>(val data: T?) : ResultData<T>() // Classe de dados que representa o estado de sucesso, com um valor genérico T
    data class Failure (val e: Exception?) : ResultData<Nothing>() // Classe de dados que representa o estado de erro, com uma mensagem de erro
}

