package com.razzaghimahdi78.dotsloading.circle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.razzaghimahdi78.dotsloading.R;
import com.razzaghimahdi78.dotsloading.core.BaseCircleLoading;
import com.razzaghimahdi78.dotsloading.core.CheckValidation;
import com.razzaghimahdi78.dotsloading.core.CheckValidationImpl;
import com.razzaghimahdi78.dotsloading.core.Constant;
import com.razzaghimahdi78.dotsloading.core.Convertor;
import com.razzaghimahdi78.dotsloading.core.ConvertorImpl;
import com.razzaghimahdi78.dotsloading.core.DotSize;

public class LoadingCircleFady extends BaseCircleLoading {
    private int COLOR = Constant.DEFAULT_COLOR;
    private int DOTS_SIZE = 20;
    private int DURATION = Constant.DEFAULT_DURATION;
    private ObjectAnimator[] animator;
    private CheckValidation checkValidation;
    private Convertor convertor;
    boolean onLayoutReach = false;

    public LoadingCircleFady(Context context) {
        super(context);
        initView(context, (AttributeSet) null, 20, Constant.DEFAULT_COLOR);
    }

    public LoadingCircleFady(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 20, Constant.DEFAULT_COLOR);
    }

    public LoadingCircleFady(Context context, AttributeSet attrs, int defStyleAttr) {
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
        if (this.animator != null) {
            for (int i = 0; i < 8; i++) {
                if (this.animator[i].isRunning()) {
                    this.animator[i].removeAllListeners();
                    this.animator[i].end();
                    this.animator[i].cancel();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initView(Context context, AttributeSet attrs, int dotsSize, int color) {
        this.convertor = new ConvertorImpl();
        this.checkValidation = new CheckValidationImpl();
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingCircleFady);
            setColor(typedArray.getColor(R.styleable.LoadingCircleFady_dots_color, Constant.DEFAULT_COLOR));
            setDuration(typedArray.getInt(R.styleable.LoadingCircleFady_dots_duration, Constant.DEFAULT_DURATION));
            setSize(typedArray.getDimensionPixelSize(R.styleable.LoadingCircleFady_dots_size, 20));
        }
        super.initView(context, attrs, this.DOTS_SIZE, this.COLOR);
    }

    /* access modifiers changed from: private */
    public void animateView(final boolean show) {
        this.animator = new ObjectAnimator[8];
        for (int i = 0; i < 8; i++) {
            this.animator[i] = ObjectAnimator.ofPropertyValuesHolder(getChildAt(i), new PropertyValuesHolder[]{show ? PropertyValuesHolder.ofFloat("alpha", new float[]{0.2f}) : PropertyValuesHolder.ofFloat("alpha", new float[]{1.0f})});
            this.animator[i].setRepeatCount(0);
            this.animator[i].setRepeatMode(2);
            this.animator[i].setDuration((long) this.DURATION);
            this.animator[i].setStartDelay((long) ((this.DURATION / 8) * i));
            this.animator[i].start();
        }
        this.animator[7].addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                LoadingCircleFady.this.animateView(!show);
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
