package com.reyndev.waterme.viewmodel

import android.app.Application
import android.icu.util.TimeUnit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.reyndev.waterme.data.DataSource

class PlantViewModel(application: Application) : ViewModel() {
    val plants = DataSource.plants

    internal fun scheduleReminder(
        duration: Long,
        unit: TimeUnit,
        plantName: String
    ) {
        // TODO: create a Data instance with the plantName passed to it

        // TODO: Generate a OneTimeWorkRequest with the passed in duration, time unit, and data
        //  instance

        // TODO: Enqueue the request as a unique work request
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