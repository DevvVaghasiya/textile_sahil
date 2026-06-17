package com.google.android.material.carousel;

import android.view.View;

public abstract class CarouselStrategy {
    /* access modifiers changed from: package-private */
    public abstract KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view);

    static float getChildMaskPercentage(float maskedSize, float unmaskedSize, float childMargins) {
        return 1.0f - ((maskedSize - childMargins) / (unmaskedSize - childMargins));
    }

    static int[] doubleCounts(int[] count) {
        int[] doubledCount = new int[count.length];
        for (int i = 0; i < doubledCount.length; i++) {
            doubledCount[i] = count[i] * 2;
        }
        return doubledCount;
    }

    /* access modifiers changed from: package-private */
    public boolean isContained() {
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean shouldRefreshKeylineState(Carousel carousel, int oldItemCount) {
        return false;
    }
}
