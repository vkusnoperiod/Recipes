package com.example.foodie
import android.app.Application
import androidx.room.Room

class App : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "recipes.db")
            // Логика конфигурации базы данных (миграции, onCreate, onOpen и т.д.)
            .build()
    }
}

