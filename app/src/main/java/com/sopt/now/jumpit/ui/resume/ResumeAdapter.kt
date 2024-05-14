package com.sopt.now.jumpit.ui.resume

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.jumpit.databinding.ItemResumeBinding

class ResumeAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var resumeList: List<Resume> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ResumeViewHolder(ItemResumeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return resumeList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ResumeViewHolder).onBind(resumeList[position])
    }

    fun setResumeList(resumeList: List<Resume>) {
        this.resumeList = resumeList
        notifyDataSetChanged()
    }
}