package com.example.foodie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationFragment : Fragment() {

    private lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем базу данных
        database = (requireActivity().application as App).database

        val usernameEditText = view.findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val registerButton = view.findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Проверяем, что поля не пустые
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Имя пользователя и пароль не должны быть пустыми.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Регистрируем пользователя
            registerUser(username, password)
        }
    }

    private fun registerUser(username: String, password: String) {
        // Запускаем корутину для работы с базой данных
        lifecycleScope.launch {
            // Сначала проверяем, не существует ли уже пользователь с таким именем
            val userExists = withContext(Dispatchers.IO) {
                database.userDao().findByUsername(username) != null
            }

            if (!userExists) {
                // Если пользователя нет, то вставляем нового
                val userId = withContext(Dispatchers.IO) {
                    val newUser = UserEntity(username = username, password = password) // 'id' и 'favouriteRecipeId' инициализируются автоматически
                    database.userDao().insert(newUser)
                }

                // Переход к MainActivity с ID пользователя
                val intent = Intent(activity, MainActivity::class.java).apply {
                }
                startActivity(intent)
            } else {
                // Если пользователь уже существует, показываем сообщение
                Toast.makeText(requireContext(), "Пользователь с таким именем уже существует.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
