package com.example.planty.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.planty.entity.Plant
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Insert
    suspend fun insertPlant(plant: Plant)
    @Delete
    suspend fun deletePlant(plant: Plant)
    @Update
    suspend fun updatePlant(plant: Plant)
    @Query("SELECT * FROM plants")
    fun getAllPlants() : Flow<List<Plant>>
    // TODO: Расширить возможности обращения к базе данных
    @Query("DELETE FROM plants")
    suspend fun deleteDatabase()
}