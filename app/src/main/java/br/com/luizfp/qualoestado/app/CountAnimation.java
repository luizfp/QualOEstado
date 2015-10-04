package br.com.luizfp.qualoestado.app;

import android.animation.ValueAnimator;
import android.widget.TextView;

/**
 * Created by luiz on 9/24/15.
 */
public class CountAnimation {

    public CountAnimation() {
    }

    public static void startCountAnimation(int from, int to, final TextView textView, int duration) {
        ValueAnimator animator = new ValueAnimator();
        animator.setObjectValues(from, to);
        animator.setDuration(duration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText("" + (int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }
}
