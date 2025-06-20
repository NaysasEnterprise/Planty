package com.example.planty.data.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.planty.data.notifications.LocalNotificationService
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject
import javax.inject.Singleton

@HiltWorker
class PlantyWorkManager @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    val localNotificationService: LocalNotificationService
) : CoroutineWorker(
    context,
    workerParams
) {
    override suspend fun doWork(): Result {
        val namePlant = workerParams.inputData.getString(WorkerKeys.NAME_PLANT)
        if (namePlant != null) {
            localNotificationService.showNotification(
                namePlant
            )
            return Result.success()

        } else return Result.failure()

    }


}