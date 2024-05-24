package com.sopt.now.jumpit.ui.home

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.FragmentHomeBinding
import com.sopt.now.jumpit.ui.base.BindingFragment

class HomeFragment : BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchBar.setOnClickListener {
            findNavController().navigate(R.id.actionHomeToSearch)
        }
    }
}