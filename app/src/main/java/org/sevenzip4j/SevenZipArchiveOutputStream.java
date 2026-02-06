package org.sevenzip4j;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.EndianUtils;
import org.apache.commons.io.output.CloseShieldOutputStream;
import org.sevenzip4j.archive.CRC;
import org.sevenzip4j.archive.CoderInfo;
import org.sevenzip4j.archive.Folder;
import org.sevenzip4j.archive.SevenZipEntry;
import org.sevenzip4j.archive.SevenZipHeader;
import org.sevenzip4j.archive.SubStream;
import org.sevenzip4j.archive.coder.LZMACoder;
import org.sevenzip4j.stream.CRCOutputStream;
import org.sevenzip4j.stream.RandomFileOutputStream;
import org.sevenzip4j.stream.SeekableByteArrayOutputStream;
import org.sevenzip4j.stream.SeekableOutputStream;

public class SevenZipArchiveOutputStream extends OutputStream {
    private Folder contentFolder;
    private CRCOutputStream crcOutputStream;
    private String encoder;
    private OutputStream encoderOutputStream;
    private OutputStream outputStream;
    private SeekableOutputStream seekableOutputStream;
    private CRCOutputStream sevenZipEntryCRCStream;
    private long sevenZipSignatureHeaderReferencePosition;
    private long sevenZipSignatureHeaderSize;
    private List<SubStream> subStreams;

    public SevenZipArchiveOutputStream(File file) throws IOException {
        this(file, "LZMA");
    }

    public SevenZipArchiveOutputStream(File file, String str) throws IOException {
        this.subStreams = new ArrayList();
        this.sevenZipSignatureHeaderReferencePosition = 0L;
        if (!file.exists() || !file.isFile() || file.delete()) {
            initialize(new RandomFileOutputStream(file), str);
        } else {
            throw new IOException("unabled to delete existing file: " + file);
        }
    }

    public SevenZipArchiveOutputStream(OutputStream outputStream) throws IOException {
        this(outputStream, "LZMA");
    }

    public SevenZipArchiveOutputStream(OutputStream outputStream, String str) throws IOException {
        this.subStreams = new ArrayList();
        this.sevenZipSignatureHeaderReferencePosition = 0L;
        this.outputStream = outputStream;
        SeekableByteArrayOutputStream seekableByteArrayOutputStream = new SeekableByteArrayOutputStream();
        this.seekableOutputStream = seekableByteArrayOutputStream;
        initialize(seekableByteArrayOutputStream, str);
    }

    private byte[] calcUINT64(long j) {
        byte[] bArrLongToByte = longToByte(j);
        int i = 1;
        for (long j2 = -128; (j & j2) != 0; j2 <<= 7) {
            i++;
        }
        if (i == 8) {
            i++;
        }
        int i2 = i;
        byte[] bArr = new byte[i2];
        byte b = 0;
        for (int i3 = 1; i3 < i2; i3++) {
            b = (byte) (((byte) (b >>> 1)) | 128);
        }
        for (int i4 = 1; i4 < i2 && i4 < bArrLongToByte.length; i4++) {
            bArr[i4] = bArrLongToByte[bArrLongToByte.length - i4];
        }
        bArr[0] = bArrLongToByte[bArrLongToByte.length - i2];
        bArr[0] = (byte) (bArr[0] | b);
        return bArr;
    }

    private OutputStream getHeaderEncoderOutputStream(Folder folder, String str, OutputStream outputStream) throws IOException {
        LZMACoder lZMACoder = new LZMACoder();
        CoderInfo coderInfo = new CoderInfo();
        coderInfo.setCoder(lZMACoder);
        folder.setCoders(new CoderInfo[]{coderInfo});
        lZMACoder.getEncoder().SetLcLpPb(93, 0, 0);
        lZMACoder.getEncoder().SetDictionarySize(1048576);
        return lZMACoder.getEncoderOutputStream(outputStream);
    }

    private OutputStream getPackedHeaderEncoderOutputStream(Folder folder, String str, OutputStream outputStream) throws IOException {
        LZMACoder lZMACoder = new LZMACoder();
        CoderInfo coderInfo = new CoderInfo();
        coderInfo.setCoder(lZMACoder);
        folder.setCoders(new CoderInfo[]{coderInfo});
        lZMACoder.getEncoder().SetLcLpPb(93, 0, 0);
        lZMACoder.getEncoder().SetDictionarySize(512);
        return lZMACoder.getEncoderOutputStream(outputStream);
    }

