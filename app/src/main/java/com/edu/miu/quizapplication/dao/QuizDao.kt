package com.edu.miu.quizapplication.dao

import androidx.room.*
import com.edu.miu.quizapplication.model.Quiz

@Dao
interface QuizDao {

    @Query("delete from quiz")
    suspend fun deleteAll()

    @Query("select * from quiz order by 1 asc")
    suspend fun getAll() : List<Quiz>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAll(vararg quiz: Quiz)

}