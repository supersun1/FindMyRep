package com.example.findmyrep.ui.userlogin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.findmyrep.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       loginViewModel =
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user_login, container, false)

        mAuth = FirebaseAuth.getInstance()

        val user : FirebaseUser? = mAuth.currentUser
        if (user != null) {
            println("user is not null")
            findNavController().navigate(R.id.navigation_user_profile)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signupButton = view.findViewById<Button>(R.id.id_user_login__signup)

        signupButton.setOnClickListener {
            // new fragment to sign up
            println("regular sign up")
            findNavController().navigate(R.id.navigation_signup)
        }

        val signinButton = view.findViewById<Button>(R.id.id_user_login__signin)

        signinButton.setOnClickListener {
            println("user tries to signin")
            val email = view.findViewById<EditText>(R.id.id_user_login__email_edit_text)
            val password = view.findViewById<EditText>(R.id.id_user_login__password_edit_text)
            userSignIn(email.text.toString(), password.text.toString())
        }
    }

    fun userSignIn(email :String, password :String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {task ->
            if(task.isSuccessful) {
                println("login success")
                findNavController().navigate(R.id.navigation_user_profile)
            }
        }
    }
}


