package com.erikriosetiawan.withcontextcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Dispatchers.IO mean the process run in the background thread for network or database call
        GlobalScope.launch(Dispatchers.IO) {
            val data = doNetworkCall()
            Log.d(TAG, "The thread name: ${Thread.currentThread().name}")

            withContext(Dispatchers.Main) {
                // Change the thread into Main Thread
                tvResult.text = data
                Log.d(TAG, "The thread name: ${Thread.currentThread().name}")
            }
        }
    }

    private suspend fun doNetworkCall(): String {
        delay(3000L)
        return "This is the answer"
    }
}
