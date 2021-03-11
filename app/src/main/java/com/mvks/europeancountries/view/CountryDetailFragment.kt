package com.mvks.europeancountries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mvks.europeancountries.R
import com.mvks.europeancountries.model.Country
import com.mvks.europeancountries.model.Language
import com.mvks.europeancountries.util.imageDownload
import com.mvks.europeancountries.viewmodel.CountryDetailViewModel
import kotlinx.android.synthetic.main.fragment_country_detail.*

class CountryDetailFragment : Fragment() {
    val countryLiveData = MutableLiveData<Country>()
    private lateinit var viewModel: CountryDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_country_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CountryDetailViewModel::class.java)
        observeLiveData()
    }

    fun observeLiveData() {
        countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                tv_country_detail_name.text = it.name
                tv_country_detail_area.text = it.area.toString()
                tv_country_detail_borders.text = getBorders(it.borders)
                tv_country_detail_language.text = getLanguages(it.languages)
                tv_country_detail_capital.text = it.capital
                tv_country_detail_subregion.text = it.subregion
                tv_country_detail_population.text = it.population.toString()
                it.flag?.let { it1 -> img_country_detail_flag.imageDownload(it1) }
            }
        })
    }

    private fun getLanguages(languages: List<Language>?): String? {
        var languageName = ""
        languages?.let {
            it.forEach {
                languageName += "-" + it.name.toString()
            }
        }
        return languageName
    }

    private fun getBorders(borders: List<Object>?): String? {
        var bordersName = ""
        borders?.let {
            it.forEach {
                bordersName +=  "-" + it.toString()
            }
        }
        return bordersName
    }

}