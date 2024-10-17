package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_b)
        val activityButton = findViewById<Button>(R.id.activity_button)
        activityButton.setOnClickListener {
            activityButtonCallback()
        }
    }

    private fun activityButtonCallback() {
        val intent = Intent(this, ActivityC::class.java)
        startActivity(intent)
    }
}