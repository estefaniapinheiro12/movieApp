package br.com.movieapp.core.presentation.components.common

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.movieapp.ui.theme.white
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import br.com.movieapp.ui.theme.black

@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier, // Permite modificar o layout (tamanho, cor, espaçamento etc.)
    @StringRes title: Int, // ID de uma string localizada (ex: R.string.nome_do_filme)
    textColor: Color = white, // Cor do texto do título (branco por padrão)
    backgroundColor: Color = black, // Cor de fundo da barra (preto por padrão)
) {
    TopAppBar( // Composable que cria uma barra superior (AppBar)
        title = {
            Text(
                text = stringResource(id = title), // Converte o ID da string para o texto correspondente
                color = textColor // Aplica a cor definida ao texto
            )
        },
        backgroundColor = backgroundColor, // Aplica a cor de fundo da barra
        modifier = modifier, // Aplica o modificador recebido (ex: padding, altura, etc.)
    )
}