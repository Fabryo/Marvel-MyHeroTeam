package com.bryo.marvel.myheroteam.core.database.characters

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface CharactersDao {

    @Query("SELECT * FROM character_table")
    fun getAllCharacters(): List<CharacterBase>?

    @Insert(onConflict = REPLACE)
    fun insertAllCharacters(characters: List<CharacterBase>)

    @Query("SELECT * FROM character_table WHERE id = :characterId")
    fun getCharacter(characterId: Int): CharacterBase?

    @Insert(onConflict = REPLACE)
    fun insert(character: CharacterBase)

    @Delete
    fun delete(character: CharacterBase)

    @Query("DELETE FROM character_table")
    fun deleteAllCharacters()

    @Query("DELETE FROM character_table WHERE id = :characterId")
    fun deleteCharacter(characterId: Int)
}