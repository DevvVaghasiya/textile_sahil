package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;

public class HeroCarouselStrategy extends CarouselStrategy {
    private static final int[] MEDIUM_COUNTS = {0, 1};
    private static final int[] SMALL_COUNTS = {1};
    private int keylineCount = 0;

    /* access modifiers changed from: package-private */
    public KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View child) {
        int[] iArr;
        int[] iArr2;
        Arrangement arrangement;
        int i;
        int availableSpace = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            availableSpace = carousel.getContainerWidth();
        }
        RecyclerView.LayoutParams childLayoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
        float childMargins = (float) (childLayoutParams.topMargin + childLayoutParams.bottomMargin);
        float measuredChildSize = (float) (child.getMeasuredWidth() * 2);
        if (carousel.isHorizontal()) {
            childMargins = (float) (childLayoutParams.leftMargin + childLayoutParams.rightMargin);
            measuredChildSize = (float) (child.getMeasuredHeight() * 2);
        }
        float smallChildSizeMin = CarouselStrategyHelper.getSmallSizeMin(child.getContext()) + childMargins;
        float smallChildSizeMax = CarouselStrategyHelper.getSmallSizeMax(child.getContext()) + childMargins;
        float targetLargeChildSize = Math.min(measuredChildSize + childMargins, (float) availableSpace);
        float targetSmallChildSize = MathUtils.clamp((measuredChildSize / 3.0f) + childMargins, CarouselStrategyHelper.getSmallSizeMin(child.getContext()) + childMargins, CarouselStrategyHelper.getSmallSizeMax(child.getContext()) + childMargins);
        float targetMediumChildSize = (targetLargeChildSize + targetSmallChildSize) / 2.0f;
        int[] smallCounts = ((float) availableSpace) < 2.0f * smallChildSizeMin ? new int[]{0} : SMALL_COUNTS;
        int largeCountMin = (int) Math.max(1.0d, Math.floor((double) ((((float) availableSpace) - (((float) CarouselStrategyHelper.maxValue(SMALL_COUNTS)) * smallChildSizeMax)) / targetLargeChildSize)));
        int largeCountMax = (int) Math.ceil((double) (((float) availableSpace) / targetLargeChildSize));
        int[] largeCounts = new int[((largeCountMax - largeCountMin) + 1)];
        for (int i2 = 0; i2 < largeCounts.length; i2++) {
            largeCounts[i2] = largeCountMin + i2;
        }
        boolean isCenterAligned = carousel.getCarouselAlignment() == 1;
        float f = (float) availableSpace;
        if (isCenterAligned) {
            iArr = doubleCounts(smallCounts);
        } else {
            iArr = smallCounts;
        }
        if (isCenterAligned) {
            iArr2 = doubleCounts(MEDIUM_COUNTS);
        } else {
            iArr2 = MEDIUM_COUNTS;
        }
        int[] largeCounts2 = largeCounts;
        int i3 = largeCountMax;
        int[] iArr3 = iArr2;
        int i4 = largeCountMin;
        Arrangement arrangement2 = Arrangement.findLowestCostArrangement(f, targetSmallChildSize, smallChildSizeMin, smallChildSizeMax, iArr, targetMediumChildSize, iArr3, targetLargeChildSize, largeCounts2);
        this.keylineCount = arrangement2.getItemCount();
        if (arrangement2.getItemCount() > carousel.getItemCount()) {
            isCenterAligned = false;
            arrangement = Arrangement.findLowestCostArrangement((float) availableSpace, targetSmallChildSize, smallChildSizeMin, smallChildSizeMax, smallCounts, targetMediumChildSize, MEDIUM_COUNTS, targetLargeChildSize, largeCounts2);
        } else {
            arrangement = arrangement2;
        }
        Context context = child.getContext();
        float f2 = (float) availableSpace;
        if (isCenterAligned) {
            i = 1;
        } else {
            i = 0;
        }
        return CarouselStrategyHelper.createKeylineState(context, childMargins, f2, arrangement, i);
    }

    /* access modifiers changed from: package-private */
    public boolean shouldRefreshKeylineState(Carousel carousel, int oldItemCount) {
        if (carousel.getCarouselAlignment() == 1) {
            if (oldItemCount < this.keylineCount && carousel.getItemCount() >= this.keylineCount) {
                return true;
            }
            if (oldItemCount < this.keylineCount || carousel.getItemCount() >= this.keylineCount) {
                return false;
            }
            return true;
        }
        return false;
    }
}
