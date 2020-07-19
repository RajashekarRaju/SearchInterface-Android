package com.developersbreach.searchinterface.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developersbreach.searchinterface.network.Article
import com.developersbreach.searchinterface.network.articleResponse
import com.developersbreach.searchinterface.network.fetchArticleJsonData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class SearchViewModel : ViewModel() {

    private val _data = MutableLiveData<List<Article>>()
    val data: LiveData<List<Article>>
        get() = _data

    private lateinit var searchableArticle: List<Article>
    private val filteredList = MutableLiveData<List<Article>>()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val list = fetchArticleJsonData(articleResponse())
            _data.postValue(list)

            searchableArticle = list
        }
    }

    fun filter(query: String): LiveData<List<Article>> {
        filterWithQuery(query)
        return filteredList
    }

    private fun filterWithQuery(query: String) {
        val filterList = ArrayList<Article>()
        for (article: Article in searchableArticle) {
            val formatTitle: String = article.name.toLowerCase(Locale.getDefault())
            if (formatTitle.contains(query)) {
                filterList.add(article)
            }
        }
        filteredList.value = filterList
    }
}