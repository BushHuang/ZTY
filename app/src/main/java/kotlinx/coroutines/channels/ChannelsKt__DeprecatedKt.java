package kotlinx.coroutines.channels;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

@Metadata(d1 = {"\u0000 \u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aJ\u0010\u0000\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u00072\u001a\u0010\b\u001a\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\n0\t\"\u0006\u0012\u0002\b\u00030\nH\u0001¢\u0006\u0002\u0010\u000b\u001a!\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a1\u0010\u0010\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\u0005\u0012\u0004\u0012\u00020\u00060\u0001j\u0002`\u0007*\u0006\u0012\u0002\b\u00030\nH\u0001\u001a!\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0007\u001aZ\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010\u0018\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a0\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001aT\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u0010!\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\"\u001a\u00020\u0012H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010#\u001a+\u0010$\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\"\u001a\u00020\u0012H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010#\u001aT\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ai\u0010&\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001727\u0010 \u001a3\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u001aT\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a$\u0010*\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\b\b\u0000\u0010\u000e*\u00020\u001b*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\nH\u0001\u001aA\u0010+\u001a\u0002H,\"\b\b\u0000\u0010\u000e*\u00020\u001b\"\u0010\b\u0001\u0010,*\n\u0012\u0006\b\u0000\u0012\u0002H\u000e0-*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0087@ø\u0001\u0000¢\u0006\u0002\u0010/\u001a?\u0010+\u001a\u0002H,\"\b\b\u0000\u0010\u000e*\u00020\u001b\"\u000e\b\u0001\u0010,*\b\u0012\u0004\u0012\u0002H\u000e00*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0087@ø\u0001\u0000¢\u0006\u0002\u00101\u001a!\u00102\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a#\u00103\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a`\u00104\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172(\u00106\u001a$\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\n0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a)\u00107\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u00108\u001a\u0002H\u000eH\u0087@ø\u0001\u0000¢\u0006\u0002\u00109\u001a!\u0010:\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a)\u0010;\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u00108\u001a\u0002H\u000eH\u0087@ø\u0001\u0000¢\u0006\u0002\u00109\u001a#\u0010<\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aZ\u0010=\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u00106\u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0001ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001ao\u0010>\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001727\u00106\u001a3\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0001ø\u0001\u0000¢\u0006\u0002\u0010(\u001au\u0010?\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\b\b\u0001\u00105*\u00020\u001b*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001729\u00106\u001a5\b\u0001\u0012\u0013\u0012\u00110\u0012¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0'H\u0007ø\u0001\u0000¢\u0006\u0002\u0010(\u001a`\u0010@\u001a\b\u0012\u0004\u0012\u0002H50\n\"\u0004\b\u0000\u0010\u000e\"\b\b\u0001\u00105*\u00020\u001b*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172$\u00106\u001a \b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001H50\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a?\u0010A\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u001a\u0010B\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`DH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010E\u001a?\u0010F\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u001a\u0010B\u001a\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000e0Cj\n\u0012\u0006\b\u0000\u0012\u0002H\u000e`DH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010E\u001a!\u0010G\u001a\u00020\r\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a$\u0010H\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\b\b\u0000\u0010\u000e*\u00020\u001b*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\nH\u0007\u001a!\u0010I\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a#\u0010J\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a0\u0010K\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010\u001e\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001aT\u0010L\u001a\b\u0012\u0004\u0012\u0002H\u000e0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\"\u0010 \u001a\u001e\b\u0001\u0012\u0004\u0012\u0002H\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0019H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001c\u001a9\u0010M\u001a\u0002H,\"\u0004\b\u0000\u0010\u000e\"\u000e\b\u0001\u0010,*\b\u0012\u0004\u0012\u0002H\u000e00*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0081@ø\u0001\u0000¢\u0006\u0002\u00101\u001a;\u0010N\u001a\u0002H,\"\u0004\b\u0000\u0010\u000e\"\u0010\b\u0001\u0010,*\n\u0012\u0006\b\u0000\u0012\u0002H\u000e0-*\b\u0012\u0004\u0012\u0002H\u000e0\n2\u0006\u0010.\u001a\u0002H,H\u0081@ø\u0001\u0000¢\u0006\u0002\u0010/\u001a?\u0010O\u001a\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0P\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010Q*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0R0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aU\u0010O\u001a\u0002HS\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010Q\"\u0018\b\u0002\u0010S*\u0012\u0012\u0006\b\u0000\u0012\u0002H\u0015\u0012\u0006\b\u0000\u0012\u0002HQ0T*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002HQ0R0\n2\u0006\u0010.\u001a\u0002HSH\u0081@ø\u0001\u0000¢\u0006\u0002\u0010U\u001a'\u0010V\u001a\b\u0012\u0004\u0012\u0002H\u000e0W\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a'\u0010X\u001a\b\u0012\u0004\u0012\u0002H\u000e0Y\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0081@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a'\u0010Z\u001a\b\u0012\u0004\u0012\u0002H\u000e0[\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\nH\u0087@ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001a.\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0]0\n\"\u0004\b\u0000\u0010\u000e*\b\u0012\u0004\u0012\u0002H\u000e0\n2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007\u001a?\u0010^\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u0002H50R0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105*\b\u0012\u0004\u0012\u0002H\u000e0\n2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002H50\nH\u0087\u0004\u001az\u0010^\u001a\b\u0012\u0004\u0012\u0002HQ0\n\"\u0004\b\u0000\u0010\u000e\"\u0004\b\u0001\u00105\"\u0004\b\u0002\u0010Q*\b\u0012\u0004\u0012\u0002H\u000e0\n2\f\u0010_\u001a\b\u0012\u0004\u0012\u0002H50\n2\b\b\u0002\u0010\u0016\u001a\u00020\u001726\u00106\u001a2\u0012\u0013\u0012\u0011H\u000e¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(`\u0012\u0013\u0012\u0011H5¢\u0006\f\b\u0003\u0012\b\b\u0004\u0012\u0004\b\b(a\u0012\u0004\u0012\u0002HQ0\u0019H\u0001\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006b"}, d2 = {"consumesAll", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "channels", "", "Lkotlinx/coroutines/channels/ReceiveChannel;", "([Lkotlinx/coroutines/channels/ReceiveChannel;)Lkotlin/jvm/functions/Function1;", "any", "", "E", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumes", "count", "", "distinct", "distinctBy", "K", "context", "Lkotlin/coroutines/CoroutineContext;", "selector", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/channels/ReceiveChannel;", "drop", "n", "dropWhile", "predicate", "elementAt", "index", "(Lkotlinx/coroutines/channels/ReceiveChannel;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "elementAtOrNull", "filter", "filterIndexed", "Lkotlin/Function3;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function3;)Lkotlinx/coroutines/channels/ReceiveChannel;", "filterNot", "filterNotNull", "filterNotNullTo", "C", "", "destination", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/channels/SendChannel;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Lkotlinx/coroutines/channels/SendChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "first", "firstOrNull", "flatMap", "R", "transform", "indexOf", "element", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "last", "lastIndexOf", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "mapNotNull", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Comparator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "minWith", "none", "requireNoNulls", "single", "singleOrNull", "take", "takeWhile", "toChannel", "toCollection", "toMap", "", "V", "Lkotlin/Pair;", "M", "", "(Lkotlinx/coroutines/channels/ReceiveChannel;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toMutableList", "", "toMutableSet", "", "toSet", "", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "kotlinx-coroutines-core"}, k = 5, mv = {1, 6, 0}, xi = 48, xs = "kotlinx/coroutines/channels/ChannelsKt")
final class ChannelsKt__DeprecatedKt {

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0}, l = {404}, m = "any", n = {"$this$consume$iv"}, s = {"L$0"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.any(null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "count", n = {"count", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.count(null, this);
        }
    }

    @Metadata(d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u0001H\u008a@"}, d2 = {"<anonymous>", "E", "it"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinct$1", f = "Deprecated.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<E, Continuation<? super E>, Object> {
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public Object invoke(Object obj, Object obj2) {
            return invoke((AnonymousClass1<E>) obj, (Continuation<? super AnonymousClass1<E>>) obj2);
        }

        public final Object invoke(E e, Continuation<? super E> continuation) {
            return ((AnonymousClass1) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return this.L$0;
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "E", "K", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$distinctBy$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 1, 2, 2, 2}, l = {387, 388, 390}, m = "invokeSuspend", n = {"$this$produce", "keys", "$this$produce", "keys", "e", "$this$produce", "keys", "k"}, s = {"L$0", "L$1", "L$0", "L$1", "L$3", "L$0", "L$1", "L$3"})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final Function2<E, Continuation<? super K>, Object> $selector;
        final ReceiveChannel<E> $this_distinctBy;
        private Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        AnonymousClass1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_distinctBy = receiveChannel;
            this.$selector = function2;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_distinctBy, this.$selector, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ProducerScope producerScope;
            AnonymousClass1<E> anonymousClass1;
            HashSet hashSet;
            ChannelIterator<E> it;
            ProducerScope producerScope2;
            HashSet hashSet2;
            E e;
            ChannelIterator<E> channelIterator;
            Object obj2;
            AnonymousClass1<E> anonymousClass12;
            ProducerScope producerScope3;
            HashSet hashSet3;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope4 = (ProducerScope) this.L$0;
                HashSet hashSet4 = new HashSet();
                producerScope = producerScope4;
                anonymousClass1 = this;
                hashSet = hashSet4;
                it = this.$this_distinctBy.iterator();
                anonymousClass1.L$0 = producerScope;
                anonymousClass1.L$1 = hashSet;
                anonymousClass1.L$2 = it;
                anonymousClass1.L$3 = null;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                }
            } else if (i == 1) {
                ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$2;
                HashSet hashSet5 = (HashSet) this.L$1;
                ProducerScope producerScope5 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope3 = producerScope5;
                hashSet3 = hashSet5;
                channelIterator = channelIterator2;
                obj2 = coroutine_suspended;
                anonymousClass12 = this;
                if (((Boolean) obj).booleanValue()) {
                }
            } else if (i == 2) {
                Object obj3 = this.L$3;
                channelIterator = (ChannelIterator) this.L$2;
                HashSet hashSet6 = (HashSet) this.L$1;
                ProducerScope producerScope6 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope2 = producerScope6;
                hashSet2 = hashSet6;
                e = obj3;
                obj2 = coroutine_suspended;
                anonymousClass12 = this;
                if (hashSet2.contains(obj)) {
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                Object obj4 = this.L$3;
                channelIterator = (ChannelIterator) this.L$2;
                HashSet hashSet7 = (HashSet) this.L$1;
                ProducerScope producerScope7 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                anonymousClass1 = this;
                hashSet7.add(obj4);
                it = channelIterator;
                hashSet = hashSet7;
                producerScope = producerScope7;
                anonymousClass1.L$0 = producerScope;
                anonymousClass1.L$1 = hashSet;
                anonymousClass1.L$2 = it;
                anonymousClass1.L$3 = null;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj5 = coroutine_suspended;
                anonymousClass12 = anonymousClass1;
                obj = objHasNext;
                producerScope3 = producerScope;
                hashSet3 = hashSet;
                channelIterator = it;
                obj2 = obj5;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                E next = channelIterator.next();
                Function2<E, Continuation<? super K>, Object> function2 = anonymousClass12.$selector;
                anonymousClass12.L$0 = producerScope3;
                anonymousClass12.L$1 = hashSet3;
                anonymousClass12.L$2 = channelIterator;
                anonymousClass12.L$3 = next;
                anonymousClass12.label = 2;
                Object objInvoke = function2.invoke(next, anonymousClass12);
                if (objInvoke == obj2) {
                    return obj2;
                }
                HashSet hashSet8 = hashSet3;
                e = next;
                obj = objInvoke;
                producerScope2 = producerScope3;
                hashSet2 = hashSet8;
                if (hashSet2.contains(obj)) {
                    anonymousClass12.L$0 = producerScope2;
                    anonymousClass12.L$1 = hashSet2;
                    anonymousClass12.L$2 = channelIterator;
                    anonymousClass12.L$3 = obj;
                    anonymousClass12.label = 3;
                    if (producerScope2.send(e, anonymousClass12) == obj2) {
                        return obj2;
                    }
                    hashSet7 = hashSet2;
                    producerScope7 = producerScope2;
                    Object obj6 = obj2;
                    obj4 = obj;
                    anonymousClass1 = anonymousClass12;
                    coroutine_suspended = obj6;
                    hashSet7.add(obj4);
                    it = channelIterator;
                    hashSet = hashSet7;
                    producerScope = producerScope7;
                    anonymousClass1.L$0 = producerScope;
                    anonymousClass1.L$1 = hashSet;
                    anonymousClass1.L$2 = it;
                    anonymousClass1.L$3 = null;
                    anonymousClass1.label = 1;
                    objHasNext = it.hasNext(anonymousClass1);
                    if (objHasNext != coroutine_suspended) {
                    }
                } else {
                    anonymousClass1 = anonymousClass12;
                    coroutine_suspended = obj2;
                    it = channelIterator;
                    hashSet = hashSet2;
                    producerScope = producerScope2;
                    anonymousClass1.L$0 = producerScope;
                    anonymousClass1.L$1 = hashSet;
                    anonymousClass1.L$2 = it;
                    anonymousClass1.L$3 = null;
                    anonymousClass1.label = 1;
                    objHasNext = it.hasNext(anonymousClass1);
                    if (objHasNext != coroutine_suspended) {
                    }
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$drop$1", f = "Deprecated.kt", i = {0, 0, 1, 2}, l = {164, 169, 170}, m = "invokeSuspend", n = {"$this$produce", "remaining", "$this$produce", "$this$produce"}, s = {"L$0", "I$0", "L$0", "L$0"})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final int $n;
        final ReceiveChannel<E> $this_drop;
        int I$0;
        private Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(int i, ReceiveChannel<? extends E> receiveChannel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$n = i;
            this.$this_drop = receiveChannel;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$n, this.$this_drop, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ProducerScope producerScope;
            Object obj2;
            AnonymousClass1<E> anonymousClass1;
            ProducerScope producerScope2;
            AnonymousClass1<E> anonymousClass12;
            ChannelIterator<E> it;
            int i;
            ChannelIterator<E> it2;
            Object objHasNext;
            ProducerScope producerScope3;
            Object objHasNext2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                producerScope = (ProducerScope) this.L$0;
                boolean z = this.$n >= 0;
                int i3 = this.$n;
                if (!z) {
                    throw new IllegalArgumentException(("Requested element count " + i3 + " is less than zero.").toString());
                }
                if (i3 > 0) {
                    producerScope2 = producerScope;
                    anonymousClass12 = this;
                    it = this.$this_drop.iterator();
                    i = i3;
                    anonymousClass12.L$0 = producerScope2;
                    anonymousClass12.L$1 = it;
                    anonymousClass12.I$0 = i;
                    anonymousClass12.label = 1;
                    objHasNext = it.hasNext(anonymousClass12);
                    if (objHasNext != coroutine_suspended) {
                    }
                } else {
                    obj2 = coroutine_suspended;
                    anonymousClass1 = this;
                    it2 = anonymousClass1.$this_drop.iterator();
                    anonymousClass1.L$0 = producerScope;
                    anonymousClass1.L$1 = it2;
                    anonymousClass1.label = 2;
                    objHasNext2 = it2.hasNext(anonymousClass1);
                    if (objHasNext2 != obj2) {
                    }
                }
            } else if (i2 == 1) {
                int i4 = this.I$0;
                ChannelIterator<E> channelIterator = (ChannelIterator) this.L$1;
                ProducerScope producerScope4 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope5 = producerScope4;
                ChannelIterator<E> channelIterator2 = channelIterator;
                int i5 = i4;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                if (((Boolean) obj).booleanValue()) {
                    channelIterator2.next();
                    int i6 = i5 - 1;
                    if (i6 != 0) {
                        it = channelIterator2;
                        producerScope2 = producerScope5;
                        Object obj3 = obj2;
                        i = i6;
                        anonymousClass12 = anonymousClass1;
                        coroutine_suspended = obj3;
                        anonymousClass12.L$0 = producerScope2;
                        anonymousClass12.L$1 = it;
                        anonymousClass12.I$0 = i;
                        anonymousClass12.label = 1;
                        objHasNext = it.hasNext(anonymousClass12);
                        if (objHasNext != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Object obj4 = coroutine_suspended;
                        anonymousClass1 = anonymousClass12;
                        obj = objHasNext;
                        producerScope5 = producerScope2;
                        channelIterator2 = it;
                        i5 = i;
                        obj2 = obj4;
                        if (((Boolean) obj).booleanValue()) {
                        }
                    }
                }
                producerScope = producerScope5;
                it2 = anonymousClass1.$this_drop.iterator();
                anonymousClass1.L$0 = producerScope;
                anonymousClass1.L$1 = it2;
                anonymousClass1.label = 2;
                objHasNext2 = it2.hasNext(anonymousClass1);
                if (objHasNext2 != obj2) {
                }
            } else if (i2 == 2) {
                ChannelIterator<E> channelIterator3 = (ChannelIterator) this.L$1;
                ProducerScope producerScope6 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope3 = producerScope6;
                it2 = channelIterator3;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ChannelIterator<E> channelIterator4 = (ChannelIterator) this.L$1;
                ProducerScope producerScope7 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope = producerScope7;
                it2 = channelIterator4;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                anonymousClass1.L$0 = producerScope;
                anonymousClass1.L$1 = it2;
                anonymousClass1.label = 2;
                objHasNext2 = it2.hasNext(anonymousClass1);
                if (objHasNext2 != obj2) {
                    return obj2;
                }
                producerScope3 = producerScope;
                obj = objHasNext2;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                anonymousClass1.L$0 = producerScope3;
                anonymousClass1.L$1 = it2;
                anonymousClass1.label = 3;
                if (producerScope3.send(it2.next(), anonymousClass1) == obj2) {
                    return obj2;
                }
                producerScope = producerScope3;
                anonymousClass1.L$0 = producerScope;
                anonymousClass1.L$1 = it2;
                anonymousClass1.label = 2;
                objHasNext2 = it2.hasNext(anonymousClass1);
                if (objHasNext2 != obj2) {
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$dropWhile$1", f = "Deprecated.kt", i = {0, 1, 1, 2, 3, 4}, l = {181, 182, 183, 187, 188}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce", "$this$produce", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0", "L$0", "L$0"})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final Function2<E, Continuation<? super Boolean>, Object> $predicate;
        final ReceiveChannel<E> $this_dropWhile;
        private Object L$0;
        Object L$1;
        Object L$2;
        int label;

        AnonymousClass1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_dropWhile = receiveChannel;
            this.$predicate = function2;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_dropWhile, this.$predicate, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ChannelIterator<E> it;
            ProducerScope producerScope;
            AnonymousClass1<E> anonymousClass1;
            ProducerScope producerScope2;
            ChannelIterator<E> channelIterator;
            Object obj2;
            AnonymousClass1<E> anonymousClass12;
            ProducerScope producerScope3;
            Object objHasNext;
            ChannelIterator<E> it2;
            ChannelIterator<E> channelIterator2;
            Object objHasNext2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope4 = (ProducerScope) this.L$0;
                it = this.$this_dropWhile.iterator();
                producerScope = producerScope4;
                anonymousClass1 = this;
                anonymousClass1.L$0 = producerScope;
                anonymousClass1.L$1 = it;
                anonymousClass1.L$2 = null;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                }
            } else if (i == 1) {
                ChannelIterator<E> channelIterator3 = (ChannelIterator) this.L$1;
                ProducerScope producerScope5 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope2 = producerScope5;
                channelIterator = channelIterator3;
                obj2 = coroutine_suspended;
                anonymousClass12 = this;
                if (((Boolean) obj).booleanValue()) {
                }
                it2 = anonymousClass12.$this_dropWhile.iterator();
                anonymousClass12.L$0 = producerScope2;
                anonymousClass12.L$1 = it2;
                anonymousClass12.label = 4;
                objHasNext2 = it2.hasNext(anonymousClass12);
                if (objHasNext2 != obj2) {
                }
            } else if (i == 2) {
                Object obj3 = this.L$2;
                channelIterator = (ChannelIterator) this.L$1;
                ProducerScope producerScope6 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope3 = producerScope6;
                E e = obj3;
                obj2 = coroutine_suspended;
                anonymousClass12 = this;
                if (((Boolean) obj).booleanValue()) {
                    anonymousClass12.L$0 = producerScope3;
                    anonymousClass12.L$1 = null;
                    anonymousClass12.L$2 = null;
                    anonymousClass12.label = 3;
                    if (producerScope3.send(e, anonymousClass12) == obj2) {
                        return obj2;
                    }
                    producerScope2 = producerScope3;
                    it2 = anonymousClass12.$this_dropWhile.iterator();
                    anonymousClass12.L$0 = producerScope2;
                    anonymousClass12.L$1 = it2;
                    anonymousClass12.label = 4;
                    objHasNext2 = it2.hasNext(anonymousClass12);
                    if (objHasNext2 != obj2) {
                    }
                } else {
                    anonymousClass1 = anonymousClass12;
                    coroutine_suspended = obj2;
                    it = channelIterator;
                    producerScope = producerScope3;
                    anonymousClass1.L$0 = producerScope;
                    anonymousClass1.L$1 = it;
                    anonymousClass1.L$2 = null;
                    anonymousClass1.label = 1;
                    objHasNext = it.hasNext(anonymousClass1);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Object obj4 = coroutine_suspended;
                    anonymousClass12 = anonymousClass1;
                    obj = objHasNext;
                    producerScope2 = producerScope;
                    channelIterator = it;
                    obj2 = obj4;
                    if (((Boolean) obj).booleanValue()) {
                        E next = channelIterator.next();
                        Function2<E, Continuation<? super Boolean>, Object> function2 = anonymousClass12.$predicate;
                        anonymousClass12.L$0 = producerScope2;
                        anonymousClass12.L$1 = channelIterator;
                        anonymousClass12.L$2 = next;
                        anonymousClass12.label = 2;
                        Object objInvoke = function2.invoke(next, anonymousClass12);
                        if (objInvoke == obj2) {
                            return obj2;
                        }
                        ProducerScope producerScope7 = producerScope2;
                        e = next;
                        obj = objInvoke;
                        producerScope3 = producerScope7;
                        if (((Boolean) obj).booleanValue()) {
                        }
                    }
                    it2 = anonymousClass12.$this_dropWhile.iterator();
                    anonymousClass12.L$0 = producerScope2;
                    anonymousClass12.L$1 = it2;
                    anonymousClass12.label = 4;
                    objHasNext2 = it2.hasNext(anonymousClass12);
                    if (objHasNext2 != obj2) {
                    }
                }
            } else if (i == 3) {
                ProducerScope producerScope8 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope3 = producerScope8;
                obj2 = coroutine_suspended;
                anonymousClass12 = this;
                producerScope2 = producerScope3;
                it2 = anonymousClass12.$this_dropWhile.iterator();
                anonymousClass12.L$0 = producerScope2;
                anonymousClass12.L$1 = it2;
                anonymousClass12.label = 4;
                objHasNext2 = it2.hasNext(anonymousClass12);
                if (objHasNext2 != obj2) {
                }
            } else if (i == 4) {
                ChannelIterator<E> channelIterator4 = (ChannelIterator) this.L$1;
                ProducerScope producerScope9 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope2 = producerScope9;
                channelIterator2 = channelIterator4;
                obj2 = coroutine_suspended;
                anonymousClass12 = this;
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i != 5) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ChannelIterator<E> channelIterator5 = (ChannelIterator) this.L$1;
                ProducerScope producerScope10 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                it2 = channelIterator5;
                producerScope2 = producerScope10;
                obj2 = coroutine_suspended;
                anonymousClass12 = this;
                anonymousClass12.L$0 = producerScope2;
                anonymousClass12.L$1 = it2;
                anonymousClass12.label = 4;
                objHasNext2 = it2.hasNext(anonymousClass12);
                if (objHasNext2 != obj2) {
                    return obj2;
                }
                channelIterator2 = it2;
                obj = objHasNext2;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                anonymousClass12.L$0 = producerScope2;
                anonymousClass12.L$1 = channelIterator2;
                anonymousClass12.label = 5;
                if (producerScope2.send(channelIterator2.next(), anonymousClass12) == obj2) {
                    return obj2;
                }
                it2 = channelIterator2;
                anonymousClass12.L$0 = producerScope2;
                anonymousClass12.L$1 = it2;
                anonymousClass12.label = 4;
                objHasNext2 = it2.hasNext(anonymousClass12);
                if (objHasNext2 != obj2) {
                }
            }
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {38}, m = "elementAt", n = {"$this$consume$iv", "index", "count"}, s = {"L$0", "I$0", "I$1"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.elementAt(null, 0, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {53}, m = "elementAtOrNull", n = {"$this$consume$iv", "index", "count"}, s = {"L$0", "I$0", "I$1"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.elementAtOrNull(null, 0, this);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filter$1", f = "Deprecated.kt", i = {0, 1, 1, 2}, l = {198, 199, 199}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0"})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final Function2<E, Continuation<? super Boolean>, Object> $predicate;
        final ReceiveChannel<E> $this_filter;
        private Object L$0;
        Object L$1;
        Object L$2;
        int label;

        AnonymousClass1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_filter = receiveChannel;
            this.$predicate = function2;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_filter, this.$predicate, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ChannelIterator<E> it;
            ProducerScope producerScope;
            ChannelIterator<E> channelIterator;
            Object obj2;
            AnonymousClass1<E> anonymousClass1;
            AnonymousClass1<E> anonymousClass12;
            ProducerScope producerScope2;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                it = this.$this_filter.iterator();
                producerScope = producerScope3;
            } else if (i == 1) {
                ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$1;
                ProducerScope producerScope4 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope2 = producerScope4;
                channelIterator = channelIterator2;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                if (((Boolean) obj).booleanValue()) {
                }
            } else if (i == 2) {
                Object obj3 = this.L$2;
                channelIterator = (ChannelIterator) this.L$1;
                ProducerScope producerScope5 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope6 = producerScope5;
                E e = obj3;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                if (!((Boolean) obj).booleanValue()) {
                    anonymousClass1.L$0 = producerScope6;
                    anonymousClass1.L$1 = channelIterator;
                    anonymousClass1.L$2 = null;
                    anonymousClass1.label = 3;
                    if (producerScope6.send(e, anonymousClass1) == obj2) {
                        return obj2;
                    }
                    anonymousClass12 = anonymousClass1;
                    coroutine_suspended = obj2;
                    it = channelIterator;
                    producerScope = producerScope6;
                }
                anonymousClass12.L$0 = producerScope;
                anonymousClass12.L$1 = it;
                anonymousClass12.L$2 = null;
                anonymousClass12.label = 1;
                objHasNext = it.hasNext(anonymousClass12);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj4 = coroutine_suspended;
                anonymousClass1 = anonymousClass12;
                obj = objHasNext;
                producerScope2 = producerScope;
                channelIterator = it;
                obj2 = obj4;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                E next = channelIterator.next();
                Function2<E, Continuation<? super Boolean>, Object> function2 = anonymousClass1.$predicate;
                anonymousClass1.L$0 = producerScope2;
                anonymousClass1.L$1 = channelIterator;
                anonymousClass1.L$2 = next;
                anonymousClass1.label = 2;
                Object objInvoke = function2.invoke(next, anonymousClass1);
                if (objInvoke == obj2) {
                    return obj2;
                }
                ProducerScope producerScope7 = producerScope2;
                e = next;
                obj = objInvoke;
                producerScope6 = producerScope7;
                if (!((Boolean) obj).booleanValue()) {
                }
                anonymousClass12.L$0 = producerScope;
                anonymousClass12.L$1 = it;
                anonymousClass12.L$2 = null;
                anonymousClass12.label = 1;
                objHasNext = it.hasNext(anonymousClass12);
                if (objHasNext == coroutine_suspended) {
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            anonymousClass12 = this;
            anonymousClass12.L$0 = producerScope;
            anonymousClass12.L$1 = it;
            anonymousClass12.L$2 = null;
            anonymousClass12.label = 1;
            objHasNext = it.hasNext(anonymousClass12);
            if (objHasNext == coroutine_suspended) {
            }
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterIndexed$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 1, 2, 2}, l = {211, 212, 212}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "e", "index", "$this$produce", "index"}, s = {"L$0", "I$0", "L$0", "L$2", "I$0", "L$0", "I$0"})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final Function3<Integer, E, Continuation<? super Boolean>, Object> $predicate;
        final ReceiveChannel<E> $this_filterIndexed;
        int I$0;
        private Object L$0;
        Object L$1;
        Object L$2;
        int label;

        AnonymousClass1(ReceiveChannel<? extends E> receiveChannel, Function3<? super Integer, ? super E, ? super Continuation<? super Boolean>, ? extends Object> function3, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_filterIndexed = receiveChannel;
            this.$predicate = function3;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_filterIndexed, this.$predicate, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            int i;
            ChannelIterator<E> it;
            ProducerScope producerScope;
            ProducerScope producerScope2;
            Object obj2;
            AnonymousClass1<E> anonymousClass1;
            AnonymousClass1<E> anonymousClass12;
            ChannelIterator<E> channelIterator;
            int i2;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                i = 0;
                it = this.$this_filterIndexed.iterator();
                producerScope = producerScope3;
            } else if (i3 == 1) {
                int i4 = this.I$0;
                ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$1;
                ProducerScope producerScope4 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope2 = producerScope4;
                channelIterator = channelIterator2;
                i2 = i4;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                if (((Boolean) obj).booleanValue()) {
                }
            } else if (i3 == 2) {
                int i5 = this.I$0;
                Object obj3 = this.L$2;
                ChannelIterator<E> channelIterator3 = (ChannelIterator) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                int i6 = i5;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                ChannelIterator<E> channelIterator4 = channelIterator3;
                E e = obj3;
                it = channelIterator4;
                if (!((Boolean) obj).booleanValue()) {
                    anonymousClass1.L$0 = producerScope2;
                    anonymousClass1.L$1 = it;
                    anonymousClass1.L$2 = null;
                    anonymousClass1.I$0 = i6;
                    anonymousClass1.label = 3;
                    if (producerScope2.send(e, anonymousClass1) == obj2) {
                        return obj2;
                    }
                    anonymousClass12 = anonymousClass1;
                    coroutine_suspended = obj2;
                    producerScope = producerScope2;
                    i = i6;
                }
                anonymousClass12.L$0 = producerScope;
                anonymousClass12.L$1 = it;
                anonymousClass12.L$2 = null;
                anonymousClass12.I$0 = i;
                anonymousClass12.label = 1;
                objHasNext = it.hasNext(anonymousClass12);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj4 = coroutine_suspended;
                anonymousClass1 = anonymousClass12;
                obj = objHasNext;
                producerScope2 = producerScope;
                channelIterator = it;
                i2 = i;
                obj2 = obj4;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                E next = channelIterator.next();
                Function3<Integer, E, Continuation<? super Boolean>, Object> function3 = anonymousClass1.$predicate;
                i6 = i2 + 1;
                Integer numBoxInt = Boxing.boxInt(i2);
                anonymousClass1.L$0 = producerScope2;
                anonymousClass1.L$1 = channelIterator;
                anonymousClass1.L$2 = next;
                anonymousClass1.I$0 = i6;
                anonymousClass1.label = 2;
                Object objInvoke = function3.invoke(numBoxInt, next, anonymousClass1);
                if (objInvoke == obj2) {
                    return obj2;
                }
                channelIterator4 = channelIterator;
                e = next;
                obj = objInvoke;
                it = channelIterator4;
                if (!((Boolean) obj).booleanValue()) {
                }
                anonymousClass12.L$0 = producerScope;
                anonymousClass12.L$1 = it;
                anonymousClass12.L$2 = null;
                anonymousClass12.I$0 = i;
                anonymousClass12.label = 1;
                objHasNext = it.hasNext(anonymousClass12);
                if (objHasNext == coroutine_suspended) {
                }
            } else {
                if (i3 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            anonymousClass12 = this;
            anonymousClass12.L$0 = producerScope;
            anonymousClass12.L$1 = it;
            anonymousClass12.L$2 = null;
            anonymousClass12.I$0 = i;
            anonymousClass12.label = 1;
            objHasNext = it.hasNext(anonymousClass12);
            if (objHasNext == coroutine_suspended) {
            }
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u0002H\u008a@"}, d2 = {"<anonymous>", "", "E", "it"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNot$1", f = "Deprecated.kt", i = {}, l = {222}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<E, Continuation<? super Boolean>, Object> {
        final Function2<E, Continuation<? super Boolean>, Object> $predicate;
        Object L$0;
        int label;

        AnonymousClass1(Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$predicate = function2;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$predicate, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public Object invoke(Object obj, Continuation<? super Boolean> continuation) {
            return invoke2((AnonymousClass1<E>) obj, continuation);
        }

        public final Object invoke2(E e, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = this.L$0;
                Function2<E, Continuation<? super Boolean>, Object> function2 = this.$predicate;
                this.label = 1;
                obj = function2.invoke(obj2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Boxing.boxBoolean(!((Boolean) obj).booleanValue());
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u0001H\u0002H\u008a@"}, d2 = {"<anonymous>", "", "E", "", "it"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$filterNotNull$1", f = "Deprecated.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<E, Continuation<? super Boolean>, Object> {
        Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public Object invoke(Object obj, Continuation<? super Boolean> continuation) {
            return invoke2((AnonymousClass1<E>) obj, continuation);
        }

        public final Object invoke2(E e, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(this.L$0 != null);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "filterNotNullTo", n = {"destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1<E, C extends Collection<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.filterNotNullTo((ReceiveChannel) null, (Collection) null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {487, 242}, m = "filterNotNullTo", n = {"destination", "$this$consume$iv$iv", "destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass3<E, C extends SendChannel<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        Object result;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.filterNotNullTo((ReceiveChannel) null, (SendChannel) null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {65}, m = "first", n = {"$this$consume$iv", "iterator"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.first(null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {75}, m = "firstOrNull", n = {"$this$consume$iv", "iterator"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.firstOrNull(null, this);
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@"}, d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$flatMap$1", f = "Deprecated.kt", i = {0, 1, 2}, l = {321, 322, 322}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "$this$produce"}, s = {"L$0", "L$0", "L$0"})
    static final class AnonymousClass1<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
        final ReceiveChannel<E> $this_flatMap;
        final Function2<E, Continuation<? super ReceiveChannel<? extends R>>, Object> $transform;
        private Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super ReceiveChannel<? extends R>>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_flatMap = receiveChannel;
            this.$transform = function2;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_flatMap, this.$transform, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ChannelIterator it;
            ProducerScope producerScope;
            ProducerScope producerScope2;
            ChannelIterator channelIterator;
            Object obj2;
            AnonymousClass1<R> anonymousClass1;
            AnonymousClass1<R> anonymousClass12;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                it = this.$this_flatMap.iterator();
                producerScope = producerScope3;
            } else if (i == 1) {
                ChannelIterator channelIterator2 = (ChannelIterator) this.L$1;
                ProducerScope producerScope4 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope2 = producerScope4;
                channelIterator = channelIterator2;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                if (((Boolean) obj).booleanValue()) {
                }
            } else if (i == 2) {
                ChannelIterator channelIterator3 = (ChannelIterator) this.L$1;
                ProducerScope producerScope5 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope2 = producerScope5;
                channelIterator = channelIterator3;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                anonymousClass1.L$0 = producerScope2;
                anonymousClass1.L$1 = channelIterator;
                anonymousClass1.label = 3;
                if (ChannelsKt.toChannel((ReceiveChannel) obj, producerScope2, anonymousClass1) != obj2) {
                    return obj2;
                }
                anonymousClass12 = anonymousClass1;
                coroutine_suspended = obj2;
                it = channelIterator;
                producerScope = producerScope2;
                anonymousClass12.L$0 = producerScope;
                anonymousClass12.L$1 = it;
                anonymousClass12.label = 1;
                objHasNext = it.hasNext(anonymousClass12);
                if (objHasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj3 = coroutine_suspended;
                anonymousClass1 = anonymousClass12;
                obj = objHasNext;
                producerScope2 = producerScope;
                channelIterator = it;
                obj2 = obj3;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                Object next = channelIterator.next();
                Function2<E, Continuation<? super ReceiveChannel<? extends R>>, Object> function2 = anonymousClass1.$transform;
                anonymousClass1.L$0 = producerScope2;
                anonymousClass1.L$1 = channelIterator;
                anonymousClass1.label = 2;
                obj = function2.invoke(next, anonymousClass1);
                if (obj == obj2) {
                    return obj2;
                }
                anonymousClass1.L$0 = producerScope2;
                anonymousClass1.L$1 = channelIterator;
                anonymousClass1.label = 3;
                if (ChannelsKt.toChannel((ReceiveChannel) obj, producerScope2, anonymousClass1) != obj2) {
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            anonymousClass12 = this;
            anonymousClass12.L$0 = producerScope;
            anonymousClass12.L$1 = it;
            anonymousClass12.label = 1;
            objHasNext = it.hasNext(anonymousClass12);
            if (objHasNext != coroutine_suspended) {
            }
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0}, l = {487}, m = "indexOf", n = {"element", "index", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$2"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.indexOf(null, null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1, 1}, l = {97, 100}, m = "last", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "iterator", "last"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.last(null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 0}, l = {487}, m = "lastIndexOf", n = {"element", "lastIndex", "index", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$2", "L$3"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.lastIndexOf(null, null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1, 1}, l = {123, 126}, m = "lastOrNull", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "iterator", "last"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.lastOrNull(null, this);
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@"}, d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$map$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 2, 2}, l = {487, 333, 333}, m = "invokeSuspend", n = {"$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv", "$this$produce", "$this$consume$iv$iv"}, s = {"L$0", "L$2", "L$0", "L$2", "L$0", "L$2"})
    static final class AnonymousClass1<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
        final ReceiveChannel<E> $this_map;
        final Function2<E, Continuation<? super R>, Object> $transform;
        private Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;

        AnonymousClass1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_map = receiveChannel;
            this.$transform = function2;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_map, this.$transform, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ReceiveChannel receiveChannel;
            ProducerScope producerScope;
            AnonymousClass1<R> anonymousClass1;
            Function2 function2;
            ChannelIterator it;
            ReceiveChannel receiveChannel2;
            AnonymousClass1<R> anonymousClass12;
            ProducerScope producerScope2;
            Function2 function22;
            ChannelIterator channelIterator;
            ProducerScope producerScope3;
            AnonymousClass1<R> anonymousClass13;
            ProducerScope producerScope4;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    producerScope = (ProducerScope) this.L$0;
                    receiveChannel = this.$this_map;
                    anonymousClass1 = this;
                    function2 = this.$transform;
                    it = receiveChannel.iterator();
                } else if (i == 1) {
                    it = (ChannelIterator) this.L$3;
                    receiveChannel = (ReceiveChannel) this.L$2;
                    function2 = (Function2) this.L$1;
                    producerScope4 = (ProducerScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    anonymousClass13 = this;
                    if (((Boolean) obj).booleanValue()) {
                        Unit unit = Unit.INSTANCE;
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return Unit.INSTANCE;
                    }
                    Object next = it.next();
                    anonymousClass13.L$0 = producerScope4;
                    anonymousClass13.L$1 = function2;
                    anonymousClass13.L$2 = receiveChannel;
                    anonymousClass13.L$3 = it;
                    anonymousClass13.L$4 = producerScope4;
                    anonymousClass13.label = 2;
                    obj = function2.invoke(next, anonymousClass13);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass12 = anonymousClass13;
                    producerScope2 = producerScope4;
                    function22 = function2;
                    receiveChannel2 = receiveChannel;
                    channelIterator = it;
                    producerScope3 = producerScope2;
                    anonymousClass12.L$0 = producerScope2;
                    anonymousClass12.L$1 = function22;
                    anonymousClass12.L$2 = receiveChannel2;
                    anonymousClass12.L$3 = channelIterator;
                    anonymousClass12.L$4 = null;
                    anonymousClass12.label = 3;
                    if (producerScope3.send(obj, anonymousClass12) != coroutine_suspended) {
                    }
                } else if (i == 2) {
                    producerScope3 = (ProducerScope) this.L$4;
                    channelIterator = (ChannelIterator) this.L$3;
                    receiveChannel2 = (ReceiveChannel) this.L$2;
                    function22 = (Function2) this.L$1;
                    producerScope2 = (ProducerScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        anonymousClass12 = this;
                        anonymousClass12.L$0 = producerScope2;
                        anonymousClass12.L$1 = function22;
                        anonymousClass12.L$2 = receiveChannel2;
                        anonymousClass12.L$3 = channelIterator;
                        anonymousClass12.L$4 = null;
                        anonymousClass12.label = 3;
                        if (producerScope3.send(obj, anonymousClass12) != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        it = channelIterator;
                        receiveChannel = receiveChannel2;
                        function2 = function22;
                        producerScope = producerScope2;
                        anonymousClass1 = anonymousClass12;
                    } catch (Throwable th) {
                        th = th;
                        receiveChannel = receiveChannel2;
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            ChannelsKt.cancelConsumed(receiveChannel, th);
                            throw th2;
                        }
                    }
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    it = (ChannelIterator) this.L$3;
                    receiveChannel = (ReceiveChannel) this.L$2;
                    function2 = (Function2) this.L$1;
                    ProducerScope producerScope5 = (ProducerScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    producerScope = producerScope5;
                    anonymousClass1 = this;
                }
                anonymousClass1.L$0 = producerScope;
                anonymousClass1.L$1 = function2;
                anonymousClass1.L$2 = receiveChannel;
                anonymousClass1.L$3 = it;
                anonymousClass1.label = 1;
                Object objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                AnonymousClass1<R> anonymousClass14 = anonymousClass1;
                producerScope4 = producerScope;
                obj = objHasNext;
                anonymousClass13 = anonymousClass14;
                if (((Boolean) obj).booleanValue()) {
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@"}, d2 = {"<anonymous>", "", "E", "R", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$mapIndexed$1", f = "Deprecated.kt", i = {0, 0, 1, 1, 2, 2}, l = {344, 345, 345}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "index", "$this$produce", "index"}, s = {"L$0", "I$0", "L$0", "I$0", "L$0", "I$0"})
    static final class AnonymousClass1<R> extends SuspendLambda implements Function2<ProducerScope<? super R>, Continuation<? super Unit>, Object> {
        final ReceiveChannel<E> $this_mapIndexed;
        final Function3<Integer, E, Continuation<? super R>, Object> $transform;
        int I$0;
        private Object L$0;
        Object L$1;
        Object L$2;
        int label;

        AnonymousClass1(ReceiveChannel<? extends E> receiveChannel, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_mapIndexed = receiveChannel;
            this.$transform = function3;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_mapIndexed, this.$transform, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super R> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ProducerScope producerScope;
            int i;
            ChannelIterator it;
            AnonymousClass1<R> anonymousClass1;
            AnonymousClass1<R> anonymousClass12;
            ProducerScope producerScope2;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                producerScope = (ProducerScope) this.L$0;
                i = 0;
                it = this.$this_mapIndexed.iterator();
            } else if (i2 == 1) {
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                anonymousClass12 = this;
                if (((Boolean) obj).booleanValue()) {
                }
            } else if (i2 == 2) {
                i = this.I$0;
                ProducerScope producerScope3 = (ProducerScope) this.L$2;
                ChannelIterator channelIterator = (ChannelIterator) this.L$1;
                ProducerScope producerScope4 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                AnonymousClass1<R> anonymousClass13 = this;
                anonymousClass13.L$0 = producerScope4;
                anonymousClass13.L$1 = channelIterator;
                anonymousClass13.L$2 = null;
                anonymousClass13.I$0 = i;
                anonymousClass13.label = 3;
                if (producerScope3.send(obj, anonymousClass13) != coroutine_suspended) {
                    return coroutine_suspended;
                }
                it = channelIterator;
                producerScope = producerScope4;
                anonymousClass1 = anonymousClass13;
                anonymousClass1.L$0 = producerScope;
                anonymousClass1.L$1 = it;
                anonymousClass1.I$0 = i;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                AnonymousClass1<R> anonymousClass14 = anonymousClass1;
                producerScope2 = producerScope;
                obj = objHasNext;
                anonymousClass12 = anonymousClass14;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                Object next = it.next();
                Function3<Integer, E, Continuation<? super R>, Object> function3 = anonymousClass12.$transform;
                int i3 = i + 1;
                Integer numBoxInt = Boxing.boxInt(i);
                anonymousClass12.L$0 = producerScope2;
                anonymousClass12.L$1 = it;
                anonymousClass12.L$2 = producerScope2;
                anonymousClass12.I$0 = i3;
                anonymousClass12.label = 2;
                obj = function3.invoke(numBoxInt, next, anonymousClass12);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                anonymousClass13 = anonymousClass12;
                i = i3;
                producerScope4 = producerScope2;
                channelIterator = it;
                producerScope3 = producerScope4;
                anonymousClass13.L$0 = producerScope4;
                anonymousClass13.L$1 = channelIterator;
                anonymousClass13.L$2 = null;
                anonymousClass13.I$0 = i;
                anonymousClass13.label = 3;
                if (producerScope3.send(obj, anonymousClass13) != coroutine_suspended) {
                }
            } else {
                if (i2 != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                ProducerScope producerScope5 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope = producerScope5;
            }
            anonymousClass1 = this;
            anonymousClass1.L$0 = producerScope;
            anonymousClass1.L$1 = it;
            anonymousClass1.I$0 = i;
            anonymousClass1.label = 1;
            objHasNext = it.hasNext(anonymousClass1);
            if (objHasNext != coroutine_suspended) {
            }
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {420, 422}, m = "maxWith", n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "max"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.maxWith(null, null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1}, l = {434, 436}, m = "minWith", n = {"comparator", "$this$consume$iv", "iterator", "comparator", "$this$consume$iv", "iterator", "min"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.minWith(null, null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0}, l = {447}, m = "none", n = {"$this$consume$iv"}, s = {"L$0"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.none(null, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u0001H\u0001H\u008a@"}, d2 = {"<anonymous>", "E", "", "it"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$requireNoNulls$1", f = "Deprecated.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<E, Continuation<? super E>, Object> {
        final ReceiveChannel<E> $this_requireNoNulls;
        Object L$0;
        int label;

        AnonymousClass1(ReceiveChannel<? extends E> receiveChannel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_requireNoNulls = receiveChannel;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_requireNoNulls, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public Object invoke(Object obj, Object obj2) {
            return invoke((AnonymousClass1<E>) obj, (Continuation<? super AnonymousClass1<E>>) obj2);
        }

        public final Object invoke(E e, Continuation<? super E> continuation) {
            return ((AnonymousClass1) create(e, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            if (obj2 != null) {
                return obj2;
            }
            throw new IllegalArgumentException("null element found in " + this.$this_requireNoNulls + '.');
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {136, 139}, m = "single", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "single"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.single(null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {149, 152}, m = "singleOrNull", n = {"$this$consume$iv", "iterator", "$this$consume$iv", "single"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass1<E> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt__DeprecatedKt.singleOrNull(null, this);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$take$1", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {254, 255}, m = "invokeSuspend", n = {"$this$produce", "remaining", "$this$produce", "remaining"}, s = {"L$0", "I$0", "L$0", "I$0"})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final int $n;
        final ReceiveChannel<E> $this_take;
        int I$0;
        private Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(int i, ReceiveChannel<? extends E> receiveChannel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$n = i;
            this.$this_take = receiveChannel;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$n, this.$this_take, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ProducerScope producerScope;
            int i;
            ChannelIterator<E> it;
            AnonymousClass1<E> anonymousClass1;
            AnonymousClass1<E> anonymousClass12;
            ProducerScope producerScope2;
            ChannelIterator<E> channelIterator;
            int i2;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i3 = this.label;
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                producerScope = (ProducerScope) this.L$0;
                int i4 = this.$n;
                if (i4 == 0) {
                    return Unit.INSTANCE;
                }
                boolean z = i4 >= 0;
                i = this.$n;
                if (!z) {
                    throw new IllegalArgumentException(("Requested element count " + i + " is less than zero.").toString());
                }
                it = this.$this_take.iterator();
                anonymousClass1 = this;
                anonymousClass1.L$0 = producerScope;
                anonymousClass1.L$1 = it;
                anonymousClass1.I$0 = i;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                }
            } else if (i3 == 1) {
                i2 = this.I$0;
                channelIterator = (ChannelIterator) this.L$1;
                producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                anonymousClass12 = this;
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i3 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i2 = this.I$0;
                channelIterator = (ChannelIterator) this.L$1;
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope = producerScope3;
                anonymousClass1 = this;
                int i5 = i2 - 1;
                if (i5 != 0) {
                    return Unit.INSTANCE;
                }
                ChannelIterator<E> channelIterator2 = channelIterator;
                i = i5;
                it = channelIterator2;
                anonymousClass1.L$0 = producerScope;
                anonymousClass1.L$1 = it;
                anonymousClass1.I$0 = i;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                AnonymousClass1<E> anonymousClass13 = anonymousClass1;
                producerScope2 = producerScope;
                obj = objHasNext;
                anonymousClass12 = anonymousClass13;
                int i6 = i;
                channelIterator = it;
                i2 = i6;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                anonymousClass12.L$0 = producerScope2;
                anonymousClass12.L$1 = channelIterator;
                anonymousClass12.I$0 = i2;
                anonymousClass12.label = 2;
                if (producerScope2.send(channelIterator.next(), anonymousClass12) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                producerScope = producerScope2;
                anonymousClass1 = anonymousClass12;
                int i52 = i2 - 1;
                if (i52 != 0) {
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$takeWhile$1", f = "Deprecated.kt", i = {0, 1, 1, 2}, l = {269, 270, 271}, m = "invokeSuspend", n = {"$this$produce", "$this$produce", "e", "$this$produce"}, s = {"L$0", "L$0", "L$2", "L$0"})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<ProducerScope<? super E>, Continuation<? super Unit>, Object> {
        final Function2<E, Continuation<? super Boolean>, Object> $predicate;
        final ReceiveChannel<E> $this_takeWhile;
        private Object L$0;
        Object L$1;
        Object L$2;
        int label;

        AnonymousClass1(ReceiveChannel<? extends E> receiveChannel, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_takeWhile = receiveChannel;
            this.$predicate = function2;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_takeWhile, this.$predicate, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super E> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ChannelIterator<E> it;
            ProducerScope producerScope;
            Object obj2;
            AnonymousClass1<E> anonymousClass1;
            AnonymousClass1<E> anonymousClass12;
            ProducerScope producerScope2;
            ChannelIterator<E> channelIterator;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                it = this.$this_takeWhile.iterator();
                producerScope = producerScope3;
            } else if (i == 1) {
                ChannelIterator<E> channelIterator2 = (ChannelIterator) this.L$1;
                ProducerScope producerScope4 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope2 = producerScope4;
                channelIterator = channelIterator2;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                if (((Boolean) obj).booleanValue()) {
                }
            } else if (i == 2) {
                Object obj3 = this.L$2;
                ChannelIterator<E> channelIterator3 = (ChannelIterator) this.L$1;
                ProducerScope producerScope5 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                ProducerScope producerScope6 = producerScope5;
                ChannelIterator<E> channelIterator4 = channelIterator3;
                E e = obj3;
                obj2 = coroutine_suspended;
                anonymousClass1 = this;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                anonymousClass1.L$0 = producerScope6;
                anonymousClass1.L$1 = channelIterator4;
                anonymousClass1.L$2 = null;
                anonymousClass1.label = 3;
                if (producerScope6.send(e, anonymousClass1) == obj2) {
                    return obj2;
                }
                anonymousClass12 = anonymousClass1;
                coroutine_suspended = obj2;
                it = channelIterator4;
                producerScope = producerScope6;
                anonymousClass12.L$0 = producerScope;
                anonymousClass12.L$1 = it;
                anonymousClass12.label = 1;
                objHasNext = it.hasNext(anonymousClass12);
                if (objHasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj4 = coroutine_suspended;
                anonymousClass1 = anonymousClass12;
                obj = objHasNext;
                producerScope2 = producerScope;
                channelIterator = it;
                obj2 = obj4;
                if (((Boolean) obj).booleanValue()) {
                    return Unit.INSTANCE;
                }
                E next = channelIterator.next();
                Function2<E, Continuation<? super Boolean>, Object> function2 = anonymousClass1.$predicate;
                anonymousClass1.L$0 = producerScope2;
                anonymousClass1.L$1 = channelIterator;
                anonymousClass1.L$2 = next;
                anonymousClass1.label = 2;
                Object objInvoke = function2.invoke(next, anonymousClass1);
                if (objInvoke == obj2) {
                    return obj2;
                }
                ChannelIterator<E> channelIterator5 = channelIterator;
                e = next;
                obj = objInvoke;
                producerScope6 = producerScope2;
                channelIterator4 = channelIterator5;
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                it = (ChannelIterator) this.L$1;
                producerScope = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            anonymousClass12 = this;
            anonymousClass12.L$0 = producerScope;
            anonymousClass12.L$1 = it;
            anonymousClass12.label = 1;
            objHasNext = it.hasNext(anonymousClass12);
            if (objHasNext != coroutine_suspended) {
            }
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {487, 278}, m = "toChannel", n = {"destination", "$this$consume$iv$iv", "destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class AnonymousClass1<E, C extends SendChannel<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.toChannel(null, null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "toCollection", n = {"destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    static final class AnonymousClass1<E, C extends Collection<? super E>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.toCollection(null, null, this);
        }
    }

    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", f = "Deprecated.kt", i = {0, 0}, l = {487}, m = "toMap", n = {"destination", "$this$consume$iv$iv"}, s = {"L$0", "L$1"})
    static final class AnonymousClass2<K, V, M extends Map<? super K, ? super V>> extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        Object result;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        @Override
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return ChannelsKt.toMap(null, null, this);
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003H\u008a@"}, d2 = {"<anonymous>", "", "E", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlin/collections/IndexedValue;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$withIndex$1", f = "Deprecated.kt", i = {0, 0, 1, 1}, l = {370, 371}, m = "invokeSuspend", n = {"$this$produce", "index", "$this$produce", "index"}, s = {"L$0", "I$0", "L$0", "I$0"})
    static final class AnonymousClass1<E> extends SuspendLambda implements Function2<ProducerScope<? super IndexedValue<? extends E>>, Continuation<? super Unit>, Object> {
        final ReceiveChannel<E> $this_withIndex;
        int I$0;
        private Object L$0;
        Object L$1;
        int label;

        AnonymousClass1(ReceiveChannel<? extends E> receiveChannel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this_withIndex = receiveChannel;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this_withIndex, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override
        public final Object invoke(ProducerScope<? super IndexedValue<? extends E>> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ProducerScope producerScope;
            int i;
            ChannelIterator<E> it;
            AnonymousClass1<E> anonymousClass1;
            Object objHasNext;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                producerScope = (ProducerScope) this.L$0;
                i = 0;
                it = this.$this_withIndex.iterator();
            } else if (i2 == 1) {
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                ProducerScope producerScope2 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                AnonymousClass1<E> anonymousClass12 = this;
                if (!((Boolean) obj).booleanValue()) {
                    int i3 = i + 1;
                    anonymousClass12.L$0 = producerScope2;
                    anonymousClass12.L$1 = it;
                    anonymousClass12.I$0 = i3;
                    anonymousClass12.label = 2;
                    if (producerScope2.send(new IndexedValue(i, it.next()), anonymousClass12) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    producerScope = producerScope2;
                    anonymousClass1 = anonymousClass12;
                    i = i3;
                    anonymousClass1.L$0 = producerScope;
                    anonymousClass1.L$1 = it;
                    anonymousClass1.I$0 = i;
                    anonymousClass1.label = 1;
                    objHasNext = it.hasNext(anonymousClass1);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    AnonymousClass1<E> anonymousClass13 = anonymousClass1;
                    producerScope2 = producerScope;
                    obj = objHasNext;
                    anonymousClass12 = anonymousClass13;
                    if (!((Boolean) obj).booleanValue()) {
                        return Unit.INSTANCE;
                    }
                }
            } else {
                if (i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                it = (ChannelIterator) this.L$1;
                ProducerScope producerScope3 = (ProducerScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                producerScope = producerScope3;
            }
            anonymousClass1 = this;
            anonymousClass1.L$0 = producerScope;
            anonymousClass1.L$1 = it;
            anonymousClass1.I$0 = i;
            anonymousClass1.label = 1;
            objHasNext = it.hasNext(anonymousClass1);
            if (objHasNext != coroutine_suspended) {
            }
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H\u008a@"}, d2 = {"<anonymous>", "", "E", "R", "V", "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt$zip$2", f = "Deprecated.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2}, l = {487, 469, 471}, m = "invokeSuspend", n = {"$this$produce", "otherIterator", "$this$consume$iv$iv", "$this$produce", "otherIterator", "$this$consume$iv$iv", "element1", "$this$produce", "otherIterator", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$3", "L$0", "L$1", "L$3", "L$5", "L$0", "L$1", "L$3"})
    static final class AnonymousClass2<V> extends SuspendLambda implements Function2<ProducerScope<? super V>, Continuation<? super Unit>, Object> {
        final ReceiveChannel<R> $other;
        final ReceiveChannel<E> $this_zip;
        final Function2<E, R, V> $transform;
        private Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        AnonymousClass2(ReceiveChannel<? extends R> receiveChannel, ReceiveChannel<? extends E> receiveChannel2, Function2<? super E, ? super R, ? extends V> function2, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$other = receiveChannel;
            this.$this_zip = receiveChannel2;
            this.$transform = function2;
        }

        @Override
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$other, this.$this_zip, this.$transform, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override
        public final Object invoke(ProducerScope<? super V> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override
        public final Object invokeSuspend(Object obj) throws Throwable {
            ReceiveChannel receiveChannel;
            Function2 function2;
            ProducerScope producerScope;
            AnonymousClass2<V> anonymousClass2;
            ChannelIterator channelIterator;
            ChannelIterator it;
            ReceiveChannel receiveChannel2;
            ProducerScope producerScope2;
            ChannelIterator channelIterator2;
            Function2 function22;
            ReceiveChannel receiveChannel3;
            Object obj2;
            ChannelIterator channelIterator3;
            Object obj3;
            AnonymousClass2<V> anonymousClass22;
            ProducerScope producerScope3;
            ChannelIterator channelIterator4;
            Function2 function23;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ProducerScope producerScope4 = (ProducerScope) this.L$0;
                    ChannelIterator it2 = this.$other.iterator();
                    receiveChannel = this.$this_zip;
                    function2 = this.$transform;
                    producerScope = producerScope4;
                    anonymousClass2 = this;
                    channelIterator = it2;
                    it = receiveChannel.iterator();
                } else if (i == 1) {
                    ChannelIterator channelIterator5 = (ChannelIterator) this.L$4;
                    ReceiveChannel receiveChannel4 = (ReceiveChannel) this.L$3;
                    Function2 function24 = (Function2) this.L$2;
                    ChannelIterator channelIterator6 = (ChannelIterator) this.L$1;
                    ProducerScope producerScope5 = (ProducerScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    producerScope3 = producerScope5;
                    channelIterator4 = channelIterator6;
                    function23 = function24;
                    receiveChannel2 = receiveChannel4;
                    channelIterator3 = channelIterator5;
                    obj3 = coroutine_suspended;
                    anonymousClass22 = this;
                    if (((Boolean) obj).booleanValue()) {
                        Unit unit = Unit.INSTANCE;
                        ChannelsKt.cancelConsumed(receiveChannel2, null);
                        return Unit.INSTANCE;
                    }
                    Object next = channelIterator3.next();
                    anonymousClass22.L$0 = producerScope3;
                    anonymousClass22.L$1 = channelIterator4;
                    anonymousClass22.L$2 = function23;
                    anonymousClass22.L$3 = receiveChannel2;
                    anonymousClass22.L$4 = channelIterator3;
                    anonymousClass22.L$5 = next;
                    anonymousClass22.label = 2;
                    Object objHasNext = channelIterator4.hasNext(anonymousClass22);
                    if (objHasNext == obj3) {
                        return obj3;
                    }
                    ReceiveChannel receiveChannel5 = receiveChannel2;
                    obj2 = next;
                    obj = objHasNext;
                    producerScope2 = producerScope3;
                    channelIterator2 = channelIterator4;
                    function22 = function23;
                    receiveChannel3 = receiveChannel5;
                    if (((Boolean) obj).booleanValue()) {
                    }
                } else if (i == 2) {
                    Object obj4 = this.L$5;
                    channelIterator3 = (ChannelIterator) this.L$4;
                    receiveChannel2 = (ReceiveChannel) this.L$3;
                    Function2 function25 = (Function2) this.L$2;
                    ChannelIterator channelIterator7 = (ChannelIterator) this.L$1;
                    ProducerScope producerScope6 = (ProducerScope) this.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                        producerScope2 = producerScope6;
                        channelIterator2 = channelIterator7;
                        function22 = function25;
                        receiveChannel3 = receiveChannel2;
                        obj2 = obj4;
                        obj3 = coroutine_suspended;
                        anonymousClass22 = this;
                    } catch (Throwable th) {
                        th = th;
                        receiveChannel = receiveChannel2;
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            ChannelsKt.cancelConsumed(receiveChannel, th);
                            throw th2;
                        }
                    }
                    try {
                        if (((Boolean) obj).booleanValue()) {
                            anonymousClass2 = anonymousClass22;
                            coroutine_suspended = obj3;
                            it = channelIterator3;
                            receiveChannel = receiveChannel3;
                            function2 = function22;
                            channelIterator = channelIterator2;
                            producerScope = producerScope2;
                        } else {
                            Object objInvoke = function22.invoke(obj2, channelIterator2.next());
                            anonymousClass22.L$0 = producerScope2;
                            anonymousClass22.L$1 = channelIterator2;
                            anonymousClass22.L$2 = function22;
                            anonymousClass22.L$3 = receiveChannel3;
                            anonymousClass22.L$4 = channelIterator3;
                            anonymousClass22.L$5 = null;
                            anonymousClass22.label = 3;
                            if (producerScope2.send(objInvoke, anonymousClass22) == obj3) {
                                return obj3;
                            }
                            anonymousClass2 = anonymousClass22;
                            coroutine_suspended = obj3;
                            it = channelIterator3;
                            receiveChannel = receiveChannel3;
                            function2 = function22;
                            channelIterator = channelIterator2;
                            producerScope = producerScope2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        receiveChannel = receiveChannel3;
                        throw th;
                    }
                } else {
                    if (i != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    it = (ChannelIterator) this.L$4;
                    receiveChannel = (ReceiveChannel) this.L$3;
                    function2 = (Function2) this.L$2;
                    channelIterator = (ChannelIterator) this.L$1;
                    producerScope = (ProducerScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    anonymousClass2 = this;
                }
                anonymousClass2.L$0 = producerScope;
                anonymousClass2.L$1 = channelIterator;
                anonymousClass2.L$2 = function2;
                anonymousClass2.L$3 = receiveChannel;
                anonymousClass2.L$4 = it;
                anonymousClass2.L$5 = null;
                anonymousClass2.label = 1;
                Object objHasNext2 = it.hasNext(anonymousClass2);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                Object obj5 = coroutine_suspended;
                anonymousClass22 = anonymousClass2;
                obj = objHasNext2;
                producerScope3 = producerScope;
                channelIterator4 = channelIterator;
                function23 = function2;
                receiveChannel2 = receiveChannel;
                channelIterator3 = it;
                obj3 = obj5;
                if (((Boolean) obj).booleanValue()) {
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object any(ReceiveChannel receiveChannel, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object objHasNext = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objHasNext);
                ChannelIterator it = receiveChannel.iterator();
                anonymousClass1.L$0 = receiveChannel;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                receiveChannel = (ReceiveChannel) anonymousClass1.L$0;
                ResultKt.throwOnFailure(objHasNext);
            }
            ChannelsKt.cancelConsumed(receiveChannel, null);
            return objHasNext;
        } finally {
        }
    }

    public static final Function1<Throwable, Unit> consumes(final ReceiveChannel<?> receiveChannel) {
        return new Function1<Throwable, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            public final void invoke2(Throwable th) {
                ChannelsKt.cancelConsumed(receiveChannel, th);
            }
        };
    }

    public static final Function1<Throwable, Unit> consumesAll(final ReceiveChannel<?>... receiveChannelArr) {
        return new Function1<Throwable, Unit>() {
            {
                super(1);
            }

            @Override
            public Unit invoke(Throwable th) throws Throwable {
                invoke2(th);
                return Unit.INSTANCE;
            }

            public final void invoke2(Throwable th) throws Throwable {
                ReceiveChannel<?>[] receiveChannelArr2 = receiveChannelArr;
                int length = receiveChannelArr2.length;
                Throwable th2 = null;
                int i = 0;
                while (i < length) {
                    ReceiveChannel<?> receiveChannel = receiveChannelArr2[i];
                    i++;
                    try {
                        ChannelsKt.cancelConsumed(receiveChannel, th);
                    } catch (Throwable th3) {
                        if (th2 == null) {
                            th2 = th3;
                        } else {
                            ExceptionsKt.addSuppressed(th2, th3);
                        }
                    }
                }
                if (th2 != null) {
                    throw th2;
                }
            }
        };
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object count(ReceiveChannel receiveChannel, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        Throwable th;
        Ref.IntRef intRef;
        ReceiveChannel receiveChannel3;
        ChannelIterator it;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                intRef = new Ref.IntRef();
                receiveChannel3 = receiveChannel;
                it = receiveChannel.iterator();
                anonymousClass1.L$0 = intRef;
                anonymousClass1.L$1 = receiveChannel3;
                anonymousClass1.L$2 = it;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) anonymousClass1.L$2;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$1;
            intRef = (Ref.IntRef) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    it.next();
                    intRef.element++;
                    receiveChannel3 = receiveChannel2;
                    try {
                        anonymousClass1.L$0 = intRef;
                        anonymousClass1.L$1 = receiveChannel3;
                        anonymousClass1.L$2 = it;
                        anonymousClass1.label = 1;
                        objHasNext = it.hasNext(anonymousClass1);
                        if (objHasNext != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel3;
                        obj = objHasNext;
                        if (!((Boolean) obj).booleanValue()) {
                            Unit unit = Unit.INSTANCE;
                            ChannelsKt.cancelConsumed(receiveChannel2, null);
                            return Boxing.boxInt(intRef.element);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        receiveChannel2 = receiveChannel3;
                        try {
                            throw th;
                        } catch (Throwable th4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, th);
                            throw th4;
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                throw th;
            }
        }
    }

    public static final <E, K> ReceiveChannel<E> distinctBy(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super K>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(receiveChannel, function2, null), 6, null);
    }

    public static ReceiveChannel distinctBy$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.distinctBy(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel drop(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(i, receiveChannel, null), 6, null);
    }

    public static ReceiveChannel drop$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return drop(receiveChannel, i, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel dropWhile(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(receiveChannel, function2, null), 6, null);
    }

    public static ReceiveChannel dropWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return dropWhile(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object elementAt(ReceiveChannel receiveChannel, int i, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        Throwable th;
        int i2;
        ChannelIterator it;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        try {
            if (i3 == 0) {
                ResultKt.throwOnFailure(obj);
                if (i < 0) {
                    throw new IndexOutOfBoundsException("ReceiveChannel doesn't contain element at index " + i + '.');
                }
                i2 = 0;
                it = receiveChannel.iterator();
                anonymousClass1.L$0 = receiveChannel;
                anonymousClass1.L$1 = it;
                anonymousClass1.I$0 = i;
                anonymousClass1.I$1 = i2;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                }
            } else {
                if (i3 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i4 = anonymousClass1.I$1;
                i = anonymousClass1.I$0;
                it = (ChannelIterator) anonymousClass1.L$1;
                receiveChannel2 = (ReceiveChannel) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    if (!((Boolean) obj).booleanValue()) {
                        Object next = it.next();
                        int i5 = i4 + 1;
                        if (i == i4) {
                            ChannelsKt.cancelConsumed(receiveChannel2, null);
                            return next;
                        }
                        receiveChannel = receiveChannel2;
                        i2 = i5;
                        anonymousClass1.L$0 = receiveChannel;
                        anonymousClass1.L$1 = it;
                        anonymousClass1.I$0 = i;
                        anonymousClass1.I$1 = i2;
                        anonymousClass1.label = 1;
                        objHasNext = it.hasNext(anonymousClass1);
                        if (objHasNext != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        receiveChannel2 = receiveChannel;
                        i4 = i2;
                        obj = objHasNext;
                        if (!((Boolean) obj).booleanValue()) {
                            throw new IndexOutOfBoundsException("ReceiveChannel doesn't contain element at index " + i + '.');
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        throw th;
                    } catch (Throwable th3) {
                        ChannelsKt.cancelConsumed(receiveChannel2, th);
                        throw th3;
                    }
                }
            }
        } catch (Throwable th4) {
            receiveChannel2 = receiveChannel;
            th = th4;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object elementAtOrNull(ReceiveChannel receiveChannel, int i, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        int i2;
        ChannelIterator it;
        Throwable th;
        ReceiveChannel receiveChannel2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            if (i < 0) {
                ChannelsKt.cancelConsumed(receiveChannel, null);
                return null;
            }
            i2 = 0;
            try {
                it = receiveChannel.iterator();
                anonymousClass1.L$0 = receiveChannel;
                anonymousClass1.L$1 = it;
                anonymousClass1.I$0 = i;
                anonymousClass1.I$1 = i2;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext == coroutine_suspended) {
                }
                if (((Boolean) objHasNext).booleanValue()) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i3 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i4 = anonymousClass1.I$1;
            i = anonymousClass1.I$0;
            it = (ChannelIterator) anonymousClass1.L$1;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                i2 = i4;
                receiveChannel = receiveChannel2;
                objHasNext = obj;
                if (((Boolean) objHasNext).booleanValue()) {
                    Object next = it.next();
                    int i5 = i2 + 1;
                    if (i == i2) {
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return next;
                    }
                    i2 = i5;
                    anonymousClass1.L$0 = receiveChannel;
                    anonymousClass1.L$1 = it;
                    anonymousClass1.I$0 = i;
                    anonymousClass1.I$1 = i2;
                    anonymousClass1.label = 1;
                    objHasNext = it.hasNext(anonymousClass1);
                    if (objHasNext == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    if (((Boolean) objHasNext).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return null;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
    }

    public static final <E> ReceiveChannel<E> filter(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super Boolean>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(receiveChannel, function2, null), 6, null);
    }

    public static ReceiveChannel filter$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.filter(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel filterIndexed(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(receiveChannel, function3, null), 6, null);
    }

    public static ReceiveChannel filterIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filterIndexed(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel filterNot(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ChannelsKt.filter(receiveChannel, coroutineContext, new AnonymousClass1(function2, null));
    }

    public static ReceiveChannel filterNot$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return filterNot(receiveChannel, coroutineContext, function2);
    }

    public static final <E> ReceiveChannel<E> filterNotNull(ReceiveChannel<? extends E> receiveChannel) {
        return filter$default(receiveChannel, null, new AnonymousClass1(null), 1, null);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object filterNotNullTo(ReceiveChannel receiveChannel, Collection collection, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator it;
        Collection collection2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                receiveChannel2 = receiveChannel;
                it = receiveChannel.iterator();
                collection2 = collection;
                anonymousClass1.L$0 = collection2;
                anonymousClass1.L$1 = receiveChannel2;
                anonymousClass1.L$2 = it;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) anonymousClass1.L$2;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$1;
            Collection collection3 = (Collection) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return collection3;
                }
                Object next = it.next();
                if (next != null) {
                    collection3.add(next);
                }
                collection2 = collection3;
                anonymousClass1.L$0 = collection2;
                anonymousClass1.L$1 = receiveChannel2;
                anonymousClass1.L$2 = it;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                collection3 = collection2;
                obj = objHasNext;
                if (!((Boolean) obj).booleanValue()) {
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object filterNotNullTo(ReceiveChannel receiveChannel, SendChannel sendChannel, Continuation continuation) throws Throwable {
        AnonymousClass3 anonymousClass3;
        AnonymousClass3 anonymousClass32;
        ChannelIterator channelIterator;
        ChannelIterator it;
        if (continuation instanceof AnonymousClass3) {
            anonymousClass3 = (AnonymousClass3) continuation;
            if ((anonymousClass3.label & Integer.MIN_VALUE) != 0) {
                anonymousClass3.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass3 = new AnonymousClass3(continuation);
            }
        }
        Object obj = anonymousClass3.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass3.label;
        if (i != 0) {
            try {
                if (i == 1) {
                    ChannelIterator channelIterator2 = (ChannelIterator) anonymousClass3.L$2;
                    ReceiveChannel receiveChannel2 = (ReceiveChannel) anonymousClass3.L$1;
                    SendChannel sendChannel2 = (SendChannel) anonymousClass3.L$0;
                    ResultKt.throwOnFailure(obj);
                    anonymousClass32 = anonymousClass3;
                    channelIterator = channelIterator2;
                    receiveChannel = receiveChannel2;
                    sendChannel = sendChannel2;
                    Object obj2 = coroutine_suspended;
                    AnonymousClass3 anonymousClass33 = anonymousClass32;
                    if (!((Boolean) obj).booleanValue()) {
                        Unit unit = Unit.INSTANCE;
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return sendChannel;
                    }
                    Object next = channelIterator.next();
                    if (next != null) {
                        anonymousClass33.L$0 = sendChannel;
                        anonymousClass33.L$1 = receiveChannel;
                        anonymousClass33.L$2 = channelIterator;
                        anonymousClass33.label = 2;
                        if (sendChannel.send(next, anonymousClass33) == obj2) {
                            return obj2;
                        }
                        it = channelIterator;
                        anonymousClass3 = anonymousClass33;
                        coroutine_suspended = obj2;
                    }
                } else {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ChannelIterator channelIterator3 = (ChannelIterator) anonymousClass3.L$2;
                    ReceiveChannel receiveChannel3 = (ReceiveChannel) anonymousClass3.L$1;
                    SendChannel sendChannel3 = (SendChannel) anonymousClass3.L$0;
                    ResultKt.throwOnFailure(obj);
                    it = channelIterator3;
                    receiveChannel = receiveChannel3;
                    sendChannel = sendChannel3;
                }
            } catch (Throwable th) {
                th = th;
                try {
                    throw th;
                } catch (Throwable th2) {
                    ChannelsKt.cancelConsumed(sendChannel, th);
                    throw th2;
                }
            }
        } else {
            ResultKt.throwOnFailure(obj);
            try {
                it = receiveChannel.iterator();
            } catch (Throwable th3) {
                sendChannel = receiveChannel;
                th = th3;
                throw th;
            }
        }
        anonymousClass3.L$0 = sendChannel;
        anonymousClass3.L$1 = receiveChannel;
        anonymousClass3.L$2 = it;
        anonymousClass3.label = 1;
        Object objHasNext = it.hasNext(anonymousClass3);
        if (objHasNext == coroutine_suspended) {
            return coroutine_suspended;
        }
        anonymousClass32 = anonymousClass3;
        channelIterator = it;
        obj = objHasNext;
        sendChannel = sendChannel;
        Object obj22 = coroutine_suspended;
        AnonymousClass3 anonymousClass332 = anonymousClass32;
        if (!((Boolean) obj).booleanValue()) {
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object first(ReceiveChannel receiveChannel, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            channelIterator = (ChannelIterator) anonymousClass1.L$1;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                if (((Boolean) obj).booleanValue()) {
                    throw new NoSuchElementException("ReceiveChannel is empty.");
                }
                Object next = channelIterator.next();
                ChannelsKt.cancelConsumed(receiveChannel2, null);
                return next;
            } catch (Throwable th2) {
                th = th2;
                try {
                    throw th;
                } catch (Throwable th3) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th3;
                }
            }
        }
        ResultKt.throwOnFailure(obj);
        try {
            ChannelIterator it = receiveChannel.iterator();
            anonymousClass1.L$0 = receiveChannel;
            anonymousClass1.L$1 = it;
            anonymousClass1.label = 1;
            Object objHasNext = it.hasNext(anonymousClass1);
            if (objHasNext == coroutine_suspended) {
                return coroutine_suspended;
            }
            receiveChannel2 = receiveChannel;
            channelIterator = it;
            obj = objHasNext;
            if (((Boolean) obj).booleanValue()) {
            }
        } catch (Throwable th4) {
            receiveChannel2 = receiveChannel;
            th = th4;
            throw th;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object firstOrNull(ReceiveChannel receiveChannel, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ChannelIterator it = receiveChannel.iterator();
                anonymousClass1.L$0 = receiveChannel;
                anonymousClass1.L$1 = it;
                anonymousClass1.label = 1;
                Object objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj = objHasNext;
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            channelIterator = (ChannelIterator) anonymousClass1.L$1;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
        if (!((Boolean) obj).booleanValue()) {
            ChannelsKt.cancelConsumed(receiveChannel2, null);
            return null;
        }
        Object next = channelIterator.next();
        ChannelsKt.cancelConsumed(receiveChannel2, null);
        return next;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel flatMap(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(receiveChannel, function2, null), 6, null);
    }

    public static ReceiveChannel flatMap$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return flatMap(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object indexOf(ReceiveChannel receiveChannel, Object obj, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator it;
        Ref.IntRef intRef;
        Object obj2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj3 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj3);
            Ref.IntRef intRef2 = new Ref.IntRef();
            try {
                receiveChannel2 = receiveChannel;
                it = receiveChannel.iterator();
                intRef = intRef2;
                obj2 = obj;
                anonymousClass1.L$0 = obj2;
                anonymousClass1.L$1 = intRef;
                anonymousClass1.L$2 = receiveChannel2;
                anonymousClass1.L$3 = it;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) anonymousClass1.L$3;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$2;
            intRef = (Ref.IntRef) anonymousClass1.L$1;
            Object obj4 = anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj3);
                if (!((Boolean) obj3).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return Boxing.boxInt(-1);
                }
                if (Intrinsics.areEqual(obj4, it.next())) {
                    Integer numBoxInt = Boxing.boxInt(intRef.element);
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return numBoxInt;
                }
                intRef.element++;
                obj2 = obj4;
                anonymousClass1.L$0 = obj2;
                anonymousClass1.L$1 = intRef;
                anonymousClass1.L$2 = receiveChannel2;
                anonymousClass1.L$3 = it;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj4 = obj2;
                obj3 = objHasNext;
                if (!((Boolean) obj3).booleanValue()) {
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        try {
            throw th;
        } catch (Throwable th4) {
            ChannelsKt.cancelConsumed(receiveChannel2, th);
            throw th4;
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object last(ReceiveChannel receiveChannel, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Object next;
        ChannelIterator channelIterator2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ChannelIterator it = receiveChannel.iterator();
                anonymousClass1.L$0 = receiveChannel;
                anonymousClass1.L$1 = it;
                anonymousClass1.label = 1;
                Object objHasNext2 = it.hasNext(anonymousClass1);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj = objHasNext2;
            } catch (Throwable th) {
                receiveChannel2 = receiveChannel;
                th = th;
                throw th;
            }
        } else if (i == 1) {
            channelIterator = (ChannelIterator) anonymousClass1.L$1;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj2 = anonymousClass1.L$2;
            channelIterator2 = (ChannelIterator) anonymousClass1.L$1;
            ReceiveChannel receiveChannel3 = (ReceiveChannel) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    next = channelIterator2.next();
                    receiveChannel = receiveChannel3;
                    anonymousClass1.L$0 = receiveChannel;
                    anonymousClass1.L$1 = channelIterator2;
                    anonymousClass1.L$2 = next;
                    anonymousClass1.label = 2;
                    objHasNext = channelIterator2.hasNext(anonymousClass1);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    receiveChannel3 = receiveChannel;
                    obj2 = next;
                    obj = objHasNext;
                    if (!((Boolean) obj).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel3, null);
                        return obj2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                receiveChannel2 = receiveChannel3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
        if (!((Boolean) obj).booleanValue()) {
            throw new NoSuchElementException("ReceiveChannel is empty.");
        }
        next = channelIterator.next();
        ReceiveChannel receiveChannel4 = receiveChannel2;
        channelIterator2 = channelIterator;
        receiveChannel = receiveChannel4;
        anonymousClass1.L$0 = receiveChannel;
        anonymousClass1.L$1 = channelIterator2;
        anonymousClass1.L$2 = next;
        anonymousClass1.label = 2;
        objHasNext = channelIterator2.hasNext(anonymousClass1);
        if (objHasNext != coroutine_suspended) {
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object lastIndexOf(ReceiveChannel receiveChannel, Object obj, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        Ref.IntRef intRef;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator it;
        Ref.IntRef intRef2;
        Object obj2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj3 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj3);
            Ref.IntRef intRef3 = new Ref.IntRef();
            intRef3.element = -1;
            intRef = new Ref.IntRef();
            try {
                receiveChannel2 = receiveChannel;
                it = receiveChannel.iterator();
                intRef2 = intRef3;
                obj2 = obj;
                anonymousClass1.L$0 = obj2;
                anonymousClass1.L$1 = intRef2;
                anonymousClass1.L$2 = intRef;
                anonymousClass1.L$3 = receiveChannel2;
                anonymousClass1.L$4 = it;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) anonymousClass1.L$4;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$3;
            intRef = (Ref.IntRef) anonymousClass1.L$2;
            intRef2 = (Ref.IntRef) anonymousClass1.L$1;
            Object obj4 = anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj3);
                if (!((Boolean) obj3).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed(receiveChannel2, null);
                    return Boxing.boxInt(intRef2.element);
                }
                if (Intrinsics.areEqual(obj4, it.next())) {
                    intRef2.element = intRef.element;
                }
                intRef.element++;
                obj2 = obj4;
                anonymousClass1.L$0 = obj2;
                anonymousClass1.L$1 = intRef2;
                anonymousClass1.L$2 = intRef;
                anonymousClass1.L$3 = receiveChannel2;
                anonymousClass1.L$4 = it;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj4 = obj2;
                obj3 = objHasNext;
                if (!((Boolean) obj3).booleanValue()) {
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object lastOrNull(ReceiveChannel receiveChannel, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Object next;
        ChannelIterator channelIterator2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ChannelIterator it = receiveChannel.iterator();
                anonymousClass1.L$0 = receiveChannel;
                anonymousClass1.L$1 = it;
                anonymousClass1.label = 1;
                Object objHasNext2 = it.hasNext(anonymousClass1);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj = objHasNext2;
            } catch (Throwable th) {
                receiveChannel2 = receiveChannel;
                th = th;
                throw th;
            }
        } else if (i == 1) {
            channelIterator = (ChannelIterator) anonymousClass1.L$1;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj2 = anonymousClass1.L$2;
            channelIterator2 = (ChannelIterator) anonymousClass1.L$1;
            ReceiveChannel receiveChannel3 = (ReceiveChannel) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                if (!((Boolean) obj).booleanValue()) {
                    next = channelIterator2.next();
                    receiveChannel = receiveChannel3;
                    anonymousClass1.L$0 = receiveChannel;
                    anonymousClass1.L$1 = channelIterator2;
                    anonymousClass1.L$2 = next;
                    anonymousClass1.label = 2;
                    objHasNext = channelIterator2.hasNext(anonymousClass1);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    receiveChannel3 = receiveChannel;
                    obj2 = next;
                    obj = objHasNext;
                    if (!((Boolean) obj).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel3, null);
                        return obj2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                receiveChannel2 = receiveChannel3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
        if (!((Boolean) obj).booleanValue()) {
            ChannelsKt.cancelConsumed(receiveChannel2, null);
            return null;
        }
        next = channelIterator.next();
        ReceiveChannel receiveChannel4 = receiveChannel2;
        channelIterator2 = channelIterator;
        receiveChannel = receiveChannel4;
        anonymousClass1.L$0 = receiveChannel;
        anonymousClass1.L$1 = channelIterator2;
        anonymousClass1.L$2 = next;
        anonymousClass1.label = 2;
        objHasNext = channelIterator2.hasNext(anonymousClass1);
        if (objHasNext != coroutine_suspended) {
        }
    }

    public static final <E, R> ReceiveChannel<R> map(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function2<? super E, ? super Continuation<? super R>, ? extends Object> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(receiveChannel, function2, null), 6, null);
    }

    public static ReceiveChannel map$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.map(receiveChannel, coroutineContext, function2);
    }

    public static final <E, R> ReceiveChannel<R> mapIndexed(ReceiveChannel<? extends E> receiveChannel, CoroutineContext coroutineContext, Function3<? super Integer, ? super E, ? super Continuation<? super R>, ? extends Object> function3) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(receiveChannel, function3, null), 6, null);
    }

    public static ReceiveChannel mapIndexed$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel mapIndexedNotNull(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3) {
        return ChannelsKt.filterNotNull(ChannelsKt.mapIndexed(receiveChannel, coroutineContext, function3));
    }

    public static ReceiveChannel mapIndexedNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapIndexedNotNull(receiveChannel, coroutineContext, function3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel mapNotNull(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ChannelsKt.filterNotNull(ChannelsKt.map(receiveChannel, coroutineContext, function2));
    }

    public static ReceiveChannel mapNotNull$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return mapNotNull(receiveChannel, coroutineContext, function2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object maxWith(ReceiveChannel receiveChannel, Comparator comparator, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Comparator comparator2;
        Object next;
        Comparator comparator3;
        ChannelIterator channelIterator2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ChannelIterator it = receiveChannel.iterator();
                anonymousClass1.L$0 = comparator;
                anonymousClass1.L$1 = receiveChannel;
                anonymousClass1.L$2 = it;
                anonymousClass1.label = 1;
                Object objHasNext2 = it.hasNext(anonymousClass1);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj = objHasNext2;
                comparator2 = comparator;
            } catch (Throwable th) {
                receiveChannel2 = receiveChannel;
                th = th;
                throw th;
            }
        } else if (i == 1) {
            channelIterator = (ChannelIterator) anonymousClass1.L$2;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$1;
            comparator2 = (Comparator) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj2 = anonymousClass1.L$3;
            channelIterator2 = (ChannelIterator) anonymousClass1.L$2;
            ReceiveChannel receiveChannel3 = (ReceiveChannel) anonymousClass1.L$1;
            comparator3 = (Comparator) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                AnonymousClass1 anonymousClass12 = anonymousClass1;
                Object obj3 = obj2;
                receiveChannel = receiveChannel3;
                Object obj4 = coroutine_suspended;
                AnonymousClass1 anonymousClass13 = anonymousClass12;
                if (!((Boolean) obj).booleanValue()) {
                    next = channelIterator2.next();
                    if (comparator3.compare(obj3, next) >= 0) {
                        next = obj3;
                    }
                    anonymousClass1 = anonymousClass13;
                    coroutine_suspended = obj4;
                    anonymousClass1.L$0 = comparator3;
                    anonymousClass1.L$1 = receiveChannel;
                    anonymousClass1.L$2 = channelIterator2;
                    anonymousClass1.L$3 = next;
                    anonymousClass1.label = 2;
                    objHasNext = channelIterator2.hasNext(anonymousClass1);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass12 = anonymousClass1;
                    obj3 = next;
                    obj = objHasNext;
                    Object obj42 = coroutine_suspended;
                    AnonymousClass1 anonymousClass132 = anonymousClass12;
                    if (!((Boolean) obj).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return obj3;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                receiveChannel2 = receiveChannel3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
        if (!((Boolean) obj).booleanValue()) {
            ChannelsKt.cancelConsumed(receiveChannel2, null);
            return null;
        }
        next = channelIterator.next();
        comparator3 = comparator2;
        ReceiveChannel receiveChannel4 = receiveChannel2;
        channelIterator2 = channelIterator;
        receiveChannel = receiveChannel4;
        anonymousClass1.L$0 = comparator3;
        anonymousClass1.L$1 = receiveChannel;
        anonymousClass1.L$2 = channelIterator2;
        anonymousClass1.L$3 = next;
        anonymousClass1.label = 2;
        objHasNext = channelIterator2.hasNext(anonymousClass1);
        if (objHasNext != coroutine_suspended) {
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object minWith(ReceiveChannel receiveChannel, Comparator comparator, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        ChannelIterator channelIterator;
        Comparator comparator2;
        Object next;
        Comparator comparator3;
        ChannelIterator channelIterator2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                ChannelIterator it = receiveChannel.iterator();
                anonymousClass1.L$0 = comparator;
                anonymousClass1.L$1 = receiveChannel;
                anonymousClass1.L$2 = it;
                anonymousClass1.label = 1;
                Object objHasNext2 = it.hasNext(anonymousClass1);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj = objHasNext2;
                comparator2 = comparator;
            } catch (Throwable th) {
                receiveChannel2 = receiveChannel;
                th = th;
                throw th;
            }
        } else if (i == 1) {
            channelIterator = (ChannelIterator) anonymousClass1.L$2;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$1;
            comparator2 = (Comparator) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Object obj2 = anonymousClass1.L$3;
            channelIterator2 = (ChannelIterator) anonymousClass1.L$2;
            ReceiveChannel receiveChannel3 = (ReceiveChannel) anonymousClass1.L$1;
            comparator3 = (Comparator) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                AnonymousClass1 anonymousClass12 = anonymousClass1;
                Object obj3 = obj2;
                receiveChannel = receiveChannel3;
                Object obj4 = coroutine_suspended;
                AnonymousClass1 anonymousClass13 = anonymousClass12;
                if (!((Boolean) obj).booleanValue()) {
                    next = channelIterator2.next();
                    if (comparator3.compare(obj3, next) <= 0) {
                        next = obj3;
                    }
                    anonymousClass1 = anonymousClass13;
                    coroutine_suspended = obj4;
                    anonymousClass1.L$0 = comparator3;
                    anonymousClass1.L$1 = receiveChannel;
                    anonymousClass1.L$2 = channelIterator2;
                    anonymousClass1.L$3 = next;
                    anonymousClass1.label = 2;
                    objHasNext = channelIterator2.hasNext(anonymousClass1);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    anonymousClass12 = anonymousClass1;
                    obj3 = next;
                    obj = objHasNext;
                    Object obj42 = coroutine_suspended;
                    AnonymousClass1 anonymousClass132 = anonymousClass12;
                    if (!((Boolean) obj).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel, null);
                        return obj3;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                receiveChannel2 = receiveChannel3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
        if (!((Boolean) obj).booleanValue()) {
            ChannelsKt.cancelConsumed(receiveChannel2, null);
            return null;
        }
        next = channelIterator.next();
        comparator3 = comparator2;
        ReceiveChannel receiveChannel4 = receiveChannel2;
        channelIterator2 = channelIterator;
        receiveChannel = receiveChannel4;
        anonymousClass1.L$0 = comparator3;
        anonymousClass1.L$1 = receiveChannel;
        anonymousClass1.L$2 = channelIterator2;
        anonymousClass1.L$3 = next;
        anonymousClass1.label = 2;
        objHasNext = channelIterator2.hasNext(anonymousClass1);
        if (objHasNext != coroutine_suspended) {
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object none(ReceiveChannel receiveChannel, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object objHasNext = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        boolean z = true;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(objHasNext);
                ChannelIterator it = receiveChannel.iterator();
                anonymousClass1.L$0 = receiveChannel;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                receiveChannel = (ReceiveChannel) anonymousClass1.L$0;
                ResultKt.throwOnFailure(objHasNext);
            }
            if (((Boolean) objHasNext).booleanValue()) {
                z = false;
            }
            Boolean boolBoxBoolean = Boxing.boxBoolean(z);
            ChannelsKt.cancelConsumed(receiveChannel, null);
            return boolBoxBoolean;
        } finally {
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object single(ReceiveChannel receiveChannel, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        ReceiveChannel receiveChannel3;
        Object obj;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            try {
                ChannelIterator it = receiveChannel.iterator();
                anonymousClass1.L$0 = receiveChannel;
                anonymousClass1.L$1 = it;
                anonymousClass1.label = 1;
                Object objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj2 = objHasNext;
                if (((Boolean) obj2).booleanValue()) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else if (i == 1) {
            channelIterator = (ChannelIterator) anonymousClass1.L$1;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj2);
                if (((Boolean) obj2).booleanValue()) {
                    throw new NoSuchElementException("ReceiveChannel is empty.");
                }
                Object next = channelIterator.next();
                anonymousClass1.L$0 = receiveChannel2;
                anonymousClass1.L$1 = next;
                anonymousClass1.label = 2;
                Object objHasNext2 = channelIterator.hasNext(anonymousClass1);
                if (objHasNext2 == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel3 = receiveChannel2;
                obj2 = objHasNext2;
                obj = next;
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            obj = anonymousClass1.L$1;
            receiveChannel3 = (ReceiveChannel) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj2);
            } catch (Throwable th4) {
                th = th4;
                receiveChannel2 = receiveChannel3;
                try {
                    throw th;
                } catch (Throwable th5) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th5;
                }
            }
        }
        if (((Boolean) obj2).booleanValue()) {
            throw new IllegalArgumentException("ReceiveChannel has more than one element.");
        }
        ChannelsKt.cancelConsumed(receiveChannel3, null);
        return obj;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final Object singleOrNull(ReceiveChannel receiveChannel, Continuation continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel receiveChannel2;
        Throwable th;
        ChannelIterator channelIterator;
        ReceiveChannel receiveChannel3;
        Object obj;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj2);
            try {
                ChannelIterator it = receiveChannel.iterator();
                anonymousClass1.L$0 = receiveChannel;
                anonymousClass1.L$1 = it;
                anonymousClass1.label = 1;
                Object objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                receiveChannel2 = receiveChannel;
                channelIterator = it;
                obj2 = objHasNext;
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                obj = anonymousClass1.L$1;
                receiveChannel3 = (ReceiveChannel) anonymousClass1.L$0;
                try {
                    ResultKt.throwOnFailure(obj2);
                    if (((Boolean) obj2).booleanValue()) {
                        ChannelsKt.cancelConsumed(receiveChannel3, null);
                        return obj;
                    }
                    ChannelsKt.cancelConsumed(receiveChannel3, null);
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    receiveChannel2 = receiveChannel3;
                    try {
                        throw th;
                    } catch (Throwable th4) {
                        ChannelsKt.cancelConsumed(receiveChannel2, th);
                        throw th4;
                    }
                }
            }
            channelIterator = (ChannelIterator) anonymousClass1.L$1;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj2);
            } catch (Throwable th5) {
                th = th5;
                throw th;
            }
        }
        if (!((Boolean) obj2).booleanValue()) {
            ChannelsKt.cancelConsumed(receiveChannel2, null);
            return null;
        }
        Object next = channelIterator.next();
        anonymousClass1.L$0 = receiveChannel2;
        anonymousClass1.L$1 = next;
        anonymousClass1.label = 2;
        Object objHasNext2 = channelIterator.hasNext(anonymousClass1);
        if (objHasNext2 == coroutine_suspended) {
            return coroutine_suspended;
        }
        receiveChannel3 = receiveChannel2;
        obj2 = objHasNext2;
        obj = next;
        if (((Boolean) obj2).booleanValue()) {
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel take(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(i, receiveChannel, null), 6, null);
    }

    public static ReceiveChannel take$default(ReceiveChannel receiveChannel, int i, CoroutineContext coroutineContext, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return take(receiveChannel, i, coroutineContext);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel takeWhile(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(receiveChannel, function2, null), 6, null);
    }

    public static ReceiveChannel takeWhile$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return takeWhile(receiveChannel, coroutineContext, function2);
    }

    public static final <E, C extends SendChannel<? super E>> Object toChannel(ReceiveChannel<? extends E> receiveChannel, C c, Continuation<? super C> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ChannelIterator<? extends E> it;
        ChannelIterator<? extends E> channelIterator;
        SendChannel sendChannel;
        SendChannel sendChannel2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    it = receiveChannel.iterator();
                    sendChannel2 = c;
                    anonymousClass1.L$0 = sendChannel2;
                    anonymousClass1.L$1 = receiveChannel;
                    anonymousClass1.L$2 = it;
                    anonymousClass1.label = 1;
                    objHasNext = it.hasNext(anonymousClass1);
                    if (objHasNext == coroutine_suspended) {
                    }
                } catch (Throwable th) {
                    c = receiveChannel;
                    th = th;
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        ChannelsKt.cancelConsumed(c, th);
                        throw th2;
                    }
                }
            } else if (i == 1) {
                channelIterator = (ChannelIterator) anonymousClass1.L$2;
                boolean z = (C) ((ReceiveChannel) anonymousClass1.L$1);
                sendChannel = (SendChannel) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj);
                c = z;
                if (((Boolean) obj).booleanValue()) {
                }
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                channelIterator = (ChannelIterator) anonymousClass1.L$2;
                ReceiveChannel<? extends E> receiveChannel2 = (C) ((ReceiveChannel) anonymousClass1.L$1);
                sendChannel = (SendChannel) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj);
                ReceiveChannel<? extends E> receiveChannel3 = receiveChannel2;
                it = channelIterator;
                receiveChannel = receiveChannel3;
                sendChannel2 = (C) sendChannel;
                anonymousClass1.L$0 = sendChannel2;
                anonymousClass1.L$1 = receiveChannel;
                anonymousClass1.L$2 = it;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                SendChannel sendChannel3 = sendChannel2;
                boolean z2 = (C) receiveChannel;
                channelIterator = it;
                obj = objHasNext;
                sendChannel = sendChannel3;
                c = z2;
                if (((Boolean) obj).booleanValue()) {
                    Unit unit = Unit.INSTANCE;
                    ChannelsKt.cancelConsumed((ReceiveChannel) c, null);
                    return sendChannel;
                }
                E next = channelIterator.next();
                anonymousClass1.L$0 = sendChannel;
                anonymousClass1.L$1 = (Object) c;
                anonymousClass1.L$2 = channelIterator;
                anonymousClass1.label = 2;
                receiveChannel3 = c;
                if (sendChannel.send(next, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                it = channelIterator;
                receiveChannel = receiveChannel3;
                sendChannel2 = (C) sendChannel;
                anonymousClass1.L$0 = sendChannel2;
                anonymousClass1.L$1 = receiveChannel;
                anonymousClass1.L$2 = it;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext == coroutine_suspended) {
                }
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public static final <E, C extends Collection<? super E>> Object toCollection(ReceiveChannel<? extends E> receiveChannel, C c, Continuation<? super C> continuation) throws Throwable {
        AnonymousClass1 anonymousClass1;
        ReceiveChannel<? extends E> receiveChannel2;
        Throwable th;
        ChannelIterator it;
        C c2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                receiveChannel2 = receiveChannel;
                it = receiveChannel.iterator();
                c2 = c;
                anonymousClass1.L$0 = c2;
                anonymousClass1.L$1 = receiveChannel2;
                anonymousClass1.L$2 = it;
                anonymousClass1.label = 1;
                objHasNext = it.hasNext(anonymousClass1);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) anonymousClass1.L$2;
            receiveChannel2 = (ReceiveChannel) anonymousClass1.L$1;
            Collection collection = (Collection) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                ?? r2 = collection;
                if (!((Boolean) obj).booleanValue()) {
                    r2.add(it.next());
                    c2 = r2;
                    anonymousClass1.L$0 = c2;
                    anonymousClass1.L$1 = receiveChannel2;
                    anonymousClass1.L$2 = it;
                    anonymousClass1.label = 1;
                    objHasNext = it.hasNext(anonymousClass1);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r2 = c2;
                    obj = objHasNext;
                    if (!((Boolean) obj).booleanValue()) {
                        Unit unit = Unit.INSTANCE;
                        ChannelsKt.cancelConsumed(receiveChannel2, null);
                        return r2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
    }

    public static final <K, V, M extends Map<? super K, ? super V>> Object toMap(ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel, M m, Continuation<? super M> continuation) throws Throwable {
        AnonymousClass2 anonymousClass2;
        ReceiveChannel<? extends Pair<? extends K, ? extends V>> receiveChannel2;
        Throwable th;
        ChannelIterator it;
        M m2;
        Object objHasNext;
        if (continuation instanceof AnonymousClass2) {
            anonymousClass2 = (AnonymousClass2) continuation;
            if ((anonymousClass2.label & Integer.MIN_VALUE) != 0) {
                anonymousClass2.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass2 = new AnonymousClass2(continuation);
            }
        }
        Object obj = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass2.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                receiveChannel2 = receiveChannel;
                it = receiveChannel.iterator();
                m2 = m;
                anonymousClass2.L$0 = m2;
                anonymousClass2.L$1 = receiveChannel2;
                anonymousClass2.L$2 = it;
                anonymousClass2.label = 1;
                objHasNext = it.hasNext(anonymousClass2);
                if (objHasNext != coroutine_suspended) {
                }
            } catch (Throwable th2) {
                receiveChannel2 = receiveChannel;
                th = th2;
                throw th;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (ChannelIterator) anonymousClass2.L$2;
            receiveChannel2 = (ReceiveChannel) anonymousClass2.L$1;
            Map map = (Map) anonymousClass2.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                ?? r2 = map;
                if (!((Boolean) obj).booleanValue()) {
                    Pair pair = (Pair) it.next();
                    r2.put(pair.getFirst(), pair.getSecond());
                    m2 = r2;
                    anonymousClass2.L$0 = m2;
                    anonymousClass2.L$1 = receiveChannel2;
                    anonymousClass2.L$2 = it;
                    anonymousClass2.label = 1;
                    objHasNext = it.hasNext(anonymousClass2);
                    if (objHasNext != coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    r2 = m2;
                    obj = objHasNext;
                    if (!((Boolean) obj).booleanValue()) {
                        Unit unit = Unit.INSTANCE;
                        ChannelsKt.cancelConsumed(receiveChannel2, null);
                        return r2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    throw th;
                } catch (Throwable th4) {
                    ChannelsKt.cancelConsumed(receiveChannel2, th);
                    throw th4;
                }
            }
        }
    }

    public static final <E> Object toMutableSet(ReceiveChannel<? extends E> receiveChannel, Continuation<? super Set<E>> continuation) {
        return ChannelsKt.toCollection(receiveChannel, new LinkedHashSet(), continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Binary compatibility")
    public static final ReceiveChannel withIndex(ReceiveChannel receiveChannel, CoroutineContext coroutineContext) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumes(receiveChannel), new AnonymousClass1(receiveChannel, null), 6, null);
    }

    public static ReceiveChannel withIndex$default(ReceiveChannel receiveChannel, CoroutineContext coroutineContext, int i, Object obj) {
        if ((i & 1) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return withIndex(receiveChannel, coroutineContext);
    }

    public static final <E, R, V> ReceiveChannel<V> zip(ReceiveChannel<? extends E> receiveChannel, ReceiveChannel<? extends R> receiveChannel2, CoroutineContext coroutineContext, Function2<? super E, ? super R, ? extends V> function2) {
        return ProduceKt.produce$default(GlobalScope.INSTANCE, coroutineContext, 0, null, ChannelsKt.consumesAll(receiveChannel, receiveChannel2), new AnonymousClass2(receiveChannel2, receiveChannel, function2, null), 6, null);
    }

    public static ReceiveChannel zip$default(ReceiveChannel receiveChannel, ReceiveChannel receiveChannel2, CoroutineContext coroutineContext, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineContext = Dispatchers.getUnconfined();
        }
        return ChannelsKt.zip(receiveChannel, receiveChannel2, coroutineContext, function2);
    }
}
