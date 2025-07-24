package br.com.movieapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.movieapp.presentation.navigation.BottomNavigationBar
import br.com.movieapp.presentation.navigation.DetailScreenNav
import br.com.movieapp.presentation.navigation.NavigationGraph
import br.com.movieapp.presentation.navigation.currentRoute

@SuppressLint("UnusedMaterialScaffoldPaddingParameter") // Essa anotação serve para suprimir o aviso de que o ScaffoldPadding não está sendo usado
@Composable // Essa anotação indica que essa função é um Composable, ou seja, uma função que pode ser usada para criar uma interface de usuário
fun MainScreen(
    navHostController: NavHostController ) { // o navController é responsável por navegar
    // entre as telas, o navHostController é responsável por gerenciar a navegação entre as telas
    Scaffold ( // Essa função cria uma estrutura básica para a tela
        bottomBar = { // Essa função define a barra de navegação inferior
            if (currentRoute(navController = navHostController ) != DetailScreenNav.DetailScreen.route){ // Essa condição verifica se a tela atual não é a tela de detalhes
                BottomNavigationBar(navController = navHostController) // Essa função chama a barra de navegação inferior
            }
        },
        content = { paddingValues -> // Essa função define o conteúdo da tela. Aqui você pode adicionar o conteúdo da sua tela principal
            Box(modifier = Modifier.padding(paddingValues)) {
                NavigationGraph( navHostController = navHostController) // Chama a função NavigationGraph para gerenciar a navegação entre as telas

            }
        }
    )
}
@Preview
@Composable
fun MainScreenPreview() { // Essa função é uma pré-visualização da tela principal
    // Aqui você pode adicionar um NavHostController de exemplo para testar a tela
  MainScreen(navHostController = rememberNavController())
}