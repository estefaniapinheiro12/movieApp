package br.com.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import br.com.movieapp.presentation.MainScreen
import br.com.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Essa anotação indica que essa classe é um ponto de entrada para o Hilt, que é
// uma biblioteca de injeção de dependência para Android
class MainActivity : ComponentActivity() { // Essa classe é a atividade principal do aplicativo
    override fun onCreate(savedInstanceState: Bundle?) { // Essa função é chamada quando a atividade é criada
        super.onCreate(savedInstanceState)
        installSplashScreen() // Essa função instala a tela de splash, que é exibida enquanto o aplicativo está carregando
        setContent { // Essa função define o conteúdo da atividade
            MovieAppTheme { // Essa função aplica o tema do aplicativo
                MainScreen(navHostController = rememberNavController()) // Essa função chama a tela principal do aplicativo
            }
        }
    }
}

