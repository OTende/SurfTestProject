package com.example.surftestproject.di

import android.content.Context
import androidx.room.Room
import com.example.surftestproject.data.db.CocktailDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideCocktailDatabase(@ApplicationContext context: Context): CocktailDatabase {
        return Room.databaseBuilder(
            context,
            CocktailDatabase::class.java,
            "main_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideDao(db: CocktailDatabase) = db.getDao()
}