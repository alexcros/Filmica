package es.alexcros.filmica

import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by alexandre on 19/11/18.
 *
 * ItemDecoration: item draw
 */
class ItemOffsetDecoration(@DimenRes val offsetId: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        // space dimension pixel size
        val offset = view.context.resources.getDimensionPixelSize(offsetId)

        // get pixel position
        val position = parent.getChildAdapterPosition(view)

        // item count
        val items: Int = parent.adapter?.itemCount ?: 0

        // layout assignment
        if (parent.layoutManager is GridLayoutManager) {
            // column & rows number
            val columns = (parent.layoutManager as GridLayoutManager).spanCount
            val rows = (items + 1 / columns)

            // find item column & row
            val column = getColumn(position, columns)
            val row = getRow(position, columns)

            // item spacing
            val topOffSet = if (row == 1) offset else offset / 2
            val leftOffSet = if (column == 1) offset else offset / 2
            val bottomOffSet = if (row == rows) offset else offset / 2
            val rightOffSet = if (column == columns) offset else offset / 2

            // rect output
            outRect.set(leftOffSet, topOffSet, rightOffSet, bottomOffSet)

        } else if (parent.layoutManager is LinearLayoutManager){
            val top = if (position == 0) offset else 0

            outRect.set(offset, top, offset, offset)
        }

    }

    private fun getRow(position: Int, columns: Int) =
            Math.ceil((position.toDouble() + 1) / columns.toDouble()).toInt()

    private fun getColumn(position: Int, columns: Int) = (position % columns) + 1
}