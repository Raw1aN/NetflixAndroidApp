package com.netflex.mobile.presentation.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.netflex.mobile.R
import com.netflex.mobile.presentation.ListAdapter
import com.netflex.mobile.presentation.MainActivity
import com.netflex.mobile.presentation.Movies
import okhttp3.*
import org.json.JSONArray
import org.json.JSONTokener
import java.io.IOException

class LoginFragment : Fragment() {

    private lateinit var loginView : EditText
    private lateinit var passwordView : EditText
    private lateinit var loginButton : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        loginView = view.findViewById(R.id.input_login1)
        passwordView = view.findViewById(R.id.input_password1)
        loginButton = view.findViewById(R.id.button_login1)
        val context = context as MainActivity

        loginButton.setOnClickListener{
            var login = ""
            var password = ""
            login = loginView.text.toString()
            password = passwordView.text.toString()
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://10.0.2.2:5224/api/auth/login?email=$login&password=$password")
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
                        if ( response.code == 200 ){
                            context.runOnUiThread {
                                findNavController().navigate(R.id.contentListFragment)
                            }

                        }
                    }
                }
            })

        }


        val toRegistrationButton = view.findViewById<Button>(R.id.button_register)
        toRegistrationButton.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment2_to_registrationFragment2)
        }

        return view
    }

//    override fun onResume() {
//        super.onResume()
//        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
//    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setHasOptionsMenu(true)
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.top_app_bar, menu)
//    }

}