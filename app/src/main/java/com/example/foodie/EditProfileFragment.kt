package com.example.foodie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodie.R
import com.example.foodie.database.UsersDatabase
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class EditProfileFragment : Fragment() {


    private var userId: Int = getCurrentUserId()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val usernameEditText = view.findViewById<TextInputEditText>(R.id.usernameEditText)
        val passwordEditText = view.findViewById<TextInputEditText>(R.id.passwordEditText)
        val confirmPasswordEditText = view.findViewById<TextInputEditText>(R.id.confirmPasswordEditText)
        val nameEditText = view.findViewById<TextInputEditText>(R.id.nameEditText)
        val allergiesEditText = view.findViewById<TextInputEditText>(R.id.allergiesEditText)
        val sexEditText = view.findViewById<TextInputEditText>(R.id.sexEditText)
        val saveButton = view.findViewById<MaterialButton>(R.id.saveProfileButton)



        saveButton.setOnClickListener {



            val user = User(
                id = userId,
                username = usernameEditText.text.toString(),
                password = passwordEditText.text.toString(),
                name = nameEditText.text.toString(),
                allergies = allergiesEditText.text.toString(),
                sex = sexEditText.text.toString()
            )


            UsersDatabase.getInstance(requireContext()).userDao().update(user)


        }
    }

    private fun getCurrentUserId(): Int {
        // Placeholder function to retrieve the current user's ID.
        return -1
    }
}
