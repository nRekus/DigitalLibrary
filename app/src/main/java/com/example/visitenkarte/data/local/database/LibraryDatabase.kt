package com.example.visitenkarte.data.local.database

import androidx.room3.Database
import androidx.room3.RoomDatabase
import android.content.Context
import androidx.room3.BuiltInColumnTypeConverters
import androidx.room3.ColumnTypeConverters
import androidx.room3.Room
import com.example.visitenkarte.data.local.converter.SimpleTypeConverter
import com.example.visitenkarte.data.local.dao.BookDao
import com.example.visitenkarte.data.local.entity.Book
import kotlin.concurrent.Volatile

@Database(entities = [
 Book::class
], version = 1)
@ColumnTypeConverters(SimpleTypeConverter::class,
    builtInColumnTypeConverters = BuiltInColumnTypeConverters
(enums = BuiltInColumnTypeConverters.State.ENABLED,
        uuid = BuiltInColumnTypeConverters.State.ENABLED))
abstract class LibraryDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
    companion object {
        @Volatile
        private var instance: LibraryDatabase? = null

        fun getInstance(context: Context):LibraryDatabase{
            return instance?: synchronized(this){
                instance?: Room.databaseBuilder(
                context.applicationContext,
                LibraryDatabase::class.java,
                "library.db"
                ).fallbackToDestructiveMigration(dropAllTables=true)
                    .build()
                    .also{instance = it}
            }
        }
    }
}