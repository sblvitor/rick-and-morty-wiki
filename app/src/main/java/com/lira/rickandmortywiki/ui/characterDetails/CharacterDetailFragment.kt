package com.lira.rickandmortywiki.ui.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.lira.rickandmortywiki.R
import com.lira.rickandmortywiki.core.translateResponseText
import com.lira.rickandmortywiki.databinding.FragmentCharacterDetailBinding


class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null

    private val binding get() = _binding!!

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMenu()

        val character = args.character

        binding.apply {
            tvCharacterNameDetail.text = character.name
            tvStatusDetail.text = getString(translateResponseText(character.status))

            Glide
                .with(requireContext())
                .load(character.image)
                .into(ivCharacterImageDetail)

            tvEpisodesCount.text = character.episode.size.toString()

            var translatedText = translateResponseText(character.species)
            tvSpecie.text = if(translatedText != R.string.blank) getString(translatedText) else character.species

            tvLocation.text = character.location.name

            translatedText = translateResponseText(character.origin.name)
            tvOrigin.text = if(translatedText != R.string.blank) getString(translatedText) else character.origin.name

            tvGender.text = getString(translateResponseText(character.gender))
        }
    }

    private fun setupMenu() {

        binding.toolbarCharacterDetails.setNavigationIcon(R.drawable.ic_back)
        binding.toolbarCharacterDetails.setNavigationOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment_activity_main).navigateUp()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}