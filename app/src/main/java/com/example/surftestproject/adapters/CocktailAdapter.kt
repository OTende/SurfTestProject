package com.example.surftestproject.adapters

import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.surftestproject.R
import com.example.surftestproject.data.Cocktail
import com.example.surftestproject.data.ImageDecoder
import com.example.surftestproject.databinding.CocktailListItemBinding
import com.example.surftestproject.ui.CocktailDetailsFragment
import javax.inject.Inject


class CocktailAdapter @Inject constructor() :
    ListAdapter<Cocktail, CocktailAdapter.CocktailViewHolder>(CocktailDiffCallback()) {
    inner class CocktailViewHolder(private val binding: CocktailListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Cocktail) {
            // Convert byte array to bitmap
            binding.cocktailImage.setImageBitmap(ImageDecoder.toBitmap(item.image!!))
            binding.cocktailName.text = item.name

            binding.root.setOnClickListener {
                val bundle = bundleOf(CocktailDetailsFragment.DETAILS_KEY to item.id)
                binding.root
                    .findNavController()
                    .navigate(R.id.action_myCocktailsFragment_to_cocktailDetailsFragment, bundle)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CocktailListItemBinding.inflate(inflater, parent, false)
        return CocktailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}