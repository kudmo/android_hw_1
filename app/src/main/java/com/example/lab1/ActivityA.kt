package com.example.lab1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class ActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_a)
    }

    override fun onPause() {
        super.onPause()
        Log.d("lab", "activity A paused")

    }
    fun activityButtonCallback(view: View) {
        Log.d("lab", "activity button clicked")
        val intent = Intent(this, ActivityB::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    fun fragmentButtonCallback(view: View) {
        Log.d("lab", "fragment button clicked")
    }
}