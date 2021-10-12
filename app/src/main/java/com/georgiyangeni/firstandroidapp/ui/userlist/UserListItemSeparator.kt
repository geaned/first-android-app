package com.georgiyangeni.firstandroidapp.ui.userlist

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView

class UserListItemSeparator(private val dividerDrawable: Drawable) : RecyclerView.ItemDecoration() {

    private val dividerHeight = dividerDrawable.intrinsicHeight

    override fun getItemOffsets(rect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            val childAdapterPosition = parent.getChildAdapterPosition(view).let {
                if (it == RecyclerView.NO_POSITION) return else it
            }
            rect.bottom = if (childAdapterPosition == adapter.itemCount - 1) 0 else dividerHeight
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        parent.adapter?.let { adapter ->
            parent.children
                .forEach { view ->
                    val childAdapterPosition = parent.getChildAdapterPosition(view).let {
                        if (it == RecyclerView.NO_POSITION) return else it
                    }
                    if (childAdapterPosition != adapter.itemCount - 1) {
                        val top = view.bottom
                        val bottom = top + dividerHeight
                        dividerDrawable.bounds = Rect(0, top, Resources.getSystem().displayMetrics.widthPixels, bottom)
                        dividerDrawable.draw(canvas)
                    }
                }
        }
    }
}