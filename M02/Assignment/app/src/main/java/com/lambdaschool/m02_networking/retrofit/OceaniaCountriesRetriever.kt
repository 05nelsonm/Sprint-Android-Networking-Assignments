package com.lambdaschool.m02_networking.retrofit

import com.google.gson.GsonBuilder
import com.lambdaschool.m02_networking.model.OceaniaCountry
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OceaniaCountriesRetriever {

    companion object {
        internal const val BASE_URL = "https://restcountries.eu/rest/v2/region/"
    }

    fun getOceaniaCountries(): Call<List<OceaniaCountry>> {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val countriesAPI = retrofit.create(OceaniaCountriesAPI::class.java)

        return countriesAPI.getCountries()
    }
}