package com.example.seekhotest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhotest.data.repositories.AnimeRepository
import com.example.seekhotest.domain.Anime
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import com.example.seekhotest.core.network.Result

data class AnimeUiState(
    val isLoading: Boolean = true,
    val animeList: List<Anime> = emptyList(),
    val error: String? = null
)

data class AnimeUDetailUiState(
    val isLoading: Boolean = true,
    val anime: Anime? = null,
    val error: String? = null
)

class AnimeViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnimeUiState())
    private val _animeUDetailUiState = MutableStateFlow(AnimeUDetailUiState())
    val uiState: StateFlow<AnimeUiState> = _uiState.asStateFlow()
    val animeUDetailUiState: StateFlow<AnimeUDetailUiState> = _animeUDetailUiState.asStateFlow()

    init {
        fetchTopAnime()
    }

    fun fetchTopAnime() {

        viewModelScope.launch {
            repository.getTopAnime().collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                animeList = result.data ?: emptyList(),
                                error = null
                            )
                        }
                    }
                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message ?: "Unknown error"
                            )
                        }
                    }
                }
            }
        }
    }


    fun getAnimeById(animeId: Int) {

        viewModelScope.launch {
            repository.getAnimeById(animeId).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _animeUDetailUiState.update { it.copy(isLoading = true, error = null) }
                    }
                    is Result.Success -> {
                        _animeUDetailUiState.update {
                            it.copy(
                                isLoading = false,
                                anime = result.data,
                                error = null
                            )
                        }
                    }
                    is Result.Error -> {
                        _animeUDetailUiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            }
        }
    }

}
