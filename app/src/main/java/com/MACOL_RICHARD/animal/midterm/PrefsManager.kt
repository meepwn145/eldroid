package com.MACOL_RICHARD.animal.midterm

import android.content.Context

object PrefsManager {
    private const val PREFS_NAME = "BlockedAnimalsPrefs"

    fun addBlockedAnimal(context: Context, animal: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val animals = getBlockedAnimals(context).toMutableSet()
        animals.add(animal)
        prefs.edit().putStringSet("blocked_animals", animals).apply()
    }

    fun getBlockedAnimals(context: Context): Set<String> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getStringSet("blocked_animals", setOf()) ?: setOf()
    }

    fun removeBlockedAnimal(context: Context, animal: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val animals = getBlockedAnimals(context).toMutableSet()
        animals.remove(animal)
        prefs.edit().putStringSet("blocked_animals", animals).apply()
    }
}
