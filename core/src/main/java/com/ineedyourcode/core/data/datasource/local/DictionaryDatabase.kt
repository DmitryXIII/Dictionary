package com.ineedyourcode.core.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ineedyourcode.core.data.datasource.local.dao.HistoryDao
import com.ineedyourcode.core.data.datasource.local.dao.WordMeaningDao
import com.ineedyourcode.core.data.datasource.local.entities.HistoryEntity
import com.ineedyourcode.core.data.datasource.local.entities.WordMeaningEntity

@Database(entities = [WordMeaningEntity::class, HistoryEntity::class],
    version = 1,
    exportSchema = true)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract val historyDao: HistoryDao
    abstract val wordMeaningDao: WordMeaningDao

    companion object {
        private var INSTANCE: DictionaryDatabase? = null

        fun getUserDatabase(context: Context): DictionaryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    DictionaryDatabase::class.java,
                    "Dictionary")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}