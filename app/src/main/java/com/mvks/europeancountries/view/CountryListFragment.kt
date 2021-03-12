package com.mvks.europeancountries.view

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvks.europeancountries.R
import com.mvks.europeancountries.adapter.CountryListRecyclerAdapter
import com.mvks.europeancountries.model.Country
import com.mvks.europeancountries.viewmodel.CountryDetailViewModel
import com.mvks.europeancountries.viewmodel.CountryListViewModel
import kotlinx.android.synthetic.main.fragment_country_list.*
import kotlinx.android.synthetic.main.tool_bar_layout.*

class CountryListFragment : Fragment(), CountryListRecyclerAdapter.CountryAdapterListener {
    private lateinit var viewModel: CountryListViewModel
    private val recyclerCountryAdapter = CountryListRecyclerAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_country_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryListViewModel::class.java)
        viewModel.refreshData()

        setToolBar()
        rv_country_list.layoutManager = LinearLayoutManager(context)
        rv_country_list.adapter = recyclerCountryAdapter
        swipeRefreshLayout.setOnRefreshListener {
            progress_country_list.visibility = View.VISIBLE
            tv_country_list_error_message.visibility = View.GONE
            rv_country_list.visibility = View.GONE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()
    }

    private fun setToolBar() {
        tool_bar_title.setText(R.string.list_title)
        img_tool_bar_back.visibility = View.GONE
        getQueryTextListener()
    }

    private fun getQueryTextListener() {
        tool_bar_search_view.setOnQueryTextListener(object  : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.dataSearchResponse(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    fun observeLiveData() {
        viewModel.countryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                rv_country_list.visibility = View.VISIBLE
                recyclerCountryAdapter.countryListRefresh(it)
            }
        })
        viewModel.countryErroMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it)
                    errorVisible()
                else
                    listVisible()
            }
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it)
                    loadingVisible()
            }
        })
    }

    private fun errorVisible() {
        rv_country_list.visibility = View.GONE
        tv_country_list_error_message.visibility = View.VISIBLE
        progress_country_list.visibility = View.GONE
    }

    private fun loadingVisible() {
        rv_country_list.visibility = View.GONE
        progress_country_list.visibility = View.VISIBLE
    }

    private fun listVisible() {
        rv_country_list.visibility = View.VISIBLE
        progress_country_list.visibility = View.GONE
        tv_country_list_error_message.visibility = View.GONE
    }

    override fun onClicked(model: Country) {
        val fragment = CountryDetailFragment()
        fragment.countryLiveData.value = model
        fragmentManager?.beginTransaction()?.replace(R.id.container_main, fragment)
            ?.addToBackStack("Tag")?.commit()
    }

}




