package com.example.planty.presentation

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planty.data.PlantDao
import com.example.planty.data.PlantDatabase
import com.example.planty.data.notifications.NotificationServiceLocal
import com.example.planty.entity.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject


@HiltViewModel
class PlantViewModel @Inject constructor(
    private val plantDao: PlantDao
) : ViewModel() {
    // Все растения
    val plants: StateFlow<List<Plant>> = plantDao.getAllPlants()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    val plantsToWater: StateFlow<List<Plant>> = plants
        .map { plants ->
            plants.filter { it.needsWatering() }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    fun addPlant(plant: Plant) {
        viewModelScope.launch {
            val plantId = plantDao.insertPlant(plant)
        }
    }

    fun addPlantToWater(plant: Plant) {
        viewModelScope.launch {
            plantDao.insertPlant(plant)
        }
    }

    fun deletePlant(plant: Plant) {
        viewModelScope.launch {
            plantDao.deletePlant(plant)
        }
    }


//    fun waterPlant(plantId: Int) {
//        viewModelScope.launch {
//            val plant = plantDao.getPlantById(plantId)
//            plant.let {
//                val updatedPlant = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    it.copy(
//                        nextWateringTime = calculateNextWatering(it)
//                    )
//                } else {
//                    TODO("VERSION.SDK_INT < O")
//                }
//                plantDao.updatePlant(updatedPlant)
//            }
//        }
//    }

    private fun calculateNextWatering(plant: Plant): Instant {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Instant.now().plus(plant.wateringInterval)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    fun checkPlantsForWatering(context: Context) {
        viewModelScope.launch {
            plantsToWater.collect { plants ->
                plants.forEach { plant ->
                    NotificationServiceLocal.showWateringNotification(context, plant)
                }
            }
        }
    }

    fun deleteAllDatabase() {
        viewModelScope.launch {
            plantDao.deleteDatabase()
        }
    }

    fun getPlantById(plantId: Int): Flow<Plant?> {
        return plantDao.getPlantByIdFlow(plantId)
    }
}