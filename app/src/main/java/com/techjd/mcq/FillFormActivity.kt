package com.techjd.mcq

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.techjd.Adapter.SpinnerAdapter

import com.techjd.Adapter.SemesterSpinnerAdapter
import com.techjd.data.SpinnerItem


class FillFormActivity : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var editTextEnrollmentNumber: EditText
    private lateinit var editTextEmailId: EditText
    private lateinit var branchSpinner: Spinner
    private lateinit var semesterSpinner: Spinner
    private lateinit var enter: Button

    val branches = arrayListOf<SpinnerItem>(
        SpinnerItem("Select Branch"),
        SpinnerItem("Computer Engineering"),
        SpinnerItem("Information Technology"),
        SpinnerItem("Food Processing"),
        SpinnerItem("Mechanical Engineering"),
        SpinnerItem("Civil Engineering"),
        SpinnerItem("Electrical Engineering")
    )

    val sem = arrayListOf<SpinnerItem>(
        SpinnerItem("Select Semester"),
        SpinnerItem("1st Semester"),
        SpinnerItem("2nd Semester"),
        SpinnerItem("3rd Semester"),
        SpinnerItem("4th Semester"),
        SpinnerItem("5th Semester"),
        SpinnerItem("6th Semester"),
        SpinnerItem("7th Semester"),
        SpinnerItem("8th Semester")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_form)

        initialize()

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        val mAdapter = SpinnerAdapter(this, branches)
        val mSemSpinnerAdapter = SemesterSpinnerAdapter(this, sem)
        branchSpinner.adapter = mAdapter
        semesterSpinner.adapter = mSemSpinnerAdapter

        enter.setOnClickListener {
            val selectedItem = branchSpinner.selectedItem as SpinnerItem
            val semSelectedItem = semesterSpinner.selectedItem as SpinnerItem
            val enrollmentNumber = editTextEnrollmentNumber.text.toString()
            val emailId = editTextEmailId.text.toString()
            if (selectedItem.name == branches[0].name) {
                Toast.makeText(
                    this@FillFormActivity,
                    "Please Enter All Fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (semSelectedItem.name == sem[0].name) {
                Toast.makeText(
                    this@FillFormActivity,
                    "Please Enter All Fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (enrollmentNumber.isEmpty() || emailId.isEmpty()) {
                Toast.makeText(
                    this@FillFormActivity,
                    "Please Enter All Fields",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                editor.putString("enrollment_number", enrollmentNumber)
                editor.putString("email_id", emailId)
                editor.putString("status", "STARTED")
                editor.putString("semester", semSelectedItem.name)
                editor.putString("branch", selectedItem.name)
                editor.commit()

                val navigate = Intent(this, MainActivity::class.java)
                startActivity(navigate)

//                Toast.makeText(
//                    this@FillFormActivity,
//                    "${selectedItem.name} and ${semSelectedItem.name} selected and $emailId and $enrollmentNumber",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }
    }

    private fun initialize() {
        editTextEnrollmentNumber = findViewById(R.id.editTextEnrollmentNumber)
        editTextEmailId = findViewById(R.id.editTextTextEmailAddress)
        branchSpinner = findViewById(R.id.branch_Spinner)
        semesterSpinner = findViewById(R.id.semSpinner)
        branchSpinner = findViewById(R.id.branch_Spinner)
        enter = findViewById(R.id.enter)
    }
}