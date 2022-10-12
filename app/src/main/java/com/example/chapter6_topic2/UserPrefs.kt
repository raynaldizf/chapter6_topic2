package com.example.chapter6_topic2

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPrefs(private val context : Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val NAME = stringPreferencesKey("name")
    val AGE = intPreferencesKey("age")

    suspend fun saveData(name : String, age : Int) {
        context.dataStore.edit {
            it[NAME] = name
            it[AGE] = age
        }
    }

    val userName : Flow<String> = context.dataStore.data
        .map {
            it[NAME]?: ""
        }

    val userAge : Flow<Int> = context.dataStore.data
        .map {
            it[AGE]?: 0
        }

    suspend fun deleteData(){
        context.dataStore.edit {
            it.clear()
        }
    }

}