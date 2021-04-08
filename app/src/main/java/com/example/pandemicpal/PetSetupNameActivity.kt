package com.example.pandemicpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast

class PetSetupNameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_setup_name)

        var nameField = findViewById<EditText>(R.id.editTextPetName)
        var btnSelect = findViewById<Button>(R.id.btnSelectName)
        btnSelect.setOnClickListener {
            if (nameField.text.toString().length == 0) {
                Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
            } else {
                // TODO Set pet name
                // Start pet activity
                startActivity(Intent(this, PetPageActivity::class.java))
            }
        }

    }
}