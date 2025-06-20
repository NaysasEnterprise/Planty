package com.example.planty.entity

import android.annotation.SuppressLint
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

    var nextWateringTime: Instant,
    val wateringInterval: Duration,

    val creationDate: Instant = Instant.now(),
    var lastWateringDate: Instant? = null,
    var notes: String? = null,
    var careFeatures: String? = null
) {
    companion object {
        private val dateFormatter by lazy {
            java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy")
        }
    }

    fun needsWatering(): Boolean {
        return nextWateringTime <= Instant.now()
    }

    fun getCreationDateFormatted(): String {
        return creationDate.atZone(java.time.ZoneId.systemDefault())
            .format(dateFormatter)
    }

    fun getWateringIntervalFormatted(): String {
        return when {
            wateringInterval.toDays() > 0 -> "${wateringInterval.toDays()} ${
                pluralDays(
                    wateringInterval.toDays()
                )
            }"

            wateringInterval.toHours() > 0 -> "${wateringInterval.toHours()} ${
                pluralHours(
                    wateringInterval.toHours()
                )
            }"

            else -> "${wateringInterval.toMinutes()} минут"
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
        return Duration.between(Instant.now(), nextWateringTime)
    }
}