package com.bytedance.notelist.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.bytedance.notelist.db.DateConverter;

import java.util.Date;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    public int id;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "date")
    public Long date;

    @ColumnInfo(name = "finished")
    public boolean finshed;

    @ColumnInfo(name = "priority")
    public int priority;
}
