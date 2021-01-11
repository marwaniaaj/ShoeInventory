package com.example.android.shoeinventory.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.shoeinventory.R
import com.example.android.shoeinventory.databinding.FragmentShoeListBinding
import com.example.android.shoeinventory.databinding.ShoeBinding
import com.example.android.shoeinventory.models.Shoe
import com.example.android.shoeinventory.viewModels.ShoeListViewModel
import kotlinx.android.synthetic.main.shoe.view.*

class ShoeListFragment: Fragment() {

    private lateinit var binding: FragmentShoeListBinding

    private val shoeViewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding =  FragmentShoeListBinding.inflate(inflater, container, false)

        // Set the viewModel for databinding
        binding.shoeListViewModel = shoeViewModel

        // Specify the current activity as the lifecycle owner.
        binding.lifecycleOwner = this

        // Set up shoe list LiveData observation
        shoeViewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            for (shoe in shoeList) {

                // Inflate shoe layout within the shoeLinearLayout, with databinding
                DataBindingUtil.inflate<ShoeBinding>(layoutInflater, R.layout.shoe, binding.shoeLinearLayout, true)
                    .apply {
                        this.shoe = shoe
                }
            }
        })

        // Navigate to Shoe Detail fragment
        binding.detailFloatingActionButton.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }

        // Fragment has menu items to contribute.
        // so onCreateOptionsMenu will be called
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

}