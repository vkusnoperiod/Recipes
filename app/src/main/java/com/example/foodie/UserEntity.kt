package com.example.foodie
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.ForeignKey


@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null, // id автоматически генерируется и nullable

    val username: String,

    val password: String,

    val favouriteRecipeId: Int? = null
) {
    // Вспомогательный конструктор, который не требует id и favouriteRecipeId
    constructor(username: String, password: String) : this(null, username, password, null)
}
