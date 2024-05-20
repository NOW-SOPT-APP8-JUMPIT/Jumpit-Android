package com.sopt.now.jumpit.ui.searchRecent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.jumpit.data.model.SearchKeyword
import com.sopt.now.jumpit.databinding.ItemSearchRecentBinding
import com.sopt.now.jumpit.util.view.ItemDiffCallback

class SearchRecentAdapter(private val onKeywordClick: (SearchKeyword) -> (Unit), private val onDeleteClick: (SearchKeyword) -> (Unit)) :
    ListAdapter<SearchKeyword, SearchRecentAdapter.SearchRecentViewHolder>(
        ItemDiffCallback<SearchKeyword>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {

    class SearchRecentViewHolder(
        private val binding: ItemSearchRecentBinding,
        private val onKeywordClick: (SearchKeyword) -> Unit,
        private val onDeleteClick: (SearchKeyword) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchKeyword) {
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