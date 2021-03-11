package com.mvks.europeancountries.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class Language(
    @SerializedName("iso639_1")
    @Expose
    val iso639_1:String?,
    @SerializedName("iso639_2")
    @Expose
    val iso639_2:String?,
    @SerializedName("name")
    @Expose
    val name:String?,
    @SerializedName("nativeName")
    @Expose
    val nativeName:String?
) {
}