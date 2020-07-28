package com.example.findmyrep.ui.usersignup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.findmyrep.Objects.User
import com.example.findmyrep.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_user_signup.*
import java.util.*


class SignupFragment : Fragment() {

    private lateinit var signupViewModel: SignupViewModel
    private lateinit var mAuth: FirebaseAuth
    private lateinit var firebase: DatabaseReference
    private lateinit var user: User

    private var firstName: EditText? = null
    private var lastName: EditText? = null
    private var address1: EditText? = null
    private var address2: EditText? = null
    private var state: Spinner? = null
    private var zipcode: EditText? = null
    private var email : EditText? = null
    private var password: EditText? = null
    private var confirmPassword : EditText? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signupViewModel =
            ViewModelProviders.of(this).get(SignupViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user_signup, container, false)
        mAuth = FirebaseAuth.getInstance()
        firebase = FirebaseDatabase.getInstance().reference

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signupButton = id_user_signup__signup_button
        val cancelButton = id_user_signup__cancel_button

        signupButton.setOnClickListener {
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
        return validateInput()
    }

    private fun validateInput(): Boolean {
        val userId = UUID.randomUUID().toString()
        user = User(userId)
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
        return true;
    }

    private fun signupUsingFirebaseAuth(email: String, password: String) {
        mAuth.signOut()

        if (email != "" && password != "") {
            println("email and passwowrd okay")

            mAuth.createUserWithEmailAndPassword(email.trim(), password.trim())
                .addOnCompleteListener {
                    if(it.isSuccessful) {
                        println("Successfuly created user with uuid: ${it.result?.user?.uid}")
                        storeUserInFirebase()

                    } else {
                        println("user auth creation was not successful")
                        return@addOnCompleteListener
                    }
                }
        }
    }

    private fun storeUserInFirebase() {
        val userId: String = mAuth.currentUser!!.uid
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

    private fun transferToSigninFragment() {
        mAuth.signOut()
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

        return isValid
    }

}