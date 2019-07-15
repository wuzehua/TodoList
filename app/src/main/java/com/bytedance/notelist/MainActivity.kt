package com.bytedance.notelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bytedance.notelist.db.NoteDataBase
import com.bytedance.notelist.model.Note
import com.bytedance.notelist.recyclerview.NoteListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

import android.Manifest
import android.content.pm.PackageManager

class MainActivity : AppCompatActivity() {



    private var mInstance: NoteDataBase? = null
    private var mAdapter: NoteListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        mInstance = NoteDataBase.getInstance(applicationContext)

        if(!isWritable())
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1001)
        }

        fb_addButton.setOnClickListener(object: View.OnClickListener
        {
            override fun onClick(view: View?) {
                val intent = Intent(this@MainActivity, AddNoteActivity::class.java)
                startActivityForResult(intent, 11)
            }
        })

        val recyclerView = rv_noteList
        recyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = NoteListAdapter(mInstance!!)
        recyclerView.adapter = mAdapter
        mAdapter?.setItems(mInstance!!.getNotes())
        mAdapter?.notifyDataSetChanged()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == 3) {
            val note = Note()
            note.finshed = false
            note.date = Date().time
            note.priority = data?.getIntExtra("priority", 0)!!
            note.content = data.getStringExtra("content")
            mInstance!!.addNote(note)
            mAdapter?.setItems(mInstance!!.getNotes())
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_page_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.action_debug->
            {
                Toast.makeText(this,"Click",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun isWritable(): Boolean
    {
        val stat = Environment.getExternalStorageState()
        if(Environment.MEDIA_MOUNTED.equals(stat))
        {
            return true
        }

        return false
    }
}
