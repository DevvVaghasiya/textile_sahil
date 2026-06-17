package com.razzaghimahdi78.dotsloading.core;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public abstract class BaseCircleLoading extends FrameLayout {
    private int COLOR = Constant.DEFAULT_COLOR;
    protected final int DOTS_COUNT = 8;
    private int DOTS_SIZE = 20;
    private Float[] dotsXCorArr;
    private Float[] dotsYCorArr;

    public BaseCircleLoading(Context context) {
        super(context);
    }

    public BaseCircleLoading(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseCircleLoading(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int i = this.DOTS_SIZE;
        int calWidthHeight = (int) ((((double) i) * 2.5d * 2.0d) + (((double) i) * 2.5d * 2.0d));
        setMeasuredDimension(calWidthHeight, calWidthHeight);
    }

    /* access modifiers changed from: protected */
    public void initView(Context context, AttributeSet attrs, int dotsSize, int color) {
        this.DOTS_SIZE = dotsSize;
        this.COLOR = color;
        adjustView();
        removeAllViews();
        addDots();
    }

    private void addDots() {
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -1);
        for (int i = 0; i < 8; i++) {
            CircleView circleView = new CircleView(getContext(), this.DOTS_SIZE, this.COLOR, true);
            LinearLayout rel = new LinearLayout(getContext());
            rel.setX(this.dotsXCorArr[i].floatValue());
            rel.setY(this.dotsYCorArr[i].floatValue());
            rel.setGravity(17);
            rel.addView(circleView);
            addView(rel, layoutParams2);
        }
    }

    private void adjustView() {
        initCordinates();
        setBackgroundColor(0);
    }

    private void initCordinates() {
        float sin45Radius = ((float) (this.DOTS_SIZE * 3)) * 0.7071f;
        this.dotsXCorArr = new Float[8];
        this.dotsYCorArr = new Float[8];
        for (int i = 0; i < 8; i++) {
            Float[] fArr = this.dotsYCorArr;
            int i2 = this.DOTS_SIZE;
            fArr[i] = Float.valueOf((float) ((i2 * 3) + i2));
            this.dotsXCorArr[i] = this.dotsYCorArr[i];
        }
        Float[] fArr2 = this.dotsXCorArr;
        fArr2[1] = Float.valueOf(fArr2[1].floatValue() + sin45Radius);
        Float[] fArr3 = this.dotsXCorArr;
        fArr3[2] = Float.valueOf(fArr3[2].floatValue() + ((float) (this.DOTS_SIZE * 3)));
        Float[] fArr4 = this.dotsXCorArr;
        fArr4[3] = Float.valueOf(fArr4[3].floatValue() + sin45Radius);
        Float[] fArr5 = this.dotsXCorArr;
        fArr5[5] = Float.valueOf(fArr5[5].floatValue() - sin45Radius);
        Float[] fArr6 = this.dotsXCorArr;
        fArr6[6] = Float.valueOf(fArr6[6].floatValue() - ((float) (this.DOTS_SIZE * 3)));
        Float[] fArr7 = this.dotsXCorArr;
        fArr7[7] = Float.valueOf(fArr7[7].floatValue() - sin45Radius);
        Float[] fArr8 = this.dotsYCorArr;
        fArr8[0] = Float.valueOf(fArr8[0].floatValue() - ((float) (this.DOTS_SIZE * 3)));
        Float[] fArr9 = this.dotsYCorArr;
        fArr9[1] = Float.valueOf(fArr9[1].floatValue() - sin45Radius);
        Float[] fArr10 = this.dotsYCorArr;
        fArr10[3] = Float.valueOf(fArr10[3].floatValue() + sin45Radius);
        Float[] fArr11 = this.dotsYCorArr;
        fArr11[4] = Float.valueOf(fArr11[4].floatValue() + ((float) (this.DOTS_SIZE * 3)));
        Float[] fArr12 = this.dotsYCorArr;
        fArr12[5] = Float.valueOf(fArr12[5].floatValue() + sin45Radius);
        Float[] fArr13 = this.dotsYCorArr;
        fArr13[7] = Float.valueOf(fArr13[7].floatValue() - sin45Radius);
    }
}
