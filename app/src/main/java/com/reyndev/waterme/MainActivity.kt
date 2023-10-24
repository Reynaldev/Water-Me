package com.reyndev.waterme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.reyndev.waterme.adapter.PlantAdapter
import com.reyndev.waterme.adapter.PlantListener
import com.reyndev.waterme.ui.ReminderDialogFragment
import com.reyndev.waterme.viewmodel.PlantViewModel
import com.reyndev.waterme.viewmodel.PlantViewModelFactory

class MainActivity : AppCompatActivity() {
    private val viewModel: PlantViewModel by viewModels {
        PlantViewModelFactory(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PlantAdapter(PlantListener { plant ->
            val dialog = ReminderDialogFragment(plant.name)
            dialog.show(supportFragmentManager, "WaterReminderDialogFragment")
            true
        })
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        val data = viewModel.plants
        adapter.submitList(data)
    }
}