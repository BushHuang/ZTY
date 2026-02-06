package kshark.internal.aosp;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\b`\u0018\u00002\u00020\u0001J0\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, d2 = {"Lkshark/internal/aosp/ByteArrayComparator;", "", "compare", "", "entrySize", "o1Array", "", "o1Index", "o2Array", "o2Index", "shark"}, k = 1, mv = {1, 1, 15})
public interface ByteArrayComparator {
    int compare(int entrySize, byte[] o1Array, int o1Index, byte[] o2Array, int o2Index);
}
