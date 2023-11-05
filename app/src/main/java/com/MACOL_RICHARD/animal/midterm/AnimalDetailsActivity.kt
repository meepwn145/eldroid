package com.MACOL_RICHARD.animal.midterm

import android.content.Intent
import android.os.Bundle
import android.app.Activity
import android.widget.Button
import android.widget.TextView

class AnimalDetailsActivity : Activity() {

    private lateinit var animalName: String
    private var animalDescription: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_details)
        val actionBar = actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val receivedIntent: Intent = intent

        val extras = receivedIntent.extras
        if (extras != null) {
            animalName = extras.getString("animal_name") ?: "No Name"
            animalDescription = "a vast array of living organisms, exhibiting a wide range of traits, behaviors, and habitats across the world."
        }

        val animalNameTextView: TextView = findViewById(R.id.animalNameTextView)
        val animalDescTextView: TextView = findViewById(R.id.animalDescTextView)
        val blockAnimalButton: Button = findViewById(R.id.blockAnimalButton)

        animalNameTextView.text = animalName
        animalDescTextView.text = animalDescription
        blockAnimalButton.setOnClickListener {
            addToBlockList(animalName)
            setResult(RESULT_OK)
            finish()
        }

        if (intent.getBooleanExtra("manage_blocks", false)) {
            blockAnimalButton.setOnClickListener {
                addToBlockList(animalName)
                setResult(RESULT_OK)
                finish()
            }
        } else {
            blockAnimalButton.setOnClickListener {
                addToBlockList(animalName)
                finish()
            }
        }
    }
    private fun addToBlockList(animalName: String) {
        PrefsManager.addBlockedAnimal(this, animalName)
    }


}
