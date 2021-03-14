package com.mvks.europeancountries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mvks.europeancountries.R
import com.mvks.europeancountries.adapter.LanguageAdapter
import com.mvks.europeancountries.model.LanguageFilter
import kotlinx.android.synthetic.main.fragment_bottom_sheet.*

class BottomSheetLanguageFragment(var languageList:List<LanguageFilter>, var callback:CountryListFragment) : BottomSheetDialogFragment() {

    var adapter: LanguageAdapter? = null
    var selectedIndex: Int = 0
    var selectedItem: LanguageFilter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        setListener()
    }

    private fun initUi() {
        languageList.get(selectedIndex).isSelected = false
        adapter = LanguageAdapter(languageList)
        listViewLanguage.adapter = adapter
    }

    private fun setListener() {
        listViewLanguage.setOnItemClickListener { parent, view, position, id ->
            languageList.forEach {
                it.isSelected = false
            }
            languageList.get(selectedIndex).isSelected = false
            languageList.get(position).isSelected = true
            selectedItem = languageList.get(position)
            selectedIndex = position
            adapter?.notifyDataSetChanged()
        }

        buttonCountryClick.setOnClickListener {
            selectedItem?.let { item ->
                callback.onClickedLanguage(switch(item.name))
                this.dismiss()
            }
        }
        //butona basıldığında dili seçecek
    }
    private fun switch(languagName: String) : String {
        when(languagName){
            "Türkçe" -> return "tr"
            "Almanca" -> return "de"
            "İspanyolca" -> return "es"
            "İngilizce" -> return "en"
            "Fransızca" -> return "fr"
        }
        return ""
    }

    interface BSheetLanguageListener {
        fun onClickedLanguage(languageText : String)
    }
}