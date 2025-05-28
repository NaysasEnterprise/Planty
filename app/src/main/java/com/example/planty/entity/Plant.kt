package com.example.planty.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    var name: String? = null,
    val species: String? = null,       // Тип растения (например, "Какой то недо-Росток")
    val photoUri: Int? = null,      // Ссылка на фото (локальный путь или URL)
    // TODO: Расширить базу данных(добавить таблицы, настроить связи между ними, расширить таблицы)
)