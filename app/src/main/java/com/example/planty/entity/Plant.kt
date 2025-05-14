package com.example.planty.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val species: String? = null,       // Вид растения (например, "Какой то недо-Росток")
    val photoUri: String? = null,      // Ссылка на фото (локальный путь или URL)
)