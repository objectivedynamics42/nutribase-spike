package com.objectivedynamics.nutribase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.objectivedynamics.nutribase.api.FileResult
import com.objectivedynamics.nutribase.api.createGitHubApiFileService
import com.objectivedynamics.nutribase.taglist.TagAdapter
import com.objectivedynamics.nutribase.models.NutritionData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Base64


class MainActivity : AppCompatActivity() {

    private val adapter = TagAdapter()

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

        val fileService = createGitHubApiFileService()
        fileService.getFile("objectivedynamics42","nutribase-data", "v1.0.json").enqueue(object :  Callback<FileResult>{
            override fun onFailure(p0: Call<FileResult>, p1: Throwable) {
                //TODO handle failure
            }
            override fun onResponse(call: Call<FileResult>, response: Response<FileResult>) {
                val content = response.body()?.content

                val nutritionData = getNutritionData(content)

                adapter.submitList(nutritionData?.tags)
            }
        })
    }

    private fun getNutritionData(content: String?): NutritionData? {
        val jsonString: String = getNutritionDataJson(content)

        val gson = Gson()
        return gson.fromJson(jsonString, NutritionData::class.java)
    }

    private fun getNutritionDataJson(content: String?): String {
        val contentNoLineBreaks = content?.replace("\n", "")

        val decoder = Base64.getDecoder()
        val decodedBytes: ByteArray = decoder.decode(contentNoLineBreaks)

        return decodedBytes.toString(Charsets.UTF_8)
    }
}