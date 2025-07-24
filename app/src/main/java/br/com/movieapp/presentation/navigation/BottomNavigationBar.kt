package br.com.movieapp.presentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.yellow

@Composable
fun BottomNavigationBar( // Essa função cria a barra de navegação inferior
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.MoviePopular, // Esse Item representa a tela de filmes populares
        BottomNavItem.MovieSearch, // Esse Item representa a tela de pesquisa de filmes
        BottomNavItem.MovieFavorite // Esse Item representa a tela de filmes favoritos
    )
    BottomNavigation( // Esse BottomNavigation cria a barra de navegação
        contentColor = yellow, // Essa cor define a cor do conteúdo da barra de navegação
        backgroundColor = black // Essa cor define a cor de fundo da barra de navegação
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState() // Essa variável serve para retornar o ultimo item da pilha de navegação
        val currentRoute =
            navBackStackEntry?.destination?.route // Essa variável retorna a rota atual

        items.forEach { // Esse forEach percorre todos os itens da lista
                destination ->
            BottomNavigationItem(
                selected = currentRoute == destination.route, // Essa variável define se o item está selecionado ou não
                onClick = { // Essa função define o que acontece quando um) item é clicado
                    navController.navigate(destination.route) {// Essa função navega para a rota do item clicado
                        launchSingleTop = true // Essa função garante que a navegação não crie várias instâncias da mesma tela
                    }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = null
                    ) // Essa função define o ícone do item
                },
                label = {
                    Text(text = destination.title) // Essa função define o texto do item
                }
            )
        }
    }
}
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState() // Essa variável serve para retornar o ultimo item da pilha de navegação
    return navBackStackEntry?.destination?.route
}

@Preview
@Composable
fun BottomNavigationBarPreview() {
    // Preview da barra de navegação inferior
    BottomNavigationBar(navController = rememberNavController())
}