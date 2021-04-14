package com.example.pandemicpal

import android.content.Intent
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

        var nameField = findViewById<EditText>(R.id.editTextPetName)
        var btnSelect = findViewById<Button>(R.id.btnSelectName)
        var petImage = findViewById<ImageView>(R.id.imageView)
        Glide.with(this)
            .load(R.drawable.doggo)
            .into(petImage)
        btnSelect.setOnClickListener {
            if (nameField.text.toString().length == 0 ) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            } else if(nameField.text.toString().length > 10) {
                Toast.makeText(this, "Name is too long", Toast.LENGTH_SHORT).show()
            }else{
                // TODO Set pet name
                // Start pet activity
                startActivity(Intent(this, PetPageActivity::class.java))
            }
        }

    }
}