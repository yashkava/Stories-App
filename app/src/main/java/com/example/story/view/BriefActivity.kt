package com.example.story.view

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.story.R
import java.io.File
import java.io.FileOutputStream


class BriefActivity : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var brief: TextView
    private lateinit var image: ImageView
    private lateinit var share: ImageView
    private lateinit var storyname:TextView


    @SuppressLint("UseSwitchCompatOrMaterialCode", "MissingInflatedId", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brief)
        title = findViewById(R.id.tvTitle)
        brief = findViewById(R.id.tvBrief)
        image = findViewById(R.id.ivImage)
        share = findViewById(R.id.btnShare)




        title.text = intent.getStringExtra("title")
        brief.text = intent.getStringExtra("brief")
        Glide.with(this).load(intent.getStringExtra("image")).into(image)

        // on below line we are adding on click listener for our generate button.
        share.setOnClickListener {
            Toast.makeText(this, "share", Toast.LENGTH_SHORT).show()

        }
    }
}




