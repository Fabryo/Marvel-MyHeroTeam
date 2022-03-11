package com.bryo.marvel.myheroteam.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import com.bryo.marvel.myheroteam.R
import com.bryo.marvel.myheroteam.databinding.FragmentCharacterListBinding
import com.bryo.marvel.myheroteam.ui.list.CharacterListFragmentDirections.navigateToCharacterDetailAction
import com.bryo.marvel.myheroteam.utils.MarginItemDecoration
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModel<CharacterListViewModel>()

    private val onCharacterClicked: (id: Int) -> Unit = { id ->
        navigateToCharacterDetail(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        val teamAdapter = TeamAdapter(onCharacterClicked)
        binding?.teamMembers?.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            addItemDecoration(MarginItemDecoration(R.dimen.margin_simple))
            this.adapter = teamAdapter
        }

        val charactersAdapter = CharactersAdapter(onCharacterClicked)
        binding?.characters?.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = charactersAdapter
        }

        binding?.swipeRefresh?.setOnRefreshListener {
            viewModel.fetchCharacters(true)
        }

        subscribeUi(teamAdapter, charactersAdapter)
        return binding?.root ?: inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCharacters(false)
    }

    private fun subscribeUi(teamAdapter: TeamAdapter, charactersAdapter: CharactersAdapter) {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            charactersAdapter.invalidateCharacters(characters)
        }

        viewModel.teamMembers.observe(viewLifecycleOwner) { teamMembers ->
            binding?.mySquad?.isVisible = teamMembers.isNotEmpty()
            binding?.teamMembers?.isVisible = teamMembers.isNotEmpty()
            teamAdapter.invalidateTeamMembers(teamMembers)
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            binding?.swipeRefresh?.isRefreshing = it
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToCharacterDetail(id: Int) {
        findNavController().navigate(navigateToCharacterDetailAction(id))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}