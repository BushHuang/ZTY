package com.tencent.tinker.android.dx.instruction;

import com.tencent.tinker.android.dex.DexException;
import com.tencent.tinker.android.dx.util.Hex;
import java.io.EOFException;

public final class InstructionReader {
    private final ShortArrayCodeInput codeIn;

    public InstructionReader(ShortArrayCodeInput shortArrayCodeInput) {
        this.codeIn = shortArrayCodeInput;
    }

    public void accept(InstructionVisitor instructionVisitor) throws EOFException {
        int iNibble3;
        this.codeIn.reset();
        while (this.codeIn.hasMore()) {
            int iCursor = this.codeIn.cursor();
            int i = this.codeIn.read();
            int iExtractOpcodeFromUnit = Opcodes.extractOpcodeFromUnit(i);
            int i2 = 0;
            if (iExtractOpcodeFromUnit == 256) {
                int iBaseAddressForCursor = this.codeIn.baseAddressForCursor();
                int i3 = this.codeIn.read();
                int i4 = this.codeIn.readInt();
                int[] iArr = new int[i3];
                while (i2 < i3) {
                    iArr[i2] = this.codeIn.readInt() + iBaseAddressForCursor;
                    i2++;
                }
                instructionVisitor.visitPackedSwitchPayloadInsn(iCursor, i, i4, iArr);
            } else if (iExtractOpcodeFromUnit != 512) {
                boolean z = true;
                if (iExtractOpcodeFromUnit != 768) {
                    switch (iExtractOpcodeFromUnit) {
                        case -1:
                            instructionVisitor.visitZeroRegisterInsn(iCursor, i, 0, 1, 0, 0L);
                            break;
                        case 0:
                        case 14:
                            instructionVisitor.visitZeroRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, InstructionCodec.byte1(i));
                            break;
                        case 1:
                        case 4:
                        case 7:
                        case 33:
                            instructionVisitor.visitTwoRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, 0L, InstructionCodec.nibble2(i), InstructionCodec.nibble3(i));
                            break;
                        case 2:
                        case 5:
                        case 8:
                            instructionVisitor.visitTwoRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, 0L, InstructionCodec.byte1(i), this.codeIn.read());
                            break;
                        case 3:
                        case 6:
                        case 9:
                            instructionVisitor.visitTwoRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, InstructionCodec.byte1(i), this.codeIn.read(), this.codeIn.read());
                            break;
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 15:
                        case 16:
                        case 17:
                        case 29:
                        case 30:
                        case 39:
                            instructionVisitor.visitOneRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, 0L, InstructionCodec.byte1(i));
                            break;
                        case 18:
                            instructionVisitor.visitOneRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, (InstructionCodec.nibble3(i) << 28) >> 28, InstructionCodec.nibble2(i));
                            break;
                        case 19:
                        case 22:
                            instructionVisitor.visitOneRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, (short) this.codeIn.read(), InstructionCodec.byte1(i));
                            break;
                        case 20:
                        case 23:
                            instructionVisitor.visitOneRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, this.codeIn.readInt(), InstructionCodec.byte1(i));
                            break;
                        case 21:
                        case 25:
                            int iByte0 = InstructionCodec.byte0(i);
                            instructionVisitor.visitOneRegisterInsn(iCursor, iByte0, 0, 1, 0, ((short) this.codeIn.read()) << (iByte0 == 21 ? (char) 16 : '0'), InstructionCodec.byte1(i));
                            break;
                        case 24:
                            instructionVisitor.visitOneRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, this.codeIn.readLong(), InstructionCodec.byte1(i));
                            break;
                        case 26:
                        case 28:
                        case 31:
                        case 34:
                            int iByte02 = InstructionCodec.byte0(i);
                            instructionVisitor.visitOneRegisterInsn(iCursor, iByte02, this.codeIn.read(), InstructionCodec.getInstructionIndexType(iByte02), 0, 0L, InstructionCodec.byte1(i));
                            break;
                        case 27:
                            int iByte03 = InstructionCodec.byte0(i);
                            instructionVisitor.visitOneRegisterInsn(iCursor, iByte03, this.codeIn.readInt(), InstructionCodec.getInstructionIndexType(iByte03), 0, 0L, InstructionCodec.byte1(i));
                            break;
                        case 32:
                        case 35:
                            int iByte04 = InstructionCodec.byte0(i);
                            instructionVisitor.visitTwoRegisterInsn(iCursor, iByte04, this.codeIn.read(), InstructionCodec.getInstructionIndexType(iByte04), 0, 0L, InstructionCodec.nibble2(i), InstructionCodec.nibble3(i));
                            break;
                        case 36:
                            int iByte05 = InstructionCodec.byte0(i);
                            int iNibble2 = InstructionCodec.nibble2(i);
                            iNibble3 = InstructionCodec.nibble3(i);
                            int i5 = this.codeIn.read();
                            int i6 = this.codeIn.read();
                            int iNibble0 = InstructionCodec.nibble0(i6);
                            int iNibble1 = InstructionCodec.nibble1(i6);
                            int iNibble22 = InstructionCodec.nibble2(i6);
                            int iNibble32 = InstructionCodec.nibble3(i6);
                            int instructionIndexType = InstructionCodec.getInstructionIndexType(iByte05);
                            if (iNibble3 == 0) {
                                if (iNibble3 != 1) {
                                    if (iNibble3 != 2) {
                                        if (iNibble3 != 3) {
                                            if (iNibble3 != 4) {
                                                if (iNibble3 != 5) {
                                                    throw new DexException("bogus registerCount: " + Hex.uNibble(iNibble3));
                                                }
                                                instructionVisitor.visitFiveRegisterInsn(iCursor, iByte05, i5, instructionIndexType, 0, 0L, iNibble0, iNibble1, iNibble22, iNibble32, iNibble2);
                                                break;
                                            } else {
                                                instructionVisitor.visitFourRegisterInsn(iCursor, iByte05, i5, instructionIndexType, 0, 0L, iNibble0, iNibble1, iNibble22, iNibble32);
                                                break;
                                            }
                                        } else {
                                            instructionVisitor.visitThreeRegisterInsn(iCursor, iByte05, i5, instructionIndexType, 0, 0L, iNibble0, iNibble1, iNibble22);
                                            break;
                                        }
                                    } else {
                                        instructionVisitor.visitTwoRegisterInsn(iCursor, iByte05, i5, instructionIndexType, 0, 0L, iNibble0, iNibble1);
                                        break;
                                    }
                                } else {
                                    instructionVisitor.visitOneRegisterInsn(iCursor, iByte05, i5, instructionIndexType, 0, 0L, iNibble0);
                                    break;
                                }
                            } else {
                                instructionVisitor.visitZeroRegisterInsn(iCursor, iByte05, i5, instructionIndexType, 0, 0L);
                                break;
                            }
                        case 37:
                            int iByte06 = InstructionCodec.byte0(i);
                            instructionVisitor.visitRegisterRangeInsn(iCursor, iByte06, this.codeIn.read(), InstructionCodec.getInstructionIndexType(iByte06), 0, 0L, this.codeIn.read(), InstructionCodec.byte1(i));
                            break;
                        case 38:
                        case 43:
                        case 44:
                            int iByte07 = InstructionCodec.byte0(i);
                            int iByte1 = InstructionCodec.byte1(i);
                            int i7 = iCursor + this.codeIn.readInt();
                            if (iByte07 == 43 || iByte07 == 44) {
                                this.codeIn.setBaseAddress(i7 + 1, iCursor);
                            }
                            instructionVisitor.visitOneRegisterInsn(iCursor, iByte07, 0, 1, i7, 0L, iByte1);
                            break;
                        case 40:
                            instructionVisitor.visitZeroRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, iCursor + ((byte) InstructionCodec.byte1(i)), 0L);
                            break;
                        case 41:
                            instructionVisitor.visitZeroRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, iCursor + ((short) this.codeIn.read()), InstructionCodec.byte1(i));
                            break;
                        case 42:
                            instructionVisitor.visitZeroRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, iCursor + this.codeIn.readInt(), InstructionCodec.byte1(i));
                            break;
                        case 45:
                        case 46:
                        case 47:
                        case 48:
                        case 49:
                            int iByte08 = InstructionCodec.byte0(i);
                            int iByte12 = InstructionCodec.byte1(i);
                            int i8 = this.codeIn.read();
                            instructionVisitor.visitThreeRegisterInsn(iCursor, iByte08, 0, 1, 0, 0L, iByte12, InstructionCodec.byte0(i8), InstructionCodec.byte1(i8));
                            break;
                        case 50:
                        case 51:
                        case 52:
                        case 53:
                        case 54:
                        case 55:
                            instructionVisitor.visitTwoRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, iCursor + ((short) this.codeIn.read()), 0L, InstructionCodec.nibble2(i), InstructionCodec.nibble3(i));
                            break;
                        case 56:
                        case 57:
                        case 58:
                        case 59:
                        case 60:
                        case 61:
                            instructionVisitor.visitOneRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, iCursor + ((short) this.codeIn.read()), 0L, InstructionCodec.byte1(i));
                            break;
                        default:
                            switch (iExtractOpcodeFromUnit) {
                                case 68:
                                case 69:
                                case 70:
                                case 71:
                                case 72:
                                case 73:
                                case 74:
                                case 75:
                                case 76:
                                case 77:
                                case 78:
                                case 79:
                                case 80:
                                case 81:
                                    int iByte082 = InstructionCodec.byte0(i);
                                    int iByte122 = InstructionCodec.byte1(i);
                                    int i82 = this.codeIn.read();
                                    instructionVisitor.visitThreeRegisterInsn(iCursor, iByte082, 0, 1, 0, 0L, iByte122, InstructionCodec.byte0(i82), InstructionCodec.byte1(i82));
                                    break;
                                case 82:
                                case 83:
                                case 84:
                                case 85:
                                case 86:
                                case 87:
                                case 88:
                                case 89:
                                case 90:
                                case 91:
                                case 92:
                                case 93:
                                case 94:
                                case 95:
                                    int iByte042 = InstructionCodec.byte0(i);
                                    instructionVisitor.visitTwoRegisterInsn(iCursor, iByte042, this.codeIn.read(), InstructionCodec.getInstructionIndexType(iByte042), 0, 0L, InstructionCodec.nibble2(i), InstructionCodec.nibble3(i));
                                    break;
                                case 96:
                                case 97:
                                case 98:
                                case 99:
                                case 100:
                                case 101:
                                case 102:
                                case 103:
                                case 104:
                                case 105:
                                case 106:
                                case 107:
                                case 108:
                                case 109:
                                    int iByte022 = InstructionCodec.byte0(i);
                                    instructionVisitor.visitOneRegisterInsn(iCursor, iByte022, this.codeIn.read(), InstructionCodec.getInstructionIndexType(iByte022), 0, 0L, InstructionCodec.byte1(i));
                                    break;
                                case 110:
                                case 111:
                                case 112:
                                case 113:
                                case 114:
                                    int iByte052 = InstructionCodec.byte0(i);
                                    int iNibble23 = InstructionCodec.nibble2(i);
                                    iNibble3 = InstructionCodec.nibble3(i);
                                    int i52 = this.codeIn.read();
                                    int i62 = this.codeIn.read();
                                    int iNibble02 = InstructionCodec.nibble0(i62);
                                    int iNibble12 = InstructionCodec.nibble1(i62);
                                    int iNibble222 = InstructionCodec.nibble2(i62);
                                    int iNibble322 = InstructionCodec.nibble3(i62);
                                    int instructionIndexType2 = InstructionCodec.getInstructionIndexType(iByte052);
                                    if (iNibble3 == 0) {
                                    }
                                    break;
                                default:
                                    switch (iExtractOpcodeFromUnit) {
                                        case 116:
                                        case 117:
                                        case 118:
                                        case 119:
                                        case 120:
                                            int iByte062 = InstructionCodec.byte0(i);
                                            instructionVisitor.visitRegisterRangeInsn(iCursor, iByte062, this.codeIn.read(), InstructionCodec.getInstructionIndexType(iByte062), 0, 0L, this.codeIn.read(), InstructionCodec.byte1(i));
                                            break;
                                        default:
                                            switch (iExtractOpcodeFromUnit) {
                                                case 123:
                                                case 124:
                                                case 125:
                                                case 126:
                                                case 127:
                                                case 128:
                                                case 129:
                                                case 130:
                                                case 131:
                                                case 132:
                                                case 133:
                                                case 134:
                                                case 135:
                                                case 136:
                                                case 137:
                                                case 138:
                                                case 139:
                                                case 140:
                                                case 141:
                                                case 142:
                                                case 143:
                                                case 176:
                                                case 177:
                                                case 178:
                                                case 179:
                                                case 180:
                                                case 181:
                                                case 182:
                                                case 183:
                                                case 184:
                                                case 185:
                                                case 186:
                                                case 187:
                                                case 188:
                                                case 189:
                                                case 190:
                                                case 191:
                                                case 192:
                                                case 193:
                                                case 194:
                                                case 195:
                                                case 196:
                                                case 197:
                                                case 198:
                                                case 199:
                                                case 200:
                                                case 201:
                                                case 202:
                                                case 203:
                                                case 204:
                                                case 205:
                                                case 206:
                                                case 207:
                                                    instructionVisitor.visitTwoRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, 0L, InstructionCodec.nibble2(i), InstructionCodec.nibble3(i));
                                                    break;
                                                case 144:
                                                case 145:
                                                case 146:
                                                case 147:
                                                case 148:
                                                case 149:
                                                case 150:
                                                case 151:
                                                case 152:
                                                case 153:
                                                case 154:
                                                case 155:
                                                case 156:
                                                case 157:
                                                case 158:
                                                case 159:
                                                case 160:
                                                case 161:
                                                case 162:
                                                case 163:
                                                case 164:
                                                case 165:
                                                case 166:
                                                case 167:
                                                case 168:
                                                case 169:
                                                case 170:
                                                case 171:
                                                case 172:
                                                case 173:
                                                case 174:
                                                case 175:
                                                    int iByte0822 = InstructionCodec.byte0(i);
                                                    int iByte1222 = InstructionCodec.byte1(i);
                                                    int i822 = this.codeIn.read();
                                                    instructionVisitor.visitThreeRegisterInsn(iCursor, iByte0822, 0, 1, 0, 0L, iByte1222, InstructionCodec.byte0(i822), InstructionCodec.byte1(i822));
                                                    break;
                                                case 208:
                                                case 209:
                                                case 210:
                                                case 211:
                                                case 212:
                                                case 213:
                                                case 214:
                                                case 215:
                                                    instructionVisitor.visitTwoRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, (short) this.codeIn.read(), InstructionCodec.nibble2(i), InstructionCodec.nibble3(i));
                                                    break;
                                                case 216:
                                                case 217:
                                                case 218:
                                                case 219:
                                                case 220:
                                                case 221:
                                                case 222:
                                                case 223:
                                                case 224:
                                                case 225:
                                                case 226:
                                                    instructionVisitor.visitTwoRegisterInsn(iCursor, InstructionCodec.byte0(i), 0, 1, 0, (byte) InstructionCodec.byte1(r1), InstructionCodec.byte1(i), InstructionCodec.byte0(this.codeIn.read()));
                                                    break;
                                                default:
                                                    throw new IllegalStateException("Unknown opcode: " + Hex.u4(iExtractOpcodeFromUnit));
                                            }
                                    }
                            }
                    }
                } else {
                    int i9 = this.codeIn.read();
                    int i10 = this.codeIn.readInt();
                    if (i9 == 1) {
                        byte[] bArr = new byte[i10];
                        int i11 = 0;
                        while (i2 < i10) {
                            if (z) {
                                i11 = this.codeIn.read();
                            }
                            bArr[i2] = (byte) (i11 & 255);
                            i11 >>= 8;
                            i2++;
                            z = !z;
                        }
                        instructionVisitor.visitFillArrayDataPayloadInsn(iCursor, i, bArr, i10, 1);
                    } else if (i9 == 2) {
                        short[] sArr = new short[i10];
                        while (i2 < i10) {
                            sArr[i2] = (short) this.codeIn.read();
                            i2++;
                        }
                        instructionVisitor.visitFillArrayDataPayloadInsn(iCursor, i, sArr, i10, 2);
                    } else if (i9 == 4) {
                        int[] iArr2 = new int[i10];
                        while (i2 < i10) {
                            iArr2[i2] = this.codeIn.readInt();
                            i2++;
                        }
                        instructionVisitor.visitFillArrayDataPayloadInsn(iCursor, i, iArr2, i10, 4);
                    } else {
                        if (i9 != 8) {
                            throw new DexException("bogus element_width: " + Hex.u2(i9));
                        }
                        long[] jArr = new long[i10];
                        while (i2 < i10) {
                            jArr[i2] = this.codeIn.readLong();
                            i2++;
                        }
                        instructionVisitor.visitFillArrayDataPayloadInsn(iCursor, i, jArr, i10, 8);
                    }
                }
            } else {
                int iBaseAddressForCursor2 = this.codeIn.baseAddressForCursor();
                int i12 = this.codeIn.read();
                int[] iArr3 = new int[i12];
                int[] iArr4 = new int[i12];
                for (int i13 = 0; i13 < i12; i13++) {
                    iArr3[i13] = this.codeIn.readInt();
                }
                while (i2 < i12) {
                    iArr4[i2] = this.codeIn.readInt() + iBaseAddressForCursor2;
                    i2++;
                }
                instructionVisitor.visitSparseSwitchPayloadInsn(iCursor, i, iArr3, iArr4);
            }
        }
    }
}
