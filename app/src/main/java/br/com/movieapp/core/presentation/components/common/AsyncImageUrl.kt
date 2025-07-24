package br.com.movieapp.core.presentation.components.common

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import br.com.movieapp.R
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable // Anotação @Composable indica que a função pode ser chamada dentro de uma função composable
fun AsyncImageUrl( // Função que carrega uma imagem de uma URL usando a biblioteca Coil. CarregarImagemAssincronamentePorUrl
    modifier: Modifier = Modifier, // Parâmetro opcional para modificar o layout. Modifier → Modificador
    imageUrl: String, // URL da imagem a ser carregada. imageUrl → urlDaImagem / String → Texto ou Cadeia de caracteres
    crossfadeEnable: Boolean = true, // Habilita o efeito de transição. crossfade → desvanecimento cruzado / enable → ativar /
    @DrawableRes errorImage: Int = R.drawable.ic_error_image, // Imagem de erro a ser exibida se o carregamento falhar. / DrawableRes → Recurso de Imagem
    // errorImage → imagemDeErro / Int → inteiro / R.drawable.ic_error_image → recurso de imagem
    @DrawableRes placeholderImage: Int = R.drawable.ic_placeholder, // Imagem de carregamento a ser exibida enquanto a imagem está sendo carregada.
    // placeholderImage → imagemDe espaço reservado / R.drawable.ic_placeholder → recurso de imagem
    contentScale: ContentScale = ContentScale.FillHeight // Escala de conteúdo para a imagem carregada
    // contentScale → escalaDeConteúdo / ContentScale.FillHeight → preenche a altura
) {
    AsyncImage( // Imagem assíncrona que vai ser usada para carregar a imagem
        model = ImageRequest.Builder(LocalContext.current) // Cria uma solicitação de imagem com o contexto atual
            .data(imageUrl) // Define a URL da imagem a ser carregada
            .crossfade(crossfadeEnable) // Habilita o efeito de transição
            .error(errorImage) // Define a imagem de erro a ser exibida se o carregamento falhar
            .placeholder(placeholderImage) // Define a imagem de carregamento a ser exibida enquanto a imagem está sendo carregada
            .build(), // Constrói a solicitação de imagem
        contentDescription = "", // Descrição do conteúdo da imagem (usado para acessibilidade)
        contentScale = contentScale, // Escala de conteúdo para a imagem carregada
        modifier = modifier // Modificador para personalizar o layout da imagem
    )
}