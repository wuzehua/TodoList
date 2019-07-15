package com.bytedance.notelist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        btn_low.isChecked = true
        edit_text.requestFocus()
        btn_add.setOnClickListener(object: View.OnClickListener
        {
            override fun onClick(view: View?) {
                val content = edit_text.text
                if(TextUtils.isEmpty(content))
                {
                    Toast.makeText(this@AddNoteActivity,"内容为空",Toast.LENGTH_SHORT).show()
                    return
                }
                else
                {
                    val intent = Intent()
                    println("Content = ${content.toString()}")
                    intent.putExtra("content",content.toString())
                    intent.putExtra("priority",getPriority())
                    setResult(3,intent)
                    finish()
                }
            }

        })

    }

    private fun getPriority(): Int
    {
        var result = 0
        when(radio_group.checkedRadioButtonId)
        {
            R.id.btn_high->
            {
                result = 2
            }

            R.id.btn_medium->
            {
                result = 1
            }

            else->
            {
                result = 0
            }
        }
        return result
    }
}
