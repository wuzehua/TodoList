package com.bytedance.notelist.api

import androidx.room.*
import com.bytedance.notelist.model.Note

@Dao
interface  NoteDao
{
    @Query("SELECT * FROM notes ORDER BY priority DESC")
    fun getNotes(): List<Note>

    @Query("DELETE FROM notes WHERE _id = :noteId")
    fun deleteNote(noteId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addANote(note: Note)

    @Update
    fun updateState(note: Note)
}
