package com.example.planty.garbage

import android.os.Build
import androidx.room.TypeConverter
import java.time.Duration
import java.time.Instant

class Converters {

    // Instant <-> Long
    @TypeConverter
    fun fromInstant(instant: Instant?): Long? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            instant?.toEpochMilli()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    @TypeConverter
    fun toInstant(millis: Long?): Instant? {
        return millis?.let { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Instant.ofEpochMilli(it)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        }
    }

    // Duration <-> Long (in millis)
    @TypeConverter
    fun fromDuration(duration: Duration?): Long? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            duration?.toMillis()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }

    @TypeConverter
    fun toDuration(millis: Long?): Duration? {
        return millis?.let { if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Duration.ofMillis(it)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        }
    }
}