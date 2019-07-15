package com.bytedance.notelist.db

import androidx.room.TypeConverter
import java.util.*

class DateConverter
{
    companion object
    {
        @TypeConverter
        fun revertDate(value: Long): Date
        {
            return Date(value)
        }

        @TypeConverter
        fun convertDate(value: Date): Long
        {
            return value.time
        }
    }
}