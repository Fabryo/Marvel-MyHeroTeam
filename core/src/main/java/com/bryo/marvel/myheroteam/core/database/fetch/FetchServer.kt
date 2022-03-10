package com.bryo.marvel.myheroteam.core.database.fetch

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "fetch_server_table")
data class FetchServer(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "date") val date: Date
)