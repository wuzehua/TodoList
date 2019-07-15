package com.bytedance.notelist.db

import android.content.Context
import android.app.Application
import androidx.room.*
import com.bytedance.notelist.api.NoteDao
import com.bytedance.notelist.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDataBase : RoomDatabase()
{
    abstract fun NoteDao():NoteDao

    companion object
    {
        @Volatile
        private var instance: NoteDataBase? = null

        fun getInstance(context: Context): NoteDataBase
        {
            if(instance == null)
            {
                synchronized(NoteDataBase::class){
                    if(instance == null)
                    {
                        instance = Room.databaseBuilder(context.applicationContext,
                            NoteDataBase::class.java, "notes.db")
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }

            return instance!!
        }
    }

    fun deleteNote(id:Int)
    {
        instance!!.NoteDao().deleteNote(id)
    }

    fun updateNote(note: Note)
    {
        instance!!.NoteDao().updateState(note)
    }

    fun getNotes(): ArrayList<Note>
    {
        return instance!!.NoteDao().getNotes() as ArrayList<Note>
    }

    fun addNote(note: Note)
    {
        instance!!.NoteDao().addANote(note)
    }

}
