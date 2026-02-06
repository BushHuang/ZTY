package com.fasterxml.jackson.core.json.async;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public abstract class NonBlockingJsonParserBase extends ParserBase {
    protected static final int MAJOR_ARRAY_ELEMENT_FIRST = 5;
    protected static final int MAJOR_ARRAY_ELEMENT_NEXT = 6;
    protected static final int MAJOR_CLOSED = 7;
    protected static final int MAJOR_INITIAL = 0;
    protected static final int MAJOR_OBJECT_FIELD_FIRST = 2;
    protected static final int MAJOR_OBJECT_FIELD_NEXT = 3;
    protected static final int MAJOR_OBJECT_VALUE = 4;
    protected static final int MAJOR_ROOT = 1;
    protected static final int MINOR_COMMENT_C = 53;
    protected static final int MINOR_COMMENT_CLOSING_ASTERISK = 52;
    protected static final int MINOR_COMMENT_CPP = 54;
    protected static final int MINOR_COMMENT_LEADING_SLASH = 51;
    protected static final int MINOR_COMMENT_YAML = 55;
    protected static final int MINOR_FIELD_APOS_NAME = 9;
    protected static final int MINOR_FIELD_LEADING_COMMA = 5;
    protected static final int MINOR_FIELD_LEADING_WS = 4;
    protected static final int MINOR_FIELD_NAME = 7;
    protected static final int MINOR_FIELD_NAME_ESCAPE = 8;
    protected static final int MINOR_FIELD_UNQUOTED_NAME = 10;
    protected static final int MINOR_NUMBER_EXPONENT_DIGITS = 32;
    protected static final int MINOR_NUMBER_EXPONENT_MARKER = 31;
    protected static final int MINOR_NUMBER_FRACTION_DIGITS = 30;
    protected static final int MINOR_NUMBER_INTEGER_DIGITS = 26;
    protected static final int MINOR_NUMBER_MINUS = 23;
    protected static final int MINOR_NUMBER_MINUSZERO = 25;
    protected static final int MINOR_NUMBER_ZERO = 24;
    protected static final int MINOR_ROOT_BOM = 1;
    protected static final int MINOR_ROOT_GOT_SEPARATOR = 3;
    protected static final int MINOR_ROOT_NEED_SEPARATOR = 2;
    protected static final int MINOR_VALUE_APOS_STRING = 45;
    protected static final int MINOR_VALUE_EXPECTING_COLON = 14;
    protected static final int MINOR_VALUE_EXPECTING_COMMA = 13;
    protected static final int MINOR_VALUE_LEADING_WS = 12;
    protected static final int MINOR_VALUE_STRING = 40;
    protected static final int MINOR_VALUE_STRING_ESCAPE = 41;
    protected static final int MINOR_VALUE_STRING_UTF8_2 = 42;
    protected static final int MINOR_VALUE_STRING_UTF8_3 = 43;
    protected static final int MINOR_VALUE_STRING_UTF8_4 = 44;
    protected static final int MINOR_VALUE_TOKEN_ERROR = 50;
    protected static final int MINOR_VALUE_TOKEN_FALSE = 18;
    protected static final int MINOR_VALUE_TOKEN_NON_STD = 19;
    protected static final int MINOR_VALUE_TOKEN_NULL = 16;
    protected static final int MINOR_VALUE_TOKEN_TRUE = 17;
    protected static final int MINOR_VALUE_WS_AFTER_COMMA = 15;
    protected static final int NON_STD_TOKEN_INFINITY = 1;
    protected static final int NON_STD_TOKEN_MINUS_INFINITY = 3;
    protected static final int NON_STD_TOKEN_NAN = 0;
    protected static final int NON_STD_TOKEN_PLUS_INFINITY = 2;
    protected int _currBufferStart;
    protected int _currInputRowAlt;
    protected boolean _endOfInput;
    protected int _majorState;
    protected int _majorStateAfterValue;
    protected int _minorState;
    protected int _minorStateAfterSplit;
    protected int _nonStdTokenType;
    protected int _pending32;
    protected int _pendingBytes;
    protected int _quad1;
    protected int[] _quadBuffer;
    protected int _quadLength;
    protected int _quoted32;
    protected int _quotedDigits;
    protected final ByteQuadsCanonicalizer _symbols;
    protected static final String[] NON_STD_TOKENS = {"NaN", "Infinity", "+Infinity", "-Infinity"};
    protected static final double[] NON_STD_TOKEN_VALUES = {Double.NaN, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY};

    public NonBlockingJsonParserBase(IOContext iOContext, int i, ByteQuadsCanonicalizer byteQuadsCanonicalizer) {
        super(iOContext, i);
        this._quadBuffer = new int[8];
        this._endOfInput = false;
        this._currBufferStart = 0;
        this._currInputRowAlt = 1;
        this._symbols = byteQuadsCanonicalizer;
        this._currToken = null;
        this._majorState = 0;
        this._majorStateAfterValue = 1;
    }

    protected static final int _padLastQuad(int i, int i2) {
        return i2 == 4 ? i : i | ((-1) << (i2 << 3));
    }

    protected final String _addName(int[] iArr, int i, int i2) throws JsonParseException {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = ((i << 2) - 4) + i2;
        if (i2 < 4) {
            int i8 = i - 1;
            i3 = iArr[i8];
            iArr[i8] = i3 << ((4 - i2) << 3);
        } else {
            i3 = 0;
        }
        char[] cArrEmptyAndGetCurrentSegment = this._textBuffer.emptyAndGetCurrentSegment();
        int i9 = 0;
        int i10 = 0;
        while (i9 < i7) {
            int i11 = (iArr[i9 >> 2] >> ((3 - (i9 & 3)) << 3)) & 255;
            i9++;
            if (i11 > 127) {
                if ((i11 & 224) == 192) {
                    i4 = i11 & 31;
                } else {
                    if ((i11 & 240) == 224) {
                        i4 = i11 & 15;
                        i5 = 2;
                    } else if ((i11 & 248) == 240) {
                        i4 = i11 & 7;
                        i5 = 3;
                    } else {
                        _reportInvalidInitial(i11);
                        i4 = 1;
                    }
                    if (i9 + i5 > i7) {
                        _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
                    }
                    i6 = iArr[i9 >> 2] >> ((3 - (i9 & 3)) << 3);
                    i9++;
                    if ((i6 & 192) != 128) {
                        _reportInvalidOther(i6);
                    }
                    int i12 = (i6 & 63) | (i4 << 6);
                    if (i5 <= 1) {
                        int i13 = iArr[i9 >> 2] >> ((3 - (i9 & 3)) << 3);
                        i9++;
                        if ((i13 & 192) != 128) {
                            _reportInvalidOther(i13);
                        }
                        int i14 = (i13 & 63) | (i12 << 6);
                        if (i5 > 2) {
                            int i15 = iArr[i9 >> 2] >> ((3 - (i9 & 3)) << 3);
                            i9++;
                            if ((i15 & 192) != 128) {
                                _reportInvalidOther(i15 & 255);
                            }
                            i14 = (i14 << 6) | (i15 & 63);
                        }
                        i11 = i14;
                    } else {
                        i11 = i12;
                    }
                    if (i5 > 2) {
                        int i16 = i11 - 65536;
                        if (i10 >= cArrEmptyAndGetCurrentSegment.length) {
                            cArrEmptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
                        }
                        cArrEmptyAndGetCurrentSegment[i10] = (char) ((i16 >> 10) + 55296);
                        i11 = (i16 & 1023) | 56320;
                        i10++;
                    }
                }
                i5 = 1;
                if (i9 + i5 > i7) {
                }
                i6 = iArr[i9 >> 2] >> ((3 - (i9 & 3)) << 3);
                i9++;
                if ((i6 & 192) != 128) {
                }
                int i122 = (i6 & 63) | (i4 << 6);
                if (i5 <= 1) {
                }
                if (i5 > 2) {
                }
            }
            if (i10 >= cArrEmptyAndGetCurrentSegment.length) {
                cArrEmptyAndGetCurrentSegment = this._textBuffer.expandCurrentSegment();
            }
            cArrEmptyAndGetCurrentSegment[i10] = (char) i11;
            i10++;
        }
        String str = new String(cArrEmptyAndGetCurrentSegment, 0, i10);
        if (i2 < 4) {
            iArr[i - 1] = i3;
        }
        return this._symbols.addName(str, iArr, i);
    }

    protected final JsonToken _closeArrayScope() throws IOException {
        if (!this._parsingContext.inArray()) {
            _reportMismatchedEndMarker(93, '}');
        }
        JsonReadContext parent = this._parsingContext.getParent();
        this._parsingContext = parent;
        int i = parent.inObject() ? 3 : parent.inArray() ? 6 : 1;
        this._majorState = i;
        this._majorStateAfterValue = i;
        JsonToken jsonToken = JsonToken.END_ARRAY;
        this._currToken = jsonToken;
        return jsonToken;
    }

    @Override
    protected void _closeInput() throws IOException {
        this._currBufferStart = 0;
        this._inputEnd = 0;
    }

    protected final JsonToken _closeObjectScope() throws IOException {
        if (!this._parsingContext.inObject()) {
            _reportMismatchedEndMarker(125, ']');
        }
        JsonReadContext parent = this._parsingContext.getParent();
        this._parsingContext = parent;
        int i = parent.inObject() ? 3 : parent.inArray() ? 6 : 1;
        this._majorState = i;
        this._majorStateAfterValue = i;
        JsonToken jsonToken = JsonToken.END_OBJECT;
        this._currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _eofAsNextToken() throws IOException {
        this._majorState = 7;
        if (!this._parsingContext.inRoot()) {
            _handleEOF();
        }
        close();
        this._currToken = null;
        return null;
    }

    protected final JsonToken _fieldComplete(String str) throws IOException {
        this._majorState = 4;
        this._parsingContext.setCurrentName(str);
        JsonToken jsonToken = JsonToken.FIELD_NAME;
        this._currToken = jsonToken;
        return jsonToken;
    }

    protected final String _findName(int i, int i2) throws JsonParseException {
        int i_padLastQuad = _padLastQuad(i, i2);
        String strFindName = this._symbols.findName(i_padLastQuad);
        if (strFindName != null) {
            return strFindName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i_padLastQuad;
        return _addName(iArr, 1, i2);
    }

    protected final String _findName(int i, int i2, int i3) throws JsonParseException {
        int i_padLastQuad = _padLastQuad(i2, i3);
        String strFindName = this._symbols.findName(i, i_padLastQuad);
        if (strFindName != null) {
            return strFindName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = i_padLastQuad;
        return _addName(iArr, 2, i3);
    }

    protected final String _findName(int i, int i2, int i3, int i4) throws JsonParseException {
        int i_padLastQuad = _padLastQuad(i3, i4);
        String strFindName = this._symbols.findName(i, i2, i_padLastQuad);
        if (strFindName != null) {
            return strFindName;
        }
        int[] iArr = this._quadBuffer;
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = _padLastQuad(i_padLastQuad, i4);
        return _addName(iArr, 3, i4);
    }

    protected final String _getText2(JsonToken jsonToken) {
        int iId;
        if (jsonToken == null || (iId = jsonToken.id()) == -1) {
            return null;
        }
        return iId != 5 ? (iId == 6 || iId == 7 || iId == 8) ? this._textBuffer.contentsAsString() : jsonToken.asString() : this._parsingContext.getCurrentName();
    }

    protected final String _nonStdToken(int i) {
        return NON_STD_TOKENS[i];
    }

    @Override
    protected void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        this._symbols.release();
    }

    protected void _reportInvalidChar(int i) throws JsonParseException {
        if (i < 32) {
            _throwInvalidSpace(i);
        }
        _reportInvalidInitial(i);
    }

    protected void _reportInvalidInitial(int i) throws JsonParseException {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i) throws JsonParseException {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(i));
    }

    protected void _reportInvalidOther(int i, int i2) throws JsonParseException {
        this._inputPtr = i2;
        _reportInvalidOther(i);
    }

    protected final JsonToken _startArrayScope() throws IOException {
        this._parsingContext = this._parsingContext.createChildArrayContext(-1, -1);
        this._majorState = 5;
        this._majorStateAfterValue = 6;
        JsonToken jsonToken = JsonToken.START_ARRAY;
        this._currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _startObjectScope() throws IOException {
        this._parsingContext = this._parsingContext.createChildObjectContext(-1, -1);
        this._majorState = 2;
        this._majorStateAfterValue = 3;
        JsonToken jsonToken = JsonToken.START_OBJECT;
        this._currToken = jsonToken;
        return jsonToken;
    }

    protected final void _updateTokenLocation() {
        this._tokenInputRow = Math.max(this._currInputRow, this._currInputRowAlt);
        this._tokenInputCol = this._inputPtr - this._currInputRowStart;
        this._tokenInputTotal = this._currInputProcessed + (r0 - this._currBufferStart);
    }

    protected final JsonToken _valueComplete(JsonToken jsonToken) throws IOException {
        this._majorState = this._majorStateAfterValue;
        this._currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _valueCompleteInt(int i, String str) throws IOException {
        this._textBuffer.resetWithString(str);
        this._intLength = str.length();
        this._numTypesValid = 1;
        this._numberInt = i;
        this._majorState = this._majorStateAfterValue;
        JsonToken jsonToken = JsonToken.VALUE_NUMBER_INT;
        this._currToken = jsonToken;
        return jsonToken;
    }

    protected final JsonToken _valueNonStdNumberComplete(int i) throws IOException {
        String str = NON_STD_TOKENS[i];
        this._textBuffer.resetWithString(str);
        if (!isEnabled(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
            _reportError("Non-standard token '%s': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow", str);
        }
        this._intLength = 0;
        this._numTypesValid = 8;
        this._numberDouble = NON_STD_TOKEN_VALUES[i];
        this._majorState = this._majorStateAfterValue;
        JsonToken jsonToken = JsonToken.VALUE_NUMBER_FLOAT;
        this._currToken = jsonToken;
        return jsonToken;
    }

    @Override
    public boolean canParseAsync() {
        return true;
    }

    @Override
    public byte[] getBinaryValue(Base64Variant base64Variant) throws IOException {
        if (this._currToken != JsonToken.VALUE_STRING) {
            _reportError("Current token (%s) not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary", this._currToken);
        }
        if (this._binaryValue == null) {
            ByteArrayBuilder byteArrayBuilder_getByteArrayBuilder = _getByteArrayBuilder();
            _decodeBase64(getText(), byteArrayBuilder_getByteArrayBuilder, base64Variant);
            this._binaryValue = byteArrayBuilder_getByteArrayBuilder.toByteArray();
        }
        return this._binaryValue;
    }

    @Override
    public ObjectCodec getCodec() {
        return null;
    }

    @Override
    public JsonLocation getCurrentLocation() {
        return new JsonLocation(_getSourceReference(), this._currInputProcessed + (this._inputPtr - this._currBufferStart), -1L, Math.max(this._currInputRow, this._currInputRowAlt), (this._inputPtr - this._currInputRowStart) + 1);
    }

    @Override
    public Object getEmbeddedObject() throws IOException {
        if (this._currToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
            return this._binaryValue;
        }
        return null;
    }

    @Override
    public Object getInputSource() {
        return null;
    }

    @Override
    public int getText(Writer writer) throws IOException {
        JsonToken jsonToken = this._currToken;
        if (jsonToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.contentsToWriter(writer);
        }
        if (jsonToken == JsonToken.FIELD_NAME) {
            String currentName = this._parsingContext.getCurrentName();
            writer.write(currentName);
            return currentName.length();
        }
        if (jsonToken == null) {
            return 0;
        }
        if (jsonToken.isNumeric()) {
            return this._textBuffer.contentsToWriter(writer);
        }
        if (jsonToken == JsonToken.NOT_AVAILABLE) {
            _reportError("Current token not available: can not call this method");
        }
        char[] cArrAsCharArray = jsonToken.asCharArray();
        writer.write(cArrAsCharArray);
        return cArrAsCharArray.length;
    }

    @Override
    public String getText() throws IOException {
        return this._currToken == JsonToken.VALUE_STRING ? this._textBuffer.contentsAsString() : _getText2(this._currToken);
    }

    @Override
    public char[] getTextCharacters() throws IOException {
        if (this._currToken == null) {
            return null;
        }
        int iId = this._currToken.id();
        if (iId != 5) {
            return (iId == 6 || iId == 7 || iId == 8) ? this._textBuffer.getTextBuffer() : this._currToken.asCharArray();
        }
        if (!this._nameCopied) {
            String currentName = this._parsingContext.getCurrentName();
            int length = currentName.length();
            if (this._nameCopyBuffer == null) {
                this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(length);
            } else if (this._nameCopyBuffer.length < length) {
                this._nameCopyBuffer = new char[length];
            }
            currentName.getChars(0, length, this._nameCopyBuffer, 0);
            this._nameCopied = true;
        }
        return this._nameCopyBuffer;
    }

    @Override
    public int getTextLength() throws IOException {
        if (this._currToken == null) {
            return 0;
        }
        int iId = this._currToken.id();
        return iId != 5 ? (iId == 6 || iId == 7 || iId == 8) ? this._textBuffer.size() : this._currToken.asCharArray().length : this._parsingContext.getCurrentName().length();
    }

    @Override
    public int getTextOffset() throws IOException {
        if (this._currToken == null) {
            return 0;
        }
        int iId = this._currToken.id();
        if (iId == 6 || iId == 7 || iId == 8) {
            return this._textBuffer.getTextOffset();
        }
        return 0;
    }

    @Override
    public JsonLocation getTokenLocation() {
        return new JsonLocation(_getSourceReference(), this._tokenInputTotal, -1L, this._tokenInputRow, this._tokenInputCol);
    }

    @Override
    public String getValueAsString() throws IOException {
        return this._currToken == JsonToken.VALUE_STRING ? this._textBuffer.contentsAsString() : this._currToken == JsonToken.FIELD_NAME ? getCurrentName() : super.getValueAsString(null);
    }

    @Override
    public String getValueAsString(String str) throws IOException {
        return this._currToken == JsonToken.VALUE_STRING ? this._textBuffer.contentsAsString() : this._currToken == JsonToken.FIELD_NAME ? getCurrentName() : super.getValueAsString(str);
    }

    @Override
    public boolean hasTextCharacters() {
        if (this._currToken == JsonToken.VALUE_STRING) {
            return this._textBuffer.hasTextAsCharacters();
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nameCopied;
        }
        return false;
    }

    @Override
    public int readBinaryValue(Base64Variant base64Variant, OutputStream outputStream) throws IOException {
        byte[] binaryValue = getBinaryValue(base64Variant);
        outputStream.write(binaryValue);
        return binaryValue.length;
    }

    @Override
    public abstract int releaseBuffered(OutputStream outputStream) throws IOException;

    @Override
    public void setCodec(ObjectCodec objectCodec) {
        throw new UnsupportedOperationException("Can not use ObjectMapper with non-blocking parser");
    }

    protected ByteQuadsCanonicalizer symbolTableForTests() {
        return this._symbols;
    }
}
