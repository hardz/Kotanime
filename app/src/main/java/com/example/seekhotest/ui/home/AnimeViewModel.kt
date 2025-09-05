package com.example.seekhotest.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.seekhotest.data.model.Anime
import com.example.seekhotest.repositories.AnimeRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

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
            repository.getTopAnime()
                .onStart {
                    _uiState.update { it.copy(isLoading = true, error = null) }
                }
                .catch { e ->
                    _uiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { animeList ->
                    _uiState.update { it.copy(isLoading = false, animeList = animeList, error = null) }
                }
        }
    }


    fun getAnimeById(animeId: Int) {
        viewModelScope.launch {
            repository.getAnimeById(animeId)
                .onStart {
                    _animeUDetailUiState.update { it.copy(isLoading = true, error = null) }
                }
                .catch { e ->
                    _animeUDetailUiState.update { it.copy(isLoading = false, error = e.message) }
                }
                .collect { anime ->
                    _animeUDetailUiState.update { it.copy(isLoading = false, anime = anime, error = null) }
                }
        }
    }

}
