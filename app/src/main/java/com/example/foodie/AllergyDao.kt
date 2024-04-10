package com.example.foodie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy

@Dao
interface AllergyDao {
    @Query("SELECT * FROM allergies")
    fun getAll(): List<AllergyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(allergy: AllergyEntity)

    @Delete
    fun delete(allergy: AllergyEntity)
}

