package com.bryo.marvel.myheroteam.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.usecases.UseCase
import kotlinx.coroutines.launch

class CharacterListViewModel(private val getMarvelCharacters: UseCase<Boolean, List<MarvelCharacter>>): ViewModel() {

    private val _characters = MutableLiveData<List<MarvelCharacter>>()
    val characters: LiveData<List<MarvelCharacter>>
        get() = _characters

    fun fetchCharacters(forceRefresh: Boolean) {
        viewModelScope.launch {
            _characters.value = getMarvelCharacters.defer(forceRefresh)
        }
    }
}