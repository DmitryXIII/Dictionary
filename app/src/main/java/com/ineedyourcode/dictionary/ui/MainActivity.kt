package com.ineedyourcode.dictionary.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingFragment
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingViewContract

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_container, WordSearchingFragment())
                .commit()
        }
    }
}