package com.example.pandemicpal

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import java.util.*
import kotlin.math.pow

class Pet (
    private var name:String, private var type:PETS
) {
    private var health = 100
    private var hunger = 0
    private var happiness = 100
    private var bathroom = false
    private var dead = false
    private var sick = false

    /**
     * Use this constructor to load the pet from the device
     */
    constructor(context: Context) : this("", PETS.Dog) {
        // Load values from device
        val sharedPreferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        name = sharedPreferences.getString("name", "<test>")!!
        type = PETS.valueOf(sharedPreferences.getString("type", "Dog")!!)
        health = sharedPreferences.getInt("health", 0)
        hunger = sharedPreferences.getInt("hunger", 100)
        happiness = sharedPreferences.getInt("happiness", 0)
        sick = sharedPreferences.getBoolean("sick", true)
        bathroom = sharedPreferences.getBoolean("bathroom", true)
        dead = sharedPreferences.getBoolean("dead", true)

        // Calculate new values from time passed
        //  2 hrs = 7x10^6 milliseconds
        val time = sharedPreferences.getLong("timeStamp", Long.MIN_VALUE)
        val timePassed = System.currentTimeMillis() - time
        var timeInterval : Long = (7.2 * 10.0.pow(6.0)).toLong()
        var loopCount = timePassed/timeInterval

        // Simulate passage of time
        if (timePassed >= timeInterval*3) {
            bathroom = true
        } else if (timePassed >= timeInterval/4) {
            var rand = Random(timePassed)
            bathroom = rand.nextBoolean()
        }
        for (i in 1..loopCount) {
            increaseHunger(10)
            decreaseHappiness(10)
            if (hunger == 100 || happiness == 0) {
                decreaseHealth(10)
            }
        }

    }

    fun getName() : String {return this.name}
    fun setName(name:String) {this.name = name}

    fun getType() : PETS {return this.type}
    fun setType(type:PETS) {this.type = type}

    fun getHealth() : Int {return this.health}
    fun increaseHealth(healthMod:Int) {
        if(healthMod + this.health > 100)
            this.health = 100
        else
            this.health += healthMod
    }
    fun decreaseHealth(healthMod:Int) {
        if((healthMod * type.healthMod).toInt() >= this.health)
            dead = true
        else
            this.health -= (healthMod*type.healthMod).toInt()
    }

    fun getHunger() : Int {return this.hunger}
    fun increaseHunger(hungerMod:Int) {
        if((hungerMod * type.hungerMod).toInt() + this.hunger > 100)
            this.hunger = 100
        else
            this.hunger += hungerMod
    }
    fun decreaseHunger(hungerMod:Int) {
        if(hungerMod >= this.hunger)
            this.hunger = 0
        else
            this.hunger -= hungerMod
    }

    fun getHappiness() : Int {return this.happiness}
    fun increaseHappiness(happinessMod:Int) {
        if(happinessMod + this.happiness > 100)
            this.happiness = 100
        else
            this.happiness += happinessMod
    }
    fun decreaseHappiness(happinessMod:Int) {
        if((happinessMod * type.happinessMod).toInt() >= this.happiness)
            this.happiness = 0
        else
            this.happiness -= (happinessMod * type.happinessMod).toInt()
    }

    fun getBathroom() : Boolean {return this.bathroom}
    fun setBathroom(bathroom:Boolean) {this.bathroom = bathroom}

    fun getSick() : Boolean {return this.sick}
    fun setSick(bathroom:Boolean) {this.sick = sick}

    /**
     * Saves all pet data to the device as well as a time stamp
     */
    fun save(context: Context) {
        val sharedPreferences = context.getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply() {
            putLong("timeStamp", System.currentTimeMillis())
            putString("name", name)
            putString("type", type.name)
            putInt("health", health)
            putInt("hunger", hunger)
            putInt("happiness", happiness)
            putBoolean("sick", sick)
            putBoolean("bathroom", bathroom)
            putBoolean("dead", dead)
        }.apply()
    }

    fun feed() {
    }

    fun walk() {
    }

    fun play() {
    }

    fun groom() {
    }

    fun meds() {
    }

    fun toilet() {
    }

}