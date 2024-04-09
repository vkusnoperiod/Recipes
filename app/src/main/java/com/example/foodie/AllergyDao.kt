package com.example.foodie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.OnConflictStrategy

@Dao
interface AllergiesDao {
    @Query("SELECT * FROM allergies")
    fun getAll(): List<AllergiesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(allergy: AllergiesEntity)

    @Delete
    fun delete(allergy: AllergiesEntity)
}

