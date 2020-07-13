package com.developersbreach.searchinterface.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.developersbreach.searchinterface.network.Article
import com.developersbreach.searchinterface.network.articleResponse
import com.developersbreach.searchinterface.network.fetchArticleJsonData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private val _data = MutableLiveData<List<Article>>()
    val data: LiveData<List<Article>>
        get() = _data

    init {
        CoroutineScope(Dispatchers.IO).launch {
            loadArticlesData()
        }
    }

    private fun loadArticlesData() {
        val list = fetchArticleJsonData(articleResponse())
        _data.postValue(list)
    }
}