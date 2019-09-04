package com.lambdaschool.httpoperations.retrofit

import com.lambdaschool.httpoperations.model.Employee
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JsonPlaceHolderApi {
    // TODO: Create API for different endpoints

    @GET("employees")
    fun getEmployees(): Call<List<Employee>>

    @GET("employees/{id}")
    fun getEmployeesById(@Path("id") employeeId: String)

    @GET("employees")
    fun getEmployeesByAge(employeeId: String)

    class Factory {

        companion object {
            private const val BASE_URL = "https://demo8143297.mockable.io"
        }

        fun getUrl() {
            BASE_URL
        }
    }
}