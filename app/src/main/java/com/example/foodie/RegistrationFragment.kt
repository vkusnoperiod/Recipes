package com.example.foodie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.foodie.R
import com.example.foodie.database.UsersDatabase
import com.example.foodie.models.User

class RegistrationFragment : Fragment() {


    private lateinit var usersDatabase: UsersDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val usernameEditText = view.findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = view.findViewById<EditText>(R.id.confirmPasswordEditText)
        val registerButton = view.findViewById<Button>(R.id.registerButton)


        val nameEditText = view.findViewById<EditText>(R.id.nameEditText)
        val favoriteFoodEditText = view.findViewById<EditText>(R.id.favoriteFoodEditText)
        val allergiesEditText = view.findViewById<EditText>(R.id.allergiesEditText)
        val sexEditText = view.findViewById<EditText>(R.id.sexEditText)
        val nextButton = view.findViewById<Button>(R.id.nextButton)

        registerButton.setOnClickListener {

        }

        nextButton.setOnClickListener {

            val user = User(
                username = usernameEditText.text.toString(),
                password = passwordEditText.text.toString(),
                name = nameEditText.text.toString(),
                sex = sexEditText.text.toString()

            )

            usersDatabase.userDao().insert(user)

        }
    }
}
