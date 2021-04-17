package com.example.pandemicpal

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MoreOptionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_options)

        val removePetButton = findViewById<Button>(R.id.deleteButton)

        removePetButton.setOnClickListener {

            val sharedPreferences : SharedPreferences = getSharedPreferences("sharedPreferences", MODE_PRIVATE)
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Delete Pet")
            alert.setMessage("Are you sure you want to do this? You cannot retrieve your pet afterwards.")
            alert.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                sharedPreferences.edit().remove("hasPet").commit()
                startActivity(Intent(this, MainActivity::class.java))
            }
            alert.setNegativeButton("No"){ dialogInterface: DialogInterface, i: Int ->}
            alert.show()

        }
    }
}