package com.example.pandemicpal

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

enum class PETS(val image: Int) {
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