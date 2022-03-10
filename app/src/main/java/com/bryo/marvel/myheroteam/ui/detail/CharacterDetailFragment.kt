package com.bryo.marvel.myheroteam.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bryo.marvel.myheroteam.R
import com.bryo.marvel.myheroteam.databinding.FragmentCharacterDetailBinding

class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null

    private val binding get() = _binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding?.root ?: inflater.inflate(R.layout.fragment_character_detail, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}