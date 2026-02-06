package com.tencent.tinker.loader.shareutil;

import com.tencent.tinker.loader.shareutil.ShareElfFile;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public final class ShareOatUtil {
    private static final String TAG = "Tinker.OatUtil";

    static class AnonymousClass1 {
        static final int[] $SwitchMap$com$tencent$tinker$loader$shareutil$ShareOatUtil$InstructionSet;

        static {
            int[] iArr = new int[InstructionSet.values().length];
            $SwitchMap$com$tencent$tinker$loader$shareutil$ShareOatUtil$InstructionSet = iArr;
            try {
                iArr[InstructionSet.kArm.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$tencent$tinker$loader$shareutil$ShareOatUtil$InstructionSet[InstructionSet.kThumb2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$tencent$tinker$loader$shareutil$ShareOatUtil$InstructionSet[InstructionSet.kArm64.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$tencent$tinker$loader$shareutil$ShareOatUtil$InstructionSet[InstructionSet.kX86.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$tencent$tinker$loader$shareutil$ShareOatUtil$InstructionSet[InstructionSet.kX86_64.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$tencent$tinker$loader$shareutil$ShareOatUtil$InstructionSet[InstructionSet.kMips.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$tencent$tinker$loader$shareutil$ShareOatUtil$InstructionSet[InstructionSet.kMips64.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$tencent$tinker$loader$shareutil$ShareOatUtil$InstructionSet[InstructionSet.kNone.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private enum InstructionSet {
        kNone,
        kArm,
        kArm64,
        kThumb2,
        kX86,
        kX86_64,
        kMips,
        kMips64
    }

    private ShareOatUtil() {
        throw new UnsupportedOperationException();
    }

    public static String getOatFileInstructionSet(File file) throws Throwable {
        String str;
        ShareElfFile shareElfFile = null;
        try {
            ShareElfFile shareElfFile2 = new ShareElfFile(file);
            try {
                ShareElfFile.SectionHeader sectionHeaderByName = shareElfFile2.getSectionHeaderByName(".rodata");
                if (sectionHeaderByName == null) {
                    throw new IOException("Unable to find .rodata section.");
                }
                FileChannel channel = shareElfFile2.getChannel();
                channel.position(sectionHeaderByName.shOffset);
                byte[] bArr = new byte[8];
                ShareElfFile.readUntilLimit(channel, ByteBuffer.wrap(bArr), "Failed to read oat magic and version.");
                if (bArr[0] != 111 || bArr[1] != 97 || bArr[2] != 116 || bArr[3] != 10) {
                    throw new IOException(String.format("Bad oat magic: %x %x %x %x", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3])));
                }
                String str2 = new String(bArr, 4, 3, Charset.forName("ASCII"));
                try {
                    Integer.parseInt(str2);
                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate(128);
                    byteBufferAllocate.order(shareElfFile2.getDataOrder());
                    channel.position(sectionHeaderByName.shOffset + 12);
                    byteBufferAllocate.limit(4);
                    ShareElfFile.readUntilLimit(channel, byteBufferAllocate, "Failed to read isa num.");
                    int i = byteBufferAllocate.getInt();
                    if (i < 0 || i >= InstructionSet.values().length) {
                        throw new IOException("Bad isa num: " + i);
                    }
                    switch (AnonymousClass1.$SwitchMap$com$tencent$tinker$loader$shareutil$ShareOatUtil$InstructionSet[InstructionSet.values()[i].ordinal()]) {
                        case 1:
                        case 2:
                            str = "arm";
                            break;
                        case 3:
                            str = "arm64";
                            break;
                        case 4:
                            str = "x86";
                            break;
                        case 5:
                            str = "x86_64";
                            break;
                        case 6:
                            str = "mips";
                            break;
                        case 7:
                            str = "mips64";
                            break;
                        case 8:
                            str = "none";
                            break;
                        default:
                            throw new IOException("Should not reach here.");
                    }
                    try {
                        shareElfFile2.close();
                    } catch (Exception unused) {
                    }
                    return str;
                } catch (NumberFormatException unused2) {
                    throw new IOException("Bad oat version: " + str2);
                }
            } catch (Throwable th) {
                th = th;
                shareElfFile = shareElfFile2;
                if (shareElfFile != null) {
                    try {
                        shareElfFile.close();
                    } catch (Exception unused3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
