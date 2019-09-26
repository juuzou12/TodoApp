package com.example.todo_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.Adapter.ToDoAdapter
import com.example.todo_app.Model.Post
import com.example.todo_app.ViewHolder.PostViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_homepage.*


class Homepage : AppCompatActivity() {
    lateinit var mQuery:Query
    lateinit var mDatabase:FirebaseFirestore
    private var recyclerView: RecyclerView? = null
    var listi = mutableListOf<Post>()

    var TAG = "CreateTodo"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_homepage)

        fab.setOnClickListener { view ->
            var intent = Intent(this, CreateTodoActivity::class.java)
            startActivityForResult(intent, newCreateTodoRequestCode)
        }
//        var listi = mutableListOf<Post>()
//            "George", "Healthy living"),
//            Post("Pinky", "Healthy living"),
//            Post("Lucy", "Healthy living"),
//            Post("Juuzou", "Healthy living")
//


    }

    fun onCreate(){
        val adapter = ToDoAdapter()
        val todoRecycler = findViewById<RecyclerView>(R.id.todoRecycler)
        todoRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        todoRecycler.adapter = adapter
        adapter.submitList(listi)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if((requestCode == newCreateTodoRequestCode) && (resultCode == Activity.RESULT_OK)){
            data?.let{
                val todo = it.getStringArrayExtra(CreateTodoActivity.EXTRA_REPLY)
                Log.i(TAG, todo[0])
                val todoItem = Post(todo[0], todo[1] )
                listi.add(todoItem)

                onCreate()

            }
        }else{
            Toast.makeText(
                applicationContext,
                "Todo Not Saved",
                Toast.LENGTH_LONG).show()
        }
    }
    companion object{
        const val newCreateTodoRequestCode = 1
    }
}
