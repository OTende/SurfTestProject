package com.example.surftestproject.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.example.surftestproject.R

class IngredientCreationFragment : DialogFragment() {
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            // Verify that the host Fragment implements the callback interface
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnStringSelectedListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ingredient_creation, container, false)
        view.findViewById<Button>(R.id.button123).setOnClickListener {
//            listener?.onStringSelected("asdfghhj")
        }
        return view
    }
}