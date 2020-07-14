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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private var mAuth: FirebaseAuth? = null

    companion object {
        var RC_SIGN_IN = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       loginViewModel =
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user_login, container, false)

        // initialize firebase authentication.
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth!!.currentUser

        // if user logs in, update the UI

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

//        val googleSignUp = view.findViewById<Button>(R.id.id_user_login__google_sign_up)
//        googleSignUp.setOnClickListener {
//            signupWithGoogle(view)
////            println("google sign in")
//        }
    }





    fun signupWithGoogle(view: View) {
        var gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(view.context, gso)
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            val task =
//                GoogleSignIn.getSignedInAccountFromIntent(data)
//            handleSignInResult(task)
//        }
//    }
//    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
//        println("handle sign in result")
//    }
    fun userSignIn(email :String, password :String) {
        println("email: $email")
        println("pasword:$password")

    }

}