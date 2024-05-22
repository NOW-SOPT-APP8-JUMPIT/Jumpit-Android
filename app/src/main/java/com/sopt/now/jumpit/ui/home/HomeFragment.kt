package com.sopt.now.jumpit.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.ui.search.SearchFragment

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchButton: ImageView = view.findViewById(R.id.searchBar)
        searchButton.setOnClickListener {
            findNavController().navigate(R.id.actionHomeToSearch)
        }
    }
}