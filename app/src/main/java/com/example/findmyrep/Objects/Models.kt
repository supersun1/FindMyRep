package com.example.findmyrep.Objects

import com.google.api.services.civicinfo.model.Office

//To Store my models for the Rep Data and keep them contained
// This is to help parse my data into my two fragments RepList and RepInfo

class RepDataJSON(val officials: List<Official>, val offices: List<Office>)

class Office(val title: String)

class Official(val name: String, val address: String, val party: String, val phones: Int, val urls: String, val photoUrl: String, val channel: List<Social>)

class Social(val type: String, id: String)
