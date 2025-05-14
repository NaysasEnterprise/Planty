package com.example.planty.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.planty.entity.Plant

@Database(
    entities = [Plant::class],
    version = 1
)
abstract class PlantDatabase: RoomDatabase() {
    abstract val dao: PlantDao
}