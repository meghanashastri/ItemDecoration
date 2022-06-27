package com.example.itemdecoratorsample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.itemdecoratorsample.RecyclerViewAdapter.Companion.VIEW_TYPE_ONE
import com.example.itemdecoratorsample.RecyclerViewAdapter.Companion.VIEW_TYPE_TWO

class ItemDecorator(context: Context) : RecyclerView.ItemDecoration() {

    private val decorationBlack = ContextCompat.getDrawable(context, R.drawable.black_separator)!!
    private val decorationWhite = ContextCompat.getDrawable(context, R.drawable.white_separator)!!

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val viewHolder = parent.getChildViewHolder(child)
            when (viewHolder.itemViewType) {
                VIEW_TYPE_ONE -> decorationBlack.drawSeparator(child, parent, canvas)
                VIEW_TYPE_TWO -> decorationWhite.drawSeparator(child, parent, canvas)
            }
        }
    }

    private fun Drawable.drawSeparator(view: View, parent: RecyclerView, canvas: Canvas) =
        apply {
            val left = view.right
            val top = parent.paddingTop
            val right = left + intrinsicWidth
            val bottom = top + intrinsicHeight - parent.paddingBottom
            bounds = Rect(left, top, right, bottom)
            draw(canvas)
        }

}