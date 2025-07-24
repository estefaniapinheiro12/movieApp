package br.com.movieapp.movie_favorite_movie.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.movieapp.R
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white

@Composable
fun MovieFavoriteContent(
    modifier: Modifier = Modifier, // Permite personalizar o layout externamente
    paddingValues: PaddingValues, // Espaçamento interno que será aplicado no conteúdo (útil com Scaffold, por exemplo)
    movies: List<Movie>, // Lista de filmes favoritos que será exibida
    onClick: (id: Int) -> Unit // Função chamada quando um item for clicado, passando o ID do filme
) {
    // Container externo com fundo preto
    Box(
        modifier = modifier.background(black)
    ) {

        if (movies.isEmpty()) {
            Text(
                text = stringResource(id = R.string.favorite_movies_empty),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = white,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Lista vertical que exibe os filmes
        LazyColumn(
            modifier = Modifier.fillMaxSize(), // Ocupa todo o espaço disponível
            verticalArrangement = Arrangement.spacedBy(8.dp), // Espaçamento vertical entre os itens
            contentPadding = paddingValues, // Aplica os paddings externos (como topo e fundo, úteis quando há uma barra inferior ou superior)
            content = {
                // Define os itens da lista com base na lista "movies"
                items(
                    items = movies, // Lista de filmes a ser renderizada
                    key = { item: Movie -> item.id } // Chave única para ajudar o Compose a identificar cada item (melhora desempenho e evita recomposição desnecessária)
                ) { movie ->
                    // Para cada filme, renderiza o componente MovieFavoriteItem
                    MovieFavoriteItem(movie = movie, onClick = onClick)
                }
            }
        )
    }
}

@Preview
@Composable
fun MovieFavoriteContentPreview() {
    // Pré-visualização da tela de favoritos com dois filmes simulados
    MovieFavoriteContent(
        modifier = Modifier, // Nenhum modificador adicional
        paddingValues = PaddingValues(), // Sem padding extra
        movies = listOf(
            // Lista fictícia de filmes para mostrar como a UI vai ficar
            Movie(
                id = 1,
                title = "Homem Aranha",
                voteAverage = 7.89,
                imageUrl = "" // Sem imagem, mas poderia ser uma URL
            ),
            Movie(
                id = 2,
                title = "Homem de Ferro",
                voteAverage = 7.89,
                imageUrl = ""
            ),
        ),
        onClick = {
            // Não faz nada no clique na prévia, mas você pode adicionar um log se quiser testar
        }
    )
}
