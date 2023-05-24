package com.netflex.mobile.presentation.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.netflex.mobile.R
import com.netflex.mobile.presentation.MainActivity
import okhttp3.*
import java.io.IOException


class RegistrationFragment : Fragment() {

    private lateinit var loginView : EditText
    private lateinit var passwordView : EditText
    private lateinit var confirmPasswordView : EditText
    private lateinit var registrationButton : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_registration, container, false)
        loginView = view.findViewById(R.id.input_login)
        passwordView = view.findViewById(R.id.input_password)
        confirmPasswordView = view.findViewById(R.id.input_confirm_password)
        registrationButton = view.findViewById(R.id.button_registration3)
        val context = context as MainActivity



        registrationButton.setOnClickListener {
            var login = ""
            var password = ""
            var confirmPassword = ""
            login = loginView.text.toString()
            password = passwordView.text.toString()
            confirmPassword = confirmPasswordView.text.toString()

            val client = OkHttpClient()
            val request = Request.Builder()
                .url("http://10.0.2.2:5224/api/auth/registration?email=$login&password=$password&confirmPassword=$confirmPassword")
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
                                findNavController().popBackStack()
                            }
                        } else{
                            Toast.makeText(context, "Wrong registration!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            })

        }

        return view
    }

}