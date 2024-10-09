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

    val TAG = "APPLifeCycleMainActivity"
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
        val clearBtn = findViewById<Button>(R.id.clearBtn)


        clearBtn.setOnClickListener {
            clearLogs()
        }

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

            captureLogs()
        }
        preview.setOnClickListener {
            if (count > 0) {
                count--
                supportFragmentManager.popBackStack()

                captureLogs()
            }
        }

        bottomSheet.setOnClickListener {
            var bottomSheetFragment: BottomSheetDialogFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, "bottomSheet")

            captureLogs()
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
            // Execute the logcat command to capture only logs with the tag "LifeCycleFragment"
            val process = Runtime.getRuntime().exec("logcat -d LifeCycle:*")

            // Read logs from the process output and store them in a list to reverse later
            val logLines = mutableListOf<String>()

            BufferedReader(InputStreamReader(process.inputStream)).use { bufferedReader ->
                var line: String?

                // Read each line from the logcat output
                while (bufferedReader.readLine().also { line = it } != null) {
                    if (line!!.contains("LifeCycle")) {
                        // Split the log message and get the last part (the actual log message)
                        val logMessage = line!!.split("LifeCycle").lastOrNull()?.trim()

                        // Add the filtered log message to the list if it's not null
                        logMessage?.let {
                            logLines.add("LOGS: $it")
                        }
                    }
                }
            }

            // Reverse the log list to display the newest logs at the top
            logLines.reverse()

            // Display the reversed logs in the TextView
            tvLogs.text = logLines.joinToString("\n")

        } catch (e: Exception) {
            e.printStackTrace()
            tvLogs.text = "Failed to retrieve logs"
        }
    }

    private fun clearLogs() {
        try {
            // Clear logcat buffer using the logcat -c command
            Runtime.getRuntime().exec("logcat -c")

            // Clear the TextView
            tvLogs.text = ""

        } catch (e: Exception) {
            e.printStackTrace()
            tvLogs.text = "Failed to clear logs"
        }
    }

}