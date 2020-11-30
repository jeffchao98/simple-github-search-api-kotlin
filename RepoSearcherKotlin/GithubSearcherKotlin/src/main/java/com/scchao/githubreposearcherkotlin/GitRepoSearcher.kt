package com.scchao.githubreposearcherkotlin

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.scchao.githubreposearcherkotlin.helper.DataParser
import com.scchao.githubreposearcherkotlin.models.RepoItem
import com.scchao.githubreposearcherkotlin.models.RepoSearchResult

open class GitRepoSearcher(
    context: Context
) {
    val requestQueue = Volley.newRequestQueue(context)

    open fun searchWith(
        platform: String,
        organization: String,
        callback: ((success: Boolean, items: List<RepoItem>) -> Unit)
    ) {
        val url = "https://api.github.com/search/repositories?q=${platform}+org:${organization}"
        requestQueue.add(makeVolleyRequest(url, callback))
    }

    fun makeVolleyRequest(
        url: String,
        callback: ((success: Boolean, items: List<RepoItem>) -> Unit)
    ): StringRequest {
        return StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                val data = DataParser.parserResult(response)
                callback(true, data.items ?: listOf())
//                Log.i("KotlinPlay", response)
            }, Response.ErrorListener {
                callback(false, listOf())
            })
    }
}