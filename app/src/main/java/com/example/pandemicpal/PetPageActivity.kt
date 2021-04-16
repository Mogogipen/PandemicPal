package com.example.pandemicpal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import com.bumptech.glide.Glide

@SuppressWarnings
class PetPageActivity : AppCompatActivity() {

    private lateinit var pet: Pet

    private lateinit var healthStatusBarImage : ImageView
    private lateinit var happinessStatusBarImage : ImageView
    private lateinit var hungerStatusBarImage : ImageView

    private lateinit var heathBars : Array<Int>
    private lateinit var hungerBars : Array<Int>
    private lateinit var happinessBars : Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_page)

        var petName = findViewById<TextView>(R.id.petName)
        var backgroundImage = findViewById<ImageView>(R.id.backgroundImage)
        var petImage = findViewById<ImageView>(R.id.petImage)
        var moreOptionsButton = findViewById<ImageButton>(R.id.moreOptionsButton)
        var feedButton = findViewById<Button>(R.id.feedButton)
        var walkButton = findViewById<Button>(R.id.walkButton)
        var playButton = findViewById<Button>(R.id.playButton)
        var groomButton = findViewById<Button>(R.id.groomButton)
        var medsButton = findViewById<Button>(R.id.medsButton)
        var toiletButton = findViewById<Button>(R.id.toiletButton)


        healthStatusBarImage = findViewById<ImageView>(R.id.healthBarImage)
        happinessStatusBarImage = findViewById<ImageView>(R.id.happinessBarImage)
        hungerStatusBarImage = findViewById<ImageView>(R.id.hungerBarImage)


        heathBars = arrayOf(R.drawable.empty, R.drawable.red_one_quarter, R.drawable.red_half, R.drawable.red_three_quarter, R.drawable.red_full)
        hungerBars = arrayOf(R.drawable.empty, R.drawable.blue_one_quarter, R.drawable.blue_half, R.drawable.blue_three_quarters, R.drawable.blue_full)
        happinessBars = arrayOf(R.drawable.empty, R.drawable.yellow_one_quarter, R.drawable.yellow_half, R.drawable.yellow_three_quarter, R.drawable.yellow_full)

        val sharedPreferences : SharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE)

        var hasPet = sharedPreferences.getBoolean("hasPet", false)
        if (!hasPet) {

            // Init pet
            var name = intent.getStringExtra("petName").toString()
            var type = intent.getSerializableExtra("pet")
            if (type == null) {
                Toast.makeText(this, "Pet creation error", Toast.LENGTH_SHORT).show()
                finish()
            }
            type = type as PETS
            pet = Pet(name, type)

            // Load values and save pet
            loadValues(petName, petImage)
            savePet()

        } else {
            pet = Pet(this)
            loadValues(petName, petImage)
        }

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

    private fun savePet() {
        pet.save(this)
        val sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply{
            putBoolean("hasPet", true)
        }.apply()
    }

    private fun loadValues(tv :TextView, iv: ImageView) {
        tv.setText(pet.getName())
        Glide.with(this)
                .load(pet.getType().image)
                .into(iv)
    }

    private fun updatePet() {
        // TO DO:
        // make a switch statement that
        when(pet.getHealth()){
            in 1..25 -> healthStatusBarImage.setImageResource(heathBars[1])
            in 26..50 -> healthStatusBarImage.setImageResource(heathBars[2])
            in 51..75 -> healthStatusBarImage.setImageResource(heathBars[3])
            in 76..100 -> healthStatusBarImage.setImageResource(heathBars[4])
            else -> healthStatusBarImage.setImageResource(heathBars[0])
        }

        when(pet.getHunger()){
            in 1..25 -> hungerStatusBarImage.setImageResource(hungerBars[1])
            in 26..50 -> hungerStatusBarImage.setImageResource(hungerBars[2])
            in 51..75 -> hungerStatusBarImage.setImageResource(hungerBars[3])
            in 76..100 -> hungerStatusBarImage.setImageResource(hungerBars[4])
            else -> hungerStatusBarImage.setImageResource(hungerBars[0])
        }

        when(pet.getHappiness()){
            in 1..25 ->happinessStatusBarImage.setImageResource(happinessBars[1])
            in 26..50 -> happinessStatusBarImage.setImageResource(happinessBars[2])
            in 51..75 -> happinessStatusBarImage.setImageResource(happinessBars[3])
            in 76..100 -> happinessStatusBarImage.setImageResource(happinessBars[4])
            else -> happinessStatusBarImage.setImageResource(happinessBars[0])
        }

        if(pet.getHealth() <= 0){
            // start dead activity

        }
    }

}