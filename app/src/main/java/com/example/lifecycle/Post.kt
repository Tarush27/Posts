package com.example.lifecycle

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class Post(
    @ColumnInfo("title") val title: String? = null, @ColumnInfo("body") val body: String? = null
)
