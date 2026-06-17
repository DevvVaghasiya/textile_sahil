package com.google.android.material.carousel;

import androidx.core.math.MathUtils;

final class Arrangement {
    private static final float MEDIUM_ITEM_FLEX_PERCENTAGE = 0.1f;
    final float cost;
    final int largeCount;
    float largeSize;
    int mediumCount;
    float mediumSize;
    final int priority;
    int smallCount;
    float smallSize;

    Arrangement(int priority2, float targetSmallSize, float minSmallSize, float maxSmallSize, int smallCount2, float targetMediumSize, int mediumCount2, float targetLargeSize, int largeCount2, float availableSpace) {
        this.priority = priority2;
        this.smallSize = MathUtils.clamp(targetSmallSize, minSmallSize, maxSmallSize);
        this.smallCount = smallCount2;
        this.mediumSize = targetMediumSize;
        this.mediumCount = mediumCount2;
        this.largeSize = targetLargeSize;
        this.largeCount = largeCount2;
        fit(availableSpace, minSmallSize, maxSmallSize, targetLargeSize);
        this.cost = cost(targetLargeSize);
    }

    public String toString() {
        return "Arrangement [priority=" + this.priority + ", smallCount=" + this.smallCount + ", smallSize=" + this.smallSize + ", mediumCount=" + this.mediumCount + ", mediumSize=" + this.mediumSize + ", largeCount=" + this.largeCount + ", largeSize=" + this.largeSize + ", cost=" + this.cost + "]";
    }

    private float getSpace() {
        return (this.largeSize * ((float) this.largeCount)) + (this.mediumSize * ((float) this.mediumCount)) + (this.smallSize * ((float) this.smallCount));
    }

    private void fit(float availableSpace, float minSmallSize, float maxSmallSize, float targetLargeSize) {
        float delta = availableSpace - getSpace();
        int i = this.smallCount;
        if (i > 0 && delta > 0.0f) {
            float f = this.smallSize;
            this.smallSize = f + Math.min(delta / ((float) i), maxSmallSize - f);
        } else if (i > 0 && delta < 0.0f) {
            float f2 = this.smallSize;
            this.smallSize = f2 + Math.max(delta / ((float) i), minSmallSize - f2);
        }
        int i2 = this.smallCount;
        float f3 = i2 > 0 ? this.smallSize : 0.0f;
        this.smallSize = f3;
        float calculateLargeSize = calculateLargeSize(availableSpace, i2, f3, this.mediumCount, this.largeCount);
        this.largeSize = calculateLargeSize;
        float f4 = (this.smallSize + calculateLargeSize) / 2.0f;
        this.mediumSize = f4;
        int i3 = this.mediumCount;
        if (i3 > 0 && calculateLargeSize != targetLargeSize) {
            float targetAdjustment = (targetLargeSize - calculateLargeSize) * ((float) this.largeCount);
            float distribute = Math.min(Math.abs(targetAdjustment), f4 * 0.1f * ((float) i3));
            if (targetAdjustment > 0.0f) {
                this.mediumSize -= distribute / ((float) this.mediumCount);
                this.largeSize += distribute / ((float) this.largeCount);
                return;
            }
            this.mediumSize += distribute / ((float) this.mediumCount);
            this.largeSize -= distribute / ((float) this.largeCount);
        }
    }

    private float calculateLargeSize(float availableSpace, int smallCount2, float smallSize2, int mediumCount2, int largeCount2) {
        return (availableSpace - ((((float) smallCount2) + (((float) mediumCount2) / 2.0f)) * (smallCount2 > 0 ? smallSize2 : 0.0f))) / (((float) largeCount2) + (((float) mediumCount2) / 2.0f));
    }

    private boolean isValid() {
        int i = this.largeCount;
        if (i > 0 && this.smallCount > 0 && this.mediumCount > 0) {
            float f = this.largeSize;
            float f2 = this.mediumSize;
            if (f <= f2 || f2 <= this.smallSize) {
                return false;
            }
            return true;
        } else if (i <= 0 || this.smallCount <= 0) {
            return true;
        } else {
            if (this.largeSize > this.smallSize) {
                return true;
            }
            return false;
        }
    }

    private float cost(float targetLargeSize) {
        if (!isValid()) {
            return Float.MAX_VALUE;
        }
        return Math.abs(targetLargeSize - this.largeSize) * ((float) this.priority);
    }

    static Arrangement findLowestCostArrangement(float availableSpace, float targetSmallSize, float minSmallSize, float maxSmallSize, int[] smallCounts, float targetMediumSize, int[] mediumCounts, float targetLargeSize, int[] largeCounts) {
        int[] iArr = smallCounts;
        int[] iArr2 = mediumCounts;
        Arrangement lowestCostArrangement = null;
        int priority2 = 1;
        for (int largeCount2 : largeCounts) {
            int length = iArr2.length;
            int i = 0;
            while (i < length) {
                int mediumCount2 = iArr2[i];
                int length2 = iArr.length;
                int i2 = 0;
                while (i2 < length2) {
                    int i3 = i2;
                    int i4 = length2;
                    int i5 = i;
                    int i6 = length;
                    Arrangement arrangement = new Arrangement(priority2, targetSmallSize, minSmallSize, maxSmallSize, iArr[i2], targetMediumSize, mediumCount2, targetLargeSize, largeCount2, availableSpace);
                    if (lowestCostArrangement == null || arrangement.cost < lowestCostArrangement.cost) {
                        lowestCostArrangement = arrangement;
                        if (lowestCostArrangement.cost == 0.0f) {
                            return lowestCostArrangement;
                        }
                    }
                    priority2++;
                    i2 = i3 + 1;
                    length2 = i4;
                    i = i5;
                    length = i6;
                }
                int i7 = length;
                i++;
            }
        }
        return lowestCostArrangement;
    }

    /* access modifiers changed from: package-private */
    public int getItemCount() {
        return this.smallCount + this.mediumCount + this.largeCount;
    }
}
