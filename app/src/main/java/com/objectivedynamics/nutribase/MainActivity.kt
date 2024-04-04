package com.objectivedynamics.nutribase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.objectivedynamics.nutribase.models.FoodGroup
import com.objectivedynamics.nutribase.foodgrouplist.FoodGroupsAdapter

class MainActivity : AppCompatActivity() {

    private val adapter = FoodGroupsAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.name)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val list: RecyclerView = findViewById(R.id.list)

        list.layoutManager = LinearLayoutManager(this)

        list.adapter = adapter

        val sampleData = listOf(
            FoodGroup("Food Group 1"),
            FoodGroup("Food Group 2"),
            FoodGroup("Food Group 3"),
            FoodGroup("Food Group 4"),
            FoodGroup("Food Group 5"),
            FoodGroup("Food Group 6"),
            FoodGroup("Food Group 7"),
            FoodGroup("Food Group 8"),
            FoodGroup("Food Group 9"),
            FoodGroup("Food Group 10")
        )
        adapter.submitList(sampleData)
    }
}