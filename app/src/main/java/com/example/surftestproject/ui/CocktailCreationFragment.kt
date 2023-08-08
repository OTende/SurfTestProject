package com.example.surftestproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.surftestproject.data.Cocktail
import com.example.surftestproject.data.ImageDecoder
import com.example.surftestproject.databinding.FragmentCocktailCreationBinding
import com.example.surftestproject.models.OnStringSelectedListener
import com.example.surftestproject.ui.viewmodels.CocktailViewModel


class CocktailCreationFragment : Fragment() {
    private var _binding: FragmentCocktailCreationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailViewModel by activityViewModels()

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        binding.cocktailImage.setImageURI(uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailCreationBinding.inflate(inflater, container, false)
        binding.cocktailImage.setOnClickListener {
            getContent.launch("image/*")
        }

        binding.saveCocktailButton.setOnClickListener {
            viewModel.saveCocktail(createCocktail())
        }

        binding.addIngredientButton.setOnClickListener {
            IngredientCreationFragment().show(
                childFragmentManager, "asd"
            )
        }

        return binding.root
    }

    private fun createCocktail(): Cocktail {
        val values = mutableListOf<String>()
        binding.ingredientsList.forEach {
            values.add((it as TextView).text.toString())
        }

        return Cocktail(
            name = binding.cocktailName.editText?.text.toString(),
            description = binding.cocktailDescription.editText?.text.toString(),
            ingredients = values.toList(),
            image = ImageDecoder.toByteArray(binding.cocktailImage),
            recipe = binding.cocktailRecipe.editText?.text.toString()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}