package com.example.surftestproject.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.surftestproject.R
import com.example.surftestproject.adapters.CocktailAdapter
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

            ViewCompat.animate(binding.mainImage)
                .translationYBy(-3000F)
                .setDuration(500)
                .start()

            ViewCompat.animate(binding.myCocktailsTv)
                .translationYBy(-500F)
                .setDuration(500)
                .start()

            ViewCompat.animate(binding.firstCocktailHint)
                .translationY(5000F)
                .setDuration(500)
                .start()

            ViewCompat.animate(binding.cocktailsList)
                .translationYBy(-500F)
                .setDuration(700)
                .start()

            adapter.submitList(it)
        }

        binding.cocktailsList.layoutManager = GridLayoutManager(requireContext(), 2)

        binding.addCocktailBtn.setOnClickListener {
            findNavController().navigate(R.id.action_myCocktailsFragment_to_cocktailCreationFragment)
        }

        binding.shareButton.setOnClickListener {
            val cocktails = viewModel.getCocktailsByCounter(4)
            val cocktailNames =
                viewModel.getCocktailsByCounter(4).joinToString(separator = ", ") { it.name }
            val finalText = if (cocktails.isEmpty())
                getString(R.string.share_message, cocktailNames)
            else getString(R.string.short_share_message)

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, finalText)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}