package com.marvel.characters.presentation.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marvel.characters.databinding.ItemComicBinding
import com.marvel.core.characters.domain.Comic

class ComicsListAdapter(private var listItems: List<Comic>) :
    RecyclerView.Adapter<ComicsListAdapter.ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding = ItemComicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = listItems[position]
        holder.itemView.tag = comic
        holder.bind(comic)
    }

    override fun getItemCount() = listItems.size

    class ComicViewHolder(private val binding: ItemComicBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(comic: Comic) {
            binding.comic = comic
        }
    }
}