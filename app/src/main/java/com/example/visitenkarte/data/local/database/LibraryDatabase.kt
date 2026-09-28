package com.example.visitenkarte.data.local.database

//import androidx.room3.Database
//import androidx.room3.RoomDatabase
import android.content.Context
import com.example.visitenkarte.data.local.dao.BookDao
import com.example.visitenkarte.data.local.entity.BookEntity
import kotlin.concurrent.Volatile

//@Database(entities = [
// BookEntity::class,
// AuthorEntity::class,
// PublisherEntity::class
// ], version = 1)
//abstract class LibraryDatabase: RoomDatabase() {
//    abstract fun bookDao(): BookDao
//    abstract fun authorDao(): AuthorDao
//    abstract fun publisherDao(): publisherDao
//
//    companion object {
//        @Volatile
//        private var instance: LibraryDatabase? = null
//
//
//        fun getInstance(context: Context):LibraryDatabase{
//            return instance?: synchronized(this){
//                instance?: Room.DatabaseBuilder(
//                context.applicationContext,
//                LibraryDatabase::class.java,
//                "library.db"
//                ).fallbackToDestructiveMigration(dropAllTables=true)
//                    .build()
//                    .also{instance = it}
//            }
//        }
//    }
//}