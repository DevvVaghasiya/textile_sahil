package com.razzaghimahdi78.dotsloading.circle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.motion.widget.Key;
import com.razzaghimahdi78.dotsloading.R;
import com.razzaghimahdi78.dotsloading.core.BaseCircleLoading;
import com.razzaghimahdi78.dotsloading.core.CheckValidation;
import com.razzaghimahdi78.dotsloading.core.CheckValidationImpl;
import com.razzaghimahdi78.dotsloading.core.Constant;
import com.razzaghimahdi78.dotsloading.core.Convertor;
import com.razzaghimahdi78.dotsloading.core.ConvertorImpl;
import com.razzaghimahdi78.dotsloading.core.DotSize;

public class LoadingCircleRotation extends BaseCircleLoading {
    private int COLOR = Constant.DEFAULT_COLOR;
    private int DOTS_SIZE = 20;
    private int DURATION = Constant.DEFAULT_DURATION;
    private ObjectAnimator alphaAnimator;
    private CheckValidation checkValidation;
    private Convertor convertor;
    boolean onLayoutReach = false;
    private ObjectAnimator rotateAnimator;
    private ObjectAnimator scaleAnimator;

    public LoadingCircleRotation(Context context) {
        super(context);
        initView(context, (AttributeSet) null, 20, Constant.DEFAULT_COLOR);
    }

    public LoadingCircleRotation(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 20, Constant.DEFAULT_COLOR);
    }

    public LoadingCircleRotation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, 20, Constant.DEFAULT_COLOR);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!this.onLayoutReach) {
            this.onLayoutReach = true;
            animateView(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ObjectAnimator objectAnimator = this.rotateAnimator;
        if (objectAnimator != null && objectAnimator.isRunning()) {
            this.rotateAnimator.removeAllListeners();
            this.rotateAnimator.end();
            this.rotateAnimator.cancel();
        }
        ObjectAnimator objectAnimator2 = this.scaleAnimator;
        if (objectAnimator2 != null && objectAnimator2.isRunning()) {
            this.scaleAnimator.removeAllListeners();
            this.scaleAnimator.end();
            this.scaleAnimator.cancel();
        }
        ObjectAnimator objectAnimator3 = this.alphaAnimator;
        if (objectAnimator3 != null && objectAnimator3.isRunning()) {
            this.alphaAnimator.removeAllListeners();
            this.alphaAnimator.end();
            this.alphaAnimator.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void initView(Context context, AttributeSet attrs, int dotsSize, int color) {
        this.convertor = new ConvertorImpl();
        this.checkValidation = new CheckValidationImpl();
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingCircleRotation);
            setColor(typedArray.getColor(R.styleable.LoadingCircleRotation_dots_color, Constant.DEFAULT_COLOR));
            setDuration(typedArray.getInt(R.styleable.LoadingCircleRotation_dots_duration, Constant.DEFAULT_DURATION));
            setSize(typedArray.getDimensionPixelSize(R.styleable.LoadingCircleRotation_dots_size, 20));
        }
        super.initView(context, attrs, this.DOTS_SIZE, this.COLOR);
    }

    /* access modifiers changed from: private */
    public void animateView(final boolean show) {
        this.rotateAnimator = new ObjectAnimator();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, Key.ROTATION, new float[]{0.0f, 360.0f});
        this.rotateAnimator = ofFloat;
        ofFloat.setRepeatCount(-1);
        this.rotateAnimator.setRepeatMode(2);
        this.rotateAnimator.setDuration((long) this.DURATION);
        this.rotateAnimator.setStartDelay(0);
        this.rotateAnimator.start();
        this.scaleAnimator = new ObjectAnimator();
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{0.5f}), PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.5f})});
        this.scaleAnimator = ofPropertyValuesHolder;
        ofPropertyValuesHolder.setRepeatCount(-1);
        this.scaleAnimator.setRepeatMode(2);
        this.scaleAnimator.setDuration((long) this.DURATION);
        this.scaleAnimator.setStartDelay(0);
        this.scaleAnimator.start();
        this.alphaAnimator = new ObjectAnimator();
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[]{show ? PropertyValuesHolder.ofFloat("alpha", new float[]{0.0f}) : PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f})});
        this.alphaAnimator = ofPropertyValuesHolder2;
        ofPropertyValuesHolder2.setRepeatCount(-1);
        this.alphaAnimator.setRepeatMode(2);
        this.alphaAnimator.setDuration((long) this.DURATION);
        this.alphaAnimator.setStartDelay(0);
        this.alphaAnimator.start();
        this.alphaAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                LoadingCircleRotation.this.animateView(!show);
            }
        });
    }

    public void setDuration(int value) {
        if (this.checkValidation.isDurationValid(value)) {
            this.DURATION = value;
            initView(getContext(), (AttributeSet) null, 20, Constant.DEFAULT_COLOR);
        }
    }

    public void setColor(int value) {
        this.COLOR = value;
        initView(getContext(), (AttributeSet) null, 20, Constant.DEFAULT_COLOR);
    }

    public void setSize(int value) {
        this.DOTS_SIZE = value;
        initView(getContext(), (AttributeSet) null, 20, Constant.DEFAULT_COLOR);
    }

    private void setSize(DotSize value) {
        this.DOTS_SIZE = this.convertor.convertDotSize(value);
        initView(getContext(), (AttributeSet) null, 20, Constant.DEFAULT_COLOR);
    }
}
