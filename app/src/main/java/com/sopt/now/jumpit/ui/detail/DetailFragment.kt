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
import com.sopt.now.jumpit.data.remote.response.SearchResultsResponse
import com.sopt.now.jumpit.data.remote.response.SimilarCompanyDummyData
import com.sopt.now.jumpit.data.remote.response.Skill
import com.sopt.now.jumpit.databinding.FragmentDetailBinding
import com.sopt.now.jumpit.ui.common.base.BindingFragment

class DetailFragment : BindingFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel by activityViewModels<DetailViewModel>()
    private lateinit var similarCompanyDummyData: SimilarCompanyDummyData
    private val similarPositionAdapter: DetailSimilarPositionAdapter =
        DetailSimilarPositionAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val positionId = arguments?.getLong("positionId") ?: 0

        val dummySimilarPositions = listOf(
            SearchResultsResponse.Position(
                id = 1,
                title = "프론트엔드 개발자",
                skills = listOf(
                    SearchResultsResponse.Position.Skill("Docker", ""),
                    SearchResultsResponse.Position.Skill("MySQL", ""),
                    SearchResultsResponse.Position.Skill("Python", "")
                ),
                company = SearchResultsResponse.Position.Company(
                    id = 1,
                    name = "엠비아이솔루션",
                    image = "https://github.com/NOW-SOPT-APP8-JUMPIT/Jumpit-Android/assets/127238018/393fcdea-d070-4e34-9dbf-3e0bde5798a8",
                    description = "나경하이"
                )
            ),
            SearchResultsResponse.Position(
                id = 2,
                title = "백엔드 개발자",
                skills = listOf(
                    SearchResultsResponse.Position.Skill("typescript", ""),
                    SearchResultsResponse.Position.Skill("react", ""),
                    SearchResultsResponse.Position.Skill("javascript", ""),
                    SearchResultsResponse.Position.Skill("solidity", ""),
                ),
                company = SearchResultsResponse.Position.Company(
                    id = 2,
                    name = "르네상스랩스",
                    image = "https://github-production-user-asset-6210df.s3.amazonaws.com/127238018/333504904-506586c3-3af6-482a-b632-d450bbe946b3.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAVCODYLSA53PQK4ZA%2F20240524%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20240524T140948Z&X-Amz-Expires=300&X-Amz-Signature=2e611111cec813028885c71887c8ca5cfb472deb00a0b39c5ee113bc9f9cbdfe&X-Amz-SignedHeaders=host&actor_id=127238018&key_id=0&repo_id=796325247",
                    description = "가을하이"
                )
            )
        )
        binding.rvDetailSimilarPositions.adapter = similarPositionAdapter
        similarPositionAdapter.submitList(dummySimilarPositions)

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