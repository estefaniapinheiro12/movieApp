package br.com.movieapp.search_movie_feature_presentation_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.movieapp.R
import br.com.movieapp.search_movie_feature_presentation.MovieSearchEvent
import br.com.movieapp.ui.theme.white

@Composable
fun SearchComponents(
    // função composable que representa os componentes de busca
    modifier: Modifier = Modifier,
    query: String, // string de consulta
    onSearch: (String) -> Unit, // função de callback para quando a busca é realizada
    onQueryChangeEvent: (MovieSearchEvent) -> Unit, // função de callback para quando a consulta muda
) {
    OutlinedTextField(
        // componente de campo de texto
        value = query, // valor atual da consulta
        onValueChange = { //
            onQueryChangeEvent(MovieSearchEvent.EnteredQuery(it)) // chama a função de callback quando o valor muda
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearch(query) // chama a função de busca com a consulta atual
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null
                ) // ícone de busca
            }
        },
        placeholder = { // texto que fica dentro do campo de texto
            Text(
                text = stringResource(id = R.string.search_movies) // texto de espaço reservado
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            // opções de teclado padrão
            capitalization = KeyboardCapitalization.Sentences, //serve para deixara  a primeira letra maiscula
            autoCorrect = true, // ativa a correção automática
            keyboardType = KeyboardType.Text, // tipo de teclado, que neste caso é texto
            imeAction = ImeAction.Search // ação do IME (Input Method Editor) para busca, que serve para abrir o teclado para pesquisa
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query) // chama a função de busca com a consulta atual
            }
        ),
        colors = TextFieldDefaults.textFieldColors( // cores do campo de texto
            textColor = white, // cor do texto
            cursorColor = white, // cor do cursor
            placeholderColor = white, // cor do texto de espaço reservado
            trailingIconColor = white // cor do ícone de busca
        ),
        modifier = modifier
            .fillMaxWidth() // preenche a largura disponível
            .heightIn(min = 60.dp) // altura mínima do campo de texto

    )

}
@Preview
@Composable
fun SearchComponentsPreview() {
    SearchComponents(
        query = "Star Wars",
        onSearch = {},
        onQueryChangeEvent = {}
    )
}