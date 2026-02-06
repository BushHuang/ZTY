package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.aztec.decoder.Decoder;
import com.google.zxing.aztec.detector.Detector;
import com.google.zxing.common.DecoderResult;
import java.util.List;
import java.util.Map;

public final class AztecReader implements Reader {
    @Override
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    @Override
    public Result decode(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        ResultPoint[] points;
        ResultPoint[] points2;
        FormatException formatException;
        ResultPoint[] resultPointArr;
        List<byte[]> byteSegments;
        String eCLevel;
        ResultPointCallback resultPointCallback;
        AztecDetectorResult aztecDetectorResultDetect;
        Detector detector = new Detector(binaryBitmap.getBlackMatrix());
        DecoderResult decoderResultDecode = null;
        try {
            aztecDetectorResultDetect = detector.detect(false);
            points = aztecDetectorResultDetect.getPoints();
        } catch (FormatException e) {
            e = e;
            points = null;
        } catch (NotFoundException e2) {
            e = e2;
            points = null;
        }
        try {
            points2 = points;
            formatException = null;
            decoderResultDecode = new Decoder().decode(aztecDetectorResultDetect);
            e = null;
        } catch (FormatException e3) {
            e = e3;
            points2 = points;
            formatException = e;
            e = null;
            if (decoderResultDecode == null) {
            }
            resultPointArr = points2;
            if (map != null) {
                while (i < r0) {
                }
            }
            Result result = new Result(decoderResultDecode.getText(), decoderResultDecode.getRawBytes(), decoderResultDecode.getNumBits(), resultPointArr, BarcodeFormat.AZTEC, System.currentTimeMillis());
            byteSegments = decoderResultDecode.getByteSegments();
            if (byteSegments != null) {
            }
            eCLevel = decoderResultDecode.getECLevel();
            if (eCLevel != null) {
            }
            return result;
        } catch (NotFoundException e4) {
            e = e4;
            points2 = points;
            formatException = null;
            if (decoderResultDecode == null) {
            }
            resultPointArr = points2;
            if (map != null) {
            }
            Result result2 = new Result(decoderResultDecode.getText(), decoderResultDecode.getRawBytes(), decoderResultDecode.getNumBits(), resultPointArr, BarcodeFormat.AZTEC, System.currentTimeMillis());
            byteSegments = decoderResultDecode.getByteSegments();
            if (byteSegments != null) {
            }
            eCLevel = decoderResultDecode.getECLevel();
            if (eCLevel != null) {
            }
            return result2;
        }
        if (decoderResultDecode == null) {
            try {
                AztecDetectorResult aztecDetectorResultDetect2 = detector.detect(true);
                points2 = aztecDetectorResultDetect2.getPoints();
                decoderResultDecode = new Decoder().decode(aztecDetectorResultDetect2);
            } catch (FormatException | NotFoundException e5) {
                if (e != null) {
                    throw e;
                }
                if (formatException != null) {
                    throw formatException;
                }
                throw e5;
            }
        }
        resultPointArr = points2;
        if (map != null && (resultPointCallback = (ResultPointCallback) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) != null) {
            for (ResultPoint resultPoint : resultPointArr) {
                resultPointCallback.foundPossibleResultPoint(resultPoint);
            }
        }
        Result result22 = new Result(decoderResultDecode.getText(), decoderResultDecode.getRawBytes(), decoderResultDecode.getNumBits(), resultPointArr, BarcodeFormat.AZTEC, System.currentTimeMillis());
        byteSegments = decoderResultDecode.getByteSegments();
        if (byteSegments != null) {
            result22.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments);
        }
        eCLevel = decoderResultDecode.getECLevel();
        if (eCLevel != null) {
            result22.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
        }
        return result22;
    }

    @Override
    public void reset() {
    }
}
