package com.mvks.europeancountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.mvks.europeancountries.R
import com.mvks.europeancountries.model.LanguageFilter
import org.intellij.lang.annotations.Language

class LanguageAdapter(val languageList: List<LanguageFilter>) : BaseAdapter() {
    var viewHolder:ViewHolder? = null

    class ViewHolder(row:View?) {
        var tvLanguageName: TextView
        var imgFlag: ImageView
        var imgCheck: ImageView

        init {
            this.tvLanguageName = row?.findViewById(R.id.tvLanguageName) as TextView
            this.imgFlag = row?.findViewById(R.id.imgLanguageFlag) as ImageView
            this.imgCheck = row?.findViewById(R.id.imgCheck)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?

        if (convertView == null){
            var layout = LayoutInflater.from(parent?.context)
            view = layout.inflate(R.layout.language_list, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var language = getItem(position) as LanguageFilter
        viewHolder?.tvLanguageName?.text = language.name
        viewHolder?.imgFlag?.setImageResource(language.flag)
        if (language.isSelected)
            viewHolder!!.imgCheck.setImageResource(R.drawable.ic_switch_on)
        else
            viewHolder!!.imgCheck.setImageResource(R.drawable.ic_switch_off)

        return view as View
    }

    override fun getItem(position: Int): Any {
        return languageList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return languageList.size
    }
}