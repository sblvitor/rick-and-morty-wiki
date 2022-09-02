package com.lira.rickandmortywiki.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.lira.rickandmortywiki.R
import com.lira.rickandmortywiki.core.createProgressDialog
import com.lira.rickandmortywiki.core.hideSoftKeyboard
import com.lira.rickandmortywiki.databinding.FragmentCharactersBinding
import com.lira.rickandmortywiki.presentation.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharactersFragment : Fragment(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val charactersViewModel by viewModel<CharactersViewModel>()
    private val adapter by lazy { CharacterAdapter() }
    private var _binding: FragmentCharactersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentCharactersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMenu()

        binding.rvCharacters.adapter = adapter

        charactersViewModel.characters.observe(viewLifecycleOwner) {
            when(it) {
                CharactersViewModel.State.Loading -> {
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    dialog.show()
                }
                is CharactersViewModel.State.Error -> {
                    dialog.dismiss()
                    Toast.makeText(requireContext(), "${it.error.message}", Toast.LENGTH_LONG).show()
                }
                is CharactersViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list.body()?.results)
                    checkAndGoToIfNextPage(it.list.body()?.info?.next)
                }
            }
        }
    }

    private fun setupMenu() {

        binding.toolbarCharacters.inflateMenu(R.menu.search_menu)
        val searchView = binding.toolbarCharacters.menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!searchView.isIconified) {
                    binding.toolbarCharacters.collapseActionView()
                } else {
                    this.isEnabled = false
                    activity?.onBackPressed()
                }
            }
        })

    }

    private fun checkAndGoToIfNextPage(nextUrl: String?): Boolean{
        if(nextUrl == null){
            binding.fabNextPage.visibility = View.GONE
            return false
        }

        binding.fabNextPage.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                charactersViewModel.getCharactersFromNextPage(nextUrl)
            }
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Log.e(TAG, "onQueryTextSubmit: $query")
        query?.let { charactersViewModel.getCharactersByName(it) }
        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "onQueryTextChange: $newText")
        return false
    }

    companion object{
        private const val TAG = "TAG"
    }

    override fun onDestroyView() {
        Log.e(TAG, "onDestroyView: now")
        super.onDestroyView()
        _binding = null
    }

}