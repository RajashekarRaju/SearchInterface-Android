package com.developersbreach.searchinterface.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.developersbreach.searchinterface.R
import com.developersbreach.searchinterface.network.Article


class DetailFragment : Fragment() {

    private lateinit var titleTextView: TextView
    private lateinit var subtitleTextView: TextView
    private lateinit var bannerImageView: ImageView

    private lateinit var article: Article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            article = it.getParcelable("ARTICLE_DATA")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        titleTextView = view.findViewById(R.id.detail_title_text_view)
        subtitleTextView = view.findViewById(R.id.detail_subtitle_text_view)
        bannerImageView = view.findViewById(R.id.detail_image_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        titleTextView.text = article.name
        subtitleTextView.text = article.description.dropLast(5).drop(3)
        Glide.with(requireContext()).load(article.banner).into(bannerImageView)
    }

    companion object {

        @JvmStatic
        fun newInstance(article: Article) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("ARTICLE_DATA", article)
                }
            }
    }
}