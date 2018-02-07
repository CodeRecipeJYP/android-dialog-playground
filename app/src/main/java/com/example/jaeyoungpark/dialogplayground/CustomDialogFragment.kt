package com.example.jaeyoungpark.dialogplayground

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v4.app.DialogFragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.*
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
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        view?.let {
            Log.d(TAG, "onViewCreated(): it.left=${it.left}, it.right=${it.right}, \n" +
                    "it.top=${it.top}, it.bottom=${it.bottom}, \n" +
                    "it.width=${it.width}, it.height=${it.height}")
        }

        view?.setOnClickListener { mListener?.onItemClicked() }
    }

    override fun onStart() {
        super.onStart()
//        setStyle(, 0)
        val attributes = dialog.window.attributes
        attributes.width = ViewGroup.LayoutParams.MATCH_PARENT
//        attributes.y = 300

//        val drawable = ContextCompat.getDrawable(context, R.drawable.corner)
        val drawable = ColorDrawable(Color.TRANSPARENT)
        dialog.window.setBackgroundDrawable(drawable)

        dialog.window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
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
        fun onItemClicked()
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
