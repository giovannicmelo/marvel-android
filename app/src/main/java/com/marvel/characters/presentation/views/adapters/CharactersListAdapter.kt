package com.marvel.characters.presentation.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.databinding.ItemCharacterBinding
import com.marvel.core.characters.domain.Character

@SuppressLint("NotifyDataSetChanged")
class CharactersListAdapter(
    private var listItems: MutableList<Character> = mutableListOf(),
    private val listener: (Character, ImageView, TextView) -> Unit
) : RecyclerView.Adapter<CharactersListAdapter.CharactersListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharactersListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharactersListViewHolder, position: Int) {
        val character = listItems[position]
        holder.itemView.tag = character
        holder.bind(character, listener)
    }

    override fun getItemCount() = listItems.size

    fun resetAdapter() {
        listItems.clear()
        notifyDataSetChanged()
    }

    fun submitList(items: List<Character>) {
        listItems.addAll(items)
        notifyDataSetChanged()
    }

    class CharactersListViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character, listener: (Character, ImageView, TextView) -> Unit) {
            binding.character = character
            binding.mcvCharacter.setOnClickListener {
                listener(character, binding.ivThumb, binding.tvName)
            }
        }
    }
}