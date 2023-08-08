package com.example.surftestproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.surftestproject.data.Cocktail

@Database(entities = [Cocktail::class], version = 1)
abstract class CocktailDatabase : RoomDatabase() {
    abstract fun getDao(): CocktailDao
}