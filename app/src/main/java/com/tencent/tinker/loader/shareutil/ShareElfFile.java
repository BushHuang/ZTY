package com.tencent.tinker.loader.shareutil;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ShareElfFile implements Closeable {
    public static final int FILE_TYPE_ELF = 1;
    public static final int FILE_TYPE_ODEX = 0;
    public static final int FILE_TYPE_OTHERS = -1;
    public ElfHeader elfHeader;
    private final FileInputStream fis;
    public ProgramHeader[] programHeaders;
    public SectionHeader[] sectionHeaders;
    private final Map<String, SectionHeader> sectionNameToHeaderMap = new HashMap();

    public static class ElfHeader {
        public static final int EI_CLASS = 4;
        public static final int EI_DATA = 5;
        private static final int EI_NINDENT = 16;
        public static final int EI_VERSION = 6;
        public static final int ELFCLASS32 = 1;
        public static final int ELFCLASS64 = 2;
        public static final int ELFDATA2LSB = 1;
        public static final int ELFDATA2MSB = 2;
        public static final int ET_CORE = 4;
        public static final int ET_DYN = 3;
        public static final int ET_EXEC = 2;
        public static final int ET_HIPROC = 65535;
        public static final int ET_LOPROC = 65280;
        public static final int ET_NONE = 0;
        public static final int ET_REL = 1;
        public static final int EV_CURRENT = 1;
        public final short eEhSize;
        public final long eEntry;
        public final int eFlags;
        public final byte[] eIndent;
        public final short eMachine;
        public final short ePhEntSize;
        public final short ePhNum;
        public final long ePhOff;
        public final short eShEntSize;
        public final short eShNum;
        public final long eShOff;
        public final short eShStrNdx;
        public final short eType;
        public final int eVersion;

        private ElfHeader(FileChannel fileChannel) throws IOException {
            this.eIndent = new byte[16];
            fileChannel.position(0L);
            fileChannel.read(ByteBuffer.wrap(this.eIndent));
            byte[] bArr = this.eIndent;
            if (bArr[0] != 127 || bArr[1] != 69 || bArr[2] != 76 || bArr[3] != 70) {
                throw new IOException(String.format("bad elf magic: %x %x %x %x.", Byte.valueOf(this.eIndent[0]), Byte.valueOf(this.eIndent[1]), Byte.valueOf(this.eIndent[2]), Byte.valueOf(this.eIndent[3])));
            }
            ShareElfFile.assertInRange(bArr[4], 1, 2, "bad elf class: " + ((int) this.eIndent[4]));
            ShareElfFile.assertInRange(this.eIndent[5], 1, 2, "bad elf data encoding: " + ((int) this.eIndent[5]));
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.eIndent[4] == 1 ? 36 : 48);
            byteBufferAllocate.order(this.eIndent[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
            ShareElfFile.readUntilLimit(fileChannel, byteBufferAllocate, "failed to read rest part of ehdr.");
            this.eType = byteBufferAllocate.getShort();
            this.eMachine = byteBufferAllocate.getShort();
            int i = byteBufferAllocate.getInt();
            this.eVersion = i;
            ShareElfFile.assertInRange(i, 1, 1, "bad elf version: " + this.eVersion);
            byte b = this.eIndent[4];
            if (b == 1) {
                this.eEntry = byteBufferAllocate.getInt();
                this.ePhOff = byteBufferAllocate.getInt();
                this.eShOff = byteBufferAllocate.getInt();
            } else {
                if (b != 2) {
                    throw new IOException("Unexpected elf class: " + ((int) this.eIndent[4]));
                }
                this.eEntry = byteBufferAllocate.getLong();
                this.ePhOff = byteBufferAllocate.getLong();
                this.eShOff = byteBufferAllocate.getLong();
            }
            this.eFlags = byteBufferAllocate.getInt();
            this.eEhSize = byteBufferAllocate.getShort();
            this.ePhEntSize = byteBufferAllocate.getShort();
            this.ePhNum = byteBufferAllocate.getShort();
            this.eShEntSize = byteBufferAllocate.getShort();
            this.eShNum = byteBufferAllocate.getShort();
            this.eShStrNdx = byteBufferAllocate.getShort();
        }
    }

    public static class ProgramHeader {
        public static final int PF_R = 4;
        public static final int PF_W = 2;
        public static final int PF_X = 1;
        public static final int PT_DYNAMIC = 2;
        public static final int PT_HIPROC = Integer.MAX_VALUE;
        public static final int PT_INTERP = 3;
        public static final int PT_LOAD = 1;
        public static final int PT_LOPROC = 1879048192;
        public static final int PT_NOTE = 4;
        public static final int PT_NULL = 0;
        public static final int PT_PHDR = 6;
        public static final int PT_SHLIB = 5;
        public final long pAlign;
        public final long pFileSize;
        public final int pFlags;
        public final long pMemSize;
        public final long pOffset;
        public final long pPddr;
        public final int pType;
        public final long pVddr;

        private ProgramHeader(ByteBuffer byteBuffer, int i) throws IOException {
            if (i == 1) {
                this.pType = byteBuffer.getInt();
                this.pOffset = byteBuffer.getInt();
                this.pVddr = byteBuffer.getInt();
                this.pPddr = byteBuffer.getInt();
                this.pFileSize = byteBuffer.getInt();
                this.pMemSize = byteBuffer.getInt();
                this.pFlags = byteBuffer.getInt();
                this.pAlign = byteBuffer.getInt();
                return;
            }
            if (i != 2) {
                throw new IOException("Unexpected elf class: " + i);
            }
            this.pType = byteBuffer.getInt();
            this.pFlags = byteBuffer.getInt();
            this.pOffset = byteBuffer.getLong();
            this.pVddr = byteBuffer.getLong();
            this.pPddr = byteBuffer.getLong();
            this.pFileSize = byteBuffer.getLong();
            this.pMemSize = byteBuffer.getLong();
            this.pAlign = byteBuffer.getLong();
        }
    }

    public static class SectionHeader {
        public static final int SHF_ALLOC = 2;
        public static final int SHF_EXECINSTR = 4;
        public static final int SHF_MASKPROC = -268435456;
        public static final int SHF_WRITE = 1;
        public static final int SHN_ABS = 65521;
        public static final int SHN_COMMON = 65522;
        public static final int SHN_HIPROC = 65311;
        public static final int SHN_HIRESERVE = 65535;
        public static final int SHN_LOPROC = 65280;
        public static final int SHN_LORESERVE = 65280;
        public static final int SHN_UNDEF = 0;
        public static final int SHT_DYNAMIC = 6;
        public static final int SHT_DYNSYM = 11;
        public static final int SHT_HASH = 5;
        public static final int SHT_HIPROC = Integer.MAX_VALUE;
        public static final int SHT_HIUSER = -1;
        public static final int SHT_LOPROC = 1879048192;
        public static final int SHT_LOUSER = Integer.MIN_VALUE;
        public static final int SHT_NOBITS = 8;
        public static final int SHT_NOTE = 7;
        public static final int SHT_NULL = 0;
        public static final int SHT_PROGBITS = 1;
        public static final int SHT_REL = 9;
        public static final int SHT_RELA = 4;
        public static final int SHT_SHLIB = 10;
        public static final int SHT_STRTAB = 3;
        public static final int SHT_SYMTAB = 2;
        public final long shAddr;
        public final long shAddrAlign;
        public final long shEntSize;
        public final long shFlags;
        public final int shInfo;
        public final int shLink;
        public final int shName;
        public String shNameStr;
        public final long shOffset;
        public final long shSize;
        public final int shType;

        private SectionHeader(ByteBuffer byteBuffer, int i) throws IOException {
            if (i == 1) {
                this.shName = byteBuffer.getInt();
                this.shType = byteBuffer.getInt();
                this.shFlags = byteBuffer.getInt();
                this.shAddr = byteBuffer.getInt();
                this.shOffset = byteBuffer.getInt();
                this.shSize = byteBuffer.getInt();
                this.shLink = byteBuffer.getInt();
                this.shInfo = byteBuffer.getInt();
                this.shAddrAlign = byteBuffer.getInt();
                this.shEntSize = byteBuffer.getInt();
            } else {
                if (i != 2) {
                    throw new IOException("Unexpected elf class: " + i);
                }
                this.shName = byteBuffer.getInt();
                this.shType = byteBuffer.getInt();
                this.shFlags = byteBuffer.getLong();
                this.shAddr = byteBuffer.getLong();
                this.shOffset = byteBuffer.getLong();
                this.shSize = byteBuffer.getLong();
                this.shLink = byteBuffer.getInt();
                this.shInfo = byteBuffer.getInt();
                this.shAddrAlign = byteBuffer.getLong();
                this.shEntSize = byteBuffer.getLong();
            }
            this.shNameStr = null;
        }
    }

    public ShareElfFile(File file) throws IOException {
        this.elfHeader = null;
        this.programHeaders = null;
        this.sectionHeaders = null;
        FileInputStream fileInputStream = new FileInputStream(file);
        this.fis = fileInputStream;
        FileChannel channel = fileInputStream.getChannel();
        this.elfHeader = new ElfHeader(channel);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(128);
        byteBufferAllocate.limit(this.elfHeader.ePhEntSize);
        byteBufferAllocate.order(this.elfHeader.eIndent[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
        channel.position(this.elfHeader.ePhOff);
        this.programHeaders = new ProgramHeader[this.elfHeader.ePhNum];
        for (int i = 0; i < this.programHeaders.length; i++) {
            readUntilLimit(channel, byteBufferAllocate, "failed to read phdr.");
            this.programHeaders[i] = new ProgramHeader(byteBufferAllocate, this.elfHeader.eIndent[4]);
        }
        channel.position(this.elfHeader.eShOff);
        byteBufferAllocate.limit(this.elfHeader.eShEntSize);
        this.sectionHeaders = new SectionHeader[this.elfHeader.eShNum];
        for (int i2 = 0; i2 < this.sectionHeaders.length; i2++) {
            readUntilLimit(channel, byteBufferAllocate, "failed to read shdr.");
            this.sectionHeaders[i2] = new SectionHeader(byteBufferAllocate, this.elfHeader.eIndent[4]);
        }
        if (this.elfHeader.eShStrNdx > 0) {
            ByteBuffer section = getSection(this.sectionHeaders[this.elfHeader.eShStrNdx]);
            for (SectionHeader sectionHeader : this.sectionHeaders) {
                section.position(sectionHeader.shName);
                sectionHeader.shNameStr = readCString(section);
                this.sectionNameToHeaderMap.put(sectionHeader.shNameStr, sectionHeader);
            }
        }
    }

    private static void assertInRange(int i, int i2, int i3, String str) throws IOException {
        if (i < i2 || i > i3) {
            throw new IOException(str);
        }
    }

    public static int getFileTypeByMagic(File file) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            byte[] bArr = new byte[4];
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileInputStream2.read(bArr);
                if (bArr[0] == 100 && bArr[1] == 101 && bArr[2] == 121 && bArr[3] == 10) {
                    try {
                        fileInputStream2.close();
                    } catch (Throwable unused) {
                    }
                    return 0;
                }
                if (bArr[0] == 127 && bArr[1] == 69 && bArr[2] == 76) {
                    if (bArr[3] == 70) {
                        try {
                            fileInputStream2.close();
                        } catch (Throwable unused2) {
                        }
                        return 1;
                    }
                }
                try {
                    fileInputStream2.close();
                } catch (Throwable unused3) {
                }
                return -1;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable unused4) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String readCString(ByteBuffer byteBuffer) {
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position();
        while (byteBuffer.hasRemaining() && bArrArray[byteBuffer.position()] != 0) {
            byteBuffer.position(byteBuffer.position() + 1);
        }
        byteBuffer.position(byteBuffer.position() + 1);
        return new String(bArrArray, iPosition, (byteBuffer.position() - iPosition) - 1, Charset.forName("ASCII"));
    }

    public static void readUntilLimit(FileChannel fileChannel, ByteBuffer byteBuffer, String str) throws IOException {
        byteBuffer.rewind();
        int i = fileChannel.read(byteBuffer);
        if (i == byteBuffer.limit()) {
            byteBuffer.flip();
            return;
        }
        throw new IOException(str + " Rest bytes insufficient, expect to read " + byteBuffer.limit() + " bytes but only " + i + " bytes were read.");
    }

    @Override
    public void close() throws IOException {
        this.fis.close();
        this.sectionNameToHeaderMap.clear();
        this.programHeaders = null;
        this.sectionHeaders = null;
    }

    public FileChannel getChannel() {
        return this.fis.getChannel();
    }

    public ByteOrder getDataOrder() {
        return this.elfHeader.eIndent[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
    }

    public ByteBuffer getSection(SectionHeader sectionHeader) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) sectionHeader.shSize);
        this.fis.getChannel().position(sectionHeader.shOffset);
        readUntilLimit(this.fis.getChannel(), byteBufferAllocate, "failed to read section: " + sectionHeader.shNameStr);
        return byteBufferAllocate;
    }

    public SectionHeader getSectionHeaderByName(String str) {
        return this.sectionNameToHeaderMap.get(str);
    }

    public ByteBuffer getSegment(ProgramHeader programHeader) throws IOException {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) programHeader.pFileSize);
        this.fis.getChannel().position(programHeader.pOffset);
        readUntilLimit(this.fis.getChannel(), byteBufferAllocate, "failed to read segment (type: " + programHeader.pType + ").");
        return byteBufferAllocate;
    }

    public boolean is32BitElf() {
        return this.elfHeader.eIndent[4] == 1;
    }
}
