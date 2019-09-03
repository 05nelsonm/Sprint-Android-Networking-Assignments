package com.lambdaschool.m02_networking.retrofit

import com.lambdaschool.m02_networking.model.OceaniaCountryList
import retrofit2.Call
import retrofit2.http.GET

interface CountriesAPI {

    @GET("region/Oceania")
    fun getCountries(): Call<OceaniaCountryList>

}