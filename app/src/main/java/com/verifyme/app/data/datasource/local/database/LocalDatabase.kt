package com.verifyme.app.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.verifyme.app.data.datasource.local.database.dao.ProfileDao
import com.verifyme.app.data.datasource.local.database.entities.ProfileEntities

@Database(entities = [ProfileEntities::class], version = 1)
abstract class LocalDatabase: RoomDatabase() {
    abstract val dao: ProfileDao
}