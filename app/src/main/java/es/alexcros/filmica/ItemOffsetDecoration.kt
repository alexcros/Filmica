package es.alexcros.filmica

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by alexandre on 19/11/18.
 */
class ItemOffsetDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

        // obtain space dimension pixel size
        val offset: Int = view.context.resources.getDimensionPixelSize(R.dimen.offset_grid)

        
    }
}