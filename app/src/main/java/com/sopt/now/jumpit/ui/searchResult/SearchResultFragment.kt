package com.sopt.now.jumpit.ui.searchResult

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sopt.now.jumpit.R
import com.sopt.now.jumpit.data.remote.response.SearchResult
import com.sopt.now.jumpit.databinding.FragmentSearchResultBinding
import com.sopt.now.jumpit.ui.base.BindingFragment
import com.sopt.now.jumpit.ui.detail.DetailFragment

class SearchResultFragment :
    BindingFragment<FragmentSearchResultBinding>(R.layout.fragment_search_result) {

    private lateinit var searchResultAdapter: SearchResultAdapter
    private val viewModel: SearchResultViewModel by activityViewModels()
    private var bottomSheet: SearchCategoryDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupPositionChipClickListener()
        observeSearchResult()
    }

    private fun setupRecyclerView() {
        searchResultAdapter = SearchResultAdapter(
            onClick = { searchResult -> navigateToDetail(searchResult.id) }
        )
        binding.rvSearchResult.adapter = searchResultAdapter
    }

    private fun navigateToDetail(searchResult: Int) {
        findNavController().navigate(
            R.id.fragmentDetail,
            bundleOf("positionId" to 5)
        )
    }

    private fun setupPositionChipClickListener() {
        binding.chipSearchCategoryPosition.setOnClickListener {
            showBottomSheet()
        }
    }

    private fun showBottomSheet() {
        if (bottomSheet == null || !bottomSheet!!.isAdded) {
            bottomSheet = SearchCategoryDialog()
            bottomSheet!!.show(parentFragmentManager, "SearchCategoryDialog")
        }
    }

    private fun observeSearchResult() {
        viewModel.searchResults.observe(viewLifecycleOwner) {
            updateSearchResultCount(it)
            updateEmptyTextVisibility(it)
            searchResultAdapter.submitList(it)
        }
    }

    private fun updateSearchResultCount(searchResults: List<SearchResult>) {
        binding.tvSearchResultCount.text = createSpannableString(
            getString(R.string.searchResultCount, searchResults.size),
            searchResults.size
        )
    }

    private fun updateEmptyTextVisibility(searchResults: List<SearchResult>) {
        when (searchResults.isEmpty()) {
            true -> binding.llSearchResultEmpty.visibility = View.VISIBLE
            false -> binding.llSearchResultEmpty.visibility = View.GONE
        }
    }

    private fun createSpannableString(
        fullText: String,
        count: Int
    ): SpannableString {
        val spannableString = SpannableString(fullText)
        val positionStart = SPANNABLE_START
        val positionEnd = positionStart + SPANNABLE_POSITION_END
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.gray5)),
            positionStart,
            positionEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        val countStart = fullText.indexOf("($count)")
        val countEnd = countStart + "($count)".length
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.green)),
            countStart,
            countEnd,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }

    companion object {
        private const val SPANNABLE_START = 0
        private const val SPANNABLE_POSITION_END = 3
    }
}
