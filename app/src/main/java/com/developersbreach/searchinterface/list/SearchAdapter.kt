package com.developersbreach.searchinterface.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.developersbreach.searchinterface.R
import com.developersbreach.searchinterface.network.Article

class ArticleAdapter(
    private val sportsList: List<Article>,
    private val onClickListener: OnClickListener
) :
    RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val banner: ImageView = itemView.findViewById(R.id.banner_item_image_view)
        private val name: TextView = itemView.findViewById(R.id.name_item_text_view)

        fun bind(
            article: Article,
            onClickListener: OnClickListener
        ) {
            Glide.with(itemView.context).load(article.banner).into(banner)
            name.text = article.name
            itemView.setOnClickListener {
                onClickListener.onClick(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = sportsList[position]
        holder.bind(article, onClickListener)
    }

    override fun getItemCount() = sportsList.size

    class OnClickListener(val clickListener: (sports: Article) -> Unit) {
        fun onClick(sports: Article) = clickListener(sports)
    }
}