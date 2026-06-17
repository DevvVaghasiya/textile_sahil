package com.razzaghimahdi78.dotsloading.core;

public class ConvertorImpl implements Convertor {
    public int convertDotSize(int value) {
        switch (value) {
            case 0:
                return 10;
            case 1:
                return 15;
            case 3:
                return 30;
            case 4:
                return 40;
            default:
                return 20;
        }
    }

    /* renamed from: com.razzaghimahdi78.dotsloading.core.ConvertorImpl$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$razzaghimahdi78$dotsloading$core$DotSize;

        static {
            int[] iArr = new int[DotSize.values().length];
            $SwitchMap$com$razzaghimahdi78$dotsloading$core$DotSize = iArr;
            try {
                iArr[DotSize.TINY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$razzaghimahdi78$dotsloading$core$DotSize[DotSize.SMALL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$razzaghimahdi78$dotsloading$core$DotSize[DotSize.BIG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$razzaghimahdi78$dotsloading$core$DotSize[DotSize.HUGE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public int convertDotSize(DotSize value) {
        switch (AnonymousClass1.$SwitchMap$com$razzaghimahdi78$dotsloading$core$DotSize[value.ordinal()]) {
            case 1:
                return 10;
            case 2:
                return 15;
            case 3:
                return 30;
            case 4:
                return 40;
            default:
                return 20;
        }
    }
}
