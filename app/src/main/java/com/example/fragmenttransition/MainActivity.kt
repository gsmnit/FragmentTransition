package com.example.fragmenttransition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.fragmenttransition.fragment.GridFragment

/**
 * Grid to pager app's main activity.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            currentPosition =
                savedInstanceState.getInt(KEY_CURRENT_POSITION, 0)
            // Return here to prevent adding additional GridFragments when changing orientation.
            return
        }
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, GridFragment(), GridFragment::class.java.simpleName)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(
            KEY_CURRENT_POSITION,
            currentPosition
        )
    }


    companion object {
        /**
         * Holds the current image position to be shared between the grid and the pager fragments. This
         * position updated when a grid item is clicked, or when paging the pager.
         *
         * In this demo app, the position always points to an image index at the [ @link com.example.fragmenttransition.adapter.imageData] class.
         */
        @JvmStatic
        var currentPosition = 0
        private const val KEY_CURRENT_POSITION =
            "com.example.fragmenttransition.key.currentPosition"
    }
}