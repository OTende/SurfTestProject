package com.example.surftestproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surftestproject.data.Cocktail
import com.example.surftestproject.data.CocktailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class CocktailViewModel @Inject constructor(private val repository: CocktailRepository) :
    ViewModel() {
    private var _cocktailList = MutableLiveData<List<Cocktail>>()
    val cocktailList: LiveData<List<Cocktail>> = _cocktailList

    fun updateCocktails() {
        viewModelScope.launch {
            _cocktailList.postValue(repository.getAllCocktails())
        }
    }

    fun saveCocktail(cocktail: Cocktail) = viewModelScope.launch {
        val saving = async { repository.saveCocktail(cocktail) }
        saving.await()
        updateCocktails()
    }

    fun getCocktailById(id: Int): Cocktail = runBlocking {
        repository.getCocktail(id)
    }

    fun getCocktailsByCounter(count: Int): List<Cocktail> = runBlocking {
        repository.getCocktailsByCounter(count)
    }

    fun deleteCocktail(cocktail: Cocktail) = viewModelScope.launch {
        repository.deleteCocktail(cocktail)
        updateCocktails()
    }

    fun changeCocktail(cocktail: Cocktail): Int {
        return runBlocking {
            val updating = async { repository.changeCocktail(cocktail) }
            updateCocktails()
            updating.await()
        }
    }
}

