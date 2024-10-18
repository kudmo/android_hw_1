package com.example.lab1

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlin.random.Random

class FragmentBA : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layoutId = if (resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
            R.layout.fragment_ba_portrait // Используем макет для портретной ориентации
        } else {
            R.layout.fragment_ba_landscape // Используем макет для альбомной ориентации
        }
        view?.setBackgroundColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Добавляем слушатель нажатия на кнопку "Open FragmentBB" (только если он есть)
        view.findViewById<Button>(R.id.open_fragment_bb)?.setOnClickListener {
            // Отправляем запрос на открытие FragmentBB в ActivityA
            val bundle = Bundle()
            bundle.putString("requestKey", "openFragmentBB")
            parentFragmentManager.setFragmentResult("openFragmentBB", bundle)
        }

        // Listener для получения результата от FragmentBB
        parentFragmentManager.setFragmentResultListener("requestKey", this) { requestKey, result ->
            val color = result.getInt("color")
            updateBackgroundColor(color)
        }
    }

    fun updateBackgroundColor(color: Int) {
        view?.setBackgroundColor(color)
    }
}