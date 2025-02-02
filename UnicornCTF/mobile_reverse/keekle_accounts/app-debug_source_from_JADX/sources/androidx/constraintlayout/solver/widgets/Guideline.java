package androidx.constraintlayout.solver.widgets;

import androidx.constraintlayout.solver.LinearSystem;
import androidx.constraintlayout.solver.SolverVariable;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor.Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget.DimensionBehaviour;
import java.util.ArrayList;

public class Guideline extends ConstraintWidget {
    public static final int HORIZONTAL = 0;
    public static final int RELATIVE_BEGIN = 1;
    public static final int RELATIVE_END = 2;
    public static final int RELATIVE_PERCENT = 0;
    public static final int RELATIVE_UNKNWON = -1;
    public static final int VERTICAL = 1;
    private ConstraintAnchor mAnchor = this.mTop;
    private Rectangle mHead = new Rectangle();
    private int mHeadSize = 8;
    private boolean mIsPositionRelaxed = false;
    private int mMinimumPosition = 0;
    private int mOrientation = 0;
    protected int mRelativeBegin = -1;
    protected int mRelativeEnd = -1;
    protected float mRelativePercent = -1.0f;

    /* renamed from: androidx.constraintlayout.solver.widgets.Guideline$1 */
    static /* synthetic */ class C01981 {

        /* renamed from: $SwitchMap$androidx$constraintlayout$solver$widgets$ConstraintAnchor$Type */
        static final /* synthetic */ int[] f26x4c44d048;

        static {
            int[] iArr = new int[Type.values().length];
            f26x4c44d048 = iArr;
            try {
                iArr[Type.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f26x4c44d048[Type.RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f26x4c44d048[Type.TOP.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f26x4c44d048[Type.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f26x4c44d048[Type.BASELINE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f26x4c44d048[Type.CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f26x4c44d048[Type.CENTER_X.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f26x4c44d048[Type.CENTER_Y.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f26x4c44d048[Type.NONE.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public Guideline() {
        this.mAnchors.clear();
        this.mAnchors.add(this.mAnchor);
        int count = this.mListAnchors.length;
        for (int i = 0; i < count; i++) {
            this.mListAnchors[i] = this.mAnchor;
        }
    }

    public boolean allowedInBarrier() {
        return true;
    }

    public int getRelativeBehaviour() {
        if (this.mRelativePercent != -1.0f) {
            return 0;
        }
        if (this.mRelativeBegin != -1) {
            return 1;
        }
        if (this.mRelativeEnd != -1) {
            return 2;
        }
        return -1;
    }

    public Rectangle getHead() {
        Rectangle rectangle = this.mHead;
        int drawX = getDrawX() - this.mHeadSize;
        int drawY = getDrawY();
        int i = this.mHeadSize;
        rectangle.setBounds(drawX, drawY - (i * 2), i * 2, i * 2);
        if (getOrientation() == 0) {
            Rectangle rectangle2 = this.mHead;
            int drawX2 = getDrawX() - (this.mHeadSize * 2);
            int drawY2 = getDrawY();
            int i2 = this.mHeadSize;
            rectangle2.setBounds(drawX2, drawY2 - i2, i2 * 2, i2 * 2);
        }
        return this.mHead;
    }

    public void setOrientation(int orientation) {
        if (this.mOrientation != orientation) {
            this.mOrientation = orientation;
            this.mAnchors.clear();
            if (this.mOrientation == 1) {
                this.mAnchor = this.mLeft;
            } else {
                this.mAnchor = this.mTop;
            }
            this.mAnchors.add(this.mAnchor);
            int count = this.mListAnchors.length;
            for (int i = 0; i < count; i++) {
                this.mListAnchors[i] = this.mAnchor;
            }
        }
    }

    public ConstraintAnchor getAnchor() {
        return this.mAnchor;
    }

    public String getType() {
        return "Guideline";
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setMinimumPosition(int minimum) {
        this.mMinimumPosition = minimum;
    }

    public void setPositionRelaxed(boolean value) {
        if (this.mIsPositionRelaxed != value) {
            this.mIsPositionRelaxed = value;
        }
    }

    public ConstraintAnchor getAnchor(Type anchorType) {
        switch (C01981.f26x4c44d048[anchorType.ordinal()]) {
            case 1:
            case 2:
                if (this.mOrientation == 1) {
                    return this.mAnchor;
                }
                break;
            case 3:
            case 4:
                if (this.mOrientation == 0) {
                    return this.mAnchor;
                }
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return null;
        }
        throw new AssertionError(anchorType.name());
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setGuidePercent(int value) {
        setGuidePercent(((float) value) / 100.0f);
    }

    public void setGuidePercent(float value) {
        if (value > -1.0f) {
            this.mRelativePercent = value;
            this.mRelativeBegin = -1;
            this.mRelativeEnd = -1;
        }
    }

    public void setGuideBegin(int value) {
        if (value > -1) {
            this.mRelativePercent = -1.0f;
            this.mRelativeBegin = value;
            this.mRelativeEnd = -1;
        }
    }

    public void setGuideEnd(int value) {
        if (value > -1) {
            this.mRelativePercent = -1.0f;
            this.mRelativeBegin = -1;
            this.mRelativeEnd = value;
        }
    }

    public float getRelativePercent() {
        return this.mRelativePercent;
    }

    public int getRelativeBegin() {
        return this.mRelativeBegin;
    }

    public int getRelativeEnd() {
        return this.mRelativeEnd;
    }

    public void analyze(int optimizationLevel) {
        ConstraintWidget constraintWidgetContainer = getParent();
        if (constraintWidgetContainer != null) {
            if (getOrientation() == 1) {
                this.mTop.getResolutionNode().dependsOn(1, constraintWidgetContainer.mTop.getResolutionNode(), 0);
                this.mBottom.getResolutionNode().dependsOn(1, constraintWidgetContainer.mTop.getResolutionNode(), 0);
                if (this.mRelativeBegin != -1) {
                    this.mLeft.getResolutionNode().dependsOn(1, constraintWidgetContainer.mLeft.getResolutionNode(), this.mRelativeBegin);
                    this.mRight.getResolutionNode().dependsOn(1, constraintWidgetContainer.mLeft.getResolutionNode(), this.mRelativeBegin);
                } else if (this.mRelativeEnd != -1) {
                    this.mLeft.getResolutionNode().dependsOn(1, constraintWidgetContainer.mRight.getResolutionNode(), -this.mRelativeEnd);
                    this.mRight.getResolutionNode().dependsOn(1, constraintWidgetContainer.mRight.getResolutionNode(), -this.mRelativeEnd);
                } else if (this.mRelativePercent != -1.0f && constraintWidgetContainer.getHorizontalDimensionBehaviour() == DimensionBehaviour.FIXED) {
                    int position = (int) (((float) constraintWidgetContainer.mWidth) * this.mRelativePercent);
                    this.mLeft.getResolutionNode().dependsOn(1, constraintWidgetContainer.mLeft.getResolutionNode(), position);
                    this.mRight.getResolutionNode().dependsOn(1, constraintWidgetContainer.mLeft.getResolutionNode(), position);
                }
            } else {
                this.mLeft.getResolutionNode().dependsOn(1, constraintWidgetContainer.mLeft.getResolutionNode(), 0);
                this.mRight.getResolutionNode().dependsOn(1, constraintWidgetContainer.mLeft.getResolutionNode(), 0);
                if (this.mRelativeBegin != -1) {
                    this.mTop.getResolutionNode().dependsOn(1, constraintWidgetContainer.mTop.getResolutionNode(), this.mRelativeBegin);
                    this.mBottom.getResolutionNode().dependsOn(1, constraintWidgetContainer.mTop.getResolutionNode(), this.mRelativeBegin);
                } else if (this.mRelativeEnd != -1) {
                    this.mTop.getResolutionNode().dependsOn(1, constraintWidgetContainer.mBottom.getResolutionNode(), -this.mRelativeEnd);
                    this.mBottom.getResolutionNode().dependsOn(1, constraintWidgetContainer.mBottom.getResolutionNode(), -this.mRelativeEnd);
                } else if (this.mRelativePercent != -1.0f && constraintWidgetContainer.getVerticalDimensionBehaviour() == DimensionBehaviour.FIXED) {
                    int position2 = (int) (((float) constraintWidgetContainer.mHeight) * this.mRelativePercent);
                    this.mTop.getResolutionNode().dependsOn(1, constraintWidgetContainer.mTop.getResolutionNode(), position2);
                    this.mBottom.getResolutionNode().dependsOn(1, constraintWidgetContainer.mTop.getResolutionNode(), position2);
                }
            }
        }
    }

    public void addToSolver(LinearSystem system) {
        ConstraintWidgetContainer parent = (ConstraintWidgetContainer) getParent();
        if (parent != null) {
            ConstraintAnchor begin = parent.getAnchor(Type.LEFT);
            ConstraintAnchor end = parent.getAnchor(Type.RIGHT);
            boolean z = true;
            boolean parentWrapContent = this.mParent != null && this.mParent.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT;
            if (this.mOrientation == 0) {
                begin = parent.getAnchor(Type.TOP);
                end = parent.getAnchor(Type.BOTTOM);
                if (this.mParent == null || this.mParent.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT) {
                    z = false;
                }
                parentWrapContent = z;
            }
            if (this.mRelativeBegin != -1) {
                SolverVariable guide = system.createObjectVariable(this.mAnchor);
                system.addEquality(guide, system.createObjectVariable(begin), this.mRelativeBegin, 6);
                if (parentWrapContent) {
                    system.addGreaterThan(system.createObjectVariable(end), guide, 0, 5);
                }
            } else if (this.mRelativeEnd != -1) {
                SolverVariable guide2 = system.createObjectVariable(this.mAnchor);
                SolverVariable parentRight = system.createObjectVariable(end);
                system.addEquality(guide2, parentRight, -this.mRelativeEnd, 6);
                if (parentWrapContent) {
                    system.addGreaterThan(guide2, system.createObjectVariable(begin), 0, 5);
                    system.addGreaterThan(parentRight, guide2, 0, 5);
                }
            } else if (this.mRelativePercent != -1.0f) {
                system.addConstraint(LinearSystem.createRowDimensionPercent(system, system.createObjectVariable(this.mAnchor), system.createObjectVariable(begin), system.createObjectVariable(end), this.mRelativePercent, this.mIsPositionRelaxed));
            }
        }
    }

    public void updateFromSolver(LinearSystem system) {
        if (getParent() != null) {
            int value = system.getObjectVariableValue(this.mAnchor);
            if (this.mOrientation == 1) {
                setX(value);
                setY(0);
                setHeight(getParent().getHeight());
                setWidth(0);
            } else {
                setX(0);
                setY(value);
                setWidth(getParent().getWidth());
                setHeight(0);
            }
        }
    }

    public void setDrawOrigin(int x, int y) {
        if (this.mOrientation == 1) {
            int position = x - this.mOffsetX;
            if (this.mRelativeBegin != -1) {
                setGuideBegin(position);
            } else if (this.mRelativeEnd != -1) {
                setGuideEnd(getParent().getWidth() - position);
            } else if (this.mRelativePercent != -1.0f) {
                setGuidePercent(((float) position) / ((float) getParent().getWidth()));
            }
        } else {
            int position2 = y - this.mOffsetY;
            if (this.mRelativeBegin != -1) {
                setGuideBegin(position2);
            } else if (this.mRelativeEnd != -1) {
                setGuideEnd(getParent().getHeight() - position2);
            } else if (this.mRelativePercent != -1.0f) {
                setGuidePercent(((float) position2) / ((float) getParent().getHeight()));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void inferRelativePercentPosition() {
        float percent = ((float) getX()) / ((float) getParent().getWidth());
        if (this.mOrientation == 0) {
            percent = ((float) getY()) / ((float) getParent().getHeight());
        }
        setGuidePercent(percent);
    }

    /* access modifiers changed from: 0000 */
    public void inferRelativeBeginPosition() {
        int position = getX();
        if (this.mOrientation == 0) {
            position = getY();
        }
        setGuideBegin(position);
    }

    /* access modifiers changed from: 0000 */
    public void inferRelativeEndPosition() {
        int position = getParent().getWidth() - getX();
        if (this.mOrientation == 0) {
            position = getParent().getHeight() - getY();
        }
        setGuideEnd(position);
    }

    public void cyclePosition() {
        if (this.mRelativeBegin != -1) {
            inferRelativePercentPosition();
        } else if (this.mRelativePercent != -1.0f) {
            inferRelativeEndPosition();
        } else if (this.mRelativeEnd != -1) {
            inferRelativeBeginPosition();
        }
    }
}
