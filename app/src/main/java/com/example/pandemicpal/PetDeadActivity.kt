package com.example.pandemicpal

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class PetDeadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_dead)


        val deadButton = findViewById<Button>(R.id.petDeadButton)
        var petNameLabel = findViewById<TextView>(R.id.petNameLabel)

        val sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE)
        var petName = sharedPreferences.getString("name", "pet")
        petNameLabel.setText(petName)

        deadButton.setOnClickListener {

            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{
                putBoolean("hasPet", false)
            }.apply()
                startActivity(Intent(this,MainActivity::class.java))
        }


    }
}