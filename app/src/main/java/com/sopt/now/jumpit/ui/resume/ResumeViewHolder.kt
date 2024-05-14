package com.sopt.now.jumpit.ui.resume

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.databinding.ItemResumeBinding

class ResumeViewHolder(
    private val binding: ItemResumeBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(resume: Resume) {
        if(resume.basicInfo){
            binding.ivBasicInfo.setImageResource(R.drawable.icn_dot_green)
        } else {
            binding.ivBasicInfo.setImageResource(R.drawable.icn_dot_red)
        }
        if(resume.techStack){
            binding.ivTechStack.setImageResource(R.drawable.icn_dot_green)
        } else {
            binding.ivTechStack.setImageResource(R.drawable.icn_dot_red)
        }
        if(resume.education){
            binding.ivEducation.setImageResource(R.drawable.icn_dot_green)
        } else {
            binding.ivEducation.setImageResource(R.drawable.icn_dot_red)
        }
        binding.tvResumeTitle.text = resume.title
        binding.tvResumeDate.text = resume.date
        binding.btnToggle.setOnClickListener {
            if(resume.state){
                binding.btnToggle.setImageResource(R.drawable.icn_toggleoff)
                resume.state = false
            } else {
                binding.btnToggle.setImageResource(R.drawable.icn_toggleon)
                resume.state = true
            }

        }
        binding.btnEdit.setOnClickListener {

        }
    }
}