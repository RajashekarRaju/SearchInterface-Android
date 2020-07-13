package com.developersbreach.searchinterface.network

import android.net.Uri
import android.util.Log
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

@Throws(IOException::class)
fun articleResponse(): String {
    val uriString: String = articleBuilder()
    val requestUrl: URL = createUrl(uriString)!!
    return getResponseFromHttpUrl(requestUrl)
}

/**
 * Builds Uri used to fetch articles data from the server.
 * @return The String to use to query the articles data from the server.
 *
 * https://developersbreach.com/wp-json/wp/v2/posts
 */
private fun articleBuilder(): String {
    val baseUri: Uri = Uri.parse("https://developersbreach.com")
    val uriBuilder: Uri.Builder = baseUri.buildUpon()
    uriBuilder.appendPath("wp-json/wp/v2")
    uriBuilder.appendPath("posts")
    return uriBuilder.build().toString()
}

/**
 * This method returns the entire result from the HTTP response.
 *
 * @param url The URL to fetch the HTTP response from.
 * @return The contents of the HTTP response, null if no response
 * @throws IOException Related to network and stream reading
 */
@Throws(IOException::class)
private fun getResponseFromHttpUrl(url: URL): String {
    val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
    return try {
        val `in`: InputStream = urlConnection.inputStream
        val scanner = Scanner(`in`)
        scanner.useDelimiter("\\A")
        val hasInput: Boolean = scanner.hasNext()
        var response: String? = null
        if (hasInput) {
            response = scanner.next()
        }
        scanner.close()
        response
    } finally {
        urlConnection.disconnect()
    }!!
}

/**
 * Returns new URL object from the given string URL.
 */
private fun createUrl(stringUrl: String): URL? {
    var url: URL? = null
    try {
        url = URL(stringUrl)
    } catch (e: MalformedURLException) {
        Log.e("CreateUrlException", e.toString())
    }
    return url
}