package com.lambdaschool.m02_networking.model

data class OceaniaCountryList {
    val country: List<OceaniaCountry>
}

data class OceaniaCountry (
    val name: String,
    val alpha2code: String,
    val capital: String,
    val subregion: String
)