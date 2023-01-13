package com.edu.miu.quizapplication.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.edu.miu.quizapplication.model.Quiz

@Database(
    entities = [Quiz::class],
    version = 1,
    exportSchema = true,
)

abstract class DatabaseConnection : RoomDatabase() {
    abstract fun getDao():QuizDao

    companion object {

        @Volatile
        private var instance: DatabaseConnection? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: build(context).also {
                instance = it
            }
        }

        private fun build(context: Context): DatabaseConnection {
            return Room.databaseBuilder(
                context.applicationContext,
                DatabaseConnection::class.java,
                "databaseconnection"
            ).build()
        }

    }
}