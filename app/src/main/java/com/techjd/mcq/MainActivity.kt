package com.techjd.mcq

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.room.Room
import com.techjd.data.QuestionsList
import com.techjd.room.AppDatabase
import com.techjd.room.Responses
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import retrofit2.Callback

import com.techjd.api.APIService
import com.techjd.data.status
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var index: Int = 0
    val questionsList = QuestionsList().returnQuestionsList()
    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var sharedPreferences: SharedPreferences
    var hashmap: HashMap<Int, Int> = HashMap()
    private lateinit var next: Button
    private lateinit var prev: Button
    private lateinit var submit: Button
    private lateinit var qNo: TextView
    private lateinit var qTitle: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var radioButton: RadioButton

    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var radioButton3: RadioButton
    private lateinit var radioButton4: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        initialize()

        sharedPreferences = this.getSharedPreferences(
            sharedPrefFile,
            Context.MODE_PRIVATE
        )
        val enrollmentNumber = sharedPreferences.getString("enrollment_number", "")!!
        val emailId = sharedPreferences.getString("email_id", "")!!
        val branch = sharedPreferences.getString("branch", "")!!
        val semester = sharedPreferences.getString("semester", "")!!


        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "responses"
        ).allowMainThreadQueries().build()

        for (i in 0..questionsList.size - 1) {
            db.responsesDao().insertResponse(response = Responses(i, 'Z'))
        }

        displayQuestionNumber(index)
        if (index == questionsList.size - 1) {

        } else {
            displayQuestions()
            displayAnswer(db)
        }

        if (index == 0) {
            prev.visibility = View.INVISIBLE
        } else {
            prev.visibility = View.VISIBLE
        }


        radioButton1.setOnClickListener {
//            Toast.makeText(this, "${radioButton1.id}", Toast.LENGTH_SHORT).show()
            newStoreValue(db, 'A')
//            newStoreValue(db, radioButton1.id)
        }

        radioButton2.setOnClickListener {
            newStoreValue(db, 'B')

//            Toast.makeText(this, "${radioButton2.id}", Toast.LENGTH_SHORT).show()
//            newStoreValue(db, radioButton2.id)
        }

        radioButton3.setOnClickListener {
            newStoreValue(db, 'C')

//            Toast.makeText(this, "${radioButton3.id}", Toast.LENGTH_SHORT).show()
//            newStoreValue(db, radioButton3.id)
        }


        radioButton4.setOnClickListener {
            newStoreValue(db, 'D')

//            Toast.makeText(this, "${radioButton4.id}", Toast.LENGTH_SHORT).show()
//            newStoreValue(db, radioButton4.id)
        }


        next.setOnClickListener {
            if (index == questionsList.size - 1) {
                Toast.makeText(this, "No Further Questions , Submit It ! ", Toast.LENGTH_SHORT)
                    .show()
                submit.visibility = View.VISIBLE
            } else {
                Log.d("MARKED OPTION", "onCreate: ${radioGroup.checkedRadioButtonId}")

                incrementQuestionNumber()
                displayQuestions()
                Log.d("NEXT INDEX", "${index}")
                clearOptions()
                displayAnswer(db)
//                storeValue()

            }

        }

        submit.setOnClickListener {
            var list: ArrayList<Char> = ArrayList()
            val responses = db.responsesDao().getAllResponses()
            var allDone = true
            for (i in 0..responses.size - 1) {
                if (responses[i].buttonId == 'Z') {
                    allDone = false
                    break;
                } else {
                    list.add(responses[i].buttonId)
                }
//                Log.d("ALL RESPONSES", "$i = ${responses[i].buttonId}")
            }
            if (allDone) {
                makeCall(
                    enrollmentNumber = enrollmentNumber,
                    emailId = emailId,
                    branch = branch,
                    semester = semester[0].toString(),
                    responses = list
                )
            } else {
                displayToast()
            }
        }

        prev.setOnClickListener {
            if (index == 0) {

            } else {
//                storeValue(db)
                submit.visibility = View.INVISIBLE
                decrementQuestionNumber()
                displayQuestions()
                Log.d("INDEX", "${index}")
                clearOptions()
                displayAnswer(db)
            }
        }
    }

    private fun makeCall(
        enrollmentNumber: String,
        emailId: String,
        branch: String,
        semester: String,
        responses: ArrayList<Char>
    ) {
        APIService.instance.sendResponses(
            enrollmentNumber, emailId, branch, semester, responses
        )
            .enqueue(object : Callback<status> {
                override fun onResponse(call: Call<status>, response: Response<status>) {
                    Log.d("MESSAGE", "onResponse: ${response.body()} ")
                    if (response.body()!!.status.equals("Success")) {

                        val editor: SharedPreferences.Editor = sharedPreferences.edit()
                        editor.putString("status","FILLED")
                        editor.commit()

                        val intent = Intent(this@MainActivity, HomeActivity::class.java)

                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Please Try Again Later",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<status>, t: Throwable) {
                    Log.d("ERRRO", "onFailure: ${t.message}")
                    Toast.makeText(
                        this@MainActivity,
                        "Please Try Again After Some Time",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
        Toast.makeText(this, "Submitted", Toast.LENGTH_SHORT).show()
    }

    private fun displayToast() {
        Toast.makeText(this, "Please Fill All Questions", Toast.LENGTH_SHORT).show()
    }


    private fun displayAnswer(db: AppDatabase) {
        val butId = db.responsesDao().getButtonId(index)
        if (butId == 'Z') {

        } else {
            if (butId == 'A') {
                radioButton1.isChecked = true
            } else if (butId == 'B') {
                radioButton2.isChecked = true
            } else if (butId == 'C') {
                radioButton3.isChecked = true
            } else if (butId == 'D') {
                radioButton4.isChecked = true
            }
//            Log.d("BUTTON ID", "displayAnswer: $butId")
//            radioButton = findViewById(butId)
//            radioButton.isChecked = true
        }
    }

//    private fun storeValue(db: AppDatabase) {
//        var radioId = radioGroup.checkedRadioButtonId
//        Log.d("STORED VALUE", "storeValue: ${radioId.toString()} index is ${index}")
//        hashmap[index] = radioId
//        db.responsesDao().insertResponse(response = Responses(index, radioId))
//    }

    private fun newStoreValue(db: AppDatabase, buttonId: Char) {

        db.responsesDao().updateResponse(response = Responses(index, buttonId))
    }

    private fun clearOptions() {
        radioGroup.clearCheck()
    }

    private fun initialize() {
        next = findViewById(R.id.next)
        qNo = findViewById(R.id.qNo)
        qTitle = findViewById(R.id.qTitle)
        prev = findViewById(R.id.prev)
        radioGroup = findViewById(R.id.optionsGroup)
        submit = findViewById(R.id.submit)

        radioButton1 = findViewById(R.id.option1)
        radioButton2 = findViewById(R.id.option2)
        radioButton3 = findViewById(R.id.option3)
        radioButton4 = findViewById(R.id.option4)

        submit.visibility = View.INVISIBLE
    }

    private fun incrementQuestionNumber() {
//        storeValue(db)
        index++
        if (index != 0) {
            prev.visibility = View.VISIBLE
        }
        if (index == questionsList.size - 1) {
            next.visibility = View.INVISIBLE
            submit.visibility = View.VISIBLE
        }
        displayQuestionNumber(index)
    }

    private fun decrementQuestionNumber() {
//        storeValue(db)
        Log.d("INSIDE INDEX", "decrementQuestionNumber: $index")
        index--;
        if (index == 0) {
            prev.visibility = View.INVISIBLE
        } else {
            next.visibility = View.VISIBLE
        }
        displayQuestionNumber(index)
    }

    private fun displayQuestions() {
        qTitle.text = questionsList[index].questions
    }

    private fun displayQuestionNumber(index: Int) {
        qNo.text = "Question ${index + 1} of 50"
    }

    fun checkId(v: View) {
        var radioId = radioGroup.checkedRadioButtonId
        radioButton = findViewById(radioId)
//        Toast.makeText(this, "$radioId $index", Toast.LENGTH_SHORT).show()
    }
}