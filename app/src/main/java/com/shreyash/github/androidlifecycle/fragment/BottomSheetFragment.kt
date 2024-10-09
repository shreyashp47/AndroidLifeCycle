package com.shreyash.github.androidlifecycle.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shreyash.github.androidlifecycle.R

class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("BottomSheetFragment", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("BottomSheetFragment", "onViewCreated")
        Toast.makeText(context, "onViewCreated", Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.e("BottomSheetFragment", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("BottomSheetFragment", "onResume")
    }


    override fun onPause() {
        super.onPause()
        Log.e("BottomSheetFragment", "onPause")
        Toast.makeText(context, "onPause", Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        Log.e("BottomSheetFragment", "onStop")
        Toast.makeText(context, "onStop", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("BottomSheetFragment", "onDestroyView")
        Toast.makeText(context, "onDestroyView", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("BottomSheetFragment", "onDestroy")
        Toast.makeText(context, "onDestroy", Toast.LENGTH_SHORT).show()
    }


    override fun onDetach() {
        super.onDetach()
        Log.e("BottomSheetFragment", "onDetach")
    }
}