package com.lambdaschool.httpoperations

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lambdaschool.httpoperations.model.Employee
import com.lambdaschool.httpoperations.retrofit.JsonPlaceHolderApi
import retrofit2.*

class HttpGetActivity : AppCompatActivity(), Callback<List<Employee>> {

    lateinit var employeesService: JsonPlaceHolderApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http_get)
        title = "Post Request: New Employee David"

        // TODO: Create the api
        employeesService = JsonPlaceHolderApi.Factory.create()

        val type = intent.getStringExtra("get")
        if (type == "simple") {
            title = "GET - Simple Request"
            getEmployees()
        } else if (type == "path") {
            title = "GET - Path Parameter: EmployeeId - 1"
            getEmployeesById("1")
        } else {
            title = "GET - Query Parameter: Age - 55"
            getEmployeesByAge("55")
        }
    }

    private fun getEmployees(){
        // TODO: Write the call for getting all employees
        employeesService.getEmployees().enqueue(this)
    }

    private fun getEmployeesById(employeeId: String){
        // TODO: Write the call to get an employee by id
        employeesService.getEmployeesById(employeeId).enqueue(this)
    }

    private fun getEmployeesByAge(age: String){
        // TODO: Write the call to get an employee by age
        employeesService.getEmployeesByAge(age).enqueue(this)
    }

    override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
        Toast.makeText(this@HttpGetActivity, "Failed", Toast.LENGTH_SHORT).show()
    }

    override fun onResponse(
        call: Call<List<Employee>>,
        response: Response<List<Employee>>
    ) {
        response.body()?.let {
            Toast.makeText(this@HttpGetActivity, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
