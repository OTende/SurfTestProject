package com.example.surftestproject.data

import com.example.surftestproject.data.db.CocktailDao
import com.example.surftestproject.data.db.CocktailDatabase
import javax.inject.Inject

class CocktailRepository @Inject constructor(
    private val dao: CocktailDao
) {
    suspend fun getAllCocktails(): List<Cocktail> = dao.getCocktails()
    suspend fun saveCocktail(cocktail: Cocktail) = dao.saveCocktail(cocktail)
    suspend fun getCocktail(id: Int): Cocktail = dao.getCocktailById(id)
    suspend fun getCocktailsByCounter(count: Int) = dao.getCocktailsByCounter(count)
    suspend fun deleteCocktail(cocktail: Cocktail) = dao.deleteCocktail(cocktail)
}