    private void initialize(SeekableOutputStream seekableOutputStream, String str) throws IOException {
        if (!"LZMA".equals(str)) {
            throw new IllegalArgumentException("only LZMA encoding is currently supported");
        }
        this.seekableOutputStream = seekableOutputStream;
        this.encoder = str;
        writeSevenZipSignatureHeader(0L, 0L, 0, seekableOutputStream);
        Folder folder = new Folder();
        this.contentFolder = folder;
        this.encoderOutputStream = getHeaderEncoderOutputStream(folder, str, new CloseShieldOutputStream(seekableOutputStream));
        this.sevenZipSignatureHeaderSize = seekableOutputStream.getPosition();
    }

    private byte[] longToByte(long j) {
        return new byte[]{(byte) ((j >> 56) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 0) & 255)};
    }

    private void writeAllAreDefined(boolean z, boolean[] zArr, OutputStream outputStream) throws IOException {
        outputStream.write(z ? 1 : 0);
        if (z) {
            return;
        }
        writeDefinedBitmap(zArr, outputStream);
    }

    private void writeCodersInfo(Folder[] folderArr, boolean z, OutputStream outputStream) throws IOException {
        if (folderArr == null) {
            throw new IllegalArgumentException("folders is null");
        }
        outputStream.write(7);
        outputStream.write(11);
        outputStream.write(calcUINT64(folderArr.length));
        outputStream.write(0);
        for (Folder folder : folderArr) {
            writeFolder(folder.getCoders(), true, false, true, null, null, outputStream);
        }
        outputStream.write(12);
        for (Folder folder2 : folderArr) {
            for (int i = 0; i < folder2.getNumOutStreams(); i++) {
                outputStream.write(calcUINT64(folder2.getUnpackSize()));
            }
        }
        if (z) {
            outputStream.write(10);
            ArrayList arrayList = new ArrayList();
            boolean z2 = true;
            for (Folder folder3 : folderArr) {
                if (folder3.isUnpackCRCDefined()) {
                    arrayList.add(Integer.valueOf(folder3.getUnpackCRC()));
                } else {
                    z2 = false;
                }
            }
            writeDigests(z2, (Integer[]) arrayList.toArray(new Integer[arrayList.size()]), outputStream);
        }
        outputStream.write(0);
    }

    private void writeDefinedBitmap(boolean[] zArr, OutputStream outputStream) throws IOException {
        int i;
        for (int i2 = 0; i2 < zArr.length; i2 += 8) {
            int i3 = 0;
            for (int i4 = 0; i4 < 8 && (i = i2 + i4) < zArr.length; i4++) {
                if (zArr[i]) {
                    i3 |= 128 >>> i4;
                }
            }
            outputStream.write(i3);
        }
    }

    private void writeDigests(boolean z, Integer[] numArr, OutputStream outputStream) throws IOException {
        boolean[] zArr = new boolean[numArr.length];
        for (int i = 0; i < numArr.length; i++) {
            zArr[i] = numArr[i] != null;
        }
        writeAllAreDefined(z, zArr, outputStream);
        for (Integer num : numArr) {
            EndianUtils.writeSwappedInteger(outputStream, num.intValue());
        }
    }

    private void writeFilesInfo(SevenZipEntry[] sevenZipEntryArr, OutputStream outputStream) throws IOException {
        outputStream.write(5);
        outputStream.write(calcUINT64(sevenZipEntryArr.length));
        int length = sevenZipEntryArr.length;
        boolean[] zArr = new boolean[length];
        int i = 0;
        int i2 = 0;
        while (true) {
            boolean z = true;
            if (i >= sevenZipEntryArr.length) {
                break;
            }
            SevenZipEntry sevenZipEntry = sevenZipEntryArr[i];
            if (sevenZipEntry.getSize() != 0 && !sevenZipEntry.isDirectory()) {
                z = false;
            }
            zArr[i] = z;
            if (zArr[i]) {
                i2++;
            }
            i++;
        }
        if (i2 > 0) {
            outputStream.write(14);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            writeDefinedBitmap(zArr, byteArrayOutputStream);
            outputStream.write(calcUINT64(byteArrayOutputStream.size()));
            outputStream.write(byteArrayOutputStream.toByteArray());
            boolean[] zArr2 = new boolean[i2];
            int i3 = 0;
            for (int i4 = 0; i4 < length; i4++) {
                if (zArr[i4]) {
                    zArr2[i3] = !sevenZipEntryArr[i4].isDirectory();
                    i3++;
                }
            }
            if (i3 > 0) {
                outputStream.write(15);
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                writeDefinedBitmap(zArr2, byteArrayOutputStream2);
                outputStream.write(calcUINT64(byteArrayOutputStream2.size()));
                outputStream.write(byteArrayOutputStream2.toByteArray());
            }
        }
        outputStream.write(17);
        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
        byteArrayOutputStream3.write(calcUINT64(0L));
        for (SevenZipEntry sevenZipEntry2 : sevenZipEntryArr) {
            writeWideStringLE(sevenZipEntry2.getName(), byteArrayOutputStream3);
        }
        outputStream.write(calcUINT64(byteArrayOutputStream3.size()));
        outputStream.write(byteArrayOutputStream3.toByteArray());
        outputStream.write(20);
        ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
        boolean[] zArr3 = new boolean[sevenZipEntryArr.length];
        for (int i5 = 0; i5 < sevenZipEntryArr.length; i5++) {
            zArr3[i5] = sevenZipEntryArr[i5].getLastWriteTime() != null;
        }
        writeAllAreDefined(true, zArr3, byteArrayOutputStream4);
        byteArrayOutputStream4.write(calcUINT64(0L));
        for (SevenZipEntry sevenZipEntry3 : sevenZipEntryArr) {
            if (sevenZipEntry3.getLastWriteTime() != null) {
                writeTime(sevenZipEntry3.getLastWriteTime().longValue(), byteArrayOutputStream4);
            }
        }
        outputStream.write(calcUINT64(byteArrayOutputStream4.size()));
        outputStream.write(byteArrayOutputStream4.toByteArray());
        outputStream.write(21);
        ByteArrayOutputStream byteArrayOutputStream5 = new ByteArrayOutputStream();
        boolean[] zArr4 = new boolean[sevenZipEntryArr.length];
        for (int i6 = 0; i6 < sevenZipEntryArr.length; i6++) {
            zArr4[i6] = sevenZipEntryArr[i6].getAttributes() != null;
        }
        writeAllAreDefined(true, zArr4, byteArrayOutputStream5);
        byteArrayOutputStream5.write(calcUINT64(0L));
        for (SevenZipEntry sevenZipEntry4 : sevenZipEntryArr) {
            EndianUtils.writeSwappedInteger(byteArrayOutputStream5, sevenZipEntry4.getAttributes().intValue());
        }
        outputStream.write(calcUINT64(byteArrayOutputStream5.size()));
        outputStream.write(byteArrayOutputStream5.toByteArray());
        outputStream.write(0);
    }

    private void writeFolder(CoderInfo[] coderInfoArr, boolean z, boolean z2, boolean z3, Long l, Long l2, OutputStream outputStream) throws IOException {
        outputStream.write(coderInfoArr.length);
        for (CoderInfo coderInfo : coderInfoArr) {
            int length = (coderInfo.getCoder().getMethodID().getID().length & 15) | 0;
            if (!z) {
                length |= 16;
            }
            if (z2) {
                length |= 32;
            }
            int i = length | 32;
            if (!z3) {
                i |= 64;
            }
            outputStream.write(i);
            outputStream.write(coderInfo.getCoder().getMethodID().getID());
            if (!z) {
                if (l == null) {
                    throw new IllegalArgumentException("numInStreams is null");
                }
                outputStream.write(calcUINT64(l.longValue()));
                if (l2 == null) {
                    throw new IllegalArgumentException("numOutStreams is null");
                }
                outputStream.write(calcUINT64(l2.longValue()));
            }
            if (coderInfo.getCoder().getMethodID().getID()[0] != 0) {
                byte[] properties = coderInfo.getCoder().getProperties();
                outputStream.write(calcUINT64(properties.length));
                for (byte b : properties) {
                    outputStream.write(b);
                }
            }
        }
        long jLongValue = (l2 != null ? l2.longValue() : 1L) - 1;
        if (l2 != null) {
            for (long j = 0; j < l2.longValue(); j++) {
            }
        }
        long jLongValue2 = (l != null ? l.longValue() : 1L) - jLongValue;
        if (jLongValue2 > 1) {
            for (long j2 = 0; j2 < jLongValue2; j2++) {
            }
        }
    }

    private void writeHeader(Folder[] folderArr, SubStream[] subStreamArr, SevenZipEntry[] sevenZipEntryArr, OutputStream outputStream) throws IOException {
        outputStream.write(1);
        if (folderArr != null) {
            outputStream.write(4);
            writeStreamsInfo(0L, folderArr, false, subStreamArr, outputStream);
        }
        if (sevenZipEntryArr != null) {
            writeFilesInfo(sevenZipEntryArr, outputStream);
        }
        outputStream.write(0);
    }

    private void writeHeaderInfo(long j, Folder[] folderArr, OutputStream outputStream) throws IOException {
        outputStream.write(23);
        writeStreamsInfo(j, folderArr, false, null, outputStream);
    }

    private void writePackInfo(long j, long j2, long[] jArr, OutputStream outputStream) throws IOException {
        outputStream.write(6);
        outputStream.write(calcUINT64(j));
        outputStream.write(calcUINT64(j2));
        if (jArr != null) {
            outputStream.write(9);
            for (long j3 : jArr) {
                outputStream.write(calcUINT64(j3));
            }
        }
        outputStream.write(0);
    }

    private void writeSevenZipSignatureHeader(long j, long j2, int i, SeekableOutputStream seekableOutputStream) throws IOException {
        seekableOutputStream.write(SevenZipHeader.kSignature);
        seekableOutputStream.write(SevenZipHeader.kMajorVersion);
        seekableOutputStream.write(SevenZipHeader.kMinorVersion);
        writeSevenZipSignatureHeaderReferences(j, j2, i, seekableOutputStream);
    }

    private void writeSevenZipSignatureHeaderReferences(long j, long j2, int i, SeekableOutputStream seekableOutputStream) throws IOException {
        this.sevenZipSignatureHeaderReferencePosition = seekableOutputStream.getPosition();
        CRC crc = new CRC();
        crc.Init();
        crc.UpdateUInt64(j);
        crc.UpdateUInt64(j2);
        crc.UpdateUInt32(i);
        EndianUtils.writeSwappedInteger(seekableOutputStream, crc.GetDigest());
        EndianUtils.writeSwappedLong(seekableOutputStream, j);
        EndianUtils.writeSwappedLong(seekableOutputStream, j2);
        EndianUtils.writeSwappedInteger(seekableOutputStream, i);
    }

    private void writeStreamsInfo(long j, Folder[] folderArr, boolean z, SubStream[] subStreamArr, OutputStream outputStream) throws IOException {
        if (folderArr != null) {
            long[] jArr = new long[folderArr.length];
            for (int i = 0; i < folderArr.length; i++) {
                jArr[i] = folderArr[i].getPackStreamSize();
            }
            writePackInfo(j, 1L, jArr, outputStream);
        }
        if (folderArr != null) {
            writeCodersInfo(folderArr, z, outputStream);
        }
        if (subStreamArr != null) {
            Integer[] numArr = (Integer[]) null;
            if (!z) {
                Integer[] numArr2 = new Integer[subStreamArr.length];
                for (int i2 = 0; i2 < subStreamArr.length; i2++) {
                    numArr2[i2] = new Integer(subStreamArr[i2].getUnpackCRC());
                }
                numArr = numArr2;
            }
            writeSubStreamsInfo(folderArr, numArr, outputStream);
        }
        outputStream.write(0);
    }

    private void writeSubStreamsInfo(Folder[] folderArr, Integer[] numArr, OutputStream outputStream) throws IOException {
        outputStream.write(8);
        if (folderArr != null) {
            outputStream.write(13);
            for (Folder folder : folderArr) {
                outputStream.write(calcUINT64(folder.getUnpackSizes().length));
            }
        }
        if (folderArr != null) {
            outputStream.write(9);
            for (Folder folder2 : folderArr) {
                long[] unpackSizes = folder2.getUnpackSizes();
                for (int i = 0; i < unpackSizes.length - 1; i++) {
                    outputStream.write(calcUINT64(unpackSizes[i]));
                }
            }
        }
        if (numArr != null) {
            outputStream.write(10);
            writeDigests(true, numArr, outputStream);
        }
        outputStream.write(0);
    }

    private void writeTime(long j, OutputStream outputStream) throws IOException {
        long j2 = (j * 10000) + 116444736000000000L;
        EndianUtils.writeSwappedInteger(outputStream, (int) (j2 & 4294967295L));
        EndianUtils.writeSwappedInteger(outputStream, (int) (j2 >>> 32));
    }

    private void writeWideStringLE(String str, OutputStream outputStream) throws IOException {
        try {
            outputStream.write(str.getBytes("UTF-16LE"));
            outputStream.write("\u0000".getBytes("UTF-16LE"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        OutputStream outputStream = this.encoderOutputStream;
        if (outputStream != null) {
            outputStream.close();
        }
        super.flush();
        super.close();
    }

    public void finish() throws IOException {
        putNextEntry(null);
        this.encoderOutputStream.close();
        this.encoderOutputStream = null;
        this.contentFolder.setUnpackCRC(this.crcOutputStream.getDigest());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (SubStream subStream : this.subStreams) {
            if (subStream.getUnPackSize() > 0) {
                arrayList.add(subStream);
            } else if (subStream.getSevenZipEntry().isDirectory()) {
                arrayList3.add(subStream);
            } else {
                arrayList2.add(subStream);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        this.subStreams = arrayList4;
        arrayList4.addAll(arrayList);
        this.subStreams.addAll(arrayList2);
        this.subStreams.addAll(arrayList3);
        long[] jArr = new long[this.subStreams.size()];
        this.contentFolder.setUnpackSizes(jArr);
        long position = this.seekableOutputStream.getPosition() - this.sevenZipSignatureHeaderSize;
        this.contentFolder.setPackStreamSizes(new long[]{position});
        SubStream[] subStreamArr = new SubStream[this.subStreams.size()];
        SevenZipEntry[] sevenZipEntryArr = new SevenZipEntry[this.subStreams.size()];
        for (int i = 0; i < this.subStreams.size(); i++) {
            SubStream subStream2 = this.subStreams.get(i);
            jArr[i] = subStream2.getUnPackSize();
            subStreamArr[i] = subStream2;
            sevenZipEntryArr[i] = subStream2.getSevenZipEntry();
        }
        Folder folder = new Folder();
        OutputStream packedHeaderEncoderOutputStream = getPackedHeaderEncoderOutputStream(folder, this.encoder, new CloseShieldOutputStream(this.seekableOutputStream));
        CRCOutputStream cRCOutputStream = new CRCOutputStream(packedHeaderEncoderOutputStream);
        writeHeader(new Folder[]{this.contentFolder}, subStreamArr, sevenZipEntryArr, cRCOutputStream);
        packedHeaderEncoderOutputStream.close();
        long position2 = (this.seekableOutputStream.getPosition() - position) - this.sevenZipSignatureHeaderSize;
        long position3 = this.seekableOutputStream.getPosition() - this.sevenZipSignatureHeaderSize;
        folder.setUnpackSizes(new long[]{cRCOutputStream.getCount()});
        folder.setPackStreamSizes(new long[]{position2});
        folder.setUnpackCRC(cRCOutputStream.getDigest());
        CRCOutputStream cRCOutputStream2 = new CRCOutputStream(this.seekableOutputStream);
        writeHeaderInfo(position, new Folder[]{folder}, cRCOutputStream2);
        this.seekableOutputStream.setPosition(this.sevenZipSignatureHeaderReferencePosition);
        writeSevenZipSignatureHeaderReferences(position3, cRCOutputStream2.getCount(), cRCOutputStream2.getDigest(), this.seekableOutputStream);
        SeekableOutputStream seekableOutputStream = this.seekableOutputStream;
        if (seekableOutputStream instanceof SeekableByteArrayOutputStream) {
            ((SeekableByteArrayOutputStream) seekableOutputStream).writeTo(this.outputStream);
            this.outputStream.flush();
        }
    }

    public void putNextEntry(SevenZipEntry sevenZipEntry) throws IOException {
        if (this.subStreams.size() > 0) {
            SubStream subStream = this.subStreams.get(r0.size() - 1);
            subStream.setUnpackCRC(this.crcOutputStream.getDigest());
            subStream.setUnPackSize(this.crcOutputStream.getCount());
        }
        if (sevenZipEntry != null) {
            if (this.sevenZipEntryCRCStream == null) {
                this.sevenZipEntryCRCStream = new CRCOutputStream(this.encoderOutputStream);
            }
            if (this.crcOutputStream == null) {
                this.crcOutputStream = new CRCOutputStream(this.sevenZipEntryCRCStream);
            }
            this.crcOutputStream.resetCount();
            SubStream subStream2 = new SubStream();
            subStream2.setSevenZipEntry(sevenZipEntry);
            this.subStreams.add(subStream2);
        }
    }

    @Override
    public void write(int i) throws IOException {
        this.crcOutputStream.write(i);
    }

    @Override
    public void write(byte[] bArr) throws IOException {
        this.crcOutputStream.write(bArr);
    }

    @Override
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.crcOutputStream.write(bArr, i, i2);
    }
}
