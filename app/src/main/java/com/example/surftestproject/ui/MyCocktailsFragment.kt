package com.example.surftestproject.ui

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.surftestproject.R
import com.example.surftestproject.adapters.CocktailAdapter
import com.example.surftestproject.data.Cocktail
import com.example.surftestproject.databinding.FragmentMyCocktailsBinding
import com.example.surftestproject.ui.viewmodels.CocktailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyCocktailsFragment : Fragment() {
    private var _binding: FragmentMyCocktailsBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var adapter: CocktailAdapter

    private val viewModel: CocktailViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.updateCocktails()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyCocktailsBinding.inflate(inflater, container, false)
        binding.cocktailsList.adapter = adapter

        viewModel.cocktailList.observe(viewLifecycleOwner) {
            if (it.isEmpty())
                binding.firstCocktailHint.visibility = View.VISIBLE

            adapter.submitList(it)
        }

        binding.cocktailsList.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.addCocktailBtn.setOnClickListener {
            findNavController().navigate(R.id.action_myCocktailsFragment_to_cocktailCreationFragment)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}