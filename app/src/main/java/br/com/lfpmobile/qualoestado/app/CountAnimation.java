package br.com.lfpmobile.qualoestado.app;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.widget.TextView;

/**
 * Created by luiz on 9/24/15.
 */
public class CountAnimation {

    public CountAnimation() {
    }

    //FIXME
    // Funciona apenas da API 3.0 (HONEYCOMB) em diante.
    @SuppressLint("NewApi")
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
