package com.example.pandemicpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startButton = findViewById<Button>(R.id.startButton)
        // have some kind of code here to check if a pet is present
        var hasPet = true

        startButton.setOnClickListener {

            if (hasPet){
                startActivity(Intent(this,PetPage::class.java))
            }else{
                // startActivity(Intent(this,SetupNewPet::class.java))
            }

        }
    }
}