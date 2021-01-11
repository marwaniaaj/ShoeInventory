package com.example.android.shoeinventory.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.shoeinventory.R
import com.example.android.shoeinventory.databinding.FragmentShoeDetailBinding
import com.example.android.shoeinventory.models.Shoe
import com.example.android.shoeinventory.viewModels.ShoeListViewModel

class ShoeDetailFragment: Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding

    private val shoeViewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate layout
        binding = FragmentShoeDetailBinding.inflate(inflater, container, false)

        // Set the viewModel for databinding
        binding.shoeViewModel = shoeViewModel

        // Initialize the new shoe
        binding.shoeModel = Shoe("",0.0,"","")

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        // Set up listening observer to save event
        shoeViewModel.eventSave.observe(viewLifecycleOwner, Observer { onSave ->
            if (onSave) {
                shoeViewModel.onSaveFinished()
                findNavController().navigateUp()
            }
        })

        // Set up listening observer to cancel event
        shoeViewModel.eventCancel.observe(viewLifecycleOwner, Observer { onCancel ->
            if (onCancel) {
                shoeViewModel.onCancelFinished()
                findNavController().navigateUp()
            }
        })

        return binding.root
    }
}