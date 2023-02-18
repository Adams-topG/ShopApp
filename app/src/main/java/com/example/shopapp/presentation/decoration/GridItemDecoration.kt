package com.example.shopapp.presentation.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridItemDecoration(
    private val horizontalSpacing: Int,
    private val verticalSpacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)
        val column: Int //position % parent.layoutManager!!
        val spanCount: Int
        if (parent.layoutManager is GridLayoutManager) {
            spanCount = (parent.layoutManager as GridLayoutManager).spanCount
            column = position % spanCount
        } else return

        outRect.left =
            column * horizontalSpacing / spanCount
        outRect.right =
            horizontalSpacing - (column + 1) * horizontalSpacing / spanCount

        outRect.bottom = verticalSpacing
    }
}