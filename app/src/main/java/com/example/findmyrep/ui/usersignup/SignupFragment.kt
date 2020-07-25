package com.example.findmyrep.ui.usersignup

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.findmyrep.Objects.User
import com.example.findmyrep.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_user_login.*
import kotlinx.android.synthetic.main.fragment_user_signup.*
import java.util.*


class SignupFragment : Fragment() {

    private lateinit var signupViewModel: SignupViewModel
    private var mAuth: FirebaseAuth? = null

    private var firstName: EditText? = null
    private var lastName: EditText? = null
    private var address1: EditText? = null
    private var address2: EditText? = null
    private var state: Spinner? = null
    private var zipcode: EditText? = null
    private var email : EditText? = null
    private var password: EditText? = null
    private var confirmPassword : EditText? = null

    private lateinit var firebase: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signupViewModel =
            ViewModelProviders.of(this).get(SignupViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user_signup, container, false)

        mAuth = FirebaseAuth.getInstance()
        populateStateDropDown()

        firebase = FirebaseDatabase.getInstance().reference

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signupButton = id_user_signup__signup_button
        val cancelButton = id_user_signup__cancel_button

        signupButton.setOnClickListener {
            println("regular sign up")
            val isValid = fetchUserInput()

            if (isValid) {
                signupUsingFirebaseAuth(email?.text.toString(), password?.text.toString())
                transferToSigninFragment()
            }

        }

        cancelButton.setOnClickListener {
            backTrackToPrevious()
        }


    }

    private fun fetchUserInput(): Boolean{
        firstName = id_user_signup__firstname_edit_text
        lastName = id_user_signup__lastname_edit_text
        address1 = id_user_signup__address1_edit_text
        address2 = id_user_signup__address2_edit_text
        state = id_user_signup__address3_state_spinner
        zipcode = id_user_signup__address3_zipcode_edit_text

        email = id_user_signup__email_edit_text
        password = id_user_signup__password_edit_text
        confirmPassword = id_user_signup__confirm_password_editText
        return submitInfoToFireBase()

    }


    private fun populateStateDropDown() {
        val states = resources.getStringArray(R.array.States)
        val spinner = view?.findViewById<Spinner>(R.id.id_user_signup__address3_state_spinner)

        if (spinner != null) {
            val adapter = ArrayAdapter(requireActivity().applicationContext, android.R.layout.simple_spinner_dropdown_item, states)
            spinner.adapter = adapter
        }
    }

    private fun submitInfoToFireBase(): Boolean {
        val userId = UUID.randomUUID().toString()
        val user = User(userId)
        user.firstName = firstName?.text.toString()
        user.lastName = lastName?.text.toString()
        user.address1 = address1?.text.toString()
        user.address2 = address2?.text.toString()
        user.email = email?.text.toString()
        user.state = matchSpinnerToState()
        user.zipcode = zipcode?.text.toString()

        if (!validateInput(user, password?.text.toString(), confirmPassword?.text.toString())) {
            return false
        }

        firebase.child("user").child(userId).setValue(user)
        return true;
    }

    private fun signupUsingFirebaseAuth(email: String, password: String) {

        if (email != "" && password != "") {
            println("email and passwowrd okay")
            mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(!it.isSuccessful) {
                        println("user auth creation was not successful")
                        return@addOnCompleteListener
                    }
                    println("Successfuly created user with uuid: ${it.result?.user?.uid}")
                }
        }
    }

    private fun matchSpinnerToState(): String {
        return "California"
    }

    private fun transferToSigninFragment() {
        Toast.makeText(requireContext(), "You're All Signed Up! Please Login.", Toast.LENGTH_LONG).show()
        backTrackToPrevious()
    }

    private fun backTrackToPrevious() {
        Thread.sleep(100L)
        getFragmentManager()?.popBackStackImmediate();
    }

    //TODO: change true to isValid
    private fun validateInput(user: User, password: String, confirmPassword: String): Boolean {

        var isValid = true

        if(user.firstName == "") {
            Toast.makeText(requireContext(), "First Name Required", Toast.LENGTH_SHORT).show()
            isValid = false
        } else if (user.lastName == "") {
            Toast.makeText(requireContext(), "Last Name Required", Toast.LENGTH_SHORT).show()
            isValid = false
        } else if (user.email == "") {
            Toast.makeText(requireContext(), "Email Required", Toast.LENGTH_SHORT).show()
            isValid = false
        } else if (password == "") {
            Toast.makeText(requireContext(), "Password Required", Toast.LENGTH_SHORT).show()
            isValid = false
        } else if (password.length < 6) {
            Toast.makeText(requireContext(), "Password Must Contain 6 Characters", Toast.LENGTH_SHORT).show()
            isValid = false
        } else if (password != confirmPassword) {
            Toast.makeText(requireContext(), "Password Does Not Match", Toast.LENGTH_SHORT).show()
            isValid = false
        }

        return true
    }

}