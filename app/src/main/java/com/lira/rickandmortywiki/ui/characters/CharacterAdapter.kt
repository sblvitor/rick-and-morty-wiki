package com.lira.rickandmortywiki.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lira.rickandmortywiki.R
import com.lira.rickandmortywiki.core.translateResponseText
import com.lira.rickandmortywiki.data.model.character.Character
import com.lira.rickandmortywiki.databinding.ItemCharacterBinding


class CharacterAdapter: ListAdapter<Character, CharacterAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Character){
            binding.tvCharacterName.text = item.name
            when(item.status){
                "Alive" -> binding.ivStatus.setImageResource(R.drawable.dot_indicator_alive)
                "Dead" -> binding.ivStatus.setImageResource(R.drawable.dot_indicator_dead)
                else -> binding.ivStatus.setImageResource(R.drawable.dot_indicator_unknown)
            }
            binding.tvStatus.text = itemView.context.resources.getString(translateResponseText(item.status))

            Glide
                .with(binding.root.context)
                .load(item.image)
                .into(binding.ivCharacterImage)

            itemView.setOnClickListener { view ->
                view.findNavController().navigate(R.id.characterDetailFragment)
            }
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Character, newItem: Character) = oldItem.id == newItem.id
}