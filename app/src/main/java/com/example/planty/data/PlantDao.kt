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
    fun insertPlant(plant: Plant)
    @Delete
    fun deletePlant(plant: Plant)
    @Update
    fun updatePlant(plant: Plant)
    @Query("SELECT * FROM plants")
    fun getAllPlants() : Flow<List<Plant>>
    // TODO: Расширить возможности обращения к базе данных 
}