package com.developersbreach.searchinterface.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.developersbreach.searchinterface.R
import com.developersbreach.searchinterface.network.Article

class SearchAdapter(
    private val sportsList: List<Article>
) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    class SearchViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.article_name_search_item_text_view)

        fun bind(
            article: Article
        ) {
            name.text = article.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_search,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val article = sportsList[position]
        holder.bind(article)
    }

    override fun getItemCount() = sportsList.size
}