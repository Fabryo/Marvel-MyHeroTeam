package com.bryo.marvel.myheroteam.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.databinding.ItemTeamMemberBinding
import com.bryo.marvel.myheroteam.extensions.loadImageUrlAsBadge

class TeamAdapter(private val onCharacterClicked: (Int) -> Unit): Adapter<TeamAdapter.TeamMemberViewHolder>() {

    private var characters: List<MarvelCharacter> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TeamMemberViewHolder(ItemTeamMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TeamMemberViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount() = characters.size

    fun invalidateTeamMembers(characters: List<MarvelCharacter>) {
        this.characters = characters
        notifyDataSetChanged()
    }

    inner class TeamMemberViewHolder(private val binding: ItemTeamMemberBinding): RecyclerView.ViewHolder(binding.root)  {
        fun bind(character: MarvelCharacter) {
            binding.characterImage.apply {
                loadImageUrlAsBadge(character.imagePath)
                setOnClickListener { onCharacterClicked(character.id) }
            }
        }
    }
}