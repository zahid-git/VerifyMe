package com.verifyme.app.data.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.verifyme.app.data.datasource.local.database.entities.ProfileEntities
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {

    @Upsert
    fun insetData(profile: ProfileEntities)

    @Delete
    fun deleteFunction(profile: ProfileEntities)

    @Query("SELECT * FROM profiles")
    fun getUserProfileList(): List<ProfileEntities>

}