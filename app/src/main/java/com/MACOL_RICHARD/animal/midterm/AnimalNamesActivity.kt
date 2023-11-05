package com.MACOL_RICHARD.animal.midterm

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.app.Activity
import android.widget.Button

class AnimalNamesActivity : Activity() {

    private val animalList = mutableListOf("Alpacca", "Bee", "Cow", "Dog", "Eagle", "Fish", "Giraffe", "Hen", "Iguana", "Jellyfish", "Komodo Dragon", "Lamb", "Manta Ray", "Narwhal", "Oarfish", "Peacock"
        , "Quail", "Rabbit", "Salmon", "Tarsier", "Urial", "Vulture", "Weasel", "Xenoceratops", "Yak", "Zebra") // Replace with your animal list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animal_names_activity)

        val animalListView: ListView = findViewById(R.id.animalListView)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, animalList)
        animalListView.adapter = adapter

        animalListView.setOnItemClickListener { _, _, position, _ ->
            val selectedAnimal = animalList[position]
            val intent = Intent(this, AnimalDetailsActivity::class.java)
            intent.putExtra("animal_name", selectedAnimal)
            startActivityForResult(intent, 1)
        }

        val manageBlockAnimalsButton: Button = findViewById(R.id.manageBlockAnimalsButton)
        manageBlockAnimalsButton.setOnClickListener {
            val intent = Intent(this, ManageBlockActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val blockedAnimal = data?.getStringExtra("animal_name")
            blockedAnimal?.let {
                animalList.remove(it)
            }
        }
    }
}
