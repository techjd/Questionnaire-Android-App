package com.techjd.room

import androidx.room.*

@Dao
interface responsesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertResponse(response: Responses)

    @Query("SELECT buttonId FROM Responses WHERE id = :id")
    fun getButtonId(id: Int): Char

    @Update
    fun updateResponse(response: Responses)

    @Query("SELECT * FROM Responses")
    fun getAllResponses() : List<Responses>
}