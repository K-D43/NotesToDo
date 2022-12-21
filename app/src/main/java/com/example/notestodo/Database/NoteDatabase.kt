package com.example.notestodo.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notestodo.Models.Note
import com.example.notestodo.utilities.DATABASE_NAME

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

    companion object{

        @Volatile
        private var INSTANCE : NoteDatabase?=null

        fun getDatabase(context: Context) : NoteDatabase{
            synchronized(this){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java,
                        DATABASE_NAME
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}