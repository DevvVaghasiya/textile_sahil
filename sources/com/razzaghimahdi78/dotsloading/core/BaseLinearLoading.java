package com.razzaghimahdi78.dotsloading.core;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class BaseLinearLoading extends LinearLayout {
    private int COLOR = Constant.DEFAULT_COLOR;
    private int DOTS_COUNT = 3;
    private int DOTS_SIZE = 20;

    public BaseLinearLoading(Context context) {
        super(context);
    }

    public BaseLinearLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseLinearLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void initView(Context context, AttributeSet attrs, int dotsSize, int dotsCount, int color) {
        this.DOTS_SIZE = dotsSize;
        this.DOTS_COUNT = dotsCount;
        this.COLOR = color;
        adjustView();
        removeAllViews();
        addDots();
    }

    private void adjustView() {
        setOrientation(0);
        setGravity(17);
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        setBackgroundColor(0);
    }

    private void addDots() {
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        for (int i = 0; i < this.DOTS_COUNT; i++) {
            CircleView circleView = new CircleView(getContext(), this.DOTS_SIZE, this.COLOR, true);
            LinearLayout rel = new LinearLayout(getContext());
            int i2 = this.DOTS_SIZE;
            layoutParams2.setMargins(i2 / 2, i2 / 2, i2 / 2, i2 / 2);
            rel.setGravity(17);
            rel.addView(circleView);
            addView(rel, layoutParams2);
        }
    }
}
