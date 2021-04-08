package com.example.pandemicpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class PetSetupActivity : AppCompatActivity() {

    enum class PET {
        DOG,
        CAT,
        RABBIT,
        CHICKEN,
        FISH,
        AXOLOTL,
        DRAGON,
        UNICORN
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_setup)

        var btnDog = findViewById<ImageButton>(R.id.btnDoggo)
        btnDog.setOnClickListener {
            selectPet(PET.DOG)
        }

        var btnCat = findViewById<ImageButton>(R.id.btnCat)
        btnCat.setOnClickListener {
            selectPet(PET.CAT)
        }

        var btnRabbit = findViewById<ImageButton>(R.id.btnRabbit)
        btnRabbit.setOnClickListener {
            selectPet(PET.RABBIT)
        }

        var btnChicken = findViewById<ImageButton>(R.id.btnChicken)
        btnChicken.setOnClickListener {
            selectPet(PET.CHICKEN)
        }

        var btnFish = findViewById<ImageButton>(R.id.btnFish)
        btnFish.setOnClickListener {
            selectPet(PET.FISH)
        }

        var btnAxolotl = findViewById<ImageButton>(R.id.btnAxolotl)
        btnAxolotl.setOnClickListener {
            selectPet(PET.AXOLOTL)
        }

        var btnDragon = findViewById<ImageButton>(R.id.btnDragon)
        btnDragon.setOnClickListener {
            selectPet(PET.DRAGON)
        }

        var btnUnicorn = findViewById<ImageButton>(R.id.btnUnicorn)
        btnUnicorn.setOnClickListener {
            selectPet(PET.UNICORN)
        }

    }

    fun selectPet(pet: PET) {
        // TODO Select pet
        // Start name setup activity
        startActivity(Intent(this, PetSetupNameActivity::class.java))
    }
}