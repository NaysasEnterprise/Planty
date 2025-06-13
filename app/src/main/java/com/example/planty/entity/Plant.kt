package com.example.planty.entity

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.time.Duration

@SuppressLint("NewApi")
@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    var name: String? = null,
    val species: String? = null,
    val photoUri: Int? = null,

    var nextWateringTime: Instant, // Время следующего полива
    val wateringInterval: Duration, // Интервал полива

    val creationDate: Instant = Instant.now(), // Автоматически при создании
    var lastWateringDate: Instant? = null,
    var notes: String? = null,
    var careFeatures: String? = null
) {
    // Проверка, нужно ли поливать сейчас
    companion object {
        private val dateFormatter by lazy {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy")
            } else {
                null
            }
        }
    }
    fun needsWatering(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nextWateringTime <= Instant.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    // Форматированная дата создания
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCreationDateFormatted(): String {
        return creationDate.atZone(java.time.ZoneId.systemDefault())
            .format(dateFormatter)
    }

    fun getWateringIntervalFormatted(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            when {
                wateringInterval.toDays() > 0 -> "${wateringInterval.toDays()} ${pluralDays(wateringInterval.toDays())}"
                wateringInterval.toHours() > 0 -> "${wateringInterval.toHours()} ${pluralHours(wateringInterval.toHours())}"
                else -> "${wateringInterval.toMinutes()} минут"
            }
        } else {
            throw UnsupportedOperationException("Requires API 26+")
        }
    }
    private fun pluralDays(days: Long): String {
        return when (days % 10) {
            1L -> "день"
            in 2..4 -> "дня"
            else -> "дней"
        }
    }
    private fun pluralHours(hours: Long): String {
        return when (hours % 10) {
            1L -> "час"
            in 2..4 -> "часа"
            else -> "часов"
        }
    }

    fun timeUntilWatering(): Duration {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Duration.between(Instant.now(), nextWateringTime)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }
}