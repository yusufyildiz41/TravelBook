package com.yusufyildiz.kotlintravelbook.Adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.yusufyildiz.kotlintravelbook.Model.Place
import com.yusufyildiz.kotlintravelbook.R
import kotlinx.android.synthetic.main.custom_list_row.view.*

class CustomAdapter(val placeList: ArrayList<Place>, val context: Activity) :
    ArrayAdapter<Place>(
        context,
        R.layout.custom_list_row,
        placeList
    ) { //Recourses = Default = custom_list_row
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = context.layoutInflater
        val customView = layoutInflater.inflate(R.layout.custom_list_row, null, true)
        customView.ListRowTextview.text = placeList.get(position).address

        return customView
    }
}