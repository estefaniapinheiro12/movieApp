package br.com.movieapp.movie_popula_feature_data_presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MovieRate(
    rate: Double,
    modifier: Modifier // modifier será usado para definir o tamanho do componente
) {
    Row(
        modifier = modifier.widthIn(max = 60.dp), // define o tamanho máximo do componente
        horizontalArrangement = Arrangement.spacedBy(2.dp), // define o espaçamento entre os elementos
        verticalAlignment = Alignment.CenterVertically // alinha os elementos verticalmente no centro
    ) {
        Icon( // ícone da estrela
            imageVector = Icons.Default.Star, // ícone da estrela
            contentDescription = null, // descrição do conteúdo
            tint = Color.Yellow, // cor do ícone
            modifier = Modifier.size(12.dp) // tamanho do ícone
        )
        Text( // texto da nota
            text = rate.toString(), // converte a nota para string
            style = MaterialTheme.typography.body1, // estilo do texto
            color = Color.White, // cor do texto
            fontSize = 10.sp // tamanho da fonte do texto
        )
    }
}

@Preview
@Composable
fun MovieRatePreview() {
    MovieRate(
        rate = 8.5,
        modifier = Modifier // define o tamanho do componente
    )
}