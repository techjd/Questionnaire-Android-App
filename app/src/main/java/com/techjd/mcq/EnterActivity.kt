package com.techjd.mcq

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class EnterActivity : AppCompatActivity() {
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var edtEnrollmentNumber: EditText
    private lateinit var edtEmailId: EditText
    private lateinit var enter: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter)
        supportActionBar?.hide()
        initialize()

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        enter.setOnClickListener {

            editor.putString("enrollment_number", edtEnrollmentNumber.editableText.toString())
            editor.putString("email_id", edtEmailId.editableText.toString())
            editor.putString("status", "NOT STARTED")
            editor.commit()

            val navigate = Intent(this, HomeActivity::class.java)
            startActivity(navigate)

        }

    }

    private fun initialize() {
        edtEnrollmentNumber = findViewById(R.id.editTextNumber)
        edtEmailId = findViewById(R.id.editTextTextEmailAddress)
        enter = findViewById(R.id.enter)
    }
}