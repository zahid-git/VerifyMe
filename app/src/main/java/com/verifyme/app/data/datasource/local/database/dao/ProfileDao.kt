package com.verifyme.app.data.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import com.verifyme.app.data.datasource.local.database.entities.ProfileEntities

@Dao
interface ProfileDao {

    @Upsert
    fun insetProfile(profile: ProfileEntities)

    @Upsert
    fun insetProfiles(profile: List<ProfileEntities>)

    @Delete
    fun deleteFunction(profile: ProfileEntities)

    @Query("SELECT * FROM profiles")
    fun getUserProfileList(): List<ProfileEntities>

    @Query("DELETE FROM profiles")
    fun deleteAllProfiles()

}