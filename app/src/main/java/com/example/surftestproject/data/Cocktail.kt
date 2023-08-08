package com.example.surftestproject.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.surftestproject.data.db.IngredientsTypeConverter

@Entity
@TypeConverters(IngredientsTypeConverter::class)
data class Cocktail(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String?,
    @TypeConverters(IngredientsTypeConverter::class)
    val ingredients: List<String>,
    val image: ByteArray?,
    val recipe: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cocktail

        if (image != null) {
            if (other.image == null) return false
            if (!image.contentEquals(other.image)) return false
        } else if (other.image != null) return false

        return true
    }

    override fun hashCode(): Int {
        return image?.contentHashCode() ?: 0
    }
}