package com.example.story.view


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.story.model.AnimalType
import com.example.story.R
import com.example.story.adapter.AnimalAdapter

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class AnimalActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var arrayList: ArrayList<AnimalType>
    private lateinit var database:DatabaseReference
    private lateinit var adapter:AnimalAdapter
    private lateinit var textView: TextView
    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal)
       textView = findViewById(R.id.tvCategory)


        recyclerView = findViewById(R.id.rvAnimal)
        recyclerView.layoutManager = LinearLayoutManager(this)
        arrayList = arrayListOf()
        database= FirebaseDatabase.getInstance().getReference("items")
        var query: Query = database.orderByKey()
        val msg = intent.getStringExtra("data")
       textView.text = msg
        var summerItems = arrayList.filter { it.category == msg?.trim() }
        query = database.orderByChild("itemCat")
        var fd = summerItems



        query.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (dataSnapshot in snapshot.children){
                        val user = dataSnapshot.getValue(AnimalType::class.java)
                        if (!summerItems.contains(user)){
                            arrayList.add(user!!)
                        }
                    }
                    summerItems = arrayList.filter { it.category == msg?.trim() }
                    if (msg != null) {
                        Log.e("000Intent Data0000", msg.toString())
                    }
                    Log.d("000002", summerItems.toString())

                    adapter = AnimalAdapter(summerItems as ArrayList<AnimalType>, this@AnimalActivity)
                    recyclerView.adapter = adapter
                }
                adapter.setonItemClickListener(object : AnimalAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {

                        var i = Intent(applicationContext, BriefActivity::class.java)

                        i.putExtra("title", summerItems[position].title.toString())
                        i.putExtra("brief", summerItems[position].brief.toString())
                        i.putExtra("image", summerItems[position].image.toString())

                        startActivity(i)



                    }
                })
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AnimalActivity, error.toString(), Toast.LENGTH_SHORT).show()
                Log.d("can00", error.toString())
            }
        })

    }
}