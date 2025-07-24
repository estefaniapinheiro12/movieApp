package br.com.movieapp.movie_popula_feature_data_presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import kotlin.math.sin
import kotlin.math.cos
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar( // função para exibir a nota do filme
    rating: Float, // armazena a nota do filme
    modifier: Modifier = Modifier,
    color: Color = Color.Yellow // cor da estrela
) {
    Row (modifier = modifier.wrapContentSize()) { // cria uma linha para exibir as estrelas
        (1..5).forEach { step -> // it cria um loop de 1 a 5 para exibir as estrelas
            val stepRating = when { // verifica a nota do filme
                rating > step -> 1f // se a nota for maior que o passo, retorna 1, o 1f representa 100% da estrela
                step.rem(rating) < 1 -> rating - (step - 1f) // se o resto da divisão do passo pela nota for menor que 1, retorna a nota menos o passo - 1
                else -> 0f // caso contrário, retorna 0
            }
            RatingStar(stepRating, color)
        }
    }
}

@Composable
private fun RatingStar( // função para exibir a estrela
    rating: Float, // armazena a nota do filme
    ratingColor: Color = Color.Yellow, // cor da estrela
    backgroundColor: Color = Color.Gray // cor de fundo da estrela
) {
    BoxWithConstraints( // cria uma caixa com restrições
        modifier = Modifier
            .fillMaxHeight() // preenche a altura máxima
            .aspectRatio(1f) // mantém a proporção 1:1
            .clip(starShape) // recorta a estrela
    ) {
        Canvas(modifier = Modifier.size(maxHeight)) { // cria um canvas para desenhar a estrela
            drawRect( // desenha o fundo da estrela
                brush = SolidColor(backgroundColor), // cor de fundo
                size = Size( // tamanho da estrela
                    height = size.height * 1.4f, // altura da estrela
                    width = size.width * 1.4f // largura da estrela
                ),
                topLeft = Offset( // posição da estrela
                    x = -(size.width * 0.1f), // desloca a estrela para a esquerda
                    y = -(size.height * 0.1f) // desloca a estrela para cima
                )
            )
            if (rating > 0) { // verifica se a nota é maior que 0
                drawRect( // desenha a estrela
                    brush = SolidColor(ratingColor), // cor da estrela
                    size = Size( // tamanho da estrela
                        height = size.height * 1.1f, // altura da estrela
                        width = size.width * rating // largura da estrela
                    )
                )
            }
        }
    }
}

private val starShape = GenericShape { size, _ -> // cria a forma da estrela
    addPath(starPath(size.height)) // adiciona o caminho da estrela
}

private val starPath = { size: Float -> // função para criar o caminho da estrela
    Path().apply { // cria um novo caminho
        val outerRadius: Float = size / 1.8f // raio externo da estrela
        val innerRadius: Double = outerRadius / 2.5 // raio interno da estrela
        var rot: Double = Math.PI / 2 * 3 // rotação inicial da estrela
        val cx: Float = size / 2 // centro da estrela
        val cy: Float = size / 20 * 11 // centro da estrela
        var x: Float = cx // posição x da estrela
        var y: Float = cy // posição y da estrela
        val step = Math.PI / 5 // passo da estrela

        moveTo(cx, cy - outerRadius) // move para o topo da estrela
        repeat(5) { // repete 5 vezes para criar os 5 pontos da estrela
            x = (cx + cos(rot) * outerRadius).toFloat() // calcula a posição x da estrela
            y = (cy + sin(rot) * outerRadius).toFloat() // calcula a posição y da estrela
            lineTo(x, y) // desenha a linha até a posição x e y
            rot += step // incrementa a rotação

            x = (cx + cos(rot) * innerRadius).toFloat() // calcula a posição x da estrela
            y = (cy + sin(rot) * innerRadius).toFloat() // calcula a posição y da estrela
            lineTo(x, y) // desenha a linha até a posição x e y
            rot += step // incrementa a rotação
        }
        close() // fecha o caminho da estrela
    }
}

@Preview ()
@Composable
fun RatingBarPreview() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        RatingBar(
            3.5f,
            modifier = Modifier.height(20.dp)
        )
    }
}