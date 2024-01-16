package com.waldorf.comparator.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.waldorf.comparator.model.SearchItem
import retrofit2.Retrofit
//import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType

private const val BASE_URL : String = "https://waldorfscomparator.azurewebsites.net/api_search/"
private val json = Json { coerceInputValues = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface ComparatorApiService {

    @GET("{countryCode}/{searchTerm}")
    suspend fun getItem(
        @Path("countryCode") countryCode: String,
        @Path("searchTerm") searchTerm: String?
    ): List<SearchItem>
}

object ComparatorApi{
    val retrofitService : ComparatorApiService by lazy {
        retrofit.create(ComparatorApiService::class.java)
    }
}