package com.bryo.marvel.myheroteam.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.usecases.UseCase
import com.bryo.marvel.myheroteam.usecases.UseCase.Companion.await
import kotlinx.coroutines.launch

class CharacterListViewModel(private val getTeamMembers: UseCase<Nothing?, List<MarvelCharacter>>,
                             private val getMarvelCharacters: UseCase<Boolean, List<MarvelCharacter>>): ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _characters = MutableLiveData<List<MarvelCharacter>>()
    val characters: LiveData<List<MarvelCharacter>>
        get() = _characters

    private val _teamMembers = MutableLiveData<List<MarvelCharacter>>()
    val teamMembers: LiveData<List<MarvelCharacter>>
        get() = _teamMembers

    fun fetchCharacters(forceRefresh: Boolean) {
        viewModelScope.launch {
            _loading.value = true
            _characters.value = getMarvelCharacters.defer(forceRefresh)
            _teamMembers.value = getTeamMembers.await()
            _loading.value = false
        }
    }
}