package com.marvel.characters.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.databinding.ItemCharacterBinding
import com.marvel.data.models.Character

class CharacterAdapter(
    private var listItems: List<Character>,
    private val listener: (Character, ImageView, TextView) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = listItems[position]
        holder.itemView.tag = character
        holder.bind(character, listener)
    }

    override fun getItemCount() = listItems.size

    fun submitList(items: List<Character>) {
        listItems = items
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character, listener: (Character, ImageView, TextView) -> Unit) {
            binding.character = character
            binding.mcvCharacter.setOnClickListener {
                listener(character, binding.ivThumb, binding.tvName)
            }
        }
    }
}