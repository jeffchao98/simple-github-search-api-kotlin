package com.scchao.githubreposearcherkotlin.models

import com.google.gson.annotations.SerializedName

open class RepoItem {
    var name: String = ""

    @SerializedName("full_name")
    var fullName: String? = ""
    var description: String? = ""
    var language: String? = ""
    var private: Boolean? = false

    override fun toString(): String {
        return "=====\n" +
                "name: ${name}\n" +
                "full_name: ${fullName}\n" +
                "description: ${description}\n" +
                "language: ${language}\n" +
                "private: ${private?.toString()}\n" +
                "=====\n";
    }
}