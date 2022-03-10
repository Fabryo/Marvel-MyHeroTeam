package com.bryo.marvel.myheroteam.utils

import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(@DimenRes private val spaceRes: Int,
                           private val displayLastDecoration: Boolean = false) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State
    ) {
        val space = parent.resources.getDimension(spaceRes).toInt()
        with(outRect) {
            if ((parent.layoutManager as LinearLayoutManager).orientation == LinearLayoutManager.VERTICAL) {
                top = if (parent.getChildAdapterPosition(view) != 0) space else 0
                bottom = if (displayLastDecoration) space else 0
            } else {
                left = if (parent.getChildAdapterPosition(view) != 0) space else 0
                right = if (displayLastDecoration) space else 0
            }
        }
    }
}