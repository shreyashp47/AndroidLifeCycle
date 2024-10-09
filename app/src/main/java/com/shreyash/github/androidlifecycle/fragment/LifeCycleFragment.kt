package com.shreyash.github.androidlifecycle.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.shreyash.github.androidlifecycle.R

class LifeCycleFragment : Fragment() {

    var count = 0
    val TAG = "APPLifeCycleFragment"
    val FRAG_COUNT = "fragment_count"


    override fun onAttach(context: Context) {
        super.onAttach(context)
        count = arguments?.getInt(FRAG_COUNT) ?: 0
        Log.e(TAG, "onAttach - Fragment:" + count)
        Toast.makeText(context, "onAttach - Fragment: $count", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate - Fragment:" + count)
        Toast.makeText(context, "onCreate - Fragment: $count", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(TAG, "onCreateView - Fragment:" + count)
        Toast.makeText(context, "onCreateView - Fragment: $count", Toast.LENGTH_SHORT).show()
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_life_cyle, container, false)

        var textView: TextView? = view.findViewById<TextView>(R.id.textView)
        textView!!.text = "Fragment: $count"


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG, "onViewCreated - Fragment:" + count)
        Toast.makeText(context, "onViewCreated - Fragment: $count", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart - Fragment:" + count)
        Toast.makeText(context, "onStart - Fragment: $count", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume - Fragment:" + count)
        Toast.makeText(context, "onResume - Fragment: $count", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause - Fragment:" + count)
        Toast.makeText(context, "onPause - Fragment: $count", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop - Fragment:" + count)
        Toast.makeText(context, "onStop - Fragment: $count", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "onDestroyView - Fragment: " + count)
        Toast.makeText(context, "onDestroyView - Fragment: $count", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy - Fragment:" + count)
        Toast.makeText(context, "onDestroy - Fragment: $count", Toast.LENGTH_SHORT).show()
    }

    override fun onDetach() {
        super.onDetach()
        Log.e(TAG, "onDetach - Fragment:" + count)
        Toast.makeText(context, "onDetach - Fragment: $count", Toast.LENGTH_SHORT).show()
    }


}