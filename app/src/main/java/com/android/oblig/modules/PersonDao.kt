package com.android.oblig.modules

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface PersonDao {
    @Query("SELECT * FROM Person")
    fun getAll(): List<Person>

    @Query("SELECT id FROM Person")
    fun getAllPersonIds():List<Int>

    @Query("SELECT * FROM Person WHERE id == :id")
    fun findPersonById(id:Int):Person

    @Insert
    fun insert(person: Person)

    @Delete
    fun delete(person: Person)
}