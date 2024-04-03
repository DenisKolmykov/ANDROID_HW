package com.example.android_hw15_room_sqlite_v3

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DictDao {
    @Query ("SELECT * FROM dict")
    fun getAll() : Flow<List<Word>>

    @Insert(entity = Word::class)
    suspend fun insert(dict: NewWord)

    @Delete
    suspend fun delete(dict: Word)

    @Update
    suspend fun update(dict: Word){
    }

}