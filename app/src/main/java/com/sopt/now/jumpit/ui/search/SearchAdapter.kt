package com.sopt.now.jumpit.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.jumpit.data.model.RecentKeyword
import com.sopt.now.jumpit.databinding.ItemSearchRecentBinding
import com.sopt.now.jumpit.util.view.ItemDiffCallback

class SearchAdapter(private val onKeywordClick: (RecentKeyword) -> (Unit), private val onDeleteClick: (RecentKeyword) -> (Unit)) :
    ListAdapter<RecentKeyword, SearchAdapter.SearchRecentViewHolder>(
        ItemDiffCallback<RecentKeyword>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {

    class SearchRecentViewHolder(
        private val binding: ItemSearchRecentBinding,
        private val onKeywordClick: (RecentKeyword) -> Unit,
        private val onDeleteClick: (RecentKeyword) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecentKeyword) {
            binding.apply {
                tvSearchRecentItem.text = item.keyword
                root.setOnClickListener { onKeywordClick(item) }
                ivSearchRecentDelete.setOnClickListener { onDeleteClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecentViewHolder {
        val binding =
            ItemSearchRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchRecentViewHolder(binding, onKeywordClick, onDeleteClick)
    }

    override fun onBindViewHolder(holder: SearchRecentViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}