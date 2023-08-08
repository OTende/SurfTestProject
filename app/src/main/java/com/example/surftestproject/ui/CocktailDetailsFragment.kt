package com.example.surftestproject.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.surftestproject.R
import com.example.surftestproject.data.ImageDecoder
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
        val id = requireArguments().getInt(DETAILS_KEY)
        val cocktail = viewModel.getCocktailById(id)

        if (cocktail.image != null) {
            binding.cocktailImage.setImageBitmap(ImageDecoder.toBitmap(cocktail.image))
        } else {
            binding.cocktailImage.setImageDrawable(
                ResourcesCompat.getDrawable(resources, R.drawable.baseline_camera_alt_24, null)
            )
        }

        binding.ingredients.text = cocktail.ingredients.joinToString(separator = "\n_\n\n")

        binding.name.text = cocktail.name
        binding.recipe.text = cocktail.recipe
        binding.description.text = cocktail.description

        binding.deleteCocktail.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.delete))
                .setMessage(R.string.are_you_sure)
                .setPositiveButton(R.string.yes) { _, _ ->
                    viewModel.deleteCocktail(cocktail)
                    Toast.makeText(
                        requireContext(), R.string.successful_deleting, Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_cocktailDetailsFragment_to_myCocktailsFragment)
                }
                .setNegativeButton(R.string.no) { dialog, _ ->
                    dialog.dismiss()
                }
                .create().show()
        }

        binding.updateCocktail.setOnClickListener {
            val bundle = CocktailCreationFragment.getBundleForUpdate(cocktail)
            findNavController().navigate(R.id.action_cocktailDetailsFragment_to_cocktailCreationFragment, bundle)
        }

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