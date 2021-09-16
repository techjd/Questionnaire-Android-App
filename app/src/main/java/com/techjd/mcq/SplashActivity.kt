package com.techjd.mcq

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    private var SPLASH_TIME_OUT: Long = 1500
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

//        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
//            sharedPrefFile,
//            Context.MODE_PRIVATE
//        )
//        val enrollment = sharedPreferences.getString("enrollment_number", "")

        Handler().postDelayed({
//            if (enrollment == "") {
//                val navigate = Intent(this, EnterActivity::class.java)
//                startActivity(navigate)
//                finish()
//            } else {
            val navigate = Intent(this, HomeActivity::class.java)
            startActivity(navigate)
            finish()
//            }

        }, SPLASH_TIME_OUT)

    }
}