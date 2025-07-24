package br.com.movieapp.movie_popula_feature_data_presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import br.com.movieapp.R
import br.com.movieapp.core.presentation.components.common.AsyncImageUrl
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun MovieItem ( // função que representa um item de filme
    modifier: Modifier = Modifier, // modificador para personalizar o item
    voteAverage: Double, // média de votos do filme
    ImageUrl: String, // URL da imagem do filme
    id: Int, // ID do filme
    onClick: (id: Int) -> Unit // função de callback para lidar com cliques no item
) {
    Box( // caixa que contém o item do filme
        modifier = modifier
    ) {
        MovieRate( // componente que exibe a média de votos
            rate = voteAverage, // passa a média de votos
            modifier = Modifier // modificador para o componente
                .align(Alignment.BottomStart) // alinha o componente na parte inferior esquerda
                .zIndex(2F) // define a ordem de empilhamento
                .padding(start = 6.dp, bottom = 8.dp) // adiciona preenchimento
                .background(Color.Black) // define a cor de fundo

        )
        Card ( // componente de cartão para o item do filme
            modifier = Modifier
                .fillMaxSize() // preenche todo o espaço disponível
                .height(200.dp) // define a altura do cartão
                .padding(4.dp) // adiciona preenchimento
                .clickable { // define ação de clique
                    onClick(id) // chama a função de callback com o ID do filme
                },
            shape = RoundedCornerShape(8.dp), // define o formato do cartão
            elevation = 8.dp, // define a elevação do cartão
        ) {
            Box {// caixa que contém a imagem do filme

                AsyncImageUrl(
                    imageUrl = ImageUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(Color.Black)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
    }
}

@Preview
@Composable
fun MovieItemPreview() {
    MovieItem(
        modifier = Modifier,
        voteAverage = 8.5,
        ImageUrl = "https://image.tmdb.org/t/p/w500/your_image_path.jpg",
        id = 1,
        onClick = {}
    )
}