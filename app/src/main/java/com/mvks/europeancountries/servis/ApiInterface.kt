package com.mvks.europeancountries.servis

import com.mvks.europeancountries.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface ApiInterface {

    //Api Url : https://restcountries.eu/rest/v2/region/europe
    @GET("rest/v2/region/europe")
    fun getCountry() : Single<List<Country>>
}