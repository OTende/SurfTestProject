package com.example.surftestproject.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.surftestproject.R
import com.example.surftestproject.databinding.FragmentIngredientCreationBinding

class IngredientCreationFragment : DialogFragment() {
    private var _binding: FragmentIngredientCreationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(R.layout.fragment_ingredient_creation)
            .setPositiveButton("asd") { _, _, ->
                val value = binding.ingredient.editText?.text
            }
            .create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientCreationBinding.inflate(inflater, container, false)
        binding.addIngredientButton.setOnClickListener {
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}