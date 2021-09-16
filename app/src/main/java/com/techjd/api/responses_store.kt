package com.techjd.api

import com.techjd.data.status
import com.techjd.room.Responses
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface responses_store {

    @FormUrlEncoded
    @POST("isSubmitted")
    fun getIsSubmitted(
        @Field("enrollment_no") enrollmentNumber: String
    ): Call<String>

    @FormUrlEncoded
    @POST("responses")
    fun sendResponses(
        @Field("enrollment_number") enrollmentNumber: String,
        @Field("email_id") emailId: String,
        @Field("branch") branch: String,
        @Field("semester") semester: String,
        @Field("responses") responses: ArrayList<Char>
    ): Call<status>
}