package com.example.surftestproject.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.surftestproject.data.Cocktail

class CocktailDiffCallback() : DiffUtil.ItemCallback<Cocktail>() {
    override fun areItemsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Cocktail, newItem: Cocktail): Boolean {
        return oldItem.name == newItem.name
    }
}