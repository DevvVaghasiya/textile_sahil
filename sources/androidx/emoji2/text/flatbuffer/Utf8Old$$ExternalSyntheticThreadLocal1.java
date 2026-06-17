package androidx.emoji2.text.flatbuffer;

import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.util.function.Supplier;

@SynthesizedClassV2(kind = 21, versionHash = "ea87655719898b9807d7a88878e9de051d12af172d2fab563c9881b5e404e7d4")
/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Utf8Old$$ExternalSyntheticThreadLocal1 extends ThreadLocal {
    public final /* synthetic */ Supplier initialValueSupplier;

    public /* synthetic */ Utf8Old$$ExternalSyntheticThreadLocal1(Supplier supplier) {
        this.initialValueSupplier = supplier;
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ Object initialValue() {
        return this.initialValueSupplier.get();
    }
}
