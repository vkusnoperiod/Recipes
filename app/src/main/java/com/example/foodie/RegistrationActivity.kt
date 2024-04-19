package com.example.foodie

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val inputUsername: EditText = findViewById(R.id.registration_input_username)
        val inputPassword: EditText = findViewById(R.id.registration_input_password)
        val inputPersonName: EditText = findViewById(R.id.registration_input_person_name)
        val RegistrationButtonFirst: Button = findViewById(R.id.registration_button_1)
        var userSex: String
        val checkBoxMale: CheckBox = findViewById(R.id.checkBox_male)
        val checkBoxFemale: CheckBox = findViewById(R.id.checkBox_female)
        var userId: Int
        val textViewReturnToLogIn: TextView =
            findViewById(R.id.registration_textView_return_to_log_in)
        checkBoxFemale.setOnClickListener {

            if (checkBoxMale.isActivated && checkBoxFemale.isActivated) {
                val toast = Toast.makeText(
                    this,
                    "You cant't be male and female at the same time",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
        }
        checkBoxMale.setOnClickListener {
            if (checkBoxMale.isActivated && checkBoxFemale.isActivated) {
                val toast = Toast.makeText(
                    this,
                    "You can't be male and female at the same time",
                    Toast.LENGTH_LONG
                )
                toast.show()
            }
            checkBoxMale.isActivated.not()
            checkBoxFemale.isActivated.not()
        }

        textViewReturnToLogIn.setOnClickListener {
            val intent: Intent = Intent(
                this,
                LoginActivity::class.java)
            startActivity(intent)
        }
        RegistrationButtonFirst.setOnClickListener {
            var isAllInformationValid: Boolean = true
            if (checkBoxMale.isActivated) {
                userSex = "male"
            } else {
                userSex = "female"
            }
            userId = App.database.userDao().getLastUserId() + 1

            // Username existence check
            if (App.database.userDao().userExists(inputUsername.text.toString())) {
                val toast = Toast.makeText(
                    this,
                    "That username is already taken, please type a different one or log in",
                    Toast.LENGTH_LONG
                )
                inputUsername.text.clear()
                toast.show()
                isAllInformationValid = false
            }
            // Null data check
            if (inputPassword.text == null || inputUsername.text == null || inputPersonName.text == null) {
                inputUsername.text.clear()
                inputPassword.text.clear()
                inputPersonName.text.clear()
                val toast =
                    Toast.makeText(
                        this,
                        "Incorrect data, please try again",
                        Toast.LENGTH_LONG)
                toast.show()
                isAllInformationValid = false
            }
            // Password length check
            if (inputPassword.text.toString().length < 8) {
                val toast = Toast.makeText(
                    this,
                    "Password has to contain at least 8 characters, please try again",
                    Toast.LENGTH_LONG
                )
                toast.show()
                isAllInformationValid = false
            }
            if (isAllInformationValid) {
                // Success
                App.database.userDao().insert(
                    UserEntity(
                        userId,
                        inputUsername.text.toString(),
                        inputPassword.text.toString(),
                        inputPersonName.text.toString(),
                        userSex
                    )
                )
                val toastUserAdded = Toast.makeText(
                    this,
                    "User Added!",
                    Toast.LENGTH_LONG)
                toastUserAdded.show()
                Session.currentUsername = inputUsername.text.toString()
                val intent: Intent = Intent(this, AuthenticatedActivity::class.java)
                startActivity(intent)
            }
        }
    }

}
