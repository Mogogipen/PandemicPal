package com.example.pandemicpal

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class PetPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_page)

        var petName = findViewById<TextView>(R.id.petName)
        var moreOptionsButton = findViewById<ImageButton>(R.id.moreOptionsButton)
        var feedButton = findViewById<Button>(R.id.feedButton)
        var walkButton = findViewById<Button>(R.id.walkButton)
        var playButton = findViewById<Button>(R.id.playButton)
        var groomButton = findViewById<Button>(R.id.groomButton)
        var medsButton = findViewById<Button>(R.id.medsButton)
        var toiletButton = findViewById<Button>(R.id.toiletButton)

        moreOptionsButton.setOnClickListener{
            startActivity(Intent(this, MoreOptionsActivity::class.java))
        }
        feedButton.setOnClickListener{ Toast.makeText(this, "Worked", Toast.LENGTH_SHORT).show()}
        walkButton.setOnClickListener{ Toast.makeText(this, "Worked", Toast.LENGTH_SHORT).show()}
        playButton.setOnClickListener{ Toast.makeText(this, "Worked", Toast.LENGTH_SHORT).show()}
        groomButton.setOnClickListener{ Toast.makeText(this, "Worked", Toast.LENGTH_SHORT).show()}
        medsButton.setOnClickListener{ Toast.makeText(this, "Worked", Toast.LENGTH_SHORT).show()}
        toiletButton.setOnClickListener{ Toast.makeText(this, "Worked", Toast.LENGTH_SHORT).show()}

    }


}