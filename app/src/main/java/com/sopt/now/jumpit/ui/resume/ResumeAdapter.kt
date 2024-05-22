package com.sopt.now.jumpit.ui.resume

import MyResumeResponse
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.ListAdapter
import com.sopt.now.jumpit.databinding.ItemResumeBinding
import com.sopt.now.jumpit.util.view.ItemDiffCallback

class ResumeAdapter(fragmentManager: FragmentManager,
    private val onClick: () -> Unit) :
    ListAdapter<Resume, ResumeViewHolder>(DiffUtil) {
    private var fragmentManager: FragmentManager

    init {
        this.fragmentManager = fragmentManager
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResumeViewHolder {
        return ResumeViewHolder(
            ItemResumeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClick
        )
    }

    override fun onBindViewHolder(holder: ResumeViewHolder, position: Int) {
        holder.onBind(position, getItem(position), fragmentManager)
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<Resume>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }

}