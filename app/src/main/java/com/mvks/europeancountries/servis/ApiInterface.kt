package com.mvks.europeancountries.servis

import com.mvks.europeancountries.model.Country
import io.reactivex.Single
import retrofit2.http.*

interface ApiInterface {

    //Api Url : https://restcountries.eu/rest/v2/region/europe
    @GET("rest/v2/region/europe")
    fun getCountry() : Single<List<Country>>

    ///rest/v2/callingcode/372
    @GET("rest/v2/name/{string}")
    fun getSearchCountry(@Path("string") searchString : String) : Single<List<Country>>

    @GET("rest/v2/lang/{string}")
    fun getLanguageFilter(@Path("string") filterLang : String) : Single<List<Country>>
}