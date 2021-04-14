package com.example.pandemicpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*


class PetPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_page)

        var petName = findViewById<TextView>(R.id.petName)
        var backgroundImage = findViewById<ImageView>(R.id.backgroundImage)
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
        feedButton.setOnClickListener{
            backgroundImage.setImageResource(R.drawable.room_kitchen)
            feedButton.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
               backgroundImage.setImageResource(R.drawable.room_default)
                feedButton.isEnabled = true
            }, 2000)
        }
        walkButton.setOnClickListener{
            backgroundImage.setImageResource(R.drawable.room_meadow)
            walkButton.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
                backgroundImage.setImageResource(R.drawable.room_default)
                walkButton.isEnabled = true
            }, 2000)
        }
        playButton.setOnClickListener{
            backgroundImage.setImageResource(R.drawable.room_default)
        }
        groomButton.setOnClickListener{
            backgroundImage.setImageResource(R.drawable.room_default)
        }
        medsButton.setOnClickListener{
            backgroundImage.setImageResource(R.drawable.room_default)
        }
        toiletButton.setOnClickListener{
            backgroundImage.setImageResource(R.drawable.room_bath)
            toiletButton.isEnabled = false
            Handler(Looper.getMainLooper()).postDelayed({
                backgroundImage.setImageResource(R.drawable.room_default)
                toiletButton.isEnabled = true
            }, 2000)
        }

    }


}