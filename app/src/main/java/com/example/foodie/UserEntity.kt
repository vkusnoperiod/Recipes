package com.example.foodie
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")val userId: Int,

    @ColumnInfo(name = "username")val username: String,

    @ColumnInfo(name = "user_password")val userPassword: String,

    @ColumnInfo(name = "person_name")val personName:String,

    @ColumnInfo(name = "person_sex")val personSex:String
)
