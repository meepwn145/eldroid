package com.MACOL_RICHARD.animal.midterm

import android.os.Bundle
import android.app.Activity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class ManageBlockActivity : Activity() {

    private lateinit var blockedAnimalListView: ListView
    private lateinit var blockedAnimalsList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manage_block_activity)

        blockedAnimalListView = findViewById(R.id.blockedAnimalListView)

        blockedAnimalsList = mutableListOf("Alpacca", "Bee", "Cow", "Dog", "Eagle", "Fish", "Giraffe", "Hen", "Iguana", "Jellyfish", "Komodo Dragon", "Lamb", "Manta Ray", "Narwhal", "Oarfish", "Peacock"
            , "Quail", "Rabbit", "Salmon", "Tarsier", "Urial", "Vulture", "Weasel", "Xenoceratops", "Yak", "Zebra")

        val adapter = CustomAdapter(this, blockedAnimalsList)
        blockedAnimalListView.adapter = adapter
    }

    private fun removeFromBlockedAnimals(unblockedAnimal: String) {
        val sharedPrefs = getSharedPreferences("blocked_animals", MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        blockedAnimalsList.remove(unblockedAnimal)

        val blockedAnimals = sharedPrefs.getStringSet("blocked_animals_list", HashSet())?.toMutableSet()
        blockedAnimals?.remove(unblockedAnimal)

        editor.putStringSet("blocked_animals_list", blockedAnimals)
        editor.apply()
        (blockedAnimalListView.adapter as? CustomAdapter)?.notifyDataSetChanged()

        // After removing the unblockedAnimal from the list, update the list and UI
        blockedAnimalsList.remove(unblockedAnimal)
    }


    private class CustomAdapter(context: Activity, items: List<String>) : ArrayAdapter<String>(context, 0, items) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var listItemView = convertView
            if (listItemView == null) {
                listItemView = LayoutInflater.from(context).inflate(R.layout.blocked_animal_list_item, parent, false)
            }

            val animalNameTextView: TextView = listItemView!!.findViewById(R.id.animalNameTextView)
            val unblockButton: Button = listItemView.findViewById(R.id.unblockButton)

            val animal = getItem(position)
            animalNameTextView.text = animal

            unblockButton.setOnClickListener {

                remove(animal)
                notifyDataSetChanged()

            }

            return listItemView
        }
    }
}
