package com.objectivedynamics.nutribase.tagDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.objectivedynamics.nutribase.R
import com.objectivedynamics.nutribase.api.FileResult
import com.objectivedynamics.nutribase.api.createGitHubApiFileService
import com.objectivedynamics.nutribase.models.NutritionData
import com.objectivedynamics.nutribase.models.Tag
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Base64

class TagDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tag_details)

        val name = intent.getStringExtra(KEY_TAG_NAME)

        val nameText: TextView = findViewById(R.id.tagName)

        nameText.text = name

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //TODO - speculatively getting the nutritionData again here to see if we can use the food data
        val fileService = createGitHubApiFileService()
        fileService.getFile("objectivedynamics42","nutribase-data", "nutribase-v1.0.json").enqueue(object :
            Callback<FileResult> {
            //MikeB This is the getFile implementation from the GitHubFileApiService interface
            override fun onFailure(p0: Call<FileResult>, p1: Throwable) {
                //TODO handle failure
            }
            override fun onResponse(call: Call<FileResult>, response: Response<FileResult>) {
                val content = response.body()?.content

                val nutritionData = getNutritionData(content)

//                adapter.submitList(nutritionData?.tags)
            }
        })
    }

    companion object{

        const val KEY_TAG_NAME = "key_tag_name"
        fun startActivity(context: Context, tag: Tag){
            val intent = Intent(context, TagDetailsActivity::class.java)
            //TODO is this where we would also pass the top(1) food name through to the new activity?
            intent.putExtra(KEY_TAG_NAME, tag.name)
            context.startActivity(intent)
        }
    }

    //TODO this is duplicated in other iles. We need to move to a common place
    private fun getNutritionData(content: String?): NutritionData? {
        val nutritionJson: String = decodeJsonData(content)

        val gson = Gson()
        return gson.fromJson(nutritionJson, NutritionData::class.java)
    }

    //TODO this is duplicated in other files. We need to move to a common place
    private fun decodeJsonData(content: String?): String {
        val contentNoLineBreaks = content?.replace("\n", "")

        val decoder = Base64.getDecoder()
        val decodedBytes: ByteArray = decoder.decode(contentNoLineBreaks)

        return decodedBytes.toString(Charsets.UTF_8)
    }
}