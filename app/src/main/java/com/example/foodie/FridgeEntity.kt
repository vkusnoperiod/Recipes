package com.example.foodie
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(
    tableName = "fridge",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class FridgeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ingredient_id") val ingredientId: Int,

    @ColumnInfo(name = "ingredient_name") val ingredientName: String,

    @ColumnInfo(name = "user_id") val userId: Int
)

