package com.example.pandemicpal

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

class PetSetupNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_setup_name)

        // Load data from intent
        val type = intent.getSerializableExtra("pet") as PETS

        // Get all views
        val nameField = findViewById<EditText>(R.id.editTextPetName)
        val btnSelect = findViewById<Button>(R.id.btnSelectName)
        val petImage = findViewById<ImageView>(R.id.imageView)

        // Set image to animated pet gif
        Glide.with(this)
            .load(type.image)
            .into(petImage)

        // Set onClickListener to name the pet, pass data to intent, and start PetPageActivity
        btnSelect.setOnClickListener {
            // Check for valid name
            val petName = nameField.text.toString()
            if (petName.isEmpty()) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            } else if(petName.length > 10) {
                Toast.makeText(this, "Name is too long", Toast.LENGTH_SHORT).show()
            }else{
                selectPetName(type, petName)
            }
        }

    }

    private fun selectPetName(pet: PETS, petName: String) {

        // If name is valid, pass data and load the next activity
        val intent = Intent(this, PetPageActivity::class.java)
        intent.putExtra("pet", pet)
        intent.putExtra("petName", petName)
        finish()
        startActivity(intent)
    }
}