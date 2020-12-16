package com.udacity.shoestore.ui.shoe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import kotlinx.android.synthetic.main.fragment_shoe_detail.*

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        binding.saveBtn.setOnClickListener {
            val size = sizeInput.text.toString()
            viewModel.addShoe(
                nameInput.text.toString(),
                if(size.isNotEmpty()) size.toDouble() else 0.0,
                companyInput.text.toString(),
                descriptionInput.text.toString()
            )

            navigateBack()
        }

        binding.cancelBtn.setOnClickListener { navigateBack() }

        return binding.root
    }

    private fun navigateBack() {
        findNavController().navigateUp()
    }
}