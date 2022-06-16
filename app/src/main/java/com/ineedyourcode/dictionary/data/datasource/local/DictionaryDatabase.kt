package com.ineedyourcode.dictionary.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ineedyourcode.dictionary.data.datasource.local.entities.FavoriteWordsEntity
import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity

@Database(entities = [FavoriteWordsEntity::class, SearchingHistoryEntity::class],
    version = 1,
    exportSchema = true)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract val historyDao: HistoryDao
    abstract val favoriteDao: FavoriteDao

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