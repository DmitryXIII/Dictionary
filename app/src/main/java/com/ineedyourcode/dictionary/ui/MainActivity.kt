package com.ineedyourcode.dictionary.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.data.datasource.local.entities.SearchingHistoryEntity
import com.ineedyourcode.dictionary.ui.searchinghistory.SearchingHistoryFragment
import com.ineedyourcode.dictionary.ui.uils.ActivityContract
import com.ineedyourcode.dictionary.ui.uils.NoConnectionDialogFragment
import com.ineedyourcode.dictionary.ui.worddetails.WordDetailsFragment
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingFragment

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

    override fun navigateToHistory() {
        navigateTo(SearchingHistoryFragment())
    }

    override fun navigateToFavorite() {

    }

    override fun openWordDetails(searchingHistoryEntity: SearchingHistoryEntity) {
        navigateTo(WordDetailsFragment.newInstance(searchingHistoryEntity))
    }

    private fun navigateTo(destination: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment_container, destination)
            .addToBackStack("")
            .commit()
    }
}