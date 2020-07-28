package com.example.findmyrep.Objects

import com.google.firebase.database.IgnoreExtraProperties
import com.example.findmyrep.Objects.UserType.CITIZEN as CITIZEN1

enum class UserType {
    CITIZEN, NONCITIZEN, OFFICIALS
}

@IgnoreExtraProperties
//data class  User(val userFirstName: String, val userLastName: String, val userAddress: String, val userAddress2: String, val userAddressState: String, val userZip: String,  val userEmail: String) {
data class User (var id: String){

    var type: UserType= UserType.CITIZEN
        get() {
            return field
        }
        set(v: UserType) {
            field = v
        }

    var firstName : String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var lastName: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var address1 : String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var address2: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var state: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
    var zipcode: String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }

    var email : String = ""
        get() {
            return field
        }
        set(value) {
            field = value
        }
}