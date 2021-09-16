package com.techjd.mcq

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Callback

import com.techjd.api.APIService
import retrofit2.Call
import retrofit2.Response
import androidx.appcompat.widget.Toolbar

import android.view.MenuInflater


class HomeActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var button: Button

    //    private lateinit var enrollmentNumber: TextView
//    private lateinit var emailId: TextView
//    private lateinit var submit: TextView
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


//        supportActionBar?.hide()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        initialize()
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val status = sharedPreferences.getString("status", "NOT STARTED")
        val erNo = sharedPreferences.getString("enrollment_number", "")
        val email = sharedPreferences.getString("email_id", "")
        Log.d("EMAIL", "${email}")
//        enrollmentNumber.text = erNo
//        emailId.text = email

        // API Call
//        APIService.instance.getIsSubmitted(erNo!!)
//            .enqueue(object : Callback<String> {
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    Log.d("STATUS CODE", "onResponse: ${response.code()} ")
//                    submit.text = "You Have Already Submitted , You Cant Submit Again "
//                }
//
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    Log.d("ERRRO", "onFailure: ${t.message}")
//                }
//
//            })

        if (status == "NOT STARTED") {
            textView.text = "You Have't started filling the Questionnaire Yet ."
//            button.text = "START"
//            button.setOnClickListener {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//            }
        } else if (status == "STARTED") {
            textView.text = "COMPLETE YOUR QUESTIONNAIRE AS SOON AS POSSIBLE"
//            button.text = "RESUME"
//            button.setOnClickListener {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//            }
        } else if (status == "FILLED") {
            textView.text = "You Have Successfully Submitted The Questionnaire"
//            button.visibility = View.GONE
        }
    }

    private fun initialize() {
        textView = findViewById(R.id.status)
//        button = findViewById(R.id.start)
//        enrollmentNumber = findViewById(R.id.enrollmentNumber)
//        emailId = findViewById(R.id.emailId)
//        submit = findViewById(R.id.status)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.fillForm -> {
                val sharedPreferences: SharedPreferences = this.getSharedPreferences(
                    sharedPrefFile,
                    Context.MODE_PRIVATE
                )
                val status = sharedPreferences.getString("status", "NOT_STARTED")
                if (status == "NOT_STARTED") {
                    val intent = Intent(this@HomeActivity, FillFormActivity::class.java)
                    startActivity(intent)
                } else if (status == "FILLED") {
                    Toast.makeText(
                        this@HomeActivity,
                        "You Have Already Responded",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val intent = Intent(this@HomeActivity, MainActivity::class.java)
                    startActivity(intent)
                }
//                Log.d("status", "${status}")
            }
            else -> {
                return super.onOptionsItemSelected(item);
            }
        }

        return super.onOptionsItemSelected(item)
    }
}