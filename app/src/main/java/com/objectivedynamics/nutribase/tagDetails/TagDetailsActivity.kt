package com.objectivedynamics.nutribase.tagDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.objectivedynamics.nutribase.R
import com.objectivedynamics.nutribase.models.Tag

class TagDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tag_details)

        val name = intent.getStringExtra(KEY_NAME)

        val nameText: TextView = findViewById(R.id.tagName)

        nameText.text = name

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object{

        const val KEY_NAME = "key_name"
        fun startActivity(context: Context, tag: Tag){
            val intent = Intent(context, TagDetailsActivity::class.java)
            intent.putExtra(KEY_NAME, tag.name)
            context.startActivity(intent)
        }
    }
}