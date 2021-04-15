package com.example.pandemicpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide

class PetSetupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_setup)

        var btnDog = findViewById<ImageButton>(R.id.btnDoggo)
        btnDog.setOnClickListener {
            selectPet(PETS.Dog)
        }
        var dogImg = findViewById<ImageView>(R.id.btnDoggo)
        Glide.with(this)
            .load(R.drawable.doggo)
            .into(dogImg)

        var btnCat = findViewById<ImageButton>(R.id.btnCat)
        btnCat.setOnClickListener {
            selectPet(PETS.Cat)
        }
        var catImg = findViewById<ImageView>(R.id.btnCat)
        Glide.with(this)
            .load(R.drawable.cat)
            .into(catImg)

        var btnRabbit = findViewById<ImageButton>(R.id.btnRabbit)
        btnRabbit.setOnClickListener {
            selectPet(PETS.Rabbit)
        }
        var rabbitImg = findViewById<ImageView>(R.id.btnRabbit)
        Glide.with(this)
            .load(R.drawable.rabbit)
            .into(rabbitImg)

        var btnChicken = findViewById<ImageButton>(R.id.btnChicken)
        btnChicken.setOnClickListener {
            selectPet(PETS.Chicken)
        }
        var chickenImg = findViewById<ImageView>(R.id.btnChicken)
        Glide.with(this)
            .load(R.drawable.chicken)
            .into(chickenImg)

        var btnFish = findViewById<ImageButton>(R.id.btnFish)
        btnFish.setOnClickListener {
            selectPet(PETS.Fish)
        }
        var fishImg = findViewById<ImageView>(R.id.btnFish)
        Glide.with(this)
            .load(R.drawable.fish)
            .into(fishImg)

        var btnAxolotl = findViewById<ImageButton>(R.id.btnAxolotl)
        btnAxolotl.setOnClickListener {
            selectPet(PETS.Axolotl)
        }
        var axolotlImg = findViewById<ImageView>(R.id.btnAxolotl)
        Glide.with(this)
            .load(R.drawable.axolotl)
            .into(axolotlImg)

        var btnDragon = findViewById<ImageButton>(R.id.btnDragon)
        btnDragon.setOnClickListener {
            selectPet(PETS.Dragon)
        }
        var dragonImg = findViewById<ImageView>(R.id.btnDragon)
        Glide.with(this)
            .load(R.drawable.dragon)
            .into(dragonImg)

        var btnUnicorn = findViewById<ImageButton>(R.id.btnUnicorn)
        btnUnicorn.setOnClickListener {
            selectPet(PETS.Unicorn)
        }
        var unicornImg = findViewById<ImageView>(R.id.btnUnicorn)
        Glide.with(this)
            .load(R.drawable.unicorn)
            .into(unicornImg)

    }

    fun selectPet(PETS: PETS) {
        var intent = Intent(this, PetSetupNameActivity::class.java)
        intent.putExtra("pet", PETS)
        startActivity(intent)
    }
}