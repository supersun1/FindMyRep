package com.example.findmyrep


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
//data class Election(
//    @Json(name = "elections") val elections: List<Election>,
//    @Json(name = "id") val id: String,
//    // @Json(name = "name") val name: String,
//    @Json(name = "electionDay") val electionDay: String,
//    @Json(name = "kind") val kind: String
//)
//
//@JsonClass(generateAdapter = true)
//data class electionsX(
//    @Json(name = "elections") val elections: List<Election>
//
//)


@JsonClass(generateAdapter = true)
data class electionsX(
    @Json(name = "elections") val elections: List<Election>
//    @Json(name = "kind")
//    val kind: String
)

@JsonClass(generateAdapter = true)
data class Election(
//    @Json(name = "id")
//    val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "electionDay") val electionDay: String
//    @Json(name = "ocdDivisionId")
//    val ocdDivisionId: String
)