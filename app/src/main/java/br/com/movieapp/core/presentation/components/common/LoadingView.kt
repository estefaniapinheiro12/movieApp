package br.com.movieapp.core.presentation.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.ui.theme.yellow

@Composable
fun LoadingView(modifier: Modifier = Modifier) { // Função composable para exibir uma tela de carregamento
    Row ( // Cria uma linha para alinhar os elementos
        modifier = modifier
            .fillMaxWidth() // Preenche toda a largura disponível
            .padding(8.dp), // Adiciona preenchimento de 8dp
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally), // Espaçamento horizontal entre os elementos
        verticalAlignment = Alignment.CenterVertically // Alinhamento vertical dos elementos
    ) {
        CircularProgressIndicator(modifier = Modifier.size(40.dp), color = yellow) // CircularProgressIndicator com tamanho de 40dp e cor amarela
    }
}

@Preview
@Composable
fun LoadingViewPreview() {
    LoadingView()
}
