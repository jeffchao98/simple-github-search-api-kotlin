package com.scchao.githubreposearcherkotlin.helper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.scchao.githubreposearcherkotlin.models.RepoSearchResult

object DataParser {
    fun parserResult(rawData: String): RepoSearchResult {
        return try {
            Gson().fromJson<RepoSearchResult>(
                rawData,
                RepoSearchResult::class.java
            ) as RepoSearchResult
        } catch (err: Exception) {
            RepoSearchResult()
        }
    }
}