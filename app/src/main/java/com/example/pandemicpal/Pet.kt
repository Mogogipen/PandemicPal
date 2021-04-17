package com.example.pandemicpal

import android.content.Context
import java.util.*
import kotlin.math.pow

class Pet (
    private var name:String, private var type:PETS
) {
    private var health = 50
    private var hunger = 50
    private var happiness = 50
    private var bathroom = false
    private var dead = false
    private var sick = false

    private var lastFeed:Long = 0
    private var lastGroom:Long = 0
    private var lastWalk:Long = 0
    private var lastPlay:Long = 0
    private var lastMed:Long = 0

    private var healthTime:Long = Long.MAX_VALUE
    private var hungerTime:Long = Long.MAX_VALUE
    private var happyTime:Long = Long.MAX_VALUE
    private var toiletTime:Long = Long.MAX_VALUE

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
        val timeInterval : Long = (7.2 * 10.0.pow(6.0)).toLong()
        val loopCount = timePassed/timeInterval

        // Set time values
        healthTime = System.currentTimeMillis()
        hungerTime = System.currentTimeMillis()
        happyTime = System.currentTimeMillis()
        toiletTime = System.currentTimeMillis()

        // Simulate passage of time
        if (timePassed >= timeInterval*3) {
            bathroom = true
        } else if (timePassed >= timeInterval/4) {
            val rand = Random(timePassed)
            bathroom = rand.nextBoolean()
        }
        for (i in 1..loopCount) {
            increaseHunger(10)
            decreaseHappiness(10)
            if (hunger == 100 || happiness == 0) {
                decreaseHealth(10)
            }
            if (bathroom) {
                decreaseHealth(10)
            }
        }

    }

    fun getName() : String {return this.name}
    fun setName(name:String) {this.name = name}

    fun getType() : PETS {return this.type}
    fun setType(type:PETS) {this.type = type}

    fun getHealth() : Int {return this.health}
    private fun increaseHealth(healthMod:Int) {
        if(healthMod + this.health > 100)
            this.health = 100
        else
            this.health += healthMod
    }
    private fun decreaseHealth(healthMod:Int) {
        if((healthMod * type.healthMod).toInt() >= this.health) {
            dead = true
            this.health = 0
        }
        else
            this.health -= (healthMod*type.healthMod).toInt()
    }

    fun getHunger() : Int {return this.hunger}
    private fun increaseHunger(hungerMod:Int) {
        if((hungerMod * type.hungerMod).toInt() + this.hunger > 100)
            this.hunger = 100
        else
            this.hunger += hungerMod
    }
    private fun decreaseHunger(hungerMod:Int) {
        if(hungerMod >= this.hunger)
            this.hunger = 0
        else
            this.hunger -= hungerMod
    }

    fun getHappiness() : Int {return this.happiness}
    private fun increaseHappiness(happinessMod:Int) {
        if(happinessMod + this.happiness > 100)
            this.happiness = 100
        else
            this.happiness += happinessMod
    }
    private fun decreaseHappiness(happinessMod:Int) {
        if((happinessMod * type.happinessMod).toInt() >= this.happiness)
            this.happiness = 0
        else
            this.happiness -= (happinessMod * type.happinessMod).toInt()
    }

    fun getBathroom() : Boolean {return this.bathroom}
    fun setBathroom(bathroom:Boolean) {this.bathroom = bathroom}

    fun getSick() : Boolean {return this.sick}
    fun setSick(bathroom:Boolean) {this.sick = sick}

    fun isDead() : Boolean {return this.dead}

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



    //
    // Pet actions
    //

    fun update() {
        // Calculate new values from time passed
        val time = System.currentTimeMillis()
        var timeInterval : Long = (60000).toLong() // 1 min

        // Simulate hunger
        if (time - hungerTime >= timeInterval) {
            var hungerCount = ((time - hungerTime)/timeInterval).toInt()
            increaseHunger(10 * hungerCount)
            hungerTime = time
        }

        // Simulate happiness
        if (time - happyTime >= timeInterval) {
            var happyCount = ((time - happyTime)/timeInterval).toInt()
            decreaseHappiness(10 * happyCount)
            happyTime = time
        }
        if (happiness == 0)
            sick = true

        // Simulate health
        if (time - healthTime >= timeInterval && (hunger >= 100 || sick)) {
            var healthCount = ((time - healthTime)/timeInterval).toInt()
            var hungry = hunger >= 100
            if (sick && hungry) {
                increaseHunger(20 * healthCount)
            } else {
                increaseHunger(10 * healthCount)
            }
            healthTime = time
        }

        // Simulate toilet
        if (time - toiletTime >= timeInterval*3) {
            bathroom = true
        } else if (time - toiletTime >= timeInterval/2) {
            bathroom = kotlin.random.Random.nextBoolean()
        }
        if (time - toiletTime >= timeInterval) {
            var toiletCount = ((time - toiletTime)/timeInterval).toInt()
            decreaseHappiness(10 * toiletCount)
            decreaseHappiness(10 * toiletCount)
            toiletTime = time
        }
    }

    fun feed(): Boolean {
        if (sick)
            decreaseHealth(10)
        update()
        var result = true
        decreaseHunger(10)
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastFeed <= 30000) {
            sick = true
            result = false
        }
        lastFeed = currentTime
        hungerTime = currentTime
        return result
    }

    fun walk(): Boolean {
        if (sick)
            decreaseHealth(10)
        update()
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastWalk <= 30000) {
            return false
        }
        increaseHunger(10)
        increaseHealth(10)
        lastWalk = currentTime
        hungerTime = currentTime
        healthTime = currentTime
        return true
    }

    fun play(): Boolean {
        if (sick)
            decreaseHealth(10)
        update()
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastPlay <= 30000) {
            return false
        }
        increaseHunger(10)
        increaseHappiness(10)
        lastPlay = currentTime
        hungerTime = currentTime
        happyTime = currentTime
        return true
    }

    fun groom(): Boolean {
        if (sick)
            decreaseHealth(10)
        update()
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastGroom <= 30000) {
            return false
        }
        increaseHappiness(10)
        lastGroom = currentTime
        happyTime = currentTime
        return true
    }

    fun meds(): Boolean {
        update()
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastMed <= 60000) {
            decreaseHappiness(10)
            increaseHealth(5)
            happyTime = currentTime
            healthTime = currentTime
            sick = false
            return false
        }
        increaseHealth(10)
        healthTime = currentTime
        sick = false
        return true
    }

    fun toilet(): Boolean {
        update()
        if (bathroom) {
            bathroom = false
            toiletTime = System.currentTimeMillis()
            return true
        }
        return false
    }

}