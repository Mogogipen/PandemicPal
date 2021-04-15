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

        var pet = intent.getSerializableExtra("pet") as PETS

        var nameField = findViewById<EditText>(R.id.editTextPetName)
        var btnSelect = findViewById<Button>(R.id.btnSelectName)
        var petImage = findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load(pet.image)
            .into(petImage)

        btnSelect.setOnClickListener {
            if (nameField.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            } else if(nameField.text.toString().length > 10) {
                Toast.makeText(this, "Name is too long", Toast.LENGTH_SHORT).show()
            }else{
                var intent = Intent(this, PetPageActivity::class.java)
                intent.putExtra("pet", pet)
                intent.putExtra("petName", nameField.text.toString())
                startActivity(intent)
                val sharedPreferences : SharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE)
                //create an editor for sharedPreferences
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                // save data
                editor.apply{
                    putBoolean("hasPet", true)
                }.apply()
            }
        }

    }
}