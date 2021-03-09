package com.mvks.europeancountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mvks.europeancountries.R
import com.mvks.europeancountries.model.Country
import com.mvks.europeancountries.view.CountryListFragmentDirections
import kotlinx.android.synthetic.main.item_country_list.view.*

class CountryListRecyclerAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListRecyclerAdapter.CountryViewHolder>() {
    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country_list, parent, false)
        return CountryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.itemView.tv_country_item_name.text = countryList.get(position).name
        holder.itemView.tv_country_item_subregion.text = countryList.get(position).subregion
        holder.itemView.tv_country_item_capital.text = countryList.get(position).capital
        holder.itemView.tv_country_item_population.text = countryList.get(position).population.toString()
        clickItem(holder.itemView, position)
    }

    fun clickItem(itemView: View, position: Int) {
        itemView.setOnClickListener {
            val action =
                CountryListFragmentDirections.actionCountryListFragmentToCountryDetailFragment(0)
            Navigation.findNavController(it).navigate(action)
        }
    }
    fun countryListRefresh(newCountryList : List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}