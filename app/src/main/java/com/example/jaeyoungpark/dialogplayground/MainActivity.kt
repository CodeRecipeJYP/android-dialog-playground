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
import android.view.Window
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*
import android.view.Window.ID_ANDROID_CONTENT



class MainActivity : AppCompatActivity(), CustomDialogFragment.Listener {
    private val TAG = MainActivity::class.java.simpleName
    var fragment: CustomDialogFragment? = null
    var ylist = ArrayDeque(listOf(400, 410, 411, 411))
    var y: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()

            initPosition()
            showDialogWithPosition(y, if (ylist.isEmpty()) 0 else ylist.pop())
        }

        frame.setOnClickListener { showDialogWithPosition(y, 1) }
        tv2.setOnClickListener { showDialogWithPosition(y, -1) }

        Log.d(TAG, "onCreate() called  with: fragment = [${fragment}]")

        showDialogWithPosition(y, 0)


//        ItemListDialogFragment.newInstance(30)
//                .apply { show(supportFragmentManager, "dialog") }

        Log.d(TAG, "onCreate() called  with: fragment = [${fragment}]")
    }

    private fun initPosition() {
        listOf(
                tv1, tv2, tv3, tv4,
                tv5
        ).forEach { printPosition(it!!) }

        printStatusBar()
    }

    private fun printStatusBar() {
        val rectangle = Rect()
        val window = window
        window.decorView.getWindowVisibleDisplayFrame(rectangle)
        val statusBarHeight = rectangle.top
        val contentViewTop = window.findViewById<View>(Window.ID_ANDROID_CONTENT).top
        val titleBarHeight = contentViewTop - statusBarHeight

        frame.text = "statusBarHeight=${statusBarHeight}, contentViewTop=${contentViewTop}, titleBarHeight=${titleBarHeight}"
    }

    private fun printPosition(tv: TextView) {
        val windowPosition = IntArray(2).apply {
            tv.getLocationInWindow(this)
        }

        val screenPosition = IntArray(2).apply {
            tv.getLocationOnScreen(this)
        }

        tv.text = "w= ${windowPosition[1]}, p= ${screenPosition[1]}"
    }

    private fun showDialogWithPosition(position: Int, grad: Int) {
        fragment = CustomDialogFragment.newInstance(position)
                .apply { show(supportFragmentManager, "dialog") }
        y += grad
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
