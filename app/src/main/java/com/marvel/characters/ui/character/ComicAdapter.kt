package com.marvel.characters.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.databinding.ItemComicBinding
import com.marvel.data.models.Comic

class ComicAdapter(private var listItems: List<Comic>) :
    RecyclerView.Adapter<ComicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemComicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val comic = listItems[position]
        holder.itemView.tag = comic
        holder.bind(comic)
    }

    override fun getItemCount() = listItems.size

    class ViewHolder(private val binding: ItemComicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: Comic) {
            binding.comic = comic
        }
    }
}