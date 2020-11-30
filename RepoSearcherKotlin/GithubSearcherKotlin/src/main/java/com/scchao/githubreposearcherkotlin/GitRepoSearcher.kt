package com.scchao.githubreposearcherkotlin

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.scchao.githubreposearcherkotlin.helper.DataParser
import com.scchao.githubreposearcherkotlin.models.RepoItem

open class GitRepoSearcher(
    context: Context
) {
    private val requestQueue = Volley.newRequestQueue(context)
    private val TAG = "GitRepoSearcher"

    open fun searchWith(
        platform: String,
        organization: String,
        callback: ((success: Boolean, items: List<RepoItem>) -> Unit)
    ) {
        val url = "https://api.github.com/search/repositories?q=${platform}+org:${organization}"
        requestQueue.add(makeVolleyRequest(url, callback))
    }

    private fun makeVolleyRequest(
        url: String,
        callback: ((success: Boolean, items: List<RepoItem>) -> Unit)
    ): StringRequest {
        return StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                val data = DataParser.parserResult(response)
                callback(true, data.items ?: listOf())
                printFetchedItems(data.items ?: listOf())
            }, Response.ErrorListener {
                callback(false, listOf())
            })
    }

    private fun printFetchedItems(items: List<RepoItem>) {
        for (item in items){
            Log.i(TAG, item.toString())
        }
    }
}