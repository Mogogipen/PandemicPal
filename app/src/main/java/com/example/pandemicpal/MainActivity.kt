package com.example.pandemicpal

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

enum class PETS(val image: Int, val healthMod : Double, val hungerMod : Double, val happinessMod : Double) {
    Dog(R.drawable.doggo, 1.0, 1.0, 1.0),
    Cat(R.drawable.cat, 0.75, 1.0, 1.0),
    Rabbit(R.drawable.rabbit, 1.0, 1.0, .75),
    Chicken(R.drawable.chicken, 1.0, 1.25, 1.0),
    Fish(R.drawable.fish, 1.5, 0.5, 1.0),
    Axolotl(R.drawable.axolotl, 1.5, 1.5, 1.0),
    Dragon(R.drawable.dragon, 2.0, 1.0, 0.25),
    Unicorn(R.drawable.unicorn, 0.5, 0.5, 0.5)
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var startButton = findViewById<Button>(R.id.startButton)
        // have some kind of code here to check if a pet is present
        val sharedPreferences : SharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE)
        sharedPreferences.edit().remove("hasPet").commit()
        val hasPet : Boolean? = sharedPreferences.getBoolean("hasPet", false)

        startButton.setOnClickListener {

            if (hasPet == true){
                startActivity(Intent(this,PetPageActivity::class.java))
            }else{
                startActivity(Intent(this,PetSetupActivity::class.java))
                // create an editor for sharedPreferences
//                val editor: SharedPreferences.Editor = sharedPreferences.edit()
//                // save data
//                editor.apply{
//                    putBoolean("hasPet", true)
//                }.apply()
            }

        }
    }
}