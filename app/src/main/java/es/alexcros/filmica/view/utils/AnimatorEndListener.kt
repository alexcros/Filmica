package es.alexcros.filmica.view.utils

import android.animation.Animator

/**
 * Created by alexandre on 23/11/18.
 */
class AnimatorEndListener(
    val callback: (Animator) -> Unit
    ) : Animator.AnimatorListener {
    override fun onAnimationRepeat(p0: Animator?) {
    }

    override fun onAnimationEnd(animator: Animator) {
        callback.invoke(animator)
    }

    override fun onAnimationCancel(p0: Animator?) {
    }

    override fun onAnimationStart(p0: Animator?) {
    }

}
