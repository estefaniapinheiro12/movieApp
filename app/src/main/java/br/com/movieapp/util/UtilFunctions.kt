package br.com.movieapp.util

import timber.log.Timber

object UtilFunctions {

    fun logError(tag: String, message: String) { // Essa função registra um erro com o Timber,
        // o Timber é uma biblioteca de logging para Android que facilita o registro de logs
        Timber.tag(tag).e("Error -> $message") // Registra um erro com o nível de log "e" (error)
    }

    fun logInfo(tag: String, message: String) {
        Timber.tag(tag).e("Info -> $message") // Registra uma informação com o nível de log "e" (error))
    }
}