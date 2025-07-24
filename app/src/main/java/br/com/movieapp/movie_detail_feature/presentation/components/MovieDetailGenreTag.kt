package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.ui.theme.white

@Composable
fun MovieDetailGenreTag( // função que recebe o gênero
    genre: String // gênero
) {
    Box( // componente que envolve o texto
        modifier = Modifier // modificador
            .border( // borda
                width = 1.dp, // largura da borda
                color = white, // cor da borda
                shape = RoundedCornerShape(1000.dp) // forma da borda
            )
            .padding(10.dp) // preenchimento
    ) {
        Text( // componente de texto
            text = genre, // texto do gênero
            color = white, // cor do texto
            textAlign = TextAlign.Center, // alinhamento do texto
            style = MaterialTheme.typography.body2 // estilo do texto
        )
    }

}

@Preview (showBackground = true, backgroundColor = 0)
@Composable
private fun GenreTagPreview () {
    MovieDetailGenreTag(
        genre = "Aventura"
    )
}