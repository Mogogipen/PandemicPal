package com.example.pandemicpal

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide

class PetPageActivity : AppCompatActivity() {

    private lateinit var pet: Pet

    private lateinit var petName: TextView
    private lateinit var backgroundImage: ImageView
    private lateinit var petImage: ImageView
    private lateinit var moreOptionsButton: ImageButton
    private lateinit var feedButton: Button
    private lateinit var walkButton: Button
    private lateinit var playButton: Button
    private lateinit var groomButton: Button
    private lateinit var medsButton: Button
    private lateinit var toiletButton: Button

    private lateinit var healthStatusBarImage: ImageView
    private lateinit var happinessStatusBarImage: ImageView
    private lateinit var hungerStatusBarImage: ImageView

    private lateinit var heathBars : Array<Int>
    private lateinit var hungerBars : Array<Int>
    private lateinit var happinessBars : Array<Int>

    private lateinit var sickImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_page)

        petName = findViewById(R.id.petName)
        backgroundImage = findViewById(R.id.backgroundImage)
        petImage = findViewById(R.id.petImage)
        moreOptionsButton = findViewById(R.id.moreOptionsButton)
        feedButton = findViewById(R.id.feedButton)
        walkButton = findViewById(R.id.walkButton)
        playButton = findViewById(R.id.playButton)
        groomButton = findViewById(R.id.groomButton)
        medsButton = findViewById(R.id.medsButton)
        toiletButton = findViewById(R.id.toiletButton)

        healthStatusBarImage = findViewById(R.id.healthBarImage)
        happinessStatusBarImage = findViewById(R.id.happinessBarImage)
        hungerStatusBarImage = findViewById(R.id.hungerBarImage)

        heathBars = arrayOf(R.drawable.empty, R.drawable.red_one_quarter, R.drawable.red_half, R.drawable.red_three_quarter, R.drawable.red_full)
        hungerBars = arrayOf(R.drawable.empty, R.drawable.blue_one_quarter, R.drawable.blue_half, R.drawable.blue_three_quarters, R.drawable.blue_full)
        happinessBars = arrayOf(R.drawable.empty, R.drawable.yellow_one_quarter, R.drawable.yellow_half, R.drawable.yellow_three_quarter, R.drawable.yellow_full)

        sickImage = findViewById(R.id.sickView)

        val sharedPreferences : SharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE)
        val hasPet = sharedPreferences.getBoolean("hasPet", false)
        if (!hasPet) {

            // Init pet
            val name = intent.getStringExtra("petName").toString()
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
        //update GUI
        updateGUI()

        moreOptionsButton.setOnClickListener{
            startActivity(Intent(this, MoreOptionsActivity::class.java))
        }
        feedButton.setOnClickListener{
            pet.feed()
            petAction(R.drawable.room_kitchen)
        }
        walkButton.setOnClickListener{
            if (pet.walk())
                petAction(R.drawable.room_meadow)
        }
        playButton.setOnClickListener{
            if (pet.play())
                petAction(R.drawable.room_play)
        }
        groomButton.setOnClickListener{
            if (pet.groom())
                petAction(R.drawable.groom)
        }
        medsButton.setOnClickListener{
            if (pet.meds())
                petAction(R.drawable.room_meds)
        }
        toiletButton.setOnClickListener{
            if (pet.toilet())
                petAction(R.drawable.room_bath)
        }

    }

    /**
     * Sets the background for the appropriate action and disables buttons.
     * Then, after a short delay (5s), background is reset and buttons re-enabled.
     */
    private fun petAction(image: Int) {
        backgroundImage.setImageResource(image)
        feedButton.isEnabled = false
        walkButton.isEnabled = false
        playButton.isEnabled = false
        groomButton.isEnabled = false
        medsButton.isEnabled = false
        toiletButton.isEnabled = false
        Handler(Looper.getMainLooper()).postDelayed({
            backgroundImage.setImageResource(R.drawable.room_default)
            feedButton.isEnabled = true
            walkButton.isEnabled = true
            playButton.isEnabled = true
            groomButton.isEnabled = true
            medsButton.isEnabled = true
            toiletButton.isEnabled = true
            updateGUI()
        }, 5000)
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

    /**
     * Update pet values in the GUI (no values are changed in pet)
     */
    private fun updateGUI() {
        // Update and save pet
        pet.update()
        pet.save(this)

        var sick = pet.getSick()
        var toilet = pet.getBathroom()


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

        when {
            (sick && !toilet) -> sickImage.setImageResource(R.drawable.sick)
            (!sick && toilet) -> sickImage.setImageResource(R.drawable.toilet_paper)
            (sick && toilet) -> sickImage.setImageResource(R.drawable.toilet_and_sick)
            (!sick && !toilet) -> sickImage.setImageResource(R.drawable.empty)
        }

        if(pet.getHealth() <= 0 || pet.isDead()){
            // start dead activity
            startActivity(Intent(this, PetDeadActivity::class.java))
        }

        if(pet.getHealth() <= 0 || pet.isDead()){
            // start dead activity
            startActivity(Intent(this, PetDeadActivity::class.java))
        }
    }

    override fun onRestart() {
        super.onRestart()
        updateGUI()
    }
}