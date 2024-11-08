package com.example.databaseapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 3)
abstract class AppDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getBase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}