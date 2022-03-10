package com.bryo.marvel.myheroteam.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bryo.marvel.myheroteam.core.database.characters.CharacterBase
import com.bryo.marvel.myheroteam.core.database.characters.CharactersDao
import com.bryo.marvel.myheroteam.core.database.converters.DateConverter
import com.bryo.marvel.myheroteam.core.database.fetch.FetchDao
import com.bryo.marvel.myheroteam.core.database.fetch.FetchServer

@Database(entities = [CharacterBase::class, FetchServer::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class MarvelHeroTeamDatabase : RoomDatabase() {

    abstract fun charactersDao(): CharactersDao
    abstract fun fetchServerDao(): FetchDao

    companion object {
        @Volatile
        private var INSTANCE: MarvelHeroTeamDatabase? = null

        fun getInstance(context: Context): MarvelHeroTeamDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarvelHeroTeamDatabase::class.java,
                    "marvel_characters_database"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}