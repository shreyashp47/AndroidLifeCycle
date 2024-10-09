package com.shreyash.github.androidlifecycle

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shreyash.github.androidlifecycle.fragment.BottomSheetFragment
import com.shreyash.github.androidlifecycle.fragment.LifeCycleFragment
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    val FRAG_COUNT = "fragment_count"

    var count = 0

    private lateinit var tvLogs: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e(TAG, "onCreate")
        tvLogs = findViewById(R.id.textView)

        // Start capturing logs from Logcat
        captureLogs()
        val next = findViewById<Button>(R.id.nextFrag)
        val preview = findViewById<Button>(R.id.lastFrag)
        val bottomSheet = findViewById<Button>(R.id.openBottomSheet)

        next.setOnClickListener {
            count++
            var bundle = Bundle()
            bundle.putInt(FRAG_COUNT, count)
            val fragment = LifeCycleFragment()
            fragment.arguments = bundle
            val transist = supportFragmentManager.beginTransaction()
            transist.replace(R.id.frameLayout, fragment)
            transist.addToBackStack(null)
            transist.commit()

        }
        preview.setOnClickListener {
            if (count > 0) {
            count--
            supportFragmentManager.popBackStack()
            }
        }

        bottomSheet.setOnClickListener {
           var bottomSheetFragment:BottomSheetDialogFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, "bottomSheet")
        }




    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }

    private fun captureLogs() {
        try {
            // Execute the logcat command and capture logs
            val process = Runtime.getRuntime().exec("logcat -d LifeCycleFragment:*") // -d flag for dumping the log

            // Read logs from the process output
            val bufferedReader = BufferedReader(InputStreamReader(process.inputStream))

            // Initialize a StringBuilder to accumulate log messages
            val logBuilder = StringBuilder()
            var line: String?

            // Read each line from the logcat output
            while (bufferedReader.readLine().also { line = it } != null) {
                logBuilder.append(line).append("\n")
            }

            // Display logs in the TextView
            tvLogs.text = logBuilder.toString()

        } catch (e: Exception) {
            e.printStackTrace()
            tvLogs.text = "Failed to retrieve logs"
        }
    }
}