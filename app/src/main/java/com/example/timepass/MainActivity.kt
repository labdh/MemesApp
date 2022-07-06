package com.example.timepass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun GetMemes(view: View) {
        val EditView = findViewById<EditText>(R.id.EditView)
        val name = EditView.editableText.toString()

        //Toast.makeText(this,"Button was clicked",Toast.LENGTH_LONG).show()

        val intent = Intent(this,NewPage::class.java)
        intent.putExtra(NewPage.NAME_EXTRA,name)
        startActivity(intent)
    }
}