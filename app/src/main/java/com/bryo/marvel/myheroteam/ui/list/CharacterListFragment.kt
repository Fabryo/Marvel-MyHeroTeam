package com.bryo.marvel.myheroteam.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bryo.marvel.myheroteam.R
import com.bryo.marvel.myheroteam.databinding.FragmentCharacterListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null

    private val binding get() = _binding

    private val viewModel by viewModel<CharacterListViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        val adapter = CharactersAdapter()
        binding?.characters?.apply {
            layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        subscribeUi(adapter)
        return binding?.root ?: inflater.inflate(R.layout.fragment_character_list, container, false)
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding?.buttonFirst?.setOnClickListener {
//            findNavController().navigate(R.id.action_list_to_detail)
//        }
//    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchCharacters(false)
    }

    private fun subscribeUi(adapter: CharactersAdapter) {
        viewModel.characters.observe(viewLifecycleOwner) { characters ->
            adapter.invalidateCharacters(characters)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}