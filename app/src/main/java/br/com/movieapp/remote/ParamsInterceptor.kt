package br.com.movieapp.remote

import br.com.movieapp.BuildConfig
import br.com.movieapp.util.Constants
import okhttp3.Interceptor
import okhttp3.Response

// ParamsInterceptor é uma classe que implementa a interface Interceptor do OkHttp
// O Interceptor é usado para interceptar chamadas de rede e adicionar parâmetros ou cabeçalhos,
// que servem para modificar a requisição ou resposta
class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response { // intercept é um método que intercepta a requisição
        val request = chain.request() // obtem a requisição original
        val url = request.url.newBuilder() // cria uma nova URL a partir da URL original
            .addQueryParameter(Constants.LANGUAGE_PARAM, Constants.LANGUAGE_VALUE) // adiciona o parâmetro de linguagem, que serve para definir o idioma da resposta
            .addQueryParameter(Constants.API_KEY_PARAM, BuildConfig.API_KEY) // adiciona o parâmetro de chave da API, que serve para autenticar a requisição
            .build() // cria a nova URL com os parâmetros adicionados

        val newRequest = request.newBuilder() // cria uma nova requisição a partir da requisição original
            .url(url) // define a nova URL na requisição
            .build() // cria a nova requisição com a URL modificada

        return chain.proceed(newRequest) // prossegue com a requisição modificada
    }
}
// Então essa classe é responsável por adicionar os parâmetros de linguagem e chave da API em todas
// as requisições feitas para a API do The Movie Database (TMDB).