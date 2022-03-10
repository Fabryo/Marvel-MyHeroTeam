package com.bryo.marvel.myheroteam.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.databinding.ItemCharacterBinding
import com.bryo.marvel.myheroteam.ui.list.CharactersAdapter.CharacterViewHolder

class CharactersAdapter: Adapter<CharacterViewHolder>() {

    private var characters: List<MarvelCharacter> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterViewHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

    fun invalidateCharacters(characters: List<MarvelCharacter>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root)  {
        fun bind(character: MarvelCharacter) {
            binding.name.text = character.name
            binding.description.text = character.description
        }
    }
}