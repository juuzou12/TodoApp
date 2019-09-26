package com.example.todo_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_todo.*

class CreateTodoActivity : AppCompatActivity() {

    var header: String = "Null"
    var subject: String = "Null"

    var TAG = "CreateTodo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo)

        save_btn.setOnClickListener {
            val intent = Intent()

            if (TextUtils.isEmpty(header_edt.text) && TextUtils.isEmpty(subject_edt.text)){
                setResult(Activity.RESULT_CANCELED, intent)
            }else {
                header = header_edt.text.toString()
                Log.i(TAG, header)
                subject = subject_edt.text.toString()
                Log.i(TAG, subject)

                val post = arrayOf( header, subject)
                intent.putExtra(EXTRA_REPLY, post)
                setResult(Activity.RESULT_OK, intent)

                Toast.makeText(this, "Todo Created", Toast.LENGTH_LONG).show()


            }
            finish()
        }
    }
    companion object{
        const val EXTRA_REPLY = "com.example.todo_app.Model.Post"
    }
}
