package com.reyndev.waterme.ui

import android.app.AlertDialog
import android.app.Dialog
import android.icu.util.TimeUnit
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.reyndev.waterme.R
import com.reyndev.waterme.viewmodel.PlantViewModel
import com.reyndev.waterme.viewmodel.PlantViewModelFactory

class ReminderDialogFragment(private val plantName: String) : DialogFragment() {
    private val viewModel: PlantViewModel by viewModels {
        PlantViewModelFactory(requireActivity().application)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
                .setTitle(R.string.water_reminder)
                .setItems(R.array.water_schedule_array) { _, position ->
                    when (position) {
                        0 ->
                            viewModel
                                .scheduleReminder(5, TimeUnit.SECOND, plantName)
                        1 ->
                            viewModel
                                .scheduleReminder(1, TimeUnit.DAY, plantName)
                        2 ->
                            viewModel
                                .scheduleReminder(7, TimeUnit.DAY, plantName)
                        3 ->
                            viewModel
                                .scheduleReminder(30, TimeUnit.DAY, plantName)
                    }
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}