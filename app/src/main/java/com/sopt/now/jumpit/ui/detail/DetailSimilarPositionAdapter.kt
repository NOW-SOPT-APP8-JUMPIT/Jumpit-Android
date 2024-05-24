package com.sopt.now.jumpit.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.sopt.now.jumpit.data.remote.response.SearchResultsResponse
import com.sopt.now.jumpit.databinding.ItemSimilarPositionBinding
import com.sopt.now.jumpit.util.view.ItemDiffCallback

class DetailSimilarPositionAdapter() :
    ListAdapter<SearchResultsResponse.Position, DetailSimilarPositionAdapter.SimilarPositionViewHolder>(
        ItemDiffCallback<SearchResultsResponse.Position>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {

    class SimilarPositionViewHolder(
        private val binding: ItemSimilarPositionBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchResultsResponse.Position) {
            binding.apply {
                tvDetailCompanyNamePlus.text = item.company.name
                tvDetailJobTitlePlus.text = item.title
                tvDetailJobSkillsPlus.text = item.skills.joinToString(" · ") { it.name }
                ivDetailBookmarkPlus.setOnClickListener {
                    it.isSelected = !it.isSelected
                }
                ivCompanyImagePlus.load(item.company.image) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarPositionViewHolder {
        val binding =
            ItemSimilarPositionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SimilarPositionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SimilarPositionViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}