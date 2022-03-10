package com.bryo.marvel.myheroteam.core.database.fetch

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import java.util.*

@Dao
interface FetchDao {

    @Query("SELECT * FROM fetch_server_table")
    fun getLastFetchDate(): FetchServer?

    @Insert(onConflict = REPLACE)
    fun insertLastFetchDate(fetchInfo: FetchServer)
}