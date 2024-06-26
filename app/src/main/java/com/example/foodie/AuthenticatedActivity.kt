package com.example.foodie

import androidx.fragment.app.Fragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class AuthenticatedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticated)
        replaceFragment(ProfileFragment())
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.search_button -> {

                    replaceFragment(SearchFragment())
                    true
                }
                R.id.fridge_button -> {

                    replaceFragment(TodayFragment())
                    true
                }
                R.id.profile_button -> {

                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.FrameLayoutMainPage, fragment)
        transaction.commit()
    }

}
