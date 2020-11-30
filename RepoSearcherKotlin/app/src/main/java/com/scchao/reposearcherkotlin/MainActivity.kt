package com.scchao.reposearcherkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.scchao.githubreposearcherkotlin.GitRepoSearcher

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val searcher = GitRepoSearcher(this)
        searcher.searchWith("android", "rakutentech") { success, list ->
            if (success) {
                Log.i("TEST", list.size.toString())
            }
        }
    }
}