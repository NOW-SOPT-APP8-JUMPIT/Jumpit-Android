package com.sopt.now.jumpit.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.FragmentDetailBinding
import com.sopt.now.jumpit.ui.base.BindingFragment

class DetailFragment : BindingFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel by activityViewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val positionId = arguments?.getLong("positionId") ?: 0L

        viewModel.getDetailInfo(positionId)
        viewModel.detailInfo.observe(viewLifecycleOwner) {
            binding.companyImage.load(it.company.image)
            binding.tvDetailJobTitle.text = it.position.title
            binding.tvDetailCorp.text = it.company.name
            binding.tvDetailCareerReq.text = it.position.career
            binding.tvDetailDeadlineReq.text = it.position.deadline
            binding.tvDetailLocationReq.text = it.position.location
            binding.tvDetailSchoolReq.text = it.position.education
        }
    }
}
