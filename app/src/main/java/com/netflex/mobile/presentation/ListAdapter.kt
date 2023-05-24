package com.netflex.mobile.presentation

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.netflex.mobile.R

class ListAdapter(var context: Context,var arrayList: ArrayList<Movies>): BaseAdapter() {

    override fun getCount(): Int {
        return arrayList.count()
    }

    override fun getItem(position: Int): Any {
        return arrayList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{

            val view: View = View.inflate(context,R.layout.list_view_content,null)

            val imageView : ImageView = view.findViewById(R.id.movie_image)
            val title : TextView = view.findViewById(R.id.movie_title)
            val ageRating : TextView = view.findViewById(R.id.age_rating)
            val userRating : TextView = view.findViewById(R.id.user_rating)



            var itemList: Movies = arrayList.get(position)
            Glide.with(context).load(itemList.poster).into(imageView)
            //imageView.setImageURI(itemList.poster.toUri())
            title.text = itemList.title
            ageRating.text = itemList.ageRating.toString()
            userRating.text = itemList.userRating.toString()

            return view
        }


}