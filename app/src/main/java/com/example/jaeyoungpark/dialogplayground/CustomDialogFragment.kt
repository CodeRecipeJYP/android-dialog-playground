package com.example.jaeyoungpark.dialogplayground

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


/**
 *
 * A fragment that shows a list of items as a modal bottom sheet.
 *
 * You can show this modal bottom sheet from your activity like this:
 * <pre>
 *    ItemListDialogFragment.newInstance(30).show(supportFragmentManager, "dialog")
 * </pre>
 *
 * You activity (or fragment) needs to implement [ItemListDialogFragment.Listener].
 */
class CustomDialogFragment : DialogFragment() {
    private val TAG = CustomDialogFragment::class.java.simpleName
    private var mListener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_custom, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        view?.let {
            Log.d(TAG, "onViewCreated(): it.left=${it.left}, it.right=${it.right}, \n" +
                    "it.top=${it.top}, it.bottom=${it.bottom}, \n" +
                    "it.width=${it.width}, it.height=${it.height}")
        }
    }

    override fun onStart() {
        super.onStart()
        val attributes = dialog.window.attributes
        attributes.width = ViewGroup.LayoutParams.MATCH_PARENT
        attributes.y = 300
        dialog.window.attributes = attributes
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val parent = parentFragment
        if (parent != null) {
            mListener = parent as Listener
        } else {
            mListener = context as Listener
        }
    }

    override fun onDetach() {
        mListener = null
        super.onDetach()
    }

    interface Listener {
        fun onItemClicked(position: Int)
    }

    companion object {
        fun newInstance(itemCount: Int): CustomDialogFragment =
                CustomDialogFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_ITEM_COUNT, itemCount)
                    }
                }

    }
}
