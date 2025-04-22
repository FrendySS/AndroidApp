package com.example.animeviewer.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeviewer.domain.models.Anime
import com.example.animeviewer.domain.usecases.GetAnimeDetailsUseCase
import com.example.animeviewer.domain.usecases.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase,
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase
) : ViewModel() {

    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> = _animeList

    private val _selectedAnime = MutableStateFlow<Anime?>(null)
    val selectedAnime: StateFlow<Anime?> = _selectedAnime

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadAnimeList()
    }

    fun loadAnimeList() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            getAnimeListUseCase()
                .catch { e ->
                    _error.value = e.message
                    _isLoading.value = false
                }
                .collect { animes ->
                    _animeList.value = animes
                    _isLoading.value = false
                }
        }
    }

    fun getAnimeDetails(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val anime = getAnimeDetailsUseCase(id)
                _selectedAnime.value = anime
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}