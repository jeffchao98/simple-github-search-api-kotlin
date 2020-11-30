package com.scchao.githubreposearcherkotlin.models

import com.google.gson.annotations.SerializedName

open class RepoItem {
    var name : String = ""

    @SerializedName("full_name") var fullName : String = ""
    var description : String = ""
    var language : String = ""
    var private : String = ""
}