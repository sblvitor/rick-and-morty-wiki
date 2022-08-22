package com.lira.rickandmortywiki.ui.characters

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.lira.rickandmortywiki.R
import com.lira.rickandmortywiki.core.createProgressDialog
import com.lira.rickandmortywiki.core.hideSoftKeyboard
import com.lira.rickandmortywiki.databinding.FragmentCharactersBinding
import com.lira.rickandmortywiki.presentation.CharactersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharactersFragment : Fragment() {

    private val dialog by lazy { createProgressDialog() }
    private val charactersViewModel by viewModel<CharactersViewModel>()
    private val adapter by lazy { CharacterAdapter() }
    private var _binding: FragmentCharactersBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setupMenu()

        //charactersViewModel = ViewModelProvider(this)[CharactersViewModel::class.java]

        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvCharacters.adapter = adapter

        charactersViewModel.getAllCharactersList()
        charactersViewModel.characters.observe(viewLifecycleOwner) {
            when(it) {
                CharactersViewModel.State.Loading -> {
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    dialog.show()
                }
                is CharactersViewModel.State.Error -> {
                    dialog.dismiss()
                }
                is CharactersViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list.body()!!.results)
                }
            }
        }

        return root
    }

    private fun setupMenu() {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider, SearchView.OnQueryTextListener {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.search_menu, menu)
                val searchView = menu.findItem(R.id.action_search).actionView as SearchView
                searchView.setOnQueryTextListener(this)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.e(TAG, "onQueryTextSubmit: $query")
                binding.root.hideSoftKeyboard()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.e(TAG, "onQueryTextChange: $newText")
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        private const val TAG = "TAG"
    }
}