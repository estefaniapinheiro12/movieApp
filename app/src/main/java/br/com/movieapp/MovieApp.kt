package br.com.movieapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp //Essa anotação indica que essa classe é a aplicação Hilt, que será usada para injeção de dependência, injeção de dependência é um padrão de design que permite que objetos sejam passados para outros objetos em vez de serem criados diretamente dentro deles
class MovieApp: Application () {

    override fun onCreate() {// Inicializações globais podem ser feitas aqui, uma inicialização global é a inicialização de um objeto ou variável que pode ser acessado em toda a aplicação
        super.onCreate() // Inicializa a classe onCreate da superclasse Application, o super indica que estamos chamando o método da superclasse, ou seja, a classe pai
        if(BuildConfig.DEBUG) { // Configurações de depuração, como logs ou ferramentas de análise
            Timber.plant(Timber.DebugTree()) // Inicializa o Timber para logs de depuração
        }
    }
}