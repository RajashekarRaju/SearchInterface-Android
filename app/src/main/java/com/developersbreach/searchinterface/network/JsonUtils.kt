package com.developersbreach.searchinterface.network

import android.util.Log
import org.json.JSONArray
import org.json.JSONObject

fun fetchArticleJsonData(response: String): List<Article> {

    // Create a new ArrayList for adding articles into list.
    val articlesList: MutableList<Article> = ArrayList()

    // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
    // is formatted, a JSONException exception object will be thrown.
    // Catch the exception so the app doesn't crash, and print the error message to the logs.
    try {
        // Create a JSONArray from the json response string.
        val baseJsonArray = JSONArray(response)

        for (i: Int in 0 until baseJsonArray.length()) {
            val baseJsonObject: JSONObject = baseJsonArray.getJSONObject(i)

            val jsonObjectTitle = baseJsonObject.getJSONObject("title")
            val jsonObjectExcerpt = baseJsonObject.getJSONObject("excerpt")

            var id = 0
            if (baseJsonObject.has("id")) {
                id = baseJsonObject.getInt("id")
            }

            var banner: String? = ""
            if (baseJsonObject.has("jetpack_featured_media_url")) {
                banner = baseJsonObject.getString("jetpack_featured_media_url")
            }

            var title: String? = ""
            if (jsonObjectTitle.has("rendered")) {
                title = jsonObjectTitle.getString("rendered")
            }

            var description: String? = ""
            if (jsonObjectExcerpt.has("rendered")) {
                description = jsonObjectExcerpt.getString("rendered")
            }

            val articles = Article(
                id,
                title!!,
                banner!!,
                description!!
            )
            articlesList.add(articles)
        }

    } catch (e: Exception) {
        // If an error is thrown when executing any of the above statements in the "try" block,
        // catch the exception here, so the app doesn't crash. Print a log message
        // with the message from the exception.
        Log.e("JsonUtils", "Problem parsing fetchArticleJsonData results")
    }

    return articlesList
}