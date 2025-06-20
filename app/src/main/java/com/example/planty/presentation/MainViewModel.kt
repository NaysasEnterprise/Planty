package com.example.planty.presentation

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.planty.R
import com.example.planty.application.App
import com.example.planty.data.PlantDao
import com.example.planty.data.notifications.LocalNotificationService
import com.example.planty.data.work.PlantyWorkManager
import com.example.planty.data.work.WorkerKeys
import com.example.planty.entity.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import java.time.Duration


@HiltViewModel
class PlantViewModel @Inject constructor(
    private val plantDao: PlantDao,
) : ViewModel() {
    @Inject
    lateinit var workManager: WorkManager
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
            val delay = Duration.ofSeconds(3)
            val request = OneTimeWorkRequestBuilder<PlantyWorkManager>()
                .setInputData(
                    workDataOf(
                        WorkerKeys.NAME_PLANT to plant.name
                    )
                )
                .setInitialDelay(delay)
                .build()
            workManager.enqueue(request)
            plantDao.insertPlant(plant)
        }
    }

    fun deletePlant(plant: Plant) {
        viewModelScope.launch {
            plantDao.deletePlant(plant)
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