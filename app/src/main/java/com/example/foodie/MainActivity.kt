package com.example.foodie

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home_button -> {

                    replaceFragment(Home())
                    true
                }

                R.id.search_button -> {

                    replaceFragment(Search())
                    true
                }
                R.id.fridge_button -> {

                    replaceFragment(Today())
                    true
                }
                R.id.profile_button -> {

                    replaceFragment(Profile())
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
