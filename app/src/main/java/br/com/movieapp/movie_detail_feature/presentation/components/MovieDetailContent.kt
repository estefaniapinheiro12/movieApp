package br.com.movieapp.movie_detail_feature.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import br.com.movieapp.R
import br.com.movieapp.modelDomain.Movie
import br.com.movieapp.modelDomain.MovieDetails
import br.com.movieapp.movie_detail_feature.data.mapper.toMovie
import br.com.movieapp.movie_popula_feature_data_presentation.components.MovieDetailSimilarMovies
import br.com.movieapp.movie_popula_feature_data_presentation.components.RatingBar
import br.com.movieapp.ui.theme.black
import br.com.movieapp.ui.theme.white
import br.com.movieapp.ui.theme.yellow
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieDetailContent( // função que representa o conteúdo dos detalhes do filme
    modifier: Modifier = Modifier, // modificador para personalizar o conteúdo
    movieDetails: MovieDetails?, // detalhes do filme
    pagingMoviesSimilar: LazyPagingItems<Movie>, // lista de filmes semelhantes
    isLoading: Boolean, // estado de carregamento
    isError: String, // mensagem de erro
    iconColor: Color, // cor da imagem do ícone
    onAddFavorite: (Movie) -> Unit // ação para adicionar o filme aos favoritos
) {
    Box( // componente de caixa para organizar os elementos
        modifier = modifier
            .fillMaxSize() // preenche todo o espaço disponível
            .background(black) // define o fundo da caixa como preto
    ) {
        Column( // componente de coluna para organizar os elementos verticalmente
            modifier = Modifier
                .fillMaxWidth() // preenche toda a largura disponível
                .fillMaxHeight(0.6f) // preenche 60% da altura disponível
                .verticalScroll(rememberScrollState()), // permite rolagem vertical
            horizontalAlignment = Alignment.CenterHorizontally // alinha os elementos horizontalmente no centro
        ) {
            MovieDetailsBackdropImage( // componente que exibe a imagem de fundo do filme
                backdropImageUrl = movieDetails?.backdropPathUrl.toString(), // URL da imagem de fundo
                modifier = Modifier
                    .fillMaxWidth() // preenche toda a largura disponível
                    .height(200.dp) // define a altura da imagem
            )
            Row( // componente de linha para organizar os elementos horizontalmente
                modifier = Modifier.fillMaxWidth(), // preenche toda a largura disponível
                horizontalArrangement = Arrangement.End // organiza os elementos horizontalmente à direita
            ) {
                IconButton( // componente de botão de ícone
                    onClick = { // ação de clique
                        movieDetails?.toMovie()?.let { movie -> // converte os detalhes do filme em um objeto Movie
                            onAddFavorite(movie) // chama a ação para adicionar aos favoritos
                        }
                    }
                ) {
                    Icon( // componente de ícone
                        imageVector = Icons.Default.Favorite, // ícone de favorito
                        contentDescription = null, // descrição do conteúdo
                        tint = iconColor // cor do ícone
                    )
                }
            }
            Text( // componente de texto
                text = movieDetails?.title.toString(), // título do filme
                color = white, // cor do texto
                fontFamily = FontFamily.SansSerif, // família da fonte
                fontWeight = FontWeight.ExtraBold, // peso da fonte
                textAlign = TextAlign.Center, // alinhamento do texto
                modifier = Modifier.padding(horizontal = 8.dp) // margem horizontal
            )
            Spacer(modifier = Modifier.height(8.dp)) // espaçador
            FlowRow( // componente de linha fluida para organizar os elementos
                mainAxisSpacing = 10.dp, // espaçamento principal
                mainAxisAlignment = MainAxisAlignment.Center, // alinhamento principal
                crossAxisSpacing = 10.dp, // espaçamento cruzado
                modifier = Modifier
                    .fillMaxWidth() // preenche toda a largura disponível
                    .padding(horizontal = 8.dp) // margem horizontal
            ) {
                movieDetails?.genres?.forEach { genre -> // itera sobre os gêneros do filme
                    MovieDetailGenreTag(genre = genre) // componente que exibe o gênero do filme
                }
            }
            Spacer(modifier = Modifier.height(8.dp)) // espaçador
            MovieInfoContent(movieDetails = movieDetails, modifier = Modifier.fillMaxWidth()) // componente que exibe as informações do filme
            Spacer(modifier = Modifier.height(8.dp)) // espaçador
            RatingBar( // componente que exibe a barra de avaliação
                rating = (movieDetails?.voteAverage?.toFloat()?.div(2f)) ?: 0f, // avaliação do filme
                modifier = Modifier.height(15.dp) // altura da barra
            )
            Spacer(modifier = Modifier.height(15.dp)) // espaçador
            MovieDetailOverview( // componente que exibe a sinopse do filme
                overview = movieDetails?.overview.toString(), // sinopse do filme
                modifier = Modifier
                    .fillMaxWidth() // preenche toda a largura disponível
                    .padding(horizontal = 8.dp) // margem horizontal
            )
            Spacer(modifier = Modifier.height(15.dp)) // espaçador
            Text( // componente de texto
                text = stringResource(id = R.string.movies_similar), // texto de filmes semelhantes
                color = white, // cor do texto
                fontFamily = FontFamily.SansSerif, // família da fonte
                fontWeight = FontWeight.ExtraBold, // peso da fonte
                fontSize = 20.sp, // tamanho da fonte
                modifier = Modifier
                    .align(alignment = Alignment.Start) // alinha o texto à esquerda
                    .padding(horizontal = 8.dp) // margem horizontal
            )
        }
        if (isError.isNotEmpty()) { // verifica se há mensagem de erro
            Text( // componente de texto
                text = isError, // mensagem de erro
                color = MaterialTheme.colors.error, // cor do texto
                textAlign = TextAlign.Center, // alinhamento do texto
                modifier = Modifier
                    .fillMaxWidth() // preenche toda a largura disponível
                    .padding(horizontal = 8.dp) // margem horizontal
                    .align(Alignment.TopCenter) // alinha o texto ao centro
            )
        }
        if (isLoading) { // verifica se está carregando
            CircularProgressIndicator( // componente de indicador de progresso
                modifier = Modifier.align(Alignment.TopCenter), // alinha o indicador ao centro
                color = yellow // cor do indicador
            )
        }
        MovieDetailSimilarMovies( // componente que exibe os filmes semelhantes
            pagingMoviesSimiliar = pagingMoviesSimilar, // lista de filmes semelhantes
            modifier = Modifier
                .fillMaxWidth() // preenche toda a largura disponível
                .fillMaxHeight(0.35f) // preenche 35% da altura disponível
                .align(Alignment.BottomEnd) // alinha o componente à direita
        )
    }
}
@Preview
@Composable
fun MovieDetailsContentPreview() {
    MovieDetailContent(
        movieDetails = MovieDetails(
            id = 1,
            title = "Homem Aranha",
            genres = listOf("Ação", "Aventura", "Aventura", "Aventura", "Aventura", "Aventura"),
            overview = "Depois do nosso herói ter sido desmascarado pelo seu inimigo Mysterio, Peter Parker já não é capaz de separar a sua vida normal do seu estatuto de super-herói, pelo que só lhe resta pedir ajuda ao Mestre das Artes Místicas, o Doutor Estranho, para que apague a sua real identidade da cabeça de todos. No entanto, esse feitiço leva-o a uma realidade que não é a sua e onde terá de enfrentar novos inimigos, ainda mais perigosos, forçando-o a descobrir o que realmente significa ser o Homem-Aranha.",
            backdropPathUrl = "",
            releaseDate = "25/05/2022",
            voteAverage = 1.4
        ),

        pagingMoviesSimilar = flowOf(PagingData.from(emptyList<Movie>())).collectAsLazyPagingItems(),
        isError = "Error",
        isLoading = false,
        iconColor = Color.Red,
        onAddFavorite = {}
    )
}