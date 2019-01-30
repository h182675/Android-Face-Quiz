package com.android.oblig.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.android.oblig.R

class MainMenuAdapter(private val context: Context, private val dataSource:Array<String>): BaseAdapter() {

    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.main_menu_item,parent,false)

        val text:String = getItem(position)

        val textView = rowView.findViewById<TextView>(R.id.menu_row_item)

        textView.text = getItem(position)

        return rowView
    }

    override fun getItem(position: Int): String {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }
}