package es.alexcros.filmica

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by alexandre on 19/11/18.
 */
class ItemOffsetDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        // obtain space dimension pixel size
        val offset = view.context.resources.getDimensionPixelSize(R.dimen.offset_grid)

        // get pixel position
        val position = parent.getChildAdapterPosition(view)

        // item count
        val items: Int = parent.adapter?.itemCount ?: 0

        // column & rows number
        val columns = (parent.layoutManager as GridLayoutManager).spanCount
        val rows = (items / columns)

        // find item column & row
        val column = (position % columns) + 1
        val row = Math.ceil((position.toDouble() + 1) / columns.toDouble()).toInt()

        // item spacing
        val topOffSet = if (row == 1) offset else offset / 2
        val leftOffSet = if (column == 1) offset else offset / 2
        val bottomOffSet = if (row == rows) offset else offset / 2
        val rightOffSet = if (column == columns) offset else offset / 2

        // rect output
        outRect.set(leftOffSet, topOffSet, rightOffSet, bottomOffSet)

    }
}