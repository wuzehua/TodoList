package com.bytedance.notelist.recyclerview

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Color.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.notelist.R
import com.bytedance.notelist.model.Note
import kotlinx.android.synthetic.main.note_item_view.view.*
import java.text.FieldPosition
import java.util.*

class NoteViewHolder (val view: View) : RecyclerView.ViewHolder(view)
{
    private var mContent: TextView
    private var mDate: TextView
    private var mFinished: CheckBox
    private var mLayout: LinearLayout

    init {
        mContent = view.findViewById(R.id.tv_contentText)
        mDate = view.findViewById(R.id.tv_dateText)
        mFinished = view.findViewById(R.id.cb_finishCheckBox)
        mLayout = view.findViewById(R.id.itemLinear)
    }


    companion object
    {
        fun create(context: Context, viewGroup: ViewGroup): NoteViewHolder
        {
            return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item_view,viewGroup, false))
        }
    }

    fun bind(data: Note?, position: Int)
    {
        if (data == null) return
        when(data.priority)
        {
            0->{
                mLayout.setBackgroundColor(parseColor("#FFFFFF"))
            }

            1->{
                mLayout.setBackgroundColor(parseColor("#FFE0A6"))
            }

            2->{
                mLayout.setBackgroundColor(parseColor("#FFAFAF"))
            }
        }

        mFinished.isChecked = data.finshed
        mDate.text = Date(data.date).toString()
        mContent.text = data.content

        if (data.finshed)
        {
            mContent.setTextColor(GRAY)
            mContent.paintFlags = mContent.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        else
        {
            mContent.setTextColor(BLACK)
            mContent.paintFlags = mContent.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

    }


}