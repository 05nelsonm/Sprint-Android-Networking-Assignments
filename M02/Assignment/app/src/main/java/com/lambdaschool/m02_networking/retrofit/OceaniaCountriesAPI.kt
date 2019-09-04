package com.lambdaschool.m02_networking.retrofit

import com.lambdaschool.m02_networking.model.OceaniaCountry
import retrofit2.Call
import retrofit2.http.GET

interface OceaniaCountriesAPI {

    @GET("Oceania")
    fun getCountries(): Call<List<OceaniaCountry>>

}