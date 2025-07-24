package br.com.movieapp.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("searchResults")
    val searchResults: List<SearchResult>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)