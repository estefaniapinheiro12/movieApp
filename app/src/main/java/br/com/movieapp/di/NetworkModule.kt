package br.com.movieapp.di

import br.com.movieapp.BuildConfig
import br.com.movieapp.remote.MovieService
import br.com.movieapp.remote.ParamsInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module // Essa anotação indica que essa classe é um módulo Dagger, que fornece dependências para a
// injeção de dependência
@InstallIn(SingletonComponent::class) // Essa anotação indica que o módulo será instalado no
// componente Singleton, o que significa que as dependências fornecidas por esse módulo terão um ciclo
// de vida igual ao da aplicação
object NetworkModule {

    private const val TIMEOUT_SECONDS = 15L // Tempo limite para requisições em segundos

    @Provides // Essa anotação indica que esse método fornece uma dependência para o Dagger
    fun provideParamsInterceptor(): ParamsInterceptor {
        return ParamsInterceptor()
    } // Fornece uma instância de ParamsInterceptor, que é um interceptor de parâmetros para

    @Provides // Essa anotação indica que esse método fornece uma dependência para o Dagger
    fun provideLogInterceptor(): HttpLoggingInterceptor { // Essa função fornece um interceptor de log
        return HttpLoggingInterceptor().apply { // Cria uma instância de HttpLoggingInterceptor
            setLevel( // Define o nível de log
                if(BuildConfig.DEBUG) { // Se a aplicação estiver em modo de depuração
                    HttpLoggingInterceptor.Level.BODY // Registra o corpo da requisição e resposta
                } else { // Se a aplicação não estiver em modo de depuração
                    HttpLoggingInterceptor.Level.NONE // Não registra nada
                }
            )
        }
    }
    @Provides // Essa anotação indica que esse método fornece uma dependência para o Dagger
    fun provideOkHttpClient(
        paramsInterceptor: ParamsInterceptor, // Interceptor de parâmetros
        logInterceptor: HttpLoggingInterceptor // Interceptor de log
    ): OkHttpClient { // Essa função fornece um cliente OkHttp, o cliente OkHttp é uma biblioteca de
        // cliente HTTP para Android e Java, que serve para fazer requisições HTTP, tipo GET, POST,
        return OkHttpClient.Builder() // Cria um construtor de OkHttpClient, o Builder é uma classe
            .addInterceptor(paramsInterceptor) // Adiciona o interceptor de parâmetros
            .addInterceptor(logInterceptor) // Adiciona o interceptor de log
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS) // Define o tempo limite de conexão
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS) // Define o tempo limite de leitura
            .build() // Constrói o cliente OkHttp
    }
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create() // Cria uma instância de GsonConverterFactory
    } // Fornece uma instância de GsonConverterFactory, que é usada para converter objetos Java em JSON

    @Provides
    fun provideMovieService ( // Essa função fornece um serviço de filme
        client: OkHttpClient, // Cliente OkHttp
        converterFactory: GsonConverterFactory // Conversor Gson
    ): MovieService { // Essa função fornece um serviço de filme
        return Retrofit.Builder() // Cria um construtor de Retrofit, o Retrofit é uma biblioteca de cliente onde HTTP para Android e Java, que serve para fazer requisições HTTP, tipo GET, POST, PUT, DELETE
            .baseUrl(BuildConfig.BASE_URL) // Define a URL base da API
            .client(client) // Define o cliente OkHttp
            .addConverterFactory(converterFactory) // Adiciona o conversor Gson
            .build() // Constrói o Retrofit
            .create(MovieService::class.java) // Cria uma instância do serviço de filme
    }

}