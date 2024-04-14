package com.example.foodie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update
import androidx.room.OnConflictStrategy

@Dao
interface UserDao {
    @Query("SELECT * FROM users")
    fun getAll(): List<UserEntity>

    @Query("SELECT * FROM users WHERE user_id = :userId")
    fun findById(userId: Int): UserEntity

    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE username = :username)")
    fun userExists(username: String): Boolean

    @Query("SELECT * FROM users WHERE username = :username")
    fun findByUsername(username: String): UserEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)
    @Query("SELECT COALESCE(MAX(user_id), -1) FROM users")
    fun getLastUserId(): Int

    @Query("SELECT user_password FROM users WHERE username = :username")
    fun getPasswordByUsername(username: String): String
    @Update
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)
}

