package com.ineedyourcode.dictionary.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.animation.AnticipateInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.ineedyourcode.core.ui.uils.NoConnectionDialogFragment
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.ui.uils.ActivityContract
import com.ineedyourcode.history.ui.SearchingHistoryFragment
import com.ineedyourcode.worddetails.ui.WordDetailsFragment
import com.ineedyourcode.wordsearching.ui.WordSearchingFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), ActivityContract {
    private var isSplashScreenKeeping = true
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition { isSplashScreenKeeping }

            setOnExitAnimationListener { viewProvider ->
                viewProvider.iconView.animate()
                    .scaleX(0f)
                    .scaleY(0f)
                    .setInterpolator(AnticipateInterpolator(3f))
                    .duration = 500

                viewProvider.view.animate()
                    .alpha(0f)
                    .withEndAction { viewProvider.remove() }
                    .duration = 500
            }
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_container, WordSearchingFragment())
                .commit()
        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(1500)
            isSplashScreenKeeping = false
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