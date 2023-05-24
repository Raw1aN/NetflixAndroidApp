package com.netflex.mobile.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.netflex.mobile.R
import com.netflex.mobile.presentation.ListAdapter
import com.netflex.mobile.presentation.MainActivity
import com.netflex.mobile.presentation.Movies
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException


class ContentViewFragment : Fragment() {

    private lateinit var imageView : ImageView
    private lateinit var descriptionView : TextView
    private lateinit var genresView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View =  inflater.inflate(R.layout.fragment_content_view, container, false)
        imageView = view.findViewById<ImageView>(R.id.imageView2)
        descriptionView = view.findViewById<TextView>(R.id.descriptionFilm)
        genresView = view.findViewById<TextView>(R.id.genresView)


        var x = 0
        val context = context as MainActivity
        var jsonString = String()
        var jsonObject = JSONObject()
        var jsonFilm = JSONObject()
        var poster = ""
        var description = String()
        var genres = String()
        var genresArray = JSONArray()
        println(arguments?.getString("amount"))
        val id = arguments?.getString("amount")

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://10.0.2.2:5224/content/getMovieById?id=" + id)
            .build()

        val call = client.newCall(request)

//        try {
//            client.newCall(request).execute().use { response ->
//                if (!response.isSuccessful) {
//                    throw IOException("Запрос к серверу не был успешен:" +
//                            " ${response.code} ${response.message}")
//                }
//                jsonString = response.body!!.string()
//                jsonObject = JSONTokener(jsonString).nextValue() as JSONObject
//                jsonFilm = JSONTokener(jsonObject.getString("film")).nextValue() as JSONObject
//
//                println(jsonFilm.getString("poster"))
//                poster = jsonFilm.getString("poster")
//                Glide.with(context).load(poster).into(imageView)
//            }
//        } catch (e: IOException) {
//            println("Ошибка подключения: $e");
//        }

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Error occurred during POST request: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                jsonString = response.body!!.string()
                jsonObject = JSONTokener(jsonString).nextValue() as JSONObject
                jsonFilm = JSONTokener(jsonObject.getString("film")).nextValue() as JSONObject

                println(jsonFilm.getString("poster"))
                poster = jsonFilm.getString("poster")
                description = jsonFilm.getString("description")
                genres = jsonObject.getString("genres")
                genresArray = JSONTokener(genres).nextValue() as JSONArray
                var genresResultString = ""

                for (i in 0 until genresArray.length()) {
                    val name = genresArray.getJSONObject(i).getString("genreName")
                    genresResultString += name + " "
                }

                println(genresResultString)


                context.runOnUiThread {
                    Glide.with(context).load(poster).into(imageView)
                    descriptionView.text = description
                    genresView.text = genresResultString
                    println("123")
                }

//                val jsonArray = jsonObject.getJSONArray("genres")
//                println(jsonFilm.getString("description"))
                x=1
            }
        })




//        Glide.with(context).load(itemList.poster).into(imageView)


        return view
    }



}