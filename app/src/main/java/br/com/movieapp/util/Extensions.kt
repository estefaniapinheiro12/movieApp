package br.com.movieapp.util

import br.com.movieapp.BuildConfig

fun String?.toPostUrl () = "${BuildConfig.BASE_URL_IMAGE}$this" // Essa função é uma extensão da
// classe String que adiciona a URL base de imagens do aplicativo à string atual, o operador $ é
// usado para interpolação de strings, ou seja, para inserir o valor de uma variável dentro de uma string

fun String?.toBackdropUrl () = "${BuildConfig.BASE_URL_IMAGE}$this"

