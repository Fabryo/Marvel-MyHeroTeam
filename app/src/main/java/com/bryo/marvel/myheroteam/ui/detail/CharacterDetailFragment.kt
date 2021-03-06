package com.bryo.marvel.myheroteam.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bryo.marvel.myheroteam.R
import com.bryo.marvel.myheroteam.core.models.MarvelCharacter
import com.bryo.marvel.myheroteam.databinding.FragmentCharacterDetailBinding
import com.bryo.marvel.myheroteam.extensions.loadImageUrlAsToolbarBackground
import com.bryo.marvel.myheroteam.ui.detail.CharacterDetailFragmentArgs.fromBundle
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.math.abs

class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding
    private val viewModel by viewModel<CharacterDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)

        subscribeUi()

        return binding?.root ?: inflater.inflate(
            R.layout.fragment_character_detail,
            container,
            false
        )
    }

    override fun onResume() {
        super.onResume()
        arguments?.let {
            fromBundle(it).let { arg ->
                viewModel.start(arg.id)
            }
        }
    }

    private fun subscribeUi() {
        viewModel.character.observe(viewLifecycleOwner) { character ->
            invalidateScreen(character)
        }
    }

    private fun invalidateScreen(character: MarvelCharacter) {
        binding?.run {
            toolbar.run {
                backButton.setOnClickListener { activity?.onBackPressed() }
                scrollView.viewTreeObserver.run {
                    addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            removeOnGlobalLayoutListener(this)

                            val params = collapsingToolbar.layoutParams as LayoutParams
                            // Disable "Drag" for AppBarLayout (i.e. User can't scroll appBarLayout by directly touching appBarLayout - User can only scroll appBarLayout by only using scrollContent)
                            if (appbar.layoutParams != null) {
                                val layoutParams = appbar.layoutParams as CoordinatorLayout.LayoutParams
                                val appBarLayoutBehaviour = AppBarLayout.Behavior()
                                appBarLayoutBehaviour.setDragCallback(object : AppBarLayout.Behavior.DragCallback() {
                                    override fun canDrag(appBarLayout: AppBarLayout) = false
                                })
                                layoutParams.behavior = appBarLayoutBehaviour
                            }

                            appbar.addOnOffsetChangedListener(OnOffsetChangedListener { appBarLayout, verticalOffset ->
                                if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                                    toolbarTitle.text = character.name
                                    characterName.isVisible = false
                                } else {
                                    toolbarTitle.text = null
                                    characterName.isVisible = true
                                }
                                val offsetAlpha = appBarLayout.y / appbar.totalScrollRange
                                characterImage.alpha = 1 - offsetAlpha * -1
                            })
                            collapsingToolbar.layoutParams = params
                        }
                    })
                }
            }

            buttonHandleTeam.apply {
                if (character.hired) {
                    setText(R.string.fire)
                    setBackgroundResource(R.drawable.button_rounded_red_selector)
                    setOnClickListener { viewModel.fireCharacter() }
                } else {
                    setText(R.string.hire)
                    setBackgroundResource(R.drawable.button_filled_red_selector)
                    setOnClickListener { viewModel.hireCharacter() }
                }
            }

            characterImage.loadImageUrlAsToolbarBackground(character.imagePath)
            characterName.text = character.name
            characterDescription.text = character.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}