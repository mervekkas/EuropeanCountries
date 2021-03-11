package com.mvks.europeancountries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    @Expose
    val name:String?,
    @SerializedName("capital")
    @Expose
    val capital:String?,
    @SerializedName("subregion")
    @Expose
    val subregion:String?,
    @SerializedName("population")
    @Expose
    val population:Int?,
    @SerializedName("area")
    @Expose
    val area:Double?,
    @SerializedName("borders")
    @Expose
    val borders:List<Object>?,
    @SerializedName("nativeName")
    @Expose
    val nativeName:String?,
    @SerializedName("languages")
    @Expose
    val languages:List<Language>?,
    @SerializedName("flag")
    @Expose
    val flag:String?
) {
}