package com.developersbreach.searchinterface.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.searchinterface.R
import com.developersbreach.searchinterface.network.Article
import java.util.*


class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var noSearchResultsFound: TextView
    private lateinit var searchEditText: AppCompatEditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.search_recycler_view)
        noSearchResultsFound = view.findViewById(R.id.no_search_results_found_text)
        searchEditText = view.findViewById(R.id.article_search_edit_text)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        searchableArticles()

        searchEditText.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(query: CharSequence, start: Int, before: Int, count: Int) {
                queriedArticles(query)
            }

            override fun afterTextChanged(s: Editable?) {
                // Do not perform operation
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do not perform operation
            }
        })
    }

    private fun searchableArticles() {
        viewModel.data.observe(viewLifecycleOwner, Observer { articles ->
            val adapter = SearchAdapter(articles)
            recyclerView.adapter = adapter
        })
    }

    private fun queriedArticles(query: CharSequence) {
        if (query.isNotEmpty()) {
            val formatQuery: String = query.toString().toLowerCase(Locale.getDefault())
            viewModel.filter(formatQuery).observe(viewLifecycleOwner, Observer { articles ->
                val adapter = SearchAdapter(articles)
                recyclerView.adapter = adapter
                toggleRecyclerView(articles)
            })
        } else {
            searchableArticles()
        }
    }

    private fun toggleRecyclerView(articleList: List<Article>) {
        if (articleList.isEmpty()) {
            recyclerView.visibility = View.INVISIBLE
            noSearchResultsFound.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.VISIBLE
            noSearchResultsFound.visibility = View.INVISIBLE
        }
    }
}