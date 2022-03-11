package com.bryo.marvel.myheroteam.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.extensions.toSingleEvent
import com.bryo.marvel.myheroteam.usecases.UseCase
import com.bryo.marvel.myheroteam.usecases.UseCase.Companion.await
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CharacterListViewModel(private val getTeamMembers: UseCase<Nothing?, List<MarvelCharacter>>,
                             private val getMarvelCharacters: UseCase<Boolean, List<MarvelCharacter>>): ViewModel() {

    private val _loading = MutableLiveData(true)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Exception>()
    val error = _error.toSingleEvent()

    private val _characters = MutableLiveData<List<MarvelCharacter>>()
    val characters: LiveData<List<MarvelCharacter>>
        get() = _characters

    private val _teamMembers = MutableLiveData<List<MarvelCharacter>>()
    val teamMembers: LiveData<List<MarvelCharacter>>
        get() = _teamMembers

    fun fetchCharacters(forceRefresh: Boolean) {
        launchStoreCall {
            _characters.value = getMarvelCharacters.defer(forceRefresh)
            _teamMembers.value = getTeamMembers.await()
        }
    }

    private fun launchStoreCall(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                _loading.value = true
                block()
            } catch (exception: Exception) {
                _error.value = exception
            } finally {
                _loading.value = false
            }
        }
    }
}