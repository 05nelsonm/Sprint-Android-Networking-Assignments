package com.lambdaschool.m01_threads

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MyAsyncTask().execute()

        btn_cancel.setOnClickListener {
            MyAsyncTask().cancel(true)
        }
    }

    override fun onStop() {

        if (MyAsyncTask() != null) {
            MyAsyncTask().cancel(true)
            progressBarShow(false)
            tv_prime_numbers.text = "Cancelled"
        }

        super.onStop()
    }

    inner class MyAsyncTask : AsyncTask<Unit, Int, String>() {

        override fun onPreExecute() {
            super.onPreExecute()

            progressBarShow(true)
        }

        override fun doInBackground(vararg p0: Unit?): String {

            return primes().take(16000).joinToString(", ")
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            progressBarShow(false)
            tv_prime_numbers.text = "Primes: $result"
        }

        override fun onCancelled() {
            super.onCancelled()

            println("MyAsyncTask was CANCELLED")

        }
    }

    fun primes(): Sequence<Long> {
        var i = 0L
        return sequence {
            generateSequence { i++ }
                .filter { n -> n > 1 && ((2 until n).none { i -> n % i == 0L }) }
                .forEach { yield(it) }
        }
    }

    fun progressBarShow(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
            tv_prime_numbers.visibility = View.INVISIBLE
            btn_cancel.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
            tv_prime_numbers.visibility = View.VISIBLE
            btn_cancel.visibility = View.INVISIBLE
        }
    }


}
