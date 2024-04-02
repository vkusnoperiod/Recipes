package com.example.foodie

import androidx.fragment.app.Fragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_button -> {
                    // Замените HomeFragment() на фрагмент для пункта Home
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.search_button -> {
                    // Замените SettingsFragment() на фрагмент для пункта Settings
                    replaceFragment(SearchFragment())
                    true
                }
                R.id.fridge_button -> {
                    // Замените FridgeFragment() на фрагмент для пункта Fridge
                    replaceFragment(FridgeFragment())
                    true
                }
                R.id.profile_button -> {
                    // Замените ProfileFragment() на фрагмент для пункта Profile
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Установить начальный фрагмент, например Home
        bottomNav.selectedItemId = R.id.home_button
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.FrameLayoutMainPage, fragment)
        transaction.commit()
    }
}