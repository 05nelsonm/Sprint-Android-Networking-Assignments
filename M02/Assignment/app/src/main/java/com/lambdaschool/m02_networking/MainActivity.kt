package com.lambdaschool.m02_networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.lambdaschool.m02_networking.model.OceaniaCountry
import com.lambdaschool.m02_networking.retrofit.OceaniaCountriesRetriever
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), Callback<List<OceaniaCountry>> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchCountriesButton.setOnClickListener {
            OceaniaCountriesRetriever().getOceaniaCountries().enqueue(this)
        }
    }

    override fun onResponse(call: Call<List<OceaniaCountry>>, response: Response<List<OceaniaCountry>>) {
        if (response.isSuccessful) {
            val oceaniaCountryList = response.body()
            var countryList = ""
            for (i in 0..(oceaniaCountryList?.size ?: -1)) {
                if (i < 0) { // uses elvis operator above to determine nullable value and will break loop
                    Toast.makeText(this@MainActivity, "oceaniaCountryList[i].name is NULL", Toast.LENGTH_SHORT).show()
                    break
                } else {
                    countryList += oceaniaCountryList!![i].name + "\n"
                }
            }
            countriesTextView.setText(countryList)
        } else {
            val response = "response not successful; ${response.errorBody().toString()}"
            Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFailure(call: Call<List<OceaniaCountry>>, t: Throwable) {
        t.printStackTrace()
        val response = "faliure; ${t.printStackTrace()}"
        Toast.makeText(this@MainActivity, response, Toast.LENGTH_SHORT).show()
    }
}
