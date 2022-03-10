package com.bryo.marvel.myheroteam.core.database.characters

import androidx.room.ColumnInfo
import androidx.room.ColumnInfo.BLOB
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class CharacterBase(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val imagePath: String,
    @ColumnInfo(name = "hired") val hired: Boolean
)
