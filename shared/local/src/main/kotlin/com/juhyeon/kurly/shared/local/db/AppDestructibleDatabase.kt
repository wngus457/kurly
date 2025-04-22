package com.juhyeon.kurly.shared.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.juhyeon.kurly.shared.local.product.ProductBookmarkDao
import com.juhyeon.kurly.shared.local.product.ProductBookmarkEntity

@Database(
    entities = [
        ProductBookmarkEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDestructibleDatabase : RoomDatabase() {

    abstract fun productBookmarkDao(): ProductBookmarkDao

    companion object {
        private const val databaseName = "kurly-room-db"

        fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDestructibleDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .build()
    }
}