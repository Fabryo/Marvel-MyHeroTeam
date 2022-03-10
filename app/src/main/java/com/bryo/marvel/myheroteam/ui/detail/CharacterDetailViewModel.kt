package com.bryo.marvel.myheroteam.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.usecases.UseCase
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val getMarvelCharacter: UseCase<Int, MarvelCharacter>,
                               private val hireCharacter: UseCase<Int, Unit>,
                               private val fireCharacter: UseCase<Int, Unit>) : ViewModel() {

    private var characterId: Int = -1

    private val _character = MutableLiveData<MarvelCharacter>()
    val character: LiveData<MarvelCharacter>
        get() = _character

    fun start(id: Int) {
        characterId = id
        viewModelScope.launch {
            _character.value = getCharacter()
        }
    }

    fun hireCharacter() {
        viewModelScope.launch {
            hireCharacter.defer(characterId)
            _character.value = getCharacter()
        }
    }

    fun fireCharacter() {
        viewModelScope.launch {
            fireCharacter.defer(characterId)
            _character.value = getCharacter()
        }
    }

    private suspend fun getCharacter() = getMarvelCharacter.defer(characterId)
}