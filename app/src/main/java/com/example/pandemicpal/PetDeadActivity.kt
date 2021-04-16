package com.example.pandemicpal

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PetDeadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_dead)


        var deadButton = findViewById<Button>(R.id.petDeadButton)

        deadButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{
                putBoolean("hasPet", false)
            }.apply()
                startActivity(Intent(this,MainActivity::class.java))
            }


    }
}