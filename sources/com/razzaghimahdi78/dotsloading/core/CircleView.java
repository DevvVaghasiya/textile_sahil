package com.razzaghimahdi78.dotsloading.core;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.razzaghimahdi78.dotsloading.R;

public class CircleView extends View {
    int circleColor = 0;
    int circleRadius = 20;
    boolean drawOnlyStroke = false;
    boolean isAntiAlias = true;
    private Paint paint = new Paint();
    int strokeWidth = 0;
    private float xyCordinates = 0.0f;

    public CircleView(Context context, int circleRadius2, int circleColor2, boolean isAntiAlias2) {
        super(context);
        this.circleRadius = circleRadius2;
        this.circleColor = circleColor2;
        this.isAntiAlias = isAntiAlias2;
        initValues();
    }

    public CircleView(Context context, int circleRadius2, int circleColor2, boolean drawOnlyStroke2, int strokeWidth2) {
        super(context);
        this.circleRadius = circleRadius2;
        this.circleColor = circleColor2;
        this.drawOnlyStroke = drawOnlyStroke2;
        this.strokeWidth = strokeWidth2;
        initValues();
    }

    public CircleView(Context context) {
        super(context);
        initValues();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(attrs);
        initValues();
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(attrs);
        initValues();
    }

    /* access modifiers changed from: package-private */
    public void initAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0);
        this.circleRadius = typedArray.getDimensionPixelSize(R.styleable.CircleView_circleRadius, 30);
        this.circleColor = typedArray.getColor(R.styleable.CircleView_circleColor, 0);
        boolean z = typedArray.getBoolean(R.styleable.CircleView_circleDrawOnlystroke, false);
        this.drawOnlyStroke = z;
        if (z) {
            this.strokeWidth = typedArray.getDimensionPixelSize(R.styleable.CircleView_circleStrokeWidth, 0);
        }
        typedArray.recycle();
    }

    private void initValues() {
        this.paint.setAntiAlias(this.isAntiAlias);
        if (this.drawOnlyStroke) {
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth((float) this.strokeWidth);
        } else {
            this.paint.setStyle(Paint.Style.FILL);
        }
        this.paint.setColor(this.circleColor);
        this.xyCordinates = ((float) this.circleRadius) + ((float) (this.strokeWidth / 2));
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthHeight = (this.circleRadius * 2) + this.strokeWidth;
        setMeasuredDimension(widthHeight, widthHeight);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float f = this.xyCordinates;
        canvas.drawCircle(f, f, (float) this.circleRadius, this.paint);
    }
}
