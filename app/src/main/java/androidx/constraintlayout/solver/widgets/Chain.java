package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.ArrayRow;
import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintWidget;
import java.util.ArrayList;

public class Chain {
    private static final boolean DEBUG = false;
    public static final boolean USE_CHAIN_OPTIMIZATION = false;

    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, int i, int i2, ChainHead chainHead) {
        boolean z;
        boolean z2;
        boolean z3;
        int i3;
        ConstraintWidget constraintWidget;
        int i4;
        ConstraintAnchor constraintAnchor;
        SolverVariable solverVariable;
        SolverVariable solverVariable2;
        ConstraintWidget constraintWidget2;
        ConstraintAnchor constraintAnchor2;
        SolverVariable solverVariable3;
        SolverVariable solverVariable4;
        ConstraintWidget constraintWidget3;
        SolverVariable solverVariable5;
        int size;
        ArrayList<ConstraintWidget> arrayList;
        int i5;
        boolean z4;
        ConstraintWidget constraintWidget4;
        boolean z5;
        int i6;
        ConstraintWidget constraintWidget5 = chainHead.mFirst;
        ConstraintWidget constraintWidget6 = chainHead.mLast;
        ConstraintWidget constraintWidget7 = chainHead.mFirstVisibleWidget;
        ConstraintWidget constraintWidget8 = chainHead.mLastVisibleWidget;
        ConstraintWidget constraintWidget9 = chainHead.mHead;
        float f = chainHead.mTotalWeight;
        ConstraintWidget constraintWidget10 = chainHead.mFirstMatchConstraintWidget;
        ConstraintWidget constraintWidget11 = chainHead.mLastMatchConstraintWidget;
        boolean z6 = constraintWidgetContainer.mListDimensionBehaviors[i] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        if (i == 0) {
            z = constraintWidget9.mHorizontalChainStyle == 0;
            z2 = constraintWidget9.mHorizontalChainStyle == 1;
            z3 = constraintWidget9.mHorizontalChainStyle == 2;
        } else {
            z = constraintWidget9.mVerticalChainStyle == 0;
            z2 = constraintWidget9.mVerticalChainStyle == 1;
            if (constraintWidget9.mVerticalChainStyle == 2) {
            }
        }
        ?? r7 = constraintWidget5;
        boolean z7 = z2;
        boolean z8 = z;
        boolean z9 = false;
        while (true) {
            if (z9) {
                break;
            }
            ConstraintAnchor constraintAnchor3 = r7.mListAnchors[i2];
            int i7 = z3 ? 1 : 4;
            int margin = constraintAnchor3.getMargin();
            float f2 = f;
            boolean z10 = z9;
            boolean z11 = r7.mListDimensionBehaviors[i] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && r7.mResolvedMatchConstraintDefault[i] == 0;
            if (constraintAnchor3.mTarget != null && r7 != constraintWidget5) {
                margin += constraintAnchor3.mTarget.getMargin();
            }
            int i8 = margin;
            if (!z3 || r7 == constraintWidget5 || r7 == constraintWidget7) {
                z4 = z7;
            } else {
                z4 = z7;
                i7 = 8;
            }
            if (constraintAnchor3.mTarget != null) {
                if (r7 == constraintWidget7) {
                    z5 = z8;
                    constraintWidget4 = constraintWidget9;
                    linearSystem.addGreaterThan(constraintAnchor3.mSolverVariable, constraintAnchor3.mTarget.mSolverVariable, i8, 6);
                } else {
                    constraintWidget4 = constraintWidget9;
                    z5 = z8;
                    linearSystem.addGreaterThan(constraintAnchor3.mSolverVariable, constraintAnchor3.mTarget.mSolverVariable, i8, 8);
                }
                linearSystem.addEquality(constraintAnchor3.mSolverVariable, constraintAnchor3.mTarget.mSolverVariable, i8, (!z11 || z3) ? i7 : 5);
            } else {
                constraintWidget4 = constraintWidget9;
                z5 = z8;
            }
            if (z6) {
                if (r7.getVisibility() == 8 || r7.mListDimensionBehaviors[i] != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    i6 = 0;
                } else {
                    i6 = 0;
                    linearSystem.addGreaterThan(r7.mListAnchors[i2 + 1].mSolverVariable, r7.mListAnchors[i2].mSolverVariable, 0, 5);
                }
                linearSystem.addGreaterThan(r7.mListAnchors[i2].mSolverVariable, constraintWidgetContainer.mListAnchors[i2].mSolverVariable, i6, 8);
            }
            ConstraintAnchor constraintAnchor4 = r7.mListAnchors[i2 + 1].mTarget;
            if (constraintAnchor4 != null) {
                ?? r2 = constraintAnchor4.mOwner;
                if (r2.mListAnchors[i2].mTarget != null && r2.mListAnchors[i2].mTarget.mOwner == r7) {
                    solverVariable = r2;
                }
            }
            if (solverVariable != null) {
                r7 = solverVariable;
                z9 = z10;
            } else {
                z9 = true;
            }
            z7 = z4;
            f = f2;
            z8 = z5;
            constraintWidget9 = constraintWidget4;
            r7 = r7;
        }
        ConstraintWidget constraintWidget12 = constraintWidget9;
        float f3 = f;
        boolean z12 = z8;
        boolean z13 = z7;
        if (constraintWidget8 != null) {
            int i9 = i2 + 1;
            if (constraintWidget6.mListAnchors[i9].mTarget != null) {
                ConstraintAnchor constraintAnchor5 = constraintWidget8.mListAnchors[i9];
                if ((constraintWidget8.mListDimensionBehaviors[i] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget8.mResolvedMatchConstraintDefault[i] == 0) && !z3 && constraintAnchor5.mTarget.mOwner == constraintWidgetContainer) {
                    linearSystem.addEquality(constraintAnchor5.mSolverVariable, constraintAnchor5.mTarget.mSolverVariable, -constraintAnchor5.getMargin(), 5);
                } else if (z3 && constraintAnchor5.mTarget.mOwner == constraintWidgetContainer) {
                    linearSystem.addEquality(constraintAnchor5.mSolverVariable, constraintAnchor5.mTarget.mSolverVariable, -constraintAnchor5.getMargin(), 4);
                }
                linearSystem.addLowerThan(constraintAnchor5.mSolverVariable, constraintWidget6.mListAnchors[i9].mTarget.mSolverVariable, -constraintAnchor5.getMargin(), 6);
            }
        }
        if (z6) {
            int i10 = i2 + 1;
            linearSystem.addGreaterThan(constraintWidgetContainer.mListAnchors[i10].mSolverVariable, constraintWidget6.mListAnchors[i10].mSolverVariable, constraintWidget6.mListAnchors[i10].getMargin(), 8);
        }
        ArrayList<ConstraintWidget> arrayList2 = chainHead.mWeightedMatchConstraintsWidgets;
        if (arrayList2 != null && (size = arrayList2.size()) > 1) {
            float f4 = (!chainHead.mHasUndefinedWeights || chainHead.mHasComplexMatchWeights) ? f3 : chainHead.mWidgetsMatchCount;
            float f5 = 0.0f;
            ConstraintWidget constraintWidget13 = null;
            int i11 = 0;
            float f6 = 0.0f;
            while (i11 < size) {
                ConstraintWidget constraintWidget14 = arrayList2.get(i11);
                float f7 = constraintWidget14.mWeight[i];
                if (f7 < f5) {
                    if (chainHead.mHasComplexMatchWeights) {
                        linearSystem.addEquality(constraintWidget14.mListAnchors[i2 + 1].mSolverVariable, constraintWidget14.mListAnchors[i2].mSolverVariable, 0, 4);
                        arrayList = arrayList2;
                        i5 = size;
                        i11++;
                        size = i5;
                        arrayList2 = arrayList;
                        f5 = 0.0f;
                    } else {
                        f7 = 1.0f;
                        f5 = 0.0f;
                    }
                }
                if (f7 == f5) {
                    linearSystem.addEquality(constraintWidget14.mListAnchors[i2 + 1].mSolverVariable, constraintWidget14.mListAnchors[i2].mSolverVariable, 0, 8);
                    arrayList = arrayList2;
                    i5 = size;
                    i11++;
                    size = i5;
                    arrayList2 = arrayList;
                    f5 = 0.0f;
                } else {
                    if (constraintWidget13 != null) {
                        SolverVariable solverVariable6 = constraintWidget13.mListAnchors[i2].mSolverVariable;
                        int i12 = i2 + 1;
                        SolverVariable solverVariable7 = constraintWidget13.mListAnchors[i12].mSolverVariable;
                        SolverVariable solverVariable8 = constraintWidget14.mListAnchors[i2].mSolverVariable;
                        arrayList = arrayList2;
                        SolverVariable solverVariable9 = constraintWidget14.mListAnchors[i12].mSolverVariable;
                        i5 = size;
                        ArrayRow arrayRowCreateRow = linearSystem.createRow();
                        arrayRowCreateRow.createRowEqualMatchDimensions(f6, f4, f7, solverVariable6, solverVariable7, solverVariable8, solverVariable9);
                        linearSystem.addConstraint(arrayRowCreateRow);
                    } else {
                        arrayList = arrayList2;
                        i5 = size;
                    }
                    f6 = f7;
                    constraintWidget13 = constraintWidget14;
                    i11++;
                    size = i5;
                    arrayList2 = arrayList;
                    f5 = 0.0f;
                }
            }
        }
        if (constraintWidget7 != null && (constraintWidget7 == constraintWidget8 || z3)) {
            ConstraintAnchor constraintAnchor6 = constraintWidget5.mListAnchors[i2];
            int i13 = i2 + 1;
            ConstraintAnchor constraintAnchor7 = constraintWidget6.mListAnchors[i13];
            SolverVariable solverVariable10 = constraintAnchor6.mTarget != null ? constraintAnchor6.mTarget.mSolverVariable : null;
            SolverVariable solverVariable11 = constraintAnchor7.mTarget != null ? constraintAnchor7.mTarget.mSolverVariable : null;
            ConstraintAnchor constraintAnchor8 = constraintWidget7.mListAnchors[i2];
            ConstraintAnchor constraintAnchor9 = constraintWidget8.mListAnchors[i13];
            if (solverVariable10 != null && solverVariable11 != null) {
                linearSystem.addCentering(constraintAnchor8.mSolverVariable, solverVariable10, constraintAnchor8.getMargin(), i == 0 ? constraintWidget12.mHorizontalBiasPercent : constraintWidget12.mVerticalBiasPercent, solverVariable11, constraintAnchor9.mSolverVariable, constraintAnchor9.getMargin(), 7);
            }
        } else if (!z12 || constraintWidget7 == null) {
            int i14 = 8;
            if (z13 && constraintWidget7 != null) {
                boolean z14 = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
                ConstraintWidget constraintWidget15 = constraintWidget7;
                ConstraintWidget constraintWidget16 = constraintWidget15;
                while (constraintWidget15 != null) {
                    ConstraintWidget constraintWidget17 = constraintWidget15.mNextChainWidget[i];
                    while (constraintWidget17 != null && constraintWidget17.getVisibility() == i14) {
                        constraintWidget17 = constraintWidget17.mNextChainWidget[i];
                    }
                    if (constraintWidget15 == constraintWidget7 || constraintWidget15 == constraintWidget8 || constraintWidget17 == null) {
                        constraintWidget = constraintWidget16;
                        i4 = 8;
                    } else {
                        ConstraintWidget constraintWidget18 = constraintWidget17 == constraintWidget8 ? null : constraintWidget17;
                        ConstraintAnchor constraintAnchor10 = constraintWidget15.mListAnchors[i2];
                        SolverVariable solverVariable12 = constraintAnchor10.mSolverVariable;
                        if (constraintAnchor10.mTarget != null) {
                            SolverVariable solverVariable13 = constraintAnchor10.mTarget.mSolverVariable;
                        }
                        int i15 = i2 + 1;
                        SolverVariable solverVariable14 = constraintWidget16.mListAnchors[i15].mSolverVariable;
                        int margin2 = constraintAnchor10.getMargin();
                        int margin3 = constraintWidget15.mListAnchors[i15].getMargin();
                        if (constraintWidget18 != null) {
                            constraintAnchor = constraintWidget18.mListAnchors[i2];
                            solverVariable = constraintAnchor.mSolverVariable;
                            solverVariable2 = constraintAnchor.mTarget != null ? constraintAnchor.mTarget.mSolverVariable : null;
                        } else {
                            constraintAnchor = constraintWidget8.mListAnchors[i2];
                            solverVariable = constraintAnchor != null ? constraintAnchor.mSolverVariable : null;
                            solverVariable2 = constraintWidget15.mListAnchors[i15].mSolverVariable;
                        }
                        if (constraintAnchor != null) {
                            margin3 += constraintAnchor.getMargin();
                        }
                        int i16 = margin3;
                        if (constraintWidget16 != null) {
                            margin2 += constraintWidget16.mListAnchors[i15].getMargin();
                        }
                        int i17 = margin2;
                        int i18 = z14 ? 8 : 4;
                        if (solverVariable12 == null || solverVariable14 == null || solverVariable == null || solverVariable2 == null) {
                            constraintWidget2 = constraintWidget18;
                            constraintWidget = constraintWidget16;
                            i4 = 8;
                        } else {
                            constraintWidget2 = constraintWidget18;
                            constraintWidget = constraintWidget16;
                            i4 = 8;
                            linearSystem.addCentering(solverVariable12, solverVariable14, i17, 0.5f, solverVariable, solverVariable2, i16, i18);
                        }
                        constraintWidget17 = constraintWidget2;
                    }
                    if (constraintWidget15.getVisibility() == i4) {
                        constraintWidget15 = constraintWidget;
                    }
                    constraintWidget16 = constraintWidget15;
                    i14 = 8;
                    constraintWidget15 = constraintWidget17;
                }
                ConstraintAnchor constraintAnchor11 = constraintWidget7.mListAnchors[i2];
                ConstraintAnchor constraintAnchor12 = constraintWidget5.mListAnchors[i2].mTarget;
                int i19 = i2 + 1;
                ConstraintAnchor constraintAnchor13 = constraintWidget8.mListAnchors[i19];
                ConstraintAnchor constraintAnchor14 = constraintWidget6.mListAnchors[i19].mTarget;
                if (constraintAnchor12 == null) {
                    i3 = 5;
                } else if (constraintWidget7 != constraintWidget8) {
                    i3 = 5;
                    linearSystem.addEquality(constraintAnchor11.mSolverVariable, constraintAnchor12.mSolverVariable, constraintAnchor11.getMargin(), 5);
                } else {
                    i3 = 5;
                    if (constraintAnchor14 != null) {
                        linearSystem.addCentering(constraintAnchor11.mSolverVariable, constraintAnchor12.mSolverVariable, constraintAnchor11.getMargin(), 0.5f, constraintAnchor13.mSolverVariable, constraintAnchor14.mSolverVariable, constraintAnchor13.getMargin(), 5);
                    }
                }
                if (constraintAnchor14 != null && constraintWidget7 != constraintWidget8) {
                    linearSystem.addEquality(constraintAnchor13.mSolverVariable, constraintAnchor14.mSolverVariable, -constraintAnchor13.getMargin(), i3);
                }
            }
        } else {
            boolean z15 = chainHead.mWidgetsMatchCount > 0 && chainHead.mWidgetsCount == chainHead.mWidgetsMatchCount;
            ConstraintWidget constraintWidget19 = constraintWidget7;
            ConstraintWidget constraintWidget20 = constraintWidget19;
            while (constraintWidget19 != null) {
                ConstraintWidget constraintWidget21 = constraintWidget19.mNextChainWidget[i];
                while (constraintWidget21 != null && constraintWidget21.getVisibility() == 8) {
                    constraintWidget21 = constraintWidget21.mNextChainWidget[i];
                }
                if (constraintWidget21 != null || constraintWidget19 == constraintWidget8) {
                    ConstraintAnchor constraintAnchor15 = constraintWidget19.mListAnchors[i2];
                    SolverVariable solverVariable15 = constraintAnchor15.mSolverVariable;
                    SolverVariable solverVariable16 = constraintAnchor15.mTarget != null ? constraintAnchor15.mTarget.mSolverVariable : null;
                    if (constraintWidget20 != constraintWidget19) {
                        solverVariable16 = constraintWidget20.mListAnchors[i2 + 1].mSolverVariable;
                    } else if (constraintWidget19 == constraintWidget7 && constraintWidget20 == constraintWidget19) {
                        solverVariable16 = constraintWidget5.mListAnchors[i2].mTarget != null ? constraintWidget5.mListAnchors[i2].mTarget.mSolverVariable : null;
                    }
                    int margin4 = constraintAnchor15.getMargin();
                    int i20 = i2 + 1;
                    int margin5 = constraintWidget19.mListAnchors[i20].getMargin();
                    if (constraintWidget21 != null) {
                        constraintAnchor2 = constraintWidget21.mListAnchors[i2];
                        SolverVariable solverVariable17 = constraintAnchor2.mSolverVariable;
                        solverVariable4 = constraintWidget19.mListAnchors[i20].mSolverVariable;
                        solverVariable3 = solverVariable17;
                    } else {
                        constraintAnchor2 = constraintWidget6.mListAnchors[i20].mTarget;
                        solverVariable3 = constraintAnchor2 != null ? constraintAnchor2.mSolverVariable : null;
                        solverVariable4 = constraintWidget19.mListAnchors[i20].mSolverVariable;
                    }
                    if (constraintAnchor2 != null) {
                        margin5 += constraintAnchor2.getMargin();
                    }
                    if (constraintWidget20 != null) {
                        margin4 += constraintWidget20.mListAnchors[i20].getMargin();
                    }
                    if (solverVariable15 == null || solverVariable16 == null || solverVariable3 == null || solverVariable4 == null) {
                        constraintWidget3 = constraintWidget21;
                    } else {
                        if (constraintWidget19 == constraintWidget7) {
                            margin4 = constraintWidget7.mListAnchors[i2].getMargin();
                        }
                        int i21 = margin4;
                        constraintWidget3 = constraintWidget21;
                        linearSystem.addCentering(solverVariable15, solverVariable16, i21, 0.5f, solverVariable3, solverVariable4, constraintWidget19 == constraintWidget8 ? constraintWidget8.mListAnchors[i20].getMargin() : margin5, z15 ? 8 : 5);
                    }
                }
                if (constraintWidget19.getVisibility() != 8) {
                    constraintWidget20 = constraintWidget19;
                }
                constraintWidget19 = constraintWidget3;
            }
        }
        if ((!z12 && !z13) || constraintWidget7 == null || constraintWidget7 == constraintWidget8) {
            return;
        }
        ConstraintAnchor constraintAnchor16 = constraintWidget7.mListAnchors[i2];
        int i22 = i2 + 1;
        ConstraintAnchor constraintAnchor17 = constraintWidget8.mListAnchors[i22];
        SolverVariable solverVariable18 = constraintAnchor16.mTarget != null ? constraintAnchor16.mTarget.mSolverVariable : null;
        SolverVariable solverVariable19 = constraintAnchor17.mTarget != null ? constraintAnchor17.mTarget.mSolverVariable : null;
        if (constraintWidget6 != constraintWidget8) {
            ConstraintAnchor constraintAnchor18 = constraintWidget6.mListAnchors[i22];
            solverVariable5 = constraintAnchor18.mTarget != null ? constraintAnchor18.mTarget.mSolverVariable : null;
        } else {
            solverVariable5 = solverVariable19;
        }
        if (constraintWidget7 == constraintWidget8) {
            constraintAnchor16 = constraintWidget7.mListAnchors[i2];
            constraintAnchor17 = constraintWidget7.mListAnchors[i22];
        }
        if (solverVariable18 == null || solverVariable5 == null) {
            return;
        }
        int margin6 = constraintAnchor16.getMargin();
        if (constraintWidget8 != null) {
            constraintWidget6 = constraintWidget8;
        }
        linearSystem.addCentering(constraintAnchor16.mSolverVariable, solverVariable18, margin6, 0.5f, solverVariable5, constraintAnchor17.mSolverVariable, constraintWidget6.mListAnchors[i22].getMargin(), 5);
    }

    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ArrayList<ConstraintWidget> arrayList, int i) {
        ChainHead[] chainHeadArr;
        int i2;
        int i3;
        if (i == 0) {
            i2 = constraintWidgetContainer.mHorizontalChainsSize;
            chainHeadArr = constraintWidgetContainer.mHorizontalChainsArray;
            i3 = 0;
        } else {
            int i4 = constraintWidgetContainer.mVerticalChainsSize;
            chainHeadArr = constraintWidgetContainer.mVerticalChainsArray;
            i2 = i4;
            i3 = 2;
        }
        for (int i5 = 0; i5 < i2; i5++) {
            ChainHead chainHead = chainHeadArr[i5];
            chainHead.define();
            if (arrayList == null || (arrayList != null && arrayList.contains(chainHead.mFirst))) {
                applyChainConstraints(constraintWidgetContainer, linearSystem, i, i3, chainHead);
            }
        }
    }
}
