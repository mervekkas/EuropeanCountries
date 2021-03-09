package com.mvks.europeancountries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegionalBloc(
    @SerializedName("acronym")
    @Expose
    val acronym:String?,
    @SerializedName("name")
    @Expose
    val name:String?,
    @SerializedName("otherAcronyms")
    @Expose
    val otherAcronyms:List<Object>?,
    @SerializedName("otherNames")
    @Expose
    val otherNames:List<Object>?
) {
}