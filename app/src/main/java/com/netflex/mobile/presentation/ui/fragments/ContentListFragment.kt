package com.netflex.mobile.presentation.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import com.bumptech.glide.Glide
import com.netflex.mobile.R
import com.netflex.mobile.presentation.ListAdapter
import com.netflex.mobile.presentation.MainActivity
import com.netflex.mobile.presentation.Movies
import okhttp3.*
import org.json.JSONArray
import org.json.JSONTokener
import java.io.IOException


class ContentListFragment : Fragment() {

    private lateinit var moviesArray : ArrayList<Movies>
    private lateinit var moviesListView : ListView
    private lateinit var listAdapter : ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =  inflater.inflate(R.layout.fragment_content_list, container, false)
        moviesListView = view.findViewById(R.id.list_view)
        moviesListView.isClickable = true


        moviesArray = ArrayList<Movies>()

        val context = context as MainActivity

        var x = 0

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("http://10.0.2.2:5224/content/getMovies")
            .build()

        var jsonString = String()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        throw IOException("Запрос к серверу не был успешен:" +
                                " ${response.code} ${response.message}")
                    }
                    // пример получения всех заголовков ответа
                    for ((name, value) in response.headers) {
                        //println("$name: $value")
                    }
                    // вывод тела ответа
                    jsonString = response.body!!.string()
                    val jsonArray = JSONTokener(jsonString).nextValue() as JSONArray
                    for (i in 0 until jsonArray.length()) {
                        val id = jsonArray.getJSONObject(i).getString("id")
                        val title = jsonArray.getJSONObject(i).getString("title")
                        val poster = jsonArray.getJSONObject(i).getString("poster")
                        val duration = jsonArray.getJSONObject(i).getString("duration")
                        val ageRating = jsonArray.getJSONObject(i).getString("ageRating")
                        val userRating = jsonArray.getJSONObject(i).getString("userRating")
                        val description = jsonArray.getJSONObject(i).getString("description")
                        val videoLink = jsonArray.getJSONObject(i).getString("videoLink")
                        moviesArray.add(Movies(id,title,poster,duration.toDouble(), ageRating.toDouble(),
                            userRating.toDouble(),description,videoLink))
                        context.runOnUiThread {
                            listAdapter = ListAdapter(context,moviesArray)
                            moviesListView.adapter = listAdapter
                        }
                    }
                }
            }
        })


        moviesListView.setOnItemClickListener { parent, view, position, id ->

            val movieId = moviesArray[position].id
            val bundle = bundleOf("amount" to movieId)
            findNavController().navigate(R.id.contentViewFragment,bundle)

        }

        return view
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController()
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

}

