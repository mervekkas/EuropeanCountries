package com.mvks.europeancountries.servis

import com.mvks.europeancountries.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryApiService {
    //Api Url : https://restcountries.eu/rest/v2/region/europe

    private val BASE_URL = "https://restcountries.eu/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ApiInterface::class.java)

    fun getData() : Single<List<Country>> {
        return api.getCountry()
    }
    fun getSearchCountry(searchString: String) : Single<List<Country>> {
        return api.getSearchCountry(searchString)
    }
}