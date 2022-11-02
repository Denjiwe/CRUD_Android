package com.example.crudandroid

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Pessoa::class], version = 1)
abstract class AppDB : RoomDatabase() {

    abstract fun getPessoaDao(): PessoaDao

    companion object{

        @Volatile
        private var instance : AppDB? =  null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }


        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDB::class.java,
            "app-db"
        ).build()
    }
}