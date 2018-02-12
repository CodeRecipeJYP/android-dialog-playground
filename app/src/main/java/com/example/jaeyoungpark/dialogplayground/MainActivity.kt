package com.example.jaeyoungpark.dialogplayground

import android.app.Dialog
import android.app.FragmentTransaction
import android.graphics.Outline
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.ViewOutlineProvider

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CustomDialogFragment.Listener {
    private val TAG = MainActivity::class.java.simpleName
    var fragment: CustomDialogFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        Log.d(TAG, "onCreate() called  with: fragment = [${fragment}]")

        fragment = CustomDialogFragment.newInstance(30)
                .apply {
                    supportFragmentManager.beginTransaction()
                            .let { transaction ->
                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                // To make it fullscreen, use the 'content' root view as the container
                                // for the fragment, which is always the root view for the activity
                                transaction.add(R.id.frame, this)
                                        .addToBackStack(null).commit()
                                // For a little polish, specify a transition animation
                            }
                }
//                .apply { show(supportFragmentManager, "dialog") }

//        ItemListDialogFragment.newInstance(30)
//                .apply { show(supportFragmentManager, "dialog") }

        Log.d(TAG, "onCreate() called  with: fragment = [${fragment}]")
    }

    private var dialog: Dialog? = null

    override fun onItemClicked() {
//        Log.d(TAG, "onItemClicked() called  with: fragment = [${fragment}]")
//        Log.d(TAG, "onItemClicked() called  with: fragment = [${fragment?.dialog}]")
//        dialog = fragment?.dialog
//        Log.d(TAG, "onItemClicked() called  with: dialog.window = [${dialog?.window}]")
//
//        Log.d(TAG, "onItemClicked() called  with: dialog.window.decorView = [${dialog?.window?.decorView}]")
//        var view: View? = dialog?.window?.decorView
//        var outlineProvider: ViewOutlineProvider? = dialog?.window?.decorView?.outlineProvider

//
//        val outlineProvider: ViewOutlineProvider? = dialog?.window?.decorView?.outlineProvider ?: null
//        val decorView: View? = dialog?.window?.decorView
//        outlineProvider.getOutline(decorView, outline)
//        Log.d(TAG, "onItemClicked() called  with: outline = [${outline}]")
    }
}
