package com.ineedyourcode.dictionary.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.ui.uils.ActivityContract
import com.ineedyourcode.core.ui.uils.NoConnectionDialogFragment
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingFragment
import com.ineedyourcode.history.ui.SearchingHistoryFragment
import com.ineedyourcode.worddetails.ui.WordDetailsFragment

class MainActivity : AppCompatActivity(), ActivityContract {

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

    override fun openHistory() {
        navigateTo(SearchingHistoryFragment())
    }

    override fun openWordDetailsWithSavingToHistory(wordTranslation: String) {
        navigateTo(WordDetailsFragment.newInstance(wordTranslation))
    }

    override fun openWordDetailsFromHistory(word: String) {
        navigateTo(WordDetailsFragment.newInstance(word))
    }

    private fun navigateTo(destination: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment_container, destination)
            .addToBackStack("")
            .commit()
    }
}