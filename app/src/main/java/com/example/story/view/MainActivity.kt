package com.example.story.view


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.story.R
import com.example.story.adapter.StoryAdapter
import com.example.story.model.StoryType


class MainActivity : AppCompatActivity() {
    private var pressedTime: Long = 0
    @SuppressLint("SuspiciousIndentation", "UseSwitchCompatOrMaterialCode", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val switch = findViewById<SwitchCompat>(R.id.switchmain)
        val sharedPreferences = getSharedPreferences("Mode", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val nightMode = sharedPreferences.getBoolean("night",false)
        if (nightMode){
            switch.isChecked = true
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        switch.setOnCheckedChangeListener { buttonView, isChecked ->

            if (!isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                editor.putBoolean("night",false)
                editor.apply()

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                editor.putBoolean("night",true)
                editor.apply()

            }
        }
        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.rvMainSc)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = GridLayoutManager(this,2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<StoryType>()

        // This loop will create 20 Views containing
        // the image with the count of view

        data.add(StoryType(R.drawable.animals,"Animal"))
        data.add(StoryType(R.drawable.funny, "Funny"))
        data.add(StoryType(R.drawable.bedtime, "Bedtime"))
        data.add(StoryType(R.drawable.aladdin, "Aladdin"))
        data.add(StoryType(R.drawable.akbar, "Akbar-Birbal"))
        data.add(StoryType(R.drawable.love, "Love"))
        data.add(StoryType(R.drawable.friend, "Friendship"))
        data.add(StoryType(R.drawable.fairy, "Fairy-Tails"))
        data.add(StoryType(R.drawable.inspi, "Inspirational"))
        data.add(StoryType(R.drawable.motivation, "Motivational"))



        // This will pass the ArrayList to our Adapter
        val adapter = StoryAdapter(this, data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
        adapter.setOnItemClickListener(object : StoryAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                val i = Intent(this@MainActivity, AnimalActivity::class.java)
                i.putExtra("data",data[position].storyName)
                Log.d("000cat activity",data[position].storyName)
                startActivity(i)

            }

        })
        Log.d("storis is opening",data.toString())

    }

    override fun onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            finishAffinity()
        } else {
            Toast.makeText(baseContext, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
        pressedTime = System.currentTimeMillis()
    }

}
