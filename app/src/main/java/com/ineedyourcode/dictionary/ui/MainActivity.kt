package com.ineedyourcode.dictionary.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.ui.uils.NoConnectionDialogFragment
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingFragment

class MainActivity : AppCompatActivity(), InternetConnectionChecker {
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

    override fun onResume() {
        checkInternet()
        super.onResume()
    }

    override fun checkInternet(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val netInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        val isConnectionOk = netInfo != null && netInfo.isConnected

        if (!isConnectionOk) {
            NoConnectionDialogFragment().show(
                supportFragmentManager, NoConnectionDialogFragment().javaClass.canonicalName)
        }

        return isConnectionOk
    }
}