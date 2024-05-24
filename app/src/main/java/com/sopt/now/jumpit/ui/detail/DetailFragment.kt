package com.sopt.now.jumpit.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import coil.load
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.data.remote.response.DummyData
import com.sopt.now.jumpit.data.remote.response.Skill
import com.sopt.now.jumpit.databinding.FragmentDetailBinding
import com.sopt.now.jumpit.ui.common.base.BindingFragment

class DetailFragment : BindingFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel by activityViewModels<DetailViewModel>()
    private lateinit var dummyData: DummyData
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val positionId = arguments?.getInt("positionId") ?: -1

        dummyData = DummyData(
            imageUrl = R.drawable.img_detail_dummy,
            companyName = "엠비아이솔루션",
            jobTitle = "[프론트엔드] React 미드레벨 챗봇 개발자"
        )

        viewModel.getDetailInfo(positionId.toLong())
        viewModel.detailInfo.observe(viewLifecycleOwner) {
            binding.companyImage.load(it.company.image)
            binding.tvDetailCorp.text = it.company.name

            binding.tvDetailJobTitle.text = it.position.title
            binding.tvDetailCareerReq.text = it.position.career
            binding.tvDetailDeadlineReq.text = it.position.deadline
            binding.tvDetailLocationReq.text = it.position.location
            binding.tvDetailSchoolReq.text = it.position.education

            binding.tvDetailResponsibilitiesReq.text = it.position.responsibilities
            binding.tvDetailEtcReq.text = it.position.notice
            binding.tvDetailPrefferedReq.text = it.position.preferred
            binding.tvDetailQualificationsReq.text = it.position.qualifications
            binding.tvDetailBenefitsReq.text = it.position.benefits

            binding.companyImageBottom.load(it.company.image)
            binding.tvDetailCompanyNameBottom.text = it.company.name

            binding.companyImagePlus.load(dummyData.imageUrl)
            binding.tvDetailCompanyNamePlus.text = dummyData.companyName
            binding.tvDetailJobTitlePlus.text = dummyData.jobTitle


            addSkills(it.skills)
        }

        setupToggleListeners()
    }

    private fun addSkills(skills: List<Skill>) {
        val container = binding.llSkillsContainer
        container.removeAllViews()

        for (skill in skills) {
            val skillView = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_skill, container, false) as ConstraintLayout
            val skillImage = skillView.findViewById<ImageView>(R.id.skill_image)
            val skillName = skillView.findViewById<TextView>(R.id.skill_name)

            skillImage.load(skill.image)
            skillName.text = skill.name

            container.addView(skillView)
        }
    }

    private fun setupToggleListeners() {
        toggleSection(binding.icToggleSkills, binding.llSkillsContainer)
        toggleSection(binding.icToggleResponsibilities, binding.tvDetailResponsibilitiesReq)
        toggleSection(binding.icToggleQualifications, binding.tvDetailQualificationsReq)
        toggleSection(binding.icTogglePreffered, binding.tvDetailPrefferedReq)
        toggleSection(binding.icToggleBenefits, binding.tvDetailBenefitsReq)
        toggleSection(binding.icToggleEtc, binding.tvDetailEtcReq)
    }

    private fun toggleSection(toggleButton: View, contentView: View) {
        toggleButton.setOnClickListener {
            contentView.visibility = if (contentView.visibility == View.GONE) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}