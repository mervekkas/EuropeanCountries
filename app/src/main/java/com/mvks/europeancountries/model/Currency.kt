package com.mvks.europeancountries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("code")
    @Expose
    val code:String?,
    @SerializedName("name")
    @Expose
    val name:String?,
    @SerializedName("symbol")
    @Expose
    val symbol:String?
) {
}