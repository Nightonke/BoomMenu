package com.nightonke.boommenu.Piece;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

import com.nightonke.boommenu.BoomButtons.BoomButtonBuilder;
import com.nightonke.boommenu.BoomMenuButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.sqrt;

/**
 * Created by Weiping Huang at 23:50 on 16/11/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class PiecePlaceManager {

    public static ArrayList<RectF> getDotPositions(BoomMenuButton bmb, Point parentSize) {

        float hm = bmb.getPieceHorizontalMargin();
        float hm_0_5 = hm * 0.5f;
        float hm_1_5 = hm * 1.5f;
        float vm = bmb.getPieceVerticalMargin();
        float vm_0_5 = vm * 0.5f;
        float vm_1_5 = vm * 1.5f;
        float im = bmb.getPieceInclinedMargin();
        float r = bmb.getDotRadius();
        float r_2_0 = r * 2;
        float r_3_0 = r * 3;

        ArrayList<RectF> positions = new ArrayList<>();
        ArrayList<PointF> pos = new ArrayList<>();

        float a, b, c, e;
        b = hm_0_5 + r;
        c = (float) (b / (sqrt(3) / 2));
        a = c / 2;
        e = c - a;

        switch (bmb.getPiecePlaceEnum()) {
            case DOT_4_2: case DOT_5_4: case DOT_8_5: case DOT_9_3:
                a = (float) ((r_2_0 + im) / sqrt(2));
                break;
            case DOT_8_2:
                b = vm_0_5 + r;
                c = (float) (b / (sqrt(3) / 2));
                a = c / 2;
                e = c - a;
                break;
        }

        float a_2_0 = 2 * a;
        float b_2_0 = 2 * b;
        float b_3_0 = 3 * b;
        float c_2_0 = 2 * c;

        switch (bmb.getPiecePlaceEnum()) {
            case DOT_1:
                pos.add(point(0, 0));
                break;
            case DOT_2_1:
                pos.add(point(-hm_0_5 - r, 0));
                pos.add(point(+hm_0_5 + r, 0));
                break;
            case DOT_2_2:
                pos.add(point(0, -vm_0_5 - r));
                pos.add(point(0, +vm_0_5 + r));
                break;
            case DOT_3_1:
                pos.add(point(-hm - r_2_0, 0));
                pos.add(point(0, 0));
                pos.add(point(+hm + r_2_0, 0));
                break;
            case DOT_3_2:
                pos.add(point(0, -vm - r_2_0));
                pos.add(point(0, 0));
                pos.add(point(0, +vm + r_2_0));
                break;
            case DOT_3_3:
                pos.add(point(-b, -a));
                pos.add(point(+b, -a));
                pos.add(point(0, c));
                break;
            case DOT_3_4:
                pos.add(point(0, -c));
                pos.add(point(-b, a));
                pos.add(point(+b, a));
                break;
            case DOT_4_1:
                pos.add(point(-hm_0_5 - r, -vm_0_5 - r));
                pos.add(point(+hm_0_5 + r, -vm_0_5 - r));
                pos.add(point(-hm_0_5 - r, +vm_0_5 + r));
                pos.add(point(+hm_0_5 + r, +vm_0_5 + r));
                break;
            case DOT_4_2:
                pos.add(point(0, -a));
                pos.add(point(+a, 0));
                pos.add(point(0, +a));
                pos.add(point(-a, 0));
                break;
            case DOT_5_1:
                pos.add(point(-b_2_0, -a));
                pos.add(point(0, -a));
                pos.add(point(+b_2_0, -a));
                pos.add(point(-hm_0_5 - r, c));
                pos.add(point(+hm_0_5 + r, c));
                break;
            case DOT_5_2:
                pos.add(point(-hm_0_5 - r, -c));
                pos.add(point(+hm_0_5 + r, -c));
                pos.add(point(-b_2_0, a));
                pos.add(point(0, a));
                pos.add(point(+b_2_0, a));
                break;
            case DOT_5_3:
                pos.add(point(0, -vm - r_2_0));
                pos.add(point(-hm - r_2_0, 0));
                pos.add(point(0, 0));
                pos.add(point(+hm + r_2_0, 0));
                pos.add(point(0, +vm + r_2_0));
                break;
            case DOT_5_4:
                pos.add(point(-a, -a));
                pos.add(point(+a, -a));
                pos.add(point(0, 0));
                pos.add(point(-a, +a));
                pos.add(point(+a, +a));
                break;
            case DOT_6_1:
                pos.add(point(-hm - r_2_0, -vm_0_5 - r));
                pos.add(point(0, -vm_0_5 - r));
                pos.add(point(+hm + r_2_0, -vm_0_5 - r));
                pos.add(point(-hm - r_2_0, +vm_0_5 + r));
                pos.add(point(0, +vm_0_5 + r));
                pos.add(point(+hm + r_2_0, +vm_0_5 + r));
                break;
            case DOT_6_2:
                pos.add(point(-hm_0_5 - r, -vm - r_2_0));
                pos.add(point(+hm_0_5 + r, -vm - r_2_0));
                pos.add(point(-hm_0_5 - r, 0));
                pos.add(point(+hm_0_5 + r, 0));
                pos.add(point(-hm_0_5 - r, +vm + r_2_0));
                pos.add(point(+hm_0_5 + r, +vm + r_2_0));
                break;
            case DOT_6_3:
                pos.add(point(-b, -a - c));
                pos.add(point(+b, -a - c));
                pos.add(point(-b_2_0, 0));
                pos.add(point(+b_2_0, 0));
                pos.add(point(-b, +a + c));
                pos.add(point(+b, +a + c));
                break;
            case DOT_6_4:
                pos.add(point(0, -b_2_0));
                pos.add(point(-a - c, -b));
                pos.add(point(+a + c, -b));
                pos.add(point(-a - c, +b));
                pos.add(point(+a + c, +b));
                pos.add(point(0, +b_2_0));
                break;
            case DOT_6_5:
                pos.add(point(-b_2_0, -a - c + e));
                pos.add(point(0, -a - c + e));
                pos.add(point(+b_2_0, -a - c + e));
                pos.add(point(-hm_0_5 - r, e));
                pos.add(point(+hm_0_5 + r, e));
                pos.add(point(0, +a + c + e));
                break;
            case DOT_6_6:
                pos.add(point(0, -a - c - e));
                pos.add(point(-hm_0_5 - r, -e));
                pos.add(point(+hm_0_5 + r, -e));
                pos.add(point(-b_2_0, a + c - e));
                pos.add(point(0, +a + c - e));
                pos.add(point(+b_2_0, a + c - e));
                break;
            case DOT_7_1:
                pos.add(point(-hm - r_2_0, -vm - r_2_0));
                pos.add(point(0, -vm - r_2_0));
                pos.add(point(+hm + r_2_0, -vm - r_2_0));
                pos.add(point(-hm - r_2_0, 0));
                pos.add(point(0, 0));
                pos.add(point(+hm + r_2_0, 0));
                pos.add(point(0, +vm + r_2_0));
                break;
            case DOT_7_2:
                pos.add(point(0, -vm - r_2_0));
                pos.add(point(-hm - r_2_0, 0));
                pos.add(point(0, 0));
                pos.add(point(+hm + r_2_0, 0));
                pos.add(point(-hm - r_2_0, +vm + r_2_0));
                pos.add(point(0, +vm + r_2_0));
                pos.add(point(+hm + r_2_0, +vm + r_2_0));
                break;
            case DOT_7_3:
                pos.add(point(-b, -a - c));
                pos.add(point(+b, -a - c));
                pos.add(point(-b_2_0, 0));
                pos.add(point(0, 0));
                pos.add(point(+b_2_0, 0));
                pos.add(point(-b, +a + c));
                pos.add(point(+b, +a + c));
                break;
            case DOT_7_4:
                pos.add(point(0, -b_2_0));
                pos.add(point(-a - c, -b));
                pos.add(point(+a + c, -b));
                pos.add(point(0, 0));
                pos.add(point(-a - c, +b));
                pos.add(point(+a + c, +b));
                pos.add(point(0, +b_2_0));
                break;
            case DOT_7_5:
                pos.add(point(-b_3_0, -a));
                pos.add(point(-b, -a));
                pos.add(point(+b, -a));
                pos.add(point(+b_3_0, -a));
                pos.add(point(-b_2_0, +c));
                pos.add(point(0, c));
                pos.add(point(+b_2_0, +c));
                break;
            case DOT_7_6:
                pos.add(point(-b_2_0, -c));
                pos.add(point(0, -c));
                pos.add(point(+b_2_0, -c));
                pos.add(point(-b_3_0, +a));
                pos.add(point(-b, a));
                pos.add(point(+b, a));
                pos.add(point(+b_3_0, +a));
                break;
            case DOT_8_1:
                pos.add(point(-b_2_0, -a - c));
                pos.add(point(0, -a - c));
                pos.add(point(+b_2_0, -a - c));
                pos.add(point(-hm_0_5 - r, 0));
                pos.add(point(+hm_0_5 + r, 0));
                pos.add(point(-b_2_0, +a + c));
                pos.add(point(0, +a + c));
                pos.add(point(+b_2_0, +a + c));
                break;
            case DOT_8_2:
                pos.add(point(-a - c, -b_2_0));
                pos.add(point(+a + c, -b_2_0));
                pos.add(point(0, -vm_0_5 - r));
                pos.add(point(-a - c, 0));
                pos.add(point(+a + c, 0));
                pos.add(point(0, +vm_0_5 + r));
                pos.add(point(-a - c, +b_2_0));
                pos.add(point(+a + c, +b_2_0));
                break;
            case DOT_8_3:
                pos.add(point(-hm - r_2_0, -vm - r_2_0));
                pos.add(point(0, -vm - r_2_0));
                pos.add(point(+hm + r_2_0, -vm - r_2_0));
                pos.add(point(-hm - r_2_0, 0));
                pos.add(point(+hm + r_2_0, 0));
                pos.add(point(-hm - r_2_0, +vm + r_2_0));
                pos.add(point(0, +vm + r_2_0));
                pos.add(point(+hm + r_2_0, +vm + r_2_0));
                break;
            case DOT_8_4:
                pos.add(point(0, -a_2_0 - c_2_0));
                pos.add(point(-hm_0_5 - r, -a - c));
                pos.add(point(+hm_0_5 + r, -a - c));
                pos.add(point(-b_2_0, 0));
                pos.add(point(+b_2_0, 0));
                pos.add(point(-hm_0_5 - r, +a + c));
                pos.add(point(+hm_0_5 + r, +a + c));
                pos.add(point(0, +a_2_0 + c_2_0));
                break;
            case DOT_8_5:
                pos.add(point(0, -a_2_0));
                pos.add(point(-a, -a));
                pos.add(point(+a, -a));
                pos.add(point(-a_2_0, 0));
                pos.add(point(+a_2_0, 0));
                pos.add(point(-a, +a));
                pos.add(point(+a, +a));
                pos.add(point(0, +a_2_0));
                break;
            case DOT_8_6:
                pos.add(point(-hm_1_5 - r_3_0, -vm_0_5 - r));
                pos.add(point(-hm_0_5 - r, -vm_0_5 - r));
                pos.add(point(+hm_0_5 + r, -vm_0_5 - r));
                pos.add(point(+hm_1_5 + r_3_0, -vm_0_5 - r));
                pos.add(point(-hm_1_5 - r_3_0, +vm_0_5 + r));
                pos.add(point(-hm_0_5 - r, +vm_0_5 + r));
                pos.add(point(+hm_0_5 + r, +vm_0_5 + r));
                pos.add(point(+hm_1_5 + r_3_0, +vm_0_5 + r));
                break;
            case DOT_8_7:
                pos.add(point(-hm_0_5 - r, -vm_1_5 - r_3_0));
                pos.add(point(+hm_0_5 + r, -vm_1_5 - r_3_0));
                pos.add(point(-hm_0_5 - r, -vm_0_5 - r));
                pos.add(point(+hm_0_5 + r, -vm_0_5 - r));
                pos.add(point(-hm_0_5 - r, +vm_0_5 + r));
                pos.add(point(+hm_0_5 + r, +vm_0_5 + r));
                pos.add(point(-hm_0_5 - r, +vm_1_5 + r_3_0));
                pos.add(point(+hm_0_5 + r, +vm_1_5 + r_3_0));
                break;
            case DOT_9_1:
                pos.add(point(-hm - r_2_0, -vm - r_2_0));
                pos.add(point(0, -vm - r_2_0));
                pos.add(point(+hm + r_2_0, -vm - r_2_0));
                pos.add(point(-hm - r_2_0, 0));
                pos.add(point(0, 0));
                pos.add(point(+hm + r_2_0, 0));
                pos.add(point(-hm - r_2_0, +vm + r_2_0));
                pos.add(point(0, +vm + r_2_0));
                pos.add(point(+hm + r_2_0, +vm + r_2_0));
                break;
            case DOT_9_2:
                pos.add(point(0, -a_2_0 - c_2_0));
                pos.add(point(-hm_0_5 - r, -a - c));
                pos.add(point(+hm_0_5 + r, -a - c));
                pos.add(point(-b_2_0, 0));
                pos.add(point(0, 0));
                pos.add(point(+b_2_0, 0));
                pos.add(point(-hm_0_5 - r, +a + c));
                pos.add(point(+hm_0_5 + r, +a + c));
                pos.add(point(0, +a_2_0 + c_2_0));
                break;
            case DOT_9_3:
                pos.add(point(0, -a_2_0));
                pos.add(point(-a, -a));
                pos.add(point(+a, -a));
                pos.add(point(-a_2_0, 0));
                pos.add(point(0, 0));
                pos.add(point(+a_2_0, 0));
                pos.add(point(-a, +a));
                pos.add(point(+a, +a));
                pos.add(point(0, +a_2_0));
                break;
            case Custom:
                for (PointF po : bmb.getCustomPiecePlacePositions()) pos.add(point(po.x, po.y));
                break;
            default:
                throw new RuntimeException("Unknown piece-place-enum!");
        }

        for (PointF po : pos) positions.add(new RectF(po.x + parentSize.x / 2 - r,
                po.y + parentSize.y / 2 - r, r_2_0, r_2_0));

        return positions;
    }

    public static ArrayList<RectF> getHamPositions(BoomMenuButton bmb, Point parentSize) {

        ArrayList<RectF> positions = new ArrayList<>();
        ArrayList<PointF> pos = new ArrayList<>();

        int pn = bmb.getPiecePlaceEnum().pieceNumber();
        int pn_0_5 = pn / 2;

        float w = bmb.getHamWidth();
        float w_0_5= w / 2;
        float h = bmb.getHamHeight();
        float h_0_5 = h / 2;

        float vm = bmb.getPieceVerticalMargin();
        float vm_0_5 = vm / 2;

        if (!bmb.getPiecePlaceEnum().equals(PiecePlaceEnum.Custom)) {
            if (pn % 2 == 0) {
                for (int i = pn_0_5 - 1; i >= 0; i--)
                    pos.add(point(0, -h_0_5 - vm_0_5 - i * (h + vm)));
                for (int i = 0; i < pn_0_5; i++)
                    pos.add(point(0, +h_0_5 + vm_0_5 + i * (h + vm)));
            } else {
                for (int i = pn_0_5 - 1; i >= 0; i--)
                    pos.add(point(0, -h - vm - i * (h + vm)));
                pos.add(point(0, 0));
                for (int i = 0; i < pn_0_5; i++)
                    pos.add(point(0, +h + vm + i * (h + vm)));
            }
        } else {
            pos = bmb.getCustomPiecePlacePositions();
        }

        for (PointF po : pos) positions.add(new RectF(po.x + parentSize.x / 2 - w_0_5,
                po.y + parentSize.y / 2 - h_0_5, w, h));

        return positions;
    }

    public static ArrayList<RectF> getShareDotPositions(BoomMenuButton bmb,
                                                        Point parentSize,
                                                        int n) {
        ArrayList<RectF> positions = new ArrayList<>();

        float r = bmb.getDotRadius();
        float h = (float) (bmb.getShareLineLength() * sqrt(3) / 3);
        float h_0_5 = h / 2;
        float l_0_5 = bmb.getShareLineLength() / 2;
        for (int i = 0; i < n; i++) {
            switch (i % 3) {
                case 0:
                    positions.add(new RectF(h_0_5, -l_0_5, r + r, r + r));
                    break;
                case 1:
                    positions.add(new RectF(-h, 0, r + r, r + r));
                    break;
                case 2:
                    positions.add(new RectF(h_0_5, +l_0_5, r + r, r + r));
                    break;
            }
        }
        Collections.sort(positions, new Comparator<RectF>() {
            @Override
            public int compare(RectF o1, RectF o2) {
                if (o1.top < o2.top) return -1;
                else if (o1.top > o2.top) return 1;
                else return 0;
            }
        });
        for (int i = 0; i < positions.size(); i++) {
            positions.get(i).left += parentSize.x / 2 - r;
            positions.get(i).top += parentSize.y / 2 - r;
        }
        return positions;
    }

    public static BoomPiece createPiece(BoomMenuButton bmb, BoomButtonBuilder builder) {
        switch (bmb.getButtonEnum()) {
            case SimpleCircle:
            case TextInsideCircle:
            case TextOutsideCircle:
                return createDot(bmb.getContext(), builder, bmb.getPieceCornerRadius());
            case Ham:
                return createHam(bmb.getContext(), builder, bmb.getPieceCornerRadius());
        }
        throw new RuntimeException("Unknown button-enum!");
    }

    private static Dot createDot(Context context, BoomButtonBuilder builder, float pieceCornerRadius) {
        Dot dot = new Dot(context);
        builder.piece(dot);
        dot.init(builder.pieceColor(context), pieceCornerRadius);
        return dot;
    }

    private static Ham createHam(Context context, BoomButtonBuilder builder, float pieceCornerRadius) {
        Ham ham = new Ham(context);
        builder.piece(ham);
        ham.init(builder.pieceColor(context), pieceCornerRadius);
        return ham;
    }

    private static PointF point(float x, float y) {
        return new PointF(x, y);
    }

    private static PointF point(int x, int y) { return new PointF(x, y); }
}
