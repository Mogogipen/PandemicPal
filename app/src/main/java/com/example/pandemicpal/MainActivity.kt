package com.example.pandemicpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

enum class PET(val image: Int) {
    Dog(R.drawable.doggo),
    Cat(R.drawable.cat),
    Rabbit(R.drawable.rabbit),
    Chicken(R.drawable.chicken),
    Fish(R.drawable.fish),
    Axolotl(R.drawable.axolotl),
    Dragon(R.drawable.dragon),
    Unicorn(R.drawable.unicorn)
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startButton = findViewById<Button>(R.id.startButton)
        // have some kind of code here to check if a pet is present
        var hasPet = false

        startButton.setOnClickListener {

            if (hasPet){
                startActivity(Intent(this,PetPageActivity::class.java))
            }else{
                startActivity(Intent(this,PetSetupActivity::class.java))
            }

        }
    }
}