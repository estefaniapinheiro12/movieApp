package br.com.movieapp.movie_favorite_movie.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.common.AsyncImageUrl
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white
import coil.compose.AsyncImage
import coil.request.ImageRequest

// Função composable que representa um item de filme favorito
@Composable
fun MovieFavoriteItem(
    modifier: Modifier = Modifier, // Permite customizar o layout externamente
    movie: Movie, // Objeto Movie que será exibido
    onClick: (id: Int) -> Unit // Função callback quando o item for clicado
) {
    // Um cartão para exibir o item com elevação e clique
    Card(
        modifier = modifier
            .fillMaxWidth() // Ocupa toda a largura possível
            .clickable {
                onClick(movie.id) // Quando clicado, chama a função passando o ID do filme
            }
    ) {
        // Organiza os elementos na vertical
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(black), // Fundo preto
            verticalArrangement = Arrangement.spacedBy(8.dp) // Espaçamento de 8dp entre os filhos
        ) {
            // Container da imagem do filme
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Altura fixa para a imagem
            ) {

                AsyncImageUrl(
                    imageUrl = movie.imageUrl,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            // Título do filme
            Text(
                text = movie.title, // Título que vem do objeto Movie
                maxLines = 1, // Máximo de 1 linha
                fontSize = 20.sp, // Tamanho da fonte
                fontWeight = FontWeight.SemiBold, // Peso da fonte
                color = white, // Cor branca do texto
                overflow = TextOverflow.Ellipsis // Adiciona "..." se o texto ultrapassar o espaço
            )
        }
    }
}

// Preview do componente para ser exibido na IDE
@Preview
@Composable
fun MovieFavoriteItemPreview() {
    MovieFavoriteItem(
        movie =  Movie(
            id = 1,
            title = "Homem Aranha", // Título de exemplo
            voteAverage = 7.89, // Nota (não exibida nesse componente, mas está no objeto)
            imageUrl = "" // URL vazia só para o preview
        ),
        onClick = {
            // Não faz nada no preview
        }
    )
}