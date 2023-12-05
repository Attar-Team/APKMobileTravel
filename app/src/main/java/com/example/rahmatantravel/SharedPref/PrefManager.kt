package com.example.rahmatantravel.SharedPref

import android.content.Context
import android.content.SharedPreferences

// Buat class PrefManager untuk menyimpan data login
class PrefManager(context: Context) {

    private val preferences: SharedPreferences = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)

    // Fungsi untuk menyimpan data login

    fun setUserName(username: String?) {
        preferences.edit().putString("username", username).apply()
    }
    fun getUserName(): String? {
        return preferences.getString("username", null)
    }
    fun saveLoginData(userId: Int?, token: String) {
        preferences.edit().putString("user_id", userId.toString()).apply()
        preferences.edit().putString("token", token).apply()
    }

    // Fungsi untuk mendapatkan data login
    fun getLoginData(): Pair<String?, String?> {
        val userId = preferences.getString("user_id", null)
        val token = preferences.getString("token", null)
        return Pair(userId, token)
    }

    // Fungsi untuk menghapus data login
    fun clearLoginData() {
        preferences.edit().clear().apply()
    }
}
