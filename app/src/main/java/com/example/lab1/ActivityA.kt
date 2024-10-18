package com.example.lab1

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlin.random.Random


class ActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lab", "A created")
        enableEdgeToEdge()
        val layoutId = if (resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
            R.layout.activity_a_portrait
        } else {
            R.layout.activity_a_landscape
        }
        setContentView(layoutId)

        val activityButton = findViewById<Button>(R.id.activity_button)
        activityButton.setOnClickListener {
            activityButtonCallback()
        }

        val fragmentButton = findViewById<Button>(R.id.fragment_button)
        fragmentButton.setOnClickListener {
            fragmentButtonCallback()
        }

        this@ActivityA.supportFragmentManager.setFragmentResultListener("requestKey", this) { requestKey, result ->
            val color= result.getInt("color") // Получаем цвет из результата
            val fragmentBA = supportFragmentManager.findFragmentById(R.id.fragment_container_a) as FragmentBA?
            fragmentBA?.updateBackgroundColor(color) // Обновляем цвет фона FragmentBA
        }

        this@ActivityA.supportFragmentManager.setFragmentResultListener("openFragmentBB", this) { requestKey, result ->
            // Открыть FragmentBB
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container_b, FragmentBB())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("lab", "A on new Intent")
    }
    private fun activityButtonCallback() {
        val intent = Intent(this, ActivityB::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
    fun fragmentButtonCallback() {
        Log.d("lab", "fragment button clicked")
        val fragmentManager: FragmentManager = this@ActivityA.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

        if (resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            // Альбомная ориентация: открываем оба фрагмента одновременно
            fragmentTransaction.replace(R.id.fragment_container_a, FragmentBA())
            fragmentTransaction.replace(R.id.fragment_container_b, FragmentBB())
        } else {
            // Портретная ориентация: сначала открываем FragmentBA
            fragmentTransaction.replace(R.id.fragment_container_a, FragmentBA())
        }

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}