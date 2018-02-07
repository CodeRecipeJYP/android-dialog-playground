package com.example.jaeyoungpark.dialogplayground

import android.app.Dialog
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
    var elev = 0f

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
                .apply { show(supportFragmentManager, "dialog") }

        Log.d(TAG, "onCreate() called  with: fragment = [${fragment}]")
    }

    private var dialog: Dialog? = null

    override fun onItemClicked() {
        Log.d(TAG, "onItemClicked() called  with: fragment = [${fragment}]")
        Log.d(TAG, "onItemClicked() called  with: fragment = [${fragment?.dialog}]")
        dialog = fragment?.dialog
        Log.d(TAG, "onItemClicked() called  with: dialog.window = [${dialog?.window}]")

        dialog?.window?.setElevation(elev)

        elev += 10f
        Log.d(TAG, "onItemClicked() called  with: dialog.window.decorView = [${dialog?.window?.decorView}]")
        var view: View? = dialog?.window?.decorView
        var outlineProvider: ViewOutlineProvider? = dialog?.window?.decorView?.outlineProvider

        val outlineRect = outlineProvider?.let {
            view?.let {
                val outline = Outline()
                outline.setEmpty()
                outline.setAlpha(1.0f)
                outlineProvider.getOutline(view, outline)

                val rect = Rect()
                outline.getRect(rect)
                Pair(rect, outline.radius)
            }
        }
        Log.d(TAG, "onItemClicked() called  with: rect = [${outlineRect?.first}], radius = [${outlineRect}]")


//
//        val outlineProvider: ViewOutlineProvider? = dialog?.window?.decorView?.outlineProvider ?: null
//        val decorView: View? = dialog?.window?.decorView
//        outlineProvider.getOutline(decorView, outline)
//        Log.d(TAG, "onItemClicked() called  with: outline = [${outline}]")
    }
}
