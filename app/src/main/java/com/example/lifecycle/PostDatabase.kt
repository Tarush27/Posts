package com.example.lifecycle

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Post::class])
abstract class PostDatabase : RoomDatabase() {

    abstract fun postDao(): PostDao

    companion object {
        var INSTANCE: PostDatabase? = null

        fun getPostDatabase(context: Context): PostDatabase? {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context, PostDatabase::class.java, "post_database"
                    ).build()
                }
            }
            return INSTANCE
        }

    }

}