package com.mvks.europeancountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvks.europeancountries.model.Country
import com.mvks.europeancountries.servis.CountryApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountryListViewModel : ViewModel() {
    val countryList = MutableLiveData<List<Country>>()
    val countryErroMessage = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private val countryApiService = CountryApiService()
    private val disposable = CompositeDisposable()

    fun refreshData() {
        dataResponse()
    }

    private fun dataResponse() {
        loading.value = true
        disposable.add(
            countryApiService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>() {
                    override fun onSuccess(t: List<Country>) {
                        onSuccesValue(t)
                    }

                    override fun onError(e: Throwable) {
                        onErrorValue(e)
                    }

                })
        )
    }

    private fun onErrorValue(e: Throwable) {
        countryErroMessage.value = false
        loading.value = true
        e.printStackTrace()
    }

    private fun onSuccesValue(countryL: List<Country>) {
        countryList.value = countryL
        countryErroMessage.value = false
        loading.value = false
    }

}