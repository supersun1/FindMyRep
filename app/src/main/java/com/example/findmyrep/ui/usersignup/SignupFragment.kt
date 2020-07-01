package com.example.findmyrep.ui.userlogin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.findmyrep.R
import com.example.findmyrep.ui.usersignup.SignupViewModel


class SignupFragment : Fragment() {

    private lateinit var signupViewModel: SignupViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signupViewModel =
            ViewModelProviders.of(this).get(SignupViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user_signup, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val signupButton = view.findViewById<Button>(R.id.id_user_login__signup)
//        signupButton.setOnClickListener {
//            // new fragment to sign up
//            println("regular sign up")
//
//        }

    }

}