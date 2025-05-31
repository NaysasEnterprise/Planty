package com.example.planty.data.model

import android.os.Build
import com.example.planty.R
import com.example.planty.entity.Plant
import java.time.Duration
import java.time.Instant

object DefaultPlants {
    val plantsList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        listOf(
            Plant(
                species = "Фикус",
                photoUri = R.drawable.plant_pot_33d3pguhqakg,
                nextWateringTime = Instant.now().plus(Duration.ofSeconds(20)),
                wateringInterval = Duration.ofSeconds(20),
                careFeatures = "Не любит сквозняки"
            ),
            Plant(
                species = "Монстера",
                photoUri = R.drawable.monstera_e4nilu90pixs,
                nextWateringTime = Instant.now().plus(Duration.ofDays(3)),
                wateringInterval = Duration.ofDays(3),
                careFeatures = "Любит опрыскивание"
            ),
            Plant(
                species = "Алоэ вера",
                photoUri = R.drawable.aloe_vera_ttz1b3usr2nm_1,
                nextWateringTime = Instant.now().plus(Duration.ofDays(30)),
                wateringInterval = Duration.ofDays(30),
                careFeatures = "Не переносит перелив"
            ),
            Plant(
                species = "Кактус",
                photoUri = R.drawable.cactus_nvsezv3fpngg,
                nextWateringTime = Instant.now().plus(Duration.ofDays(30)),
                wateringInterval = Duration.ofDays(30),
                careFeatures = "Любит солнце"
            ),
            Plant(
                species = "Орхидея",
                photoUri = R.drawable.orchid_hp3hf33kzosx,
                nextWateringTime = Instant.now().plus(Duration.ofDays(7)),
                wateringInterval = Duration.ofDays(7),
                careFeatures = "Не любит застой воды"
            ),
            Plant(
                species = "Драцена",
                photoUri = R.drawable.dracaena_6w91k2bcgweo,
                nextWateringTime = Instant.now().plus(Duration.ofDays(3)),
                wateringInterval = Duration.ofDays(3),
                careFeatures = "Чувствительна к хлору"
            ),
            Plant(
                species = "Фиалка",
                photoUri = R.drawable.flower_pot_qla9sc2bmzin_1,
                nextWateringTime = Instant.now().plus(Duration.ofDays(3)),
                wateringInterval = Duration.ofDays(3),
                careFeatures = "Не любит воду на листьях"
            ),
            Plant(
                species = "Бонсай",
                photoUri = R.drawable.bonsai_iqvufw3n4lrj,
                nextWateringTime = Instant.now().plus(Duration.ofDays(2)),
                wateringInterval = Duration.ofDays(2),
                careFeatures = "Нельзя опрыскивать"
            ),
            Plant(
                species = "Пальма",
                photoUri = R.drawable.palm_x0tezj0087a7,
                nextWateringTime = Instant.now().plus(Duration.ofDays(4)),
                wateringInterval = Duration.ofDays(4),
                careFeatures = "Любит опрыскивание"
            ),
            Plant(
                species = "Папоротник",
                photoUri = R.drawable.fern_0bid3woaq02m,
                nextWateringTime = Instant.now().plus(Duration.ofDays(2)),
                wateringInterval = Duration.ofDays(2),
                careFeatures = "Любит влажность"
            ),
            Plant(
                species = "Гардения",
                photoUri = R.drawable.plant_pot_r5gko3k8u1ms,
                nextWateringTime = Instant.now().plus(Duration.ofDays(3)),
                wateringInterval = Duration.ofDays(3),
                careFeatures = "Капризна, любит кислую почву"
            ),
            Plant(
                species = "Бамбук",
                photoUri = R.drawable.bamboo_92zgzcfhkphb,
                nextWateringTime = Instant.now().plus(Duration.ofDays(7)),
                wateringInterval = Duration.ofDays(7),
                careFeatures = "Можно выращивать в воде"
            ),
            Plant(
                species = "Гибискус",
                photoUri = R.drawable.lily_qpjsqt7j27q2_1,
                nextWateringTime = Instant.now().plus(Duration.ofDays(3)),
                wateringInterval = Duration.ofDays(3),
                careFeatures = "Любит свет"
            ),
            Plant(
                species = "Плющ",
                photoUri = R.drawable.pothos_7j900bxopf9r,
                nextWateringTime = Instant.now().plus(Duration.ofDays(3)),
                wateringInterval = Duration.ofDays(3),
                careFeatures = "Хорошо растёт в тени"
            ),
            Plant(
                species = "Крассула",
                photoUri = R.drawable.pilea_yqiiz9ukmqoh,
                nextWateringTime = Instant.now().plus(Duration.ofDays(20)),
                wateringInterval = Duration.ofDays(20),
                careFeatures = "Любит опрыскивание"
            ),
        )
    } else {
        TODO("VERSION.SDK_INT < O")
    }
}