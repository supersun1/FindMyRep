package com.example.findmyrep.ui.usersignup

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.findmyrep.Objects.User
import com.example.findmyrep.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_user_signup.*


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

        signupButton.setOnClickListener {
            println("regular sign up")
            fetchUserInput()

        }


    }

    private fun fetchUserInput() {
        println("here")
        println("yoyoyo")
        firstName = id_user_signup__firstname_edit_text
        lastName = id_user_signup__lastname_edit_text
        address1 = id_user_signup__address1_edit_text
        address2 = id_user_signup__address2_edit_text
        state = id_user_signup__address3_state_spinner
        zipcode = id_user_signup__address3_zipcode_edit_text

        email = id_user_signup__email_edit_text
        password = id_user_signup__password_edit_text
        confirmPassword = id_user_signup__confirm_password_editText
        submitInfoToFireBase()


    }


    private fun populateStateDropDown() {
        val states = resources.getStringArray(R.array.States)
        val spinner = id_user_signup__address3_state_spinner
        if (spinner != null) {
            val adapter = ArrayAdapter(requireActivity().applicationContext, android.R.layout.simple_spinner_item, states)
            spinner.adapter = adapter
        }
    }

    private fun submitInfoToFireBase() {
        val userId = "123"
        val user = User(userId)
        user.firstName = firstName?.text.toString()
        user.lastName = lastName?.text.toString()
        user.address1 = address1?.text.toString()
        user.address2 = address2?.text.toString()
        user.email = email?.text.toString()
        user.state = matchSpinnerToState()
        user.zipcode = zipcode?.text.toString()


        firebase.child("user").child(userId).setValue(user)

    }

    private fun matchSpinnerToState(): String {
        return "California"
    }


}