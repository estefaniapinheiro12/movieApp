package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.movieapp.R
import br.com.movieapp.modelDomain.MovieDetails

@Composable
fun MovieInfoContent( // função que recebe os detalhes do filme
    movieDetails: MovieDetails?, modifier: Modifier = Modifier // modificador para o layout
) {
    Row ( // layout em linha
        horizontalArrangement = Arrangement.SpaceEvenly,// distribui os elementos horizontalmente
        modifier = modifier
    ){
        MovieInfo( // componente que exibe as informações do filme
            name = stringResource(id = R.string.vote_average), // nome da informação
            value = movieDetails?.voteAverage.toString() // valor da informação
        )
        MovieInfo( // componente que exibe as informações do filme
            name = stringResource(id = R.string.duration), // nome da informação
            value = stringResource( // valor da informação
                id = R.string.duration_minutes, // string resource para minutos
                movieDetails?.duration.toString() // duração do filme
            )
        )
            MovieInfo(
                name = stringResource(id = R.string.release_date),
                value = movieDetails?.releaseDate.toString()
        )
    }
}

@Composable
fun MovieInfo( // componente que exibe as informações do filme
    name: String, value: String // nome e valor da informação
) {
    Column { // layout em coluna
        Text( // exibe o nome da informação
            text = name, // nome da informação
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 13.sp, letterSpacing = 1.sp), // estilo do texto
            color = Color.DarkGray, // cor do texto
            modifier = Modifier.align(Alignment.CenterHorizontally) // alinhamento do texto
        )
        Text(
            text = value,
            style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold),
            color = Color.DarkGray,
            modifier = Modifier
                .align(Alignment.CenterHorizontally) // alinhamento do texto
                .padding(top = 4.dp) // espaçamento entre os textos
        )
    }
}

@Composable
@Preview (showBackground = true)
fun MovieInfoContentPreview() {
    MovieInfoContent(
        movieDetails = MovieDetails(
            id = 1,
            title = "Filme",
            genres = listOf("Aventura", "Ação"),
            overview = null,
            backdropPathUrl = null,
            releaseDate = null,
            voteAverage = 8.5,
            duration = 120,
            voteCount = 100
        ),
        modifier = Modifier.fillMaxWidth()
    )
}