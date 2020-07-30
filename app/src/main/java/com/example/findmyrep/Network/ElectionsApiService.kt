package com.example.findmyrep.Network
import com.example.findmyrep.Election
import com.example.findmyrep.electionsX
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



private const val BASE_URL ="https://www.googleapis.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

//    private val retrofit = Retrofit.Builder()
//        .addConverterFactory(ScalarsConverterFactory.create())
//        .baseUrl(BASE_URL)
//        .build()

interface ElectionsApiService {
    @GET("/civicinfo/v2/elections")
    fun getProperties(@Query("key") apiKey: String):
            Call<electionsX>
}
    object ElectionsApi {
        val retrofitService: ElectionsApiService by lazy {
            retrofit.create(ElectionsApiService::class.java)
        }
//class ElectionsApiService {
    }

