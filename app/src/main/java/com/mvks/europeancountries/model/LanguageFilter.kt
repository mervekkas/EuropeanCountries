package com.mvks.europeancountries.model

import com.mvks.europeancountries.R

class LanguageFilter(var name:String, var flag: Int, var isSelected:Boolean = false) {

    object SupplierLanguage{

        val language = arrayListOf(
            LanguageFilter("Türkçe", R.drawable.ic_ulke_tr),
            LanguageFilter("Almanca", R.drawable.ic_ulke_de),
            LanguageFilter("İngilizce", R.drawable.ic_ulke_us),
            LanguageFilter("İspanyolca", R.drawable.ic_ulke_es),
            LanguageFilter("Fransızca", R.drawable.ic_ulke_fr)

        )
    }
}