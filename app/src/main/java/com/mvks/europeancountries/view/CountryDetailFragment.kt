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
import kotlinx.android.synthetic.main.tool_bar_layout.*

class CountryDetailFragment : Fragment() {
    val countryLiveData = MutableLiveData<Country>()
    private lateinit var viewModel: CountryDetailViewModel
    private lateinit var countryName: String

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

    private fun setToolBar() {
        tool_bar_title.text = countryName
        tool_bar_search_view.visibility = View.GONE
        img_toolBar_filter.visibility = View.GONE
        img_toolBar_sort.visibility = View.GONE
        line_tool_bar.visibility = View.GONE
        img_tool_bar_back.visibility = View.VISIBLE
        backClick()
    }

    private fun backClick() {
        img_tool_bar_back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    fun observeLiveData() {
        countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                countryName = it.name.toString()
                tv_country_detail_name.text = "  " + it.name
                tv_country_detail_area.text = "  " + it.area.toString()
                tv_country_detail_borders.text = getBorders(it.borders)
                tv_country_detail_language.text = "  " + getLanguages(it.languages)
                tv_country_detail_capital.text = "  " + it.capital
                tv_country_detail_subregion.text = "  " + it.subregion
                tv_country_detail_population.text = "  " + it.population.toString()
                it.flag?.let { it1 -> img_country_detail_flag.imageDownload(it1) }
                setToolBar()
            }
        })
    }

    private fun getLanguages(languages: List<Language>?): String? {
        var languageName = ""
        languages?.let {
            it.forEachIndexed { index, language ->
                if (it.size - index > 1)
                    languageName += language.name.toString() + ", "
                else
                    languageName += language.name.toString()

            }
        }
        return languageName
    }

    private fun getBorders(borders: List<Object>?): String? {
        var bordersName = ""
        borders?.let {
            it.forEach {
                bordersName += "-" + it.toString()
            }
        }
        return bordersName
    }

}