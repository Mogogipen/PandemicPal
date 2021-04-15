package com.example.pandemicpal

import android.app.Application

class Pet (
    name:String, type:PETS, health:Int, hunger:Int, happiness:Int, bathroom:Boolean
) : Application() {
    private lateinit var name : String
    private lateinit var type : PETS
    private var health = 100
    private var hunger = 0
    private var happiness = 100
    private var bathroom = false
    private var dead = false
    private var sick = false

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
        if((healthMod * type.healthMod) as Int >= this.health)
            dead = true
        else
            this.health -= (healthMod*type.healthMod) as Int
    }

    fun getHunger() : Int {return this.hunger}
    fun increaseHunger(hungerMod:Int) {
        if((hungerMod * type.hungerMod) as Int + this.hunger > 100)
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
        if((happinessMod * type.happinessMod) as Int >= this.happiness)
            this.happiness = 0
        else
            this.happiness -= (happinessMod * type.happinessMod) as Int
    }

    fun getBathroom() : Boolean {return this.bathroom}
    fun setBathroom(bathroom:Boolean) {this.bathroom = bathroom}

    fun getSick() : Boolean {return this.sick}
    fun setSick(bathroom:Boolean) {this.sick = sick}

}