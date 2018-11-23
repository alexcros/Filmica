package es.alexcros.filmica.view.utils

import android.animation.Animator
import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.widget.ImageView

/**
 * Created by alexandre on 23/11/18.
 */
class FadeImageView : ImageView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun setImageBitmap(bm: Bitmap?) {

        animate()
                .alpha(0F)
                .setDuration(150)
                .setListener(AnimatorEndListener {
                    super@FadeImageView.setImageBitmap(bm)
                    animate()
                            .alpha(1F)
                            .setDuration(120)
                })
    }
}