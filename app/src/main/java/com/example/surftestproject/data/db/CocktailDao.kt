package com.example.surftestproject.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.surftestproject.data.Cocktail

@Dao
interface CocktailDao {
    @Query("SELECT * FROM Cocktail")
    suspend fun getCocktails(): List<Cocktail>

    @Insert
    suspend fun saveCocktail(cocktail: Cocktail)

    @Query("SELECT * FROM Cocktail WHERE id = :id")
    suspend fun getCocktailById(id: Int): Cocktail

    @Query("SELECT * FROM Cocktail ORDER BY id DESC LIMIT :count")
    suspend fun getCocktailsByCounter(count: Int): List<Cocktail>

    @Delete
    suspend fun deleteCocktail(cocktail: Cocktail)
}