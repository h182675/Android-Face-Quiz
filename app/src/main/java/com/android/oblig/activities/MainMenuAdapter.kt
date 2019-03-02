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

    private lateinit var holder:ViewHolder


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var rowView = convertView

        if(convertView == null) {
            rowView = inflater.inflate(R.layout.main_menu_item, parent, false)
            holder = ViewHolder(
                rowView.findViewById(R.id.menu_row_item)
            )
            rowView.tag = holder
        } else{
            holder = convertView.tag as ViewHolder
        }
        holder.textView.text = getItem(position)


        return rowView!!
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

    private data class ViewHolder(
        val textView:TextView
    )
}