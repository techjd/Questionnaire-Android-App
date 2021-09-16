package com.techjd.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Responses(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "buttonId") val buttonId: Char
)