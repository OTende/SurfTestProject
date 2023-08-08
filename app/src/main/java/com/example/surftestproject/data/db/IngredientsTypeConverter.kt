package com.example.surftestproject.data.db

import androidx.room.TypeConverter

class IngredientsTypeConverter {
    @TypeConverter
    fun fromRecipeList(list: List<String>): String {
        return list.joinToString(separator = ";")
    }

    @TypeConverter
    fun fromString(string: String): List<String> {
        return string.split(";")
    }
}