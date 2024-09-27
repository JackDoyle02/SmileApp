package com.example.smile

import android.app.Application
import com.example.smile.data.db.AppDatabase

class SmileApplication: Application() {
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
}