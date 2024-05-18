package com.sopt.now.jumpit.ui.resume

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.jumpit.databinding.ItemResumeBinding

class ResumeAdapter(fragmentManager: FragmentManager) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var resumeList: List<Resume> = listOf()
    private var fragmentManager: FragmentManager

    init {
        this.fragmentManager = fragmentManager
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ResumeViewHolder(
            ItemResumeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return resumeList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ResumeViewHolder).onBind(position, resumeList[position], fragmentManager)
    }

    fun setResumeList(resumeList: List<Resume>) {
        this.resumeList = resumeList
        notifyDataSetChanged()
    }

}