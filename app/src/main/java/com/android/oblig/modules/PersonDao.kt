package com.android.oblig.modules

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getAll(): List<Person>

    @Insert
    fun insert(vararg person: Person)

    @Delete
    fun delete(person: Person)
}