package com.razzaghimahdi78.dotsloading.linear;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import com.razzaghimahdi78.dotsloading.R;
import com.razzaghimahdi78.dotsloading.core.BaseLinearLoading;
import com.razzaghimahdi78.dotsloading.core.CheckValidation;
import com.razzaghimahdi78.dotsloading.core.CheckValidationImpl;
import com.razzaghimahdi78.dotsloading.core.Constant;
import com.razzaghimahdi78.dotsloading.core.Convertor;
import com.razzaghimahdi78.dotsloading.core.ConvertorImpl;
import com.razzaghimahdi78.dotsloading.core.DotSize;

public class LoadingScaly extends BaseLinearLoading {
    private int COLOR = Constant.DEFAULT_COLOR;
    private int DOTS_COUNT = 3;
    private int DOTS_SIZE = 20;
    private int DURATION = Constant.DEFAULT_DURATION;
    private ObjectAnimator[] animator;
    private CheckValidation checkValidation;
    private Convertor convertor;
    boolean onLayoutReach = false;

    public LoadingScaly(Context context) {
        super(context);
        initView(context, (AttributeSet) null, 20, 3, Constant.DEFAULT_COLOR);
    }

    public LoadingScaly(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 20, 3, Constant.DEFAULT_COLOR);
    }

    public LoadingScaly(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, 20, 3, Constant.DEFAULT_COLOR);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (!this.onLayoutReach) {
            this.onLayoutReach = true;
            animateView();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.animator != null) {
            for (int i = 0; i < this.DOTS_COUNT; i++) {
                if (this.animator[i].isRunning()) {
                    this.animator[i].removeAllListeners();
                    this.animator[i].end();
                    this.animator[i].cancel();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void initView(Context context, AttributeSet attrs, int dotsSize, int dotsCount, int color) {
        this.convertor = new ConvertorImpl();
        this.checkValidation = new CheckValidationImpl();
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LoadingScaly);
            setColor(typedArray.getColor(R.styleable.LoadingScaly_dots_color, Constant.DEFAULT_COLOR));
            setDuration(typedArray.getInt(R.styleable.LoadingScaly_dots_duration, Constant.DEFAULT_DURATION));
            setDotsCount(typedArray.getInt(R.styleable.LoadingScaly_dots_count, 3));
            setSize(typedArray.getDimensionPixelSize(R.styleable.LoadingScaly_dots_size, 20));
        }
        super.initView(context, attrs, this.DOTS_SIZE, this.DOTS_COUNT, this.COLOR);
    }

    /* access modifiers changed from: private */
    public void animateView() {
        this.animator = new ObjectAnimator[this.DOTS_COUNT];
        int i = 0;
        while (true) {
            int i2 = this.DOTS_COUNT;
            if (i < i2) {
                PropertyValuesHolder SCALE_X = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{0.0f});
                PropertyValuesHolder SCALE_Y = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.0f});
                this.animator[i] = ObjectAnimator.ofPropertyValuesHolder(getChildAt(i), new PropertyValuesHolder[]{SCALE_X, SCALE_Y});
                this.animator[i].setRepeatCount(1);
                this.animator[i].setRepeatMode(2);
                this.animator[i].setDuration((long) this.DURATION);
                this.animator[i].setStartDelay((long) ((this.DURATION / this.DOTS_COUNT) * i));
                this.animator[i].start();
                i++;
            } else {
                this.animator[i2 - 1].addListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        LoadingScaly.this.animateView();
                    }
                });
                return;
            }
        }
    }

    public void setDotsCount(int value) {
        if (this.checkValidation.isCountValid(value)) {
            this.DOTS_COUNT = value;
            initView(getContext(), (AttributeSet) null, 20, 3, Constant.DEFAULT_COLOR);
        }
    }

    public void setDuration(int value) {
        if (this.checkValidation.isDurationValid(value)) {
            this.DURATION = value;
            initView(getContext(), (AttributeSet) null, 20, 3, Constant.DEFAULT_COLOR);
        }
    }

    public void setColor(int value) {
        this.COLOR = value;
        initView(getContext(), (AttributeSet) null, 20, 3, Constant.DEFAULT_COLOR);
    }

    public void setSize(int value) {
        this.DOTS_SIZE = value;
        initView(getContext(), (AttributeSet) null, 20, 3, Constant.DEFAULT_COLOR);
    }

    private void setSize(DotSize value) {
        this.DOTS_SIZE = this.convertor.convertDotSize(value);
        initView(getContext(), (AttributeSet) null, 20, 3, Constant.DEFAULT_COLOR);
    }
}
