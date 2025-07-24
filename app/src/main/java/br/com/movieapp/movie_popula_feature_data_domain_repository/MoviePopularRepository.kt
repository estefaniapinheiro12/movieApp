package br.com.movieapp.movie_popula_feature_data_domain_repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import br.com.movieapp.modelDomain.Movie
import kotlinx.coroutines.flow.Flow

interface MoviePopularRepository { // Essa interface é a que vai ser injetada no viewModel para que o viewModel não tenha dependencia direta com a implementação

    fun getPopularMovies(): PagingSource<Int, Movie>
}