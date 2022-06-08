package com.ineedyourcode.dictionary.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ineedyourcode.dictionary.App
import com.ineedyourcode.dictionary.R
import com.ineedyourcode.dictionary.ui.uils.NoConnectionDialogFragment
import com.ineedyourcode.dictionary.ui.wordsearching.WordSearchingFragment
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.random.nextLong

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

        val cancel = App.instance.job?.cancel()
        var list = mutableListOf<String>()
//        val job = CoroutineScope(Dispatchers.IO).async {
//            Log.d("@@@@@", "ASYNC START")
//            repeat(10) {
//                val s = doIt()
//                list.add("$s + $it")
//                Log.d("@@@@@", "$s + $it")
//            }
//            Log.d("@@@@@", "================================================")
//            list
//        }

        val job = CoroutineScope(Dispatchers.IO).async {
            Log.d("@@@@@", "ASYNC START")
            repeat(10) { async {
                    val time = doIt()

                    Log.d("@@@@@", "$it + $time")
                    list.add("$it + $time")
                }


            }
            Log.d("@@@@@", "================================================")
            list
        }

        CoroutineScope(Dispatchers.IO).launch {
            Log.d("@@@@@", "LAUNCH START")
            job.await()
            Log.d("@@@@@", "================================================")
            for (s in list) {
                Log.d("@@@@@", s.toString())
            }
        }

        checkInternet()
        super.onResume()
    }

    private suspend fun doIt(): Long {
        val time = Random.nextLong(1000..5000L)
        delay(time)
        return time
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