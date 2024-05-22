package com.sopt.now.jumpit.ui.searchResult

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.sopt.now.jumpit.data.remote.response.SearchResult
import com.sopt.now.jumpit.databinding.ItemSearchResultBinding
import com.sopt.now.jumpit.util.view.ItemDiffCallback

class SearchResultAdapter(private val onClick: (SearchResult) -> (Unit)) :
    ListAdapter<SearchResult, SearchResultAdapter.SearchResultViewHolder>(
        ItemDiffCallback<SearchResult>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {

    class SearchResultViewHolder(
        private val binding: ItemSearchResultBinding,
        private val onClick: (SearchResult) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchResult) {
            binding.apply {
                ivSearchResultLogo.load(item.image) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }
                tvSearchResultCompany.text = item.company
                tvSearchResultTitle.text = item.title
                tvSearchResultTags.text = item.tags
                ivSearchResultBookmark.setOnClickListener {
                    it.isSelected = !it.isSelected
                }
                root.setOnClickListener { onClick(item) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}