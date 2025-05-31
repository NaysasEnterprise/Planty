package com.example.planty.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.planty.entity.Plant
import com.example.planty.garbage.Converters

@Database(
    entities = [Plant::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PlantDatabase: RoomDatabase() {
    abstract val dao: PlantDao

    companion object {
        @Volatile
        private var INSTANCE: PlantDatabase? = null

        fun getDatabase(context: Context): PlantDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantDatabase::class.java,
                    "plant_database"
                )
                    .fallbackToDestructiveMigration() // Удаляет и пересоздает БД при миграциях
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
