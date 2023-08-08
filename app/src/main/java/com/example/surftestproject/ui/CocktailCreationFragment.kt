package com.example.surftestproject.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.surftestproject.R
import com.example.surftestproject.data.Cocktail
import com.example.surftestproject.data.ImageDecoder
import com.example.surftestproject.databinding.FragmentCocktailCreationBinding
import com.example.surftestproject.ui.viewmodels.CocktailViewModel
import com.google.android.material.textfield.TextInputLayout

class CocktailCreationFragment : Fragment() {
    private var _binding: FragmentCocktailCreationBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CocktailViewModel by activityViewModels()

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding.cocktailImage.setImageURI(uri)
        }

    private var id = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCocktailCreationBinding.inflate(inflater, container, false)
        binding.cocktailImage.setOnClickListener {
            getContent.launch("image/*")
        }

        if (arguments != null) {
            with(requireArguments()) {
                binding.cocktailName.editText?.text =
                    Editable.Factory.getInstance().newEditable(getString(NAME_TAG))
                binding.cocktailDescription.editText?.text =
                    SpannableStringBuilder(getString(DESCRIPTION_TAG))
                binding.cocktailImage.setImageBitmap(ImageDecoder.toBitmap(getByteArray(IMAGE_TAG)!!))
                binding.cocktailRecipe.editText?.text =
                    SpannableStringBuilder(getString(RECIPE_TAG))
                id = getInt(ID_TAG)
            }
        }

        // Изменение так и не заработало, только добавление
        binding.saveCocktailButton.setOnClickListener {
            if (binding.cocktailName.verifyDataNotEmpty("Add title")
                && binding.cocktailRecipe.verifyDataNotEmpty("Add recipe")
            ) {
                val cocktail = createCocktail()
                if (id == -1) {
                    viewModel.saveCocktail(cocktail)
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.save_success),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.changeCocktail(cocktail)
                    // Крашит всё
//                val bundle = bundleOf(CocktailDetailsFragment.DETAILS_KEY to id)
//                findNavController().navigate(
//                    R.id.action_cocktailCreationFragment_to_cocktailDetailsFragment,
//                    bundle
//                )
                }
            }
        }

        binding.cancelButton.setOnClickListener {
            findNavController().popBackStack()
        }

        // Делалось впопыхах, вариант с нормальным фрагментом был выкинут
        binding.addIngredientButton.setOnClickListener {
            val input = EditText(requireContext())
            AlertDialog.Builder(requireContext())
                .setView(input)
                .setTitle(R.string.add_ingredient)
                .setPositiveButton(R.string.add) { _, _ ->
                    val view = TextView(requireContext()).apply {
                        text = input.text.toString()
                    }
                    binding.ingredientsList.addView(view)
                }
                .setNegativeButton(R.string.cancel) { _, _ ->

                }
                .show()
        }

        return binding.root
    }

    private fun TextInputLayout.verifyDataNotEmpty(errorMessage: String): Boolean {
        if (this.editText?.text?.isEmpty() == true) {
            this.editText?.error = errorMessage
            return false
        }
        this.editText?.error = null
        return true
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

    companion object {
        private const val ID_TAG = "ID_TAG"
        private const val NAME_TAG = "NAME_TAG"
        private const val DESCRIPTION_TAG = "DESCRIPTION_TAG"
        private const val INGREDIENTS_TAG = "INGREDIENTS_TAG"
        private const val IMAGE_TAG = "IMAGE_TAG"
        private const val RECIPE_TAG = "RECIPE_TAG"

        fun getBundleForUpdate(cocktail: Cocktail): Bundle {
            with(cocktail) {
                val bundle = bundleOf().apply {
                    putInt(ID_TAG, id)
                    putString(NAME_TAG, name)
                    putString(DESCRIPTION_TAG, description)
                    putStringArray(INGREDIENTS_TAG, ingredients.toTypedArray())
                    putByteArray(IMAGE_TAG, image)
                    putString(RECIPE_TAG, recipe)
                }
                return bundle
            }
        }
    }
}