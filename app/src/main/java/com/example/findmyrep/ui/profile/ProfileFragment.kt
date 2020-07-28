package com.example.findmyrep.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.findmyrep.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_user_profile.*

class ProfileFragment : Fragment() {
    private lateinit var profileViewModel: ProfileViewModel
    var mAuth:FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var database: DatabaseReference
    private lateinit var userName: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user_profile, container, false)

        println("finished on create view")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val curUser: FirebaseUser? = mAuth.currentUser
        var signOutButton = id_user_profile__sign_out

        database = FirebaseDatabase.getInstance().reference

        if (curUser != null) {
            getUser(database.child("user").child(curUser.uid))
        }
        println("finished on view created")

        signOutButton.setOnClickListener {
            mAuth.signOut()
            findNavController().navigate(R.id.navigation_userlogin)
        }
    }

    fun getUser(rootRef: DatabaseReference) {
        rootRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                println(error.message)
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                userName = snapshot.child("firstName").value.toString()
                println("this user name is $userName")
                updateEditText(userName)
            }
        })
    }

    private fun updateEditText(name:String) {
        println("user name is : $name")

        val editText = id_text_user_profile
        editText.text = "welcome $name"
    }


}


