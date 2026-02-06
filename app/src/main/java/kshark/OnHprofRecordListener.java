package kshark;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lkshark/OnHprofRecordListener;", "", "onHprofRecord", "", "position", "", "record", "Lkshark/HprofRecord;", "Companion", "shark"}, k = 1, mv = {1, 1, 15})
public interface OnHprofRecordListener {

    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u001a\b\u0004\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006H\u0086\n¨\u0006\n"}, d2 = {"Lkshark/OnHprofRecordListener$Companion;", "", "()V", "invoke", "Lkshark/OnHprofRecordListener;", "block", "Lkotlin/Function2;", "", "Lkshark/HprofRecord;", "", "shark"}, k = 1, mv = {1, 1, 15})
    public static final class Companion {
        static final Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final OnHprofRecordListener invoke(final Function2<? super Long, ? super HprofRecord, Unit> block) {
            Intrinsics.checkParameterIsNotNull(block, "block");
            return new OnHprofRecordListener() {
                @Override
                public void onHprofRecord(long position, HprofRecord record) {
                    Intrinsics.checkParameterIsNotNull(record, "record");
                    block.invoke(Long.valueOf(position), record);
                }
            };
        }
    }

    void onHprofRecord(long position, HprofRecord record);
}
