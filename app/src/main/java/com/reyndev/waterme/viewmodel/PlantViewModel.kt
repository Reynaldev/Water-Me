package com.reyndev.waterme.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkManagerInitializer
import androidx.work.WorkRequest
import androidx.work.Worker
import androidx.work.impl.WorkManagerImpl
import com.reyndev.waterme.data.DataSource
import com.reyndev.waterme.worker.WaterReminderWorker
import java.util.concurrent.TimeUnit

class PlantViewModel(private val application: Application) : ViewModel() {
    val plants = DataSource.plants

    @SuppressLint("RestrictedApi")
    internal fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        plantName: String
    ) {
        // TODO: create a Data instance with the plantName passed to it
        val data = Data.Builder()
            .put(WaterReminderWorker.nameKey, plantName)
            .build()

        // TODO: Generate a OneTimeWorkRequest with the passed in duration, time unit, and data
        //  instance
        val reminder = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInitialDelay(duration, unit)
            .setInputData(data)
            .build()

        // TODO: Enqueue the request as a unique work request
        WorkManager.getInstance(application)
            .enqueueUniqueWork(plantName, ExistingWorkPolicy.REPLACE, reminder)
    }
}

class PlantViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PlantViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            PlantViewModel(application) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}