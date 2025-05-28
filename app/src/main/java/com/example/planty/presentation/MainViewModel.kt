package com.example.planty.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planty.data.PlantDao
import com.example.planty.data.PlantyRepository
import com.example.planty.data.model.DefaultPlants
import com.example.planty.entity.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlantViewModel @Inject constructor(
    private val plantDao: PlantDao
) : ViewModel() {

    val plants: StateFlow<List<Plant>> = plantDao.getAllPlants()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000), // 5 секунд таймаут при отсутствии подписчиков
            initialValue = DefaultPlants.plantsList
        )

    fun addPlant(plant: Plant) {
        viewModelScope.launch {
            plantDao.insertPlant(plant)
        }
    }

    fun deleteAllDatabase() {
        viewModelScope.launch {
            plantDao.deleteDatabase()
        }
    }
}