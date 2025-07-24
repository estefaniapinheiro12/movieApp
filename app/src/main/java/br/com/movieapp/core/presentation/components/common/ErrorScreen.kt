package br.com.movieapp.core.presentation.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import br.com.movieapp.ui.theme.white

@Composable
fun ErrorScreen( // Função composable para exibir uma tela de erro
    modifier: Modifier = Modifier,
    message: String, // Mensagem de erro a ser exibida
    retry: () -> Unit // Função a ser chamada ao clicar no botão de recarregar
) {
    Column( // Cria uma coluna para alinhar os elementos
        modifier = modifier,
        verticalArrangement = Arrangement.Center, // Alinhamento vertical dos elementos
        horizontalAlignment = Alignment.CenterHorizontally // Alinhamento horizontal dos elementos
    ) {
        Text( // Exibe a mensagem de erro
            text = message,// Mensagem de erro
            maxLines = 2, // Número máximo de linhas
            overflow = TextOverflow.Ellipsis, // Exibe reticências se o texto for muito longo
            fontStyle = FontStyle.Italic, // Estilo de fonte itálico
            fontSize = 16.sp, // Tamanho da fonte
            color = white // Cor do texto
        )
        Button(onClick = retry) { // Botão para recarregar
            Text(text = "Recarregar") // Texto do botão
        }
    }
}

@Preview
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        message = "Error",
        retry = {}
    )
}
