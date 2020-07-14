package com.example.findmyrep.Objects

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
//data class  User(val userFirstName: String, val userLastName: String, val userAddress: String, val userAddress2: String, val userAddressState: String, val userZip: String,  val userEmail: String) {
data class User (val id: String){

//   var id : String = ""
//    var firstName : String = userFirstName
//    var lastName: String = userLastName
//
//    var address1 : String = userAddress
//    var address2: String = userAddress2
//    var state: String = userAddressState
//    var zipcode: String = userZip
//
//    var email : String = userEmail


    var idNum: String = this.id

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