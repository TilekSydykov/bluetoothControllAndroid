package com.example.bluetoothrfidcard.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.bluetoothrfidcard.R


class LessonsAdapter(context: Context, var foodsList: ArrayList<LiveData<String>>) : BaseAdapter() {
    var context: Context? = context

    override fun getCount(): Int {
        return foodsList.size
    }

    override fun getItem(position: Int): Any {
        return foodsList[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val lesson = this.foodsList[position]

        val inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val clubView = inflator.inflate(R.layout.item_remote, null)

        return clubView
    }
}