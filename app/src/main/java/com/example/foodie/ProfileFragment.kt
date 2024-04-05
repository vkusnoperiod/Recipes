package com.example.foodie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodie.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<View>(R.id.edit_profile_button).setOnClickListener {

        }


        view.findViewById<View>(R.id.favorites_button).setOnClickListener {

        }


        view.findViewById<View>(R.id.logout_button).setOnClickListener {

        }
    }
}
