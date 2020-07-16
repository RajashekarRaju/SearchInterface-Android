package com.developersbreach.searchinterface.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.searchinterface.R

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)

        viewModel.data.observe(viewLifecycleOwner, Observer { list ->
            val adapter = ArticleAdapter(list, itemClickListener)
            recyclerView.adapter = adapter
        })
    }

    private val itemClickListener = ArticleAdapter.OnClickListener { article ->
        Log.e("SearchAdapter", article.name)
    }

}