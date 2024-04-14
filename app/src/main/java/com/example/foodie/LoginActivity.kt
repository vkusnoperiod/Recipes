package com.example.foodie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val inputUsername: EditText = findViewById(R.id.login_input_username)
        val inputPassword: EditText = findViewById(R.id.login_input_password)
        val logInTextViewRegistration: TextView =
            findViewById(R.id.log_in_textView_create_a_new_profile)
        val buttonLoginFirst: Button = findViewById(R.id.login_button_1)

        logInTextViewRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        buttonLoginFirst.setOnClickListener {
            var isAllInformationValid = true
            // Checking if the username exists
            if (!App.database.userDao().userExists(inputUsername.text.toString())) {
                val toast: Toast = Toast.makeText(
                    this,
                    "That username does not exist. Try another one or create a new profile",
                    Toast.LENGTH_LONG
                )
                toast.show()
                inputPassword.text.clear()
                inputUsername.text.clear()
                isAllInformationValid = false
            }
            val passwordFromDatabase: String =
                App.database.userDao().getPasswordByUsername(inputUsername.text.toString())
            // Checking if the given password matches with a password from database
            if (passwordFromDatabase != inputPassword.text.toString()) {
                val toast: Toast = Toast.makeText(
                    this,
                    "The password is incorrect. Try again",
                    Toast.LENGTH_LONG
                )
                toast.show()
                inputPassword.text.clear()
                isAllInformationValid = false
            }
            if(isAllInformationValid) {
                val toast: Toast = Toast.makeText(
                    this,
                    "Successful!",
                    Toast.LENGTH_LONG
                )
                toast.show()
                inputPassword.text.clear()
                inputUsername.text.clear()
                val intent = Intent(this, AuthenticatedActivity::class.java)
                startActivity(intent)
            }
        }

    }

}
