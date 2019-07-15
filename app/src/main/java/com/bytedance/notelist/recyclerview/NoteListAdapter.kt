package com.bytedance.notelist.recyclerview

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import com.bytedance.notelist.db.NoteDataBase
import com.bytedance.notelist.model.Note
import kotlinx.android.synthetic.main.note_item_view.view.*

class NoteListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private var items: ArrayList<Note> = ArrayList()
    private var mInstance: NoteDataBase

    constructor(instance: NoteDataBase) : super()
    {
        mInstance = instance
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return NoteViewHolder.create(parent.context, parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NoteViewHolder).bind(items[position], position)
        holder.view.cb_finishCheckBox.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener
        {
            override fun onCheckedChanged(button: CompoundButton?, value: Boolean) {
                val data = items[position]
                data.finshed = value
                mInstance.updateNote(data)
                items = mInstance.getNotes()
                notifyDataSetChanged()
            }

        })

        holder.view.ib_deleteButton.setOnClickListener(object: View.OnClickListener
        {
            override fun onClick(view: View?) {
                val data = items[position]
                mInstance.deleteNote(data.id)
                items = mInstance.getNotes()
                notifyDataSetChanged()
            }

        })

    }

    fun setItems(data: ArrayList<Note>)
    {
        items = data
    }

}