package com.example.surftestproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import com.example.surftestproject.R
import com.example.surftestproject.data.ImageDecoder
import com.example.surftestproject.databinding.FragmentCocktailCreationBinding
import com.example.surftestproject.databinding.FragmentCocktailDetailsBinding
import com.example.surftestproject.ui.viewmodels.CocktailViewModel

class CocktailDetailsFragment : Fragment() {
    private var _binding: FragmentCocktailDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CocktailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailDetailsBinding.inflate(inflater, container, false)
        val id = arguments?.getInt(DETAILS_KEY)
        val cocktail = viewModel.getCocktailById(id ?: 0)

        if (cocktail.image != null) {
            binding.cocktailImage.setImageBitmap(ImageDecoder.toBitmap(cocktail.image))
        } else {
            binding.cocktailImage.setImageDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.baseline_camera_alt_24, null)
            )
        }

        binding.ingredients.text = cocktail.ingredients.joinToString(separator = "\n_\n")

        binding.name.text = cocktail.name
        binding.recipe.text = cocktail.recipe
        binding.description.text = cocktail.description
//        cocktail.ingredients.forEach {
//            val view = TextView(requireContext()).apply {
//                setTextAppearance(R.style.Base_Theme_AppCompat_DefaultText)
//                text = it
//            }
////            binding.recipeContainer.addView(view)
//
//            val underscore = TextView(requireContext()).apply {
//                text = "_"
//                setTextAppearance(R.style.Base_Theme_AppCompat_DefaultText)
//            }
//
//            binding.recipeContainer.addView(underscore)
//        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val DETAILS_KEY = "CocktailDetailsFragment"
    }
}