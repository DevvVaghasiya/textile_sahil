package kotlinx.coroutines.channels;

import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CancellableContinuationImplKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentKt;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.SelectKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000L2\b\u0012\u0004\u0012\u00028\u00000M:\u0001JB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0019\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0017¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\t\u001a\u00020\r2\u000e\u0010\u0007\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\fH\u0016¢\u0006\u0004\b\t\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\u000f\u0010\nJ\u000f\u0010\u0010\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\nJ\u000f\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u001b\u0010\u001cJ#\u0010\u001f\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00028\u00002\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001dH\u0014¢\u0006\u0004\b\u001f\u0010 J\u0015\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000!H\u0016¢\u0006\u0004\b\"\u0010#J4\u0010'\u001a\u00020\r2\u0010\b\u0002\u0010%\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010$2\u0010\b\u0002\u0010&\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010$H\u0010¢\u0006\u0004\b'\u0010(R\u001c\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0)8\u0002X\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010/\u001a\u00020,8TX\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0018\u00102\u001a\u000600j\u0002`18\u0002X\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u00104\u001a\u0004\b5\u00106R$\u0010;\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u00138B@BX\u000e¢\u0006\f\u001a\u0004\b8\u0010\u0015\"\u0004\b9\u0010:R\u0014\u0010<\u001a\u00020\b8TX\u0004¢\u0006\u0006\u001a\u0004\b<\u0010=R\u0014\u0010>\u001a\u00020\b8TX\u0004¢\u0006\u0006\u001a\u0004\b>\u0010=R$\u0010A\u001a\u00020\u00022\u0006\u00107\u001a\u00020\u00028B@BX\u000e¢\u0006\f\u001a\u0004\b?\u00106\"\u0004\b@\u0010\u0005R6\u0010D\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$0Bj\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000$`C8\u0002X\u0004¢\u0006\f\n\u0004\bD\u0010E\u0012\u0004\bF\u0010\u0011R$\u0010I\u001a\u00020\u00132\u0006\u00107\u001a\u00020\u00138B@BX\u000e¢\u0006\f\u001a\u0004\bG\u0010\u0015\"\u0004\bH\u0010:¨\u0006K"}, d2 = {"Lkotlinx/coroutines/channels/ArrayBroadcastChannel;", "E", "", "capacity", "<init>", "(I)V", "", "cause", "", "cancel", "(Ljava/lang/Throwable;)Z", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "", "(Ljava/util/concurrent/CancellationException;)V", "cancelInternal", "checkSubOffers", "()V", "close", "", "computeMinHead", "()J", "index", "elementAt", "(J)Ljava/lang/Object;", "element", "", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "offerSelectInternal", "(Ljava/lang/Object;Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ReceiveChannel;", "openSubscription", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;", "addSub", "removeSub", "updateHead", "(Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;)V", "", "buffer", "[Ljava/lang/Object;", "", "getBufferDebugString", "()Ljava/lang/String;", "bufferDebugString", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "bufferLock", "Ljava/util/concurrent/locks/ReentrantLock;", "I", "getCapacity", "()I", "value", "getHead", "setHead", "(J)V", "head", "isBufferAlwaysFull", "()Z", "isBufferFull", "getSize", "setSize", "size", "", "Lkotlinx/coroutines/internal/SubscribersList;", "subscribers", "Ljava/util/List;", "getSubscribers$annotations", "getTail", "setTail", "tail", "Subscriber", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/AbstractSendChannel;", "Lkotlinx/coroutines/channels/BroadcastChannel;"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* compiled from: ArrayBroadcastChannel.kt */
public final class ArrayBroadcastChannel<E> extends AbstractSendChannel<E> implements BroadcastChannel<E> {
    private volatile /* synthetic */ long _head;
    private volatile /* synthetic */ int _size;
    private volatile /* synthetic */ long _tail;
    private final Object[] buffer;
    private final ReentrantLock bufferLock;
    private final int capacity;
    private final List<Subscriber<E>> subscribers;

    private static /* synthetic */ void getSubscribers$annotations() {
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public ArrayBroadcastChannel(int capacity2) {
        super((Function1) null);
        this.capacity = capacity2;
        if (capacity2 < 1 ? false : true) {
            this.bufferLock = new ReentrantLock();
            this.buffer = new Object[capacity2];
            this._head = 0;
            this._tail = 0;
            this._size = 0;
            this.subscribers = ConcurrentKt.subscriberList();
            return;
        }
        throw new IllegalArgumentException(("ArrayBroadcastChannel capacity must be at least 1, but " + capacity2 + " was specified").toString());
    }

    private final long getHead() {
        return this._head;
    }

    private final void setHead(long value) {
        this._head = value;
    }

    /* access modifiers changed from: private */
    public final long getTail() {
        return this._tail;
    }

    private final void setTail(long value) {
        this._tail = value;
    }

    private final int getSize() {
        return this._size;
    }

    private final void setSize(int value) {
        this._size = value;
    }

    /* access modifiers changed from: protected */
    public boolean isBufferAlwaysFull() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isBufferFull() {
        return getSize() >= this.capacity;
    }

    public ReceiveChannel<E> openSubscription() {
        Subscriber it = new Subscriber(this);
        updateHead$default(this, it, (Subscriber) null, 2, (Object) null);
        return it;
    }

    public boolean close(Throwable cause) {
        if (!super.close(cause)) {
            return false;
        }
        checkSubOffers();
        return true;
    }

    public void cancel(CancellationException cause) {
        cancel(cause);
    }

    /* access modifiers changed from: private */
    /* renamed from: cancelInternal */
    public final boolean cancel(Throwable cause) {
        boolean close = close(cause);
        boolean z = close;
        for (Subscriber<E> sub : this.subscribers) {
            sub.cancel(cause);
        }
        return close;
    }

    /* access modifiers changed from: protected */
    public Object offerInternal(E element) {
        Lock lock = this.bufferLock;
        lock.lock();
        try {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            int size = getSize();
            if (size >= this.capacity) {
                Symbol symbol = AbstractChannelKt.OFFER_FAILED;
                lock.unlock();
                return symbol;
            }
            long tail = getTail();
            this.buffer[(int) (tail % ((long) this.capacity))] = element;
            setSize(size + 1);
            setTail(1 + tail);
            Unit unit = Unit.INSTANCE;
            lock.unlock();
            checkSubOffers();
            return AbstractChannelKt.OFFER_SUCCESS;
        } finally {
            lock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public Object offerSelectInternal(E element, SelectInstance<?> select) {
        Lock lock = this.bufferLock;
        lock.lock();
        try {
            Closed<?> closedForSend = getClosedForSend();
            if (closedForSend != null) {
                return closedForSend;
            }
            int size = getSize();
            if (size >= this.capacity) {
                Symbol symbol = AbstractChannelKt.OFFER_FAILED;
                lock.unlock();
                return symbol;
            } else if (!select.trySelect()) {
                Object already_selected = SelectKt.getALREADY_SELECTED();
                lock.unlock();
                return already_selected;
            } else {
                long tail = getTail();
                this.buffer[(int) (tail % ((long) this.capacity))] = element;
                setSize(size + 1);
                setTail(1 + tail);
                Unit unit = Unit.INSTANCE;
                lock.unlock();
                checkSubOffers();
                return AbstractChannelKt.OFFER_SUCCESS;
            }
        } finally {
            lock.unlock();
        }
    }

    private final void checkSubOffers() {
        boolean updated = false;
        boolean hasSubs = false;
        for (Subscriber<E> sub : this.subscribers) {
            hasSubs = true;
            if (sub.checkOffer()) {
                updated = true;
            }
        }
        if (updated || !hasSubs) {
            updateHead$default(this, (Subscriber) null, (Subscriber) null, 3, (Object) null);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b0, code lost:
        if (kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED() == false) goto L_0x00c6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b6, code lost:
        if (r4 != kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN) goto L_0x00bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b8, code lost:
        r21 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00bb, code lost:
        r21 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00bd, code lost:
        if (r21 == false) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00c5, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c6, code lost:
        r22 = r3;
        r16 = r4;
        r1.buffer[(int) (r10 % ((long) r1.capacity))] = r2.getPollResult();
        setSize(r0 + 1);
        setTail(r10 + 1);
        r0 = kotlin.Unit.INSTANCE;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void updateHead(kotlinx.coroutines.channels.ArrayBroadcastChannel.Subscriber<E> r24, kotlinx.coroutines.channels.ArrayBroadcastChannel.Subscriber<E> r25) {
        /*
            r23 = this;
            r1 = r23
            r0 = r24
            r2 = r25
        L_0x0006:
            r3 = 0
            java.util.concurrent.locks.ReentrantLock r4 = r1.bufferLock
            r5 = 0
            r6 = r4
            java.util.concurrent.locks.Lock r6 = (java.util.concurrent.locks.Lock) r6
            r6.lock()
            r7 = 0
            if (r0 == 0) goto L_0x0032
            long r8 = r23.getTail()     // Catch:{ all -> 0x002b }
            r0.setSubHead(r8)     // Catch:{ all -> 0x002b }
            java.util.List<kotlinx.coroutines.channels.ArrayBroadcastChannel$Subscriber<E>> r8 = r1.subscribers     // Catch:{ all -> 0x002b }
            boolean r8 = r8.isEmpty()     // Catch:{ all -> 0x002b }
            java.util.List<kotlinx.coroutines.channels.ArrayBroadcastChannel$Subscriber<E>> r9 = r1.subscribers     // Catch:{ all -> 0x002b }
            r9.add(r0)     // Catch:{ all -> 0x002b }
            if (r8 != 0) goto L_0x0032
            r6.unlock()
            return
        L_0x002b:
            r0 = move-exception
            r17 = r4
            r18 = r5
            goto L_0x012f
        L_0x0032:
            if (r2 == 0) goto L_0x0049
            java.util.List<kotlinx.coroutines.channels.ArrayBroadcastChannel$Subscriber<E>> r0 = r1.subscribers     // Catch:{ all -> 0x002b }
            r0.remove(r2)     // Catch:{ all -> 0x002b }
            long r8 = r23.getHead()     // Catch:{ all -> 0x002b }
            long r10 = r2.getSubHead()     // Catch:{ all -> 0x002b }
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0049
            r6.unlock()
            return
        L_0x0049:
            long r8 = r23.computeMinHead()     // Catch:{ all -> 0x012a }
            long r10 = r23.getTail()     // Catch:{ all -> 0x012a }
            long r12 = r23.getHead()     // Catch:{ all -> 0x012a }
            long r14 = kotlin.ranges.RangesKt.coerceAtMost((long) r8, (long) r10)     // Catch:{ all -> 0x012a }
            int r0 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r0 > 0) goto L_0x0061
            r6.unlock()
            return
        L_0x0061:
            int r0 = r23.getSize()     // Catch:{ all -> 0x012a }
        L_0x0065:
            int r2 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r2 >= 0) goto L_0x0125
            java.lang.Object[] r2 = r1.buffer     // Catch:{ all -> 0x011d }
            r16 = r3
            int r3 = r1.capacity     // Catch:{ all -> 0x0115 }
            r17 = r4
            r18 = r5
            long r4 = (long) r3
            long r4 = r12 % r4
            int r4 = (int) r4     // Catch:{ all -> 0x0111 }
            r5 = 0
            r2[r4] = r5     // Catch:{ all -> 0x0111 }
            if (r0 < r3) goto L_0x007e
            r3 = 1
            goto L_0x007f
        L_0x007e:
            r3 = 0
        L_0x007f:
            r19 = 1
            long r12 = r12 + r19
            r1.setHead(r12)     // Catch:{ all -> 0x0111 }
            int r0 = r0 + -1
            r1.setSize(r0)     // Catch:{ all -> 0x0111 }
            if (r3 == 0) goto L_0x0107
        L_0x008d:
            kotlinx.coroutines.channels.Send r21 = r23.takeFirstSendOrPeekClosed()     // Catch:{ all -> 0x0111 }
            if (r21 != 0) goto L_0x009b
            r3 = r16
            r4 = r17
            r5 = r18
            goto L_0x0065
        L_0x009b:
            r16 = r21
            r2 = r16
            boolean r4 = r2 instanceof kotlinx.coroutines.channels.Closed     // Catch:{ all -> 0x0104 }
            if (r4 != 0) goto L_0x00fb
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ all -> 0x0104 }
            kotlinx.coroutines.internal.Symbol r4 = r2.tryResumeSend(r5)     // Catch:{ all -> 0x0104 }
            if (r4 == 0) goto L_0x00f3
            boolean r16 = kotlinx.coroutines.DebugKt.getASSERTIONS_ENABLED()     // Catch:{ all -> 0x0104 }
            if (r16 == 0) goto L_0x00c6
            r16 = 0
            kotlinx.coroutines.internal.Symbol r5 = kotlinx.coroutines.CancellableContinuationImplKt.RESUME_TOKEN     // Catch:{ all -> 0x0104 }
            if (r4 != r5) goto L_0x00bb
            r21 = 1
            goto L_0x00bd
        L_0x00bb:
            r21 = 0
        L_0x00bd:
            if (r21 == 0) goto L_0x00c0
            goto L_0x00c6
        L_0x00c0:
            java.lang.AssertionError r5 = new java.lang.AssertionError     // Catch:{ all -> 0x0104 }
            r5.<init>()     // Catch:{ all -> 0x0104 }
            throw r5     // Catch:{ all -> 0x0104 }
        L_0x00c6:
            java.lang.Object[] r5 = r1.buffer     // Catch:{ all -> 0x0104 }
            r22 = r3
            int r3 = r1.capacity     // Catch:{ all -> 0x0104 }
            r16 = r4
            long r3 = (long) r3     // Catch:{ all -> 0x0104 }
            long r3 = r10 % r3
            int r3 = (int) r3     // Catch:{ all -> 0x0104 }
            java.lang.Object r4 = r2.getPollResult()     // Catch:{ all -> 0x0104 }
            r5[r3] = r4     // Catch:{ all -> 0x0104 }
            int r3 = r0 + 1
            r1.setSize(r3)     // Catch:{ all -> 0x0104 }
            long r3 = r10 + r19
            r1.setTail(r3)     // Catch:{ all -> 0x0104 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0104 }
            r6.unlock()
            r2.completeResumeSend()
            r23.checkSubOffers()
            r0 = 0
            r2 = 0
            goto L_0x0006
        L_0x00f3:
            r22 = r3
            r16 = r4
            r16 = r2
            r5 = 0
            goto L_0x008d
        L_0x00fb:
            r22 = r3
            r3 = r2
            r4 = r17
            r5 = r18
            goto L_0x0065
        L_0x0104:
            r0 = move-exception
            r3 = r2
            goto L_0x012f
        L_0x0107:
            r22 = r3
            r3 = r16
            r4 = r17
            r5 = r18
            goto L_0x0065
        L_0x0111:
            r0 = move-exception
            r3 = r16
            goto L_0x012f
        L_0x0115:
            r0 = move-exception
            r17 = r4
            r18 = r5
            r3 = r16
            goto L_0x012f
        L_0x011d:
            r0 = move-exception
            r16 = r3
            r17 = r4
            r18 = r5
            goto L_0x012f
        L_0x0125:
            r6.unlock()
            return
        L_0x012a:
            r0 = move-exception
            r17 = r4
            r18 = r5
        L_0x012f:
            r6.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.channels.ArrayBroadcastChannel.updateHead(kotlinx.coroutines.channels.ArrayBroadcastChannel$Subscriber, kotlinx.coroutines.channels.ArrayBroadcastChannel$Subscriber):void");
    }

    static /* synthetic */ void updateHead$default(ArrayBroadcastChannel arrayBroadcastChannel, Subscriber subscriber, Subscriber subscriber2, int i, Object obj) {
        if ((i & 1) != 0) {
            subscriber = null;
        }
        if ((i & 2) != 0) {
            subscriber2 = null;
        }
        arrayBroadcastChannel.updateHead(subscriber, subscriber2);
    }

    private final long computeMinHead() {
        long minHead = Long.MAX_VALUE;
        for (Subscriber<E> sub : this.subscribers) {
            minHead = RangesKt.coerceAtMost(minHead, sub.getSubHead());
        }
        return minHead;
    }

    /* access modifiers changed from: private */
    public final E elementAt(long index) {
        return this.buffer[(int) (index % ((long) this.capacity))];
    }

    @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u00028\u00010'2\b\u0012\u0004\u0012\u00028\u00010(B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0019\u0010\u000b\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\r\u0010\bJ\u0011\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0011\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u0014¢\u0006\u0004\b\u0011\u0010\u0010J\u001d\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0014¢\u0006\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\bR\u0014\u0010\u0018\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\bR\u0014\u0010\u0019\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\bR\u0014\u0010\u001a\u001a\u00020\u00068TX\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\bR$\u0010!\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b8F@FX\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0018\u0010$\u001a\u00060\"j\u0002`#8\u0002X\u0004¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006&"}, d2 = {"Lkotlinx/coroutines/channels/ArrayBroadcastChannel$Subscriber;", "E", "Lkotlinx/coroutines/channels/ArrayBroadcastChannel;", "broadcastChannel", "<init>", "(Lkotlinx/coroutines/channels/ArrayBroadcastChannel;)V", "", "checkOffer", "()Z", "", "cause", "close", "(Ljava/lang/Throwable;)Z", "needsToCheckOfferWithoutLock", "", "peekUnderLock", "()Ljava/lang/Object;", "pollInternal", "Lkotlinx/coroutines/selects/SelectInstance;", "select", "pollSelectInternal", "(Lkotlinx/coroutines/selects/SelectInstance;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/ArrayBroadcastChannel;", "isBufferAlwaysEmpty", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "", "value", "getSubHead", "()J", "setSubHead", "(J)V", "subHead", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "subLock", "Ljava/util/concurrent/locks/ReentrantLock;", "kotlinx-coroutines-core", "Lkotlinx/coroutines/channels/AbstractChannel;", "Lkotlinx/coroutines/channels/ReceiveChannel;"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* compiled from: ArrayBroadcastChannel.kt */
    private static final class Subscriber<E> extends AbstractChannel<E> implements ReceiveChannel<E> {
        private volatile /* synthetic */ long _subHead = 0;
        private final ArrayBroadcastChannel<E> broadcastChannel;
        private final ReentrantLock subLock = new ReentrantLock();

        public Subscriber(ArrayBroadcastChannel<E> broadcastChannel2) {
            super((Function1) null);
            this.broadcastChannel = broadcastChannel2;
        }

        public final long getSubHead() {
            return this._subHead;
        }

        public final void setSubHead(long value) {
            this._subHead = value;
        }

        /* access modifiers changed from: protected */
        public boolean isBufferAlwaysEmpty() {
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean isBufferEmpty() {
            return getSubHead() >= this.broadcastChannel.getTail();
        }

        /* access modifiers changed from: protected */
        public boolean isBufferAlwaysFull() {
            throw new IllegalStateException("Should not be used".toString());
        }

        /* access modifiers changed from: protected */
        public boolean isBufferFull() {
            throw new IllegalStateException("Should not be used".toString());
        }

        public boolean close(Throwable cause) {
            boolean wasClosed = super.close(cause);
            if (wasClosed) {
                ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, (Subscriber) null, this, 1, (Object) null);
                Lock lock = this.subLock;
                lock.lock();
                try {
                    setSubHead(this.broadcastChannel.getTail());
                    Unit unit = Unit.INSTANCE;
                } finally {
                    lock.unlock();
                }
            }
            return wasClosed;
        }

        public final boolean checkOffer() {
            boolean updated = false;
            Closed closed = null;
            while (true) {
                if (!needsToCheckOfferWithoutLock() || !this.subLock.tryLock()) {
                    break;
                }
                try {
                    Object result = peekUnderLock();
                    if (result != AbstractChannelKt.POLL_FAILED) {
                        if (result instanceof Closed) {
                            closed = (Closed) result;
                            break;
                        }
                        ReceiveOrClosed takeFirstReceiveOrPeekClosed = takeFirstReceiveOrPeekClosed();
                        if (takeFirstReceiveOrPeekClosed == null) {
                            break;
                        }
                        ReceiveOrClosed receive = takeFirstReceiveOrPeekClosed;
                        if (receive instanceof Closed) {
                            break;
                        }
                        Symbol token = receive.tryResumeReceive(result, (LockFreeLinkedListNode.PrepareOp) null);
                        if (token != null) {
                            if (DebugKt.getASSERTIONS_ENABLED()) {
                                if (!(token == CancellableContinuationImplKt.RESUME_TOKEN)) {
                                    throw new AssertionError();
                                }
                            }
                            setSubHead(1 + getSubHead());
                            updated = true;
                            this.subLock.unlock();
                            receive.completeResumeReceive(result);
                        }
                    }
                } finally {
                    this.subLock.unlock();
                }
            }
            this.subLock.unlock();
            if (closed != null) {
                close(closed.closeCause);
            }
            return updated;
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: protected */
        public Object pollInternal() {
            boolean updated = false;
            Lock lock = this.subLock;
            lock.lock();
            try {
                Object result = peekUnderLock();
                if (!(result instanceof Closed) && result != AbstractChannelKt.POLL_FAILED) {
                    setSubHead(1 + getSubHead());
                    updated = true;
                }
                lock.unlock();
                Object result2 = result;
                Closed it = result2 instanceof Closed ? (Closed) result2 : null;
                if (it != null) {
                    close(it.closeCause);
                }
                if (checkOffer()) {
                    updated = true;
                }
                if (updated) {
                    ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, (Subscriber) null, (Subscriber) null, 3, (Object) null);
                }
                return result2;
            } catch (Throwable th) {
                lock.unlock();
                throw th;
            }
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: protected */
        public Object pollSelectInternal(SelectInstance<?> select) {
            boolean updated = false;
            Lock lock = this.subLock;
            lock.lock();
            try {
                Object result = peekUnderLock();
                if (!(result instanceof Closed) && result != AbstractChannelKt.POLL_FAILED) {
                    if (!select.trySelect()) {
                        result = SelectKt.getALREADY_SELECTED();
                    } else {
                        setSubHead(1 + getSubHead());
                        updated = true;
                    }
                }
                lock.unlock();
                Object result2 = result;
                Closed it = result2 instanceof Closed ? (Closed) result2 : null;
                if (it != null) {
                    close(it.closeCause);
                }
                if (checkOffer()) {
                    updated = true;
                }
                if (updated) {
                    ArrayBroadcastChannel.updateHead$default(this.broadcastChannel, (Subscriber) null, (Subscriber) null, 3, (Object) null);
                }
                return result2;
            } catch (Throwable th) {
                lock.unlock();
                throw th;
            }
        }

        private final boolean needsToCheckOfferWithoutLock() {
            if (getClosedForReceive() != null) {
                return false;
            }
            if (!isBufferEmpty() || this.broadcastChannel.getClosedForReceive() != null) {
                return true;
            }
            return false;
        }

        private final Object peekUnderLock() {
            long subHead = getSubHead();
            Closed closedBroadcast = this.broadcastChannel.getClosedForReceive();
            if (subHead < this.broadcastChannel.getTail()) {
                Object result = this.broadcastChannel.elementAt(subHead);
                Closed closedSub = getClosedForReceive();
                if (closedSub != null) {
                    return closedSub;
                }
                return result;
            } else if (closedBroadcast != null) {
                return closedBroadcast;
            } else {
                Closed<?> closedForReceive = getClosedForReceive();
                return closedForReceive == null ? AbstractChannelKt.POLL_FAILED : closedForReceive;
            }
        }
    }

    /* access modifiers changed from: protected */
    public String getBufferDebugString() {
        return "(buffer:capacity=" + this.buffer.length + ",size=" + getSize() + ')';
    }
}
