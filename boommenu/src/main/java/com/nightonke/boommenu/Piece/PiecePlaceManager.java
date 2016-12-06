package com.nightonke.boommenu.Piece;

import android.content.Context;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Weiping Huang at 23:50 on 16/11/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class PiecePlaceManager {

    public static ArrayList<Point> getDotPositions(PiecePlaceEnum piecePlaceEnum,
                                            Point parentSize,
                                            int dotRadius,
                                            int dotHorizontalMargin,
                                            int dotVerticalMargin,
                                            int dotInclinedMargin) {
        ArrayList<Point> positions = new ArrayList<>();
        float a, b, c, e;
        float r = dotRadius, hm = dotHorizontalMargin, vm = dotVerticalMargin, im = dotInclinedMargin;
        switch (piecePlaceEnum) {
            case DOT_1:
                positions.add(point(0, 0));
                break;
            case DOT_2_1:
                positions.add(point(-hm / 2 - r, 0));
                positions.add(point(hm / 2 + r, 0));
                break;
            case DOT_2_2:
                positions.add(point(0, -vm / 2 - r));
                positions.add(point(0, vm / 2 + r));
                break;
            case DOT_3_1:
                positions.add(point(-hm - 2 * r, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + 2 * r, 0));
                break;
            case DOT_3_2:
                positions.add(point(0, -hm - 2 * r));
                positions.add(point(0, 0));
                positions.add(point(0, hm + 2 * r));
                break;
            case DOT_3_3:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-b, -a));
                positions.add(point(b, -a));
                positions.add(point(0, c));
                break;
            case DOT_3_4:
                b = hm / 2f + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(0, -c));
                positions.add(point(-b, a));
                positions.add(point(b, a));
                break;
            case DOT_4_1:
                positions.add(point(-hm / 2 - r, -vm / 2 - r));
                positions.add(point(hm / 2 + r, -vm / 2 - r));
                positions.add(point(-hm / 2 - r, vm / 2 + r));
                positions.add(point(hm / 2 + r, vm / 2 + r));
                break;
            case DOT_4_2:
                a = (float) ((2 * r + im) / Math.sqrt(2));
                positions.add(point(0, -a));
                positions.add(point(a, 0));
                positions.add(point(0, a));
                positions.add(point(-a, 0));
                break;
            case DOT_5_1:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-2 * b, -a));
                positions.add(point(0, -a));
                positions.add(point(2 * b, -a));
                positions.add(point(-hm / 2 - r, c));
                positions.add(point(hm / 2 + r, c));
                break;
            case DOT_5_2:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-hm / 2 - r, -c));
                positions.add(point(hm / 2 + r, -c));
                positions.add(point(-2 * b, a));
                positions.add(point(0, a));
                positions.add(point(2 * b, a));
                break;
            case DOT_5_3:
                positions.add(point(0, 0));
                positions.add(point(0, -vm - 2 * r));
                positions.add(point(hm + 2 * r, 0));
                positions.add(point(0, vm + 2 * r));
                positions.add(point(-hm - 2 * r, 0));
                break;
            case DOT_5_4:
                a = (float) ((2 * r + im) / Math.sqrt(2));
                positions.add(point(0, 0));
                positions.add(point(a, -a));
                positions.add(point(a, a));
                positions.add(point(-a, a));
                positions.add(point(-a, -a));
                break;
            case DOT_6_1:
                positions.add(point(-hm - 2 * r, -vm / 2 - r));
                positions.add(point(0, -vm / 2 - r));
                positions.add(point(hm + 2 * r, -vm / 2 - r));
                positions.add(point(-hm - 2 * r, vm / 2 + r));
                positions.add(point(0, vm / 2 + r));
                positions.add(point(hm + 2 * r, vm / 2 + r));
                break;
            case DOT_6_2:
                positions.add(point(-hm / 2 - r, -vm - 2 * r));
                positions.add(point(-hm / 2 - r, 0));
                positions.add(point(-hm / 2 - r, vm + 2 * r));
                positions.add(point(hm / 2 + r, -vm - 2 * r));
                positions.add(point(hm / 2 + r, 0));
                positions.add(point(hm / 2 + r, vm + 2 * r));
                break;
            case DOT_6_3:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-b, -a - c));
                positions.add(point(b, -a -c));
                positions.add(point(2 * b, 0));
                positions.add(point(b, a + c));
                positions.add(point(-b, a + c));
                positions.add(point(-2 * b, 0));
                break;
            case DOT_6_4:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(0, -2 * b));
                positions.add(point(a + c, -b));
                positions.add(point(a + c, b));
                positions.add(point(0, 2 * b));
                positions.add(point(-a - c, b));
                positions.add(point(-a - c, -b));
                break;
            case DOT_6_5:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                e = c - a;
                positions.add(point(-2 * b, -a - c + e));
                positions.add(point(0, -a - c + e));
                positions.add(point(2 * b, -a - c + e));
                positions.add(point(-hm / 2 - r, e));
                positions.add(point(hm / 2 + r, e));
                positions.add(point(0, a + c + e));
                break;
            case DOT_6_6:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                e = c - a;
                positions.add(point(0, -a - c - e));
                positions.add(point(-hm / 2 - r, -e));
                positions.add(point(hm / 2 + r, -e));
                positions.add(point(-2 * b, a + c - e));
                positions.add(point(0, a + c - e));
                positions.add(point(2 * b, a + c - e));
                break;
            case DOT_7_1:
                positions.add(point(-hm - 2 * r, -vm - 2 * r));
                positions.add(point(0, -vm - 2 * r));
                positions.add(point(hm + 2 * r, -vm - 2 * r));
                positions.add(point(-hm - 2 * r, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + 2 * r, 0));
                positions.add(point(0, vm + 2 * r));
                break;
            case DOT_7_2:
                positions.add(point(0, -vm - 2 * r));
                positions.add(point(-hm - 2 * r, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + 2 * r, 0));
                positions.add(point(-hm - 2 * r, vm + 2 * r));
                positions.add(point(0, vm + 2 * r));
                positions.add(point(hm + 2 * r, vm + 2 * r));
                break;
            case DOT_7_3:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(0, 0));
                positions.add(point(-b, -a - c));
                positions.add(point(b, -a - c));
                positions.add(point(2 * b, 0));
                positions.add(point(b, a + c));
                positions.add(point(-b, a + c));
                positions.add(point(-2 * b, 0));
                break;
            case DOT_7_4:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(0, 0));
                positions.add(point(0, -2 * b));
                positions.add(point(a + c, -b));
                positions.add(point(a + c, b));
                positions.add(point(0, 2 * b));
                positions.add(point(-a - c, b));
                positions.add(point(-a - c, -b));
                break;
            case DOT_7_5:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-3 * b, -a));
                positions.add(point(-b, -a));
                positions.add(point(b, -a));
                positions.add(point(3 * b, -a));
                positions.add(point(-2 * b, c));
                positions.add(point(0, c));
                positions.add(point(2 * b, c));
                break;
            case DOT_7_6:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-2 * b, -c));
                positions.add(point(0, -c));
                positions.add(point(2 * b, -c));
                positions.add(point(-3 * b, a));
                positions.add(point(-b, a));
                positions.add(point(b, a));
                positions.add(point(3 * b, a));
                break;
            case DOT_8_1:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-2 * b, -a - c));
                positions.add(point(0, -a - c));
                positions.add(point(2 * b, -a - c));
                positions.add(point(-hm / 2 - r, 0));
                positions.add(point(hm / 2 + r, 0));
                positions.add(point(-2 * b, a + c));
                positions.add(point(0, a + c));
                positions.add(point(2 * b, a + c));
                break;
            case DOT_8_2:
                b = vm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-a - c, -2 * b));
                positions.add(point(-a - c, 0));
                positions.add(point(-a - c, 2 * b));
                positions.add(point(0, -vm / 2 - r));
                positions.add(point(0, vm / 2 + r));
                positions.add(point(a + c, -2 * b));
                positions.add(point(a + c, 0));
                positions.add(point(a + c, 2 * b));
                break;
            case DOT_8_3:
                positions.add(point(-hm - 2 * r, -vm - 2 * r));
                positions.add(point(0, -vm - 2 * r));
                positions.add(point(hm + 2 * r, -vm - 2 * r));
                positions.add(point(-hm - 2 * r, 0));
                positions.add(point(hm + 2 * r, 0));
                positions.add(point(-hm - 2 * r, vm + 2 * r));
                positions.add(point(0, vm + 2 * r));
                positions.add(point(hm + 2 * r, vm + 2 * r));
                break;
            case DOT_8_4:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(0, -2 * a - 2 * c));
                positions.add(point(-hm / 2 - r, -a - c));
                positions.add(point(hm / 2 + r, -a - c));
                positions.add(point(-2 * b, 0));
                positions.add(point(2 * b, 0));
                positions.add(point(-hm / 2 - r, a + c));
                positions.add(point(hm / 2 + r, a + c));
                positions.add(point(0, 2 * a + 2 * c));
                break;
            case DOT_8_5:
                a = (float) ((2 * r + im) / Math.sqrt(2));
                positions.add(point(0, -2 * a));
                positions.add(point(a, -a));
                positions.add(point(2 * a, 0));
                positions.add(point(a, a));
                positions.add(point(0, 2 * a));
                positions.add(point(-a, a));
                positions.add(point(-2 * a, 0));
                positions.add(point(-a, -a));
                break;
            case DOT_8_6:
                positions.add(point(-hm * 3 / 2 - 3 * r, -vm / 2 - r));
                positions.add(point(-hm / 2 - r, -vm / 2 - r));
                positions.add(point(hm / 2 + r, -vm / 2 - r));
                positions.add(point(hm * 3 / 2 + 3 * r, -vm / 2 - r));
                positions.add(point(-hm * 3 / 2 - 3 * r, vm / 2 + r));
                positions.add(point(-hm / 2 - r, vm / 2 + r));
                positions.add(point(hm / 2 + r, vm / 2 + r));
                positions.add(point(hm * 3 / 2 + 3 * r, vm / 2 + r));
                break;
            case DOT_8_7:
                positions.add(point(-hm / 2 - r, -vm * 3 / 2 - 3 * r));
                positions.add(point(hm / 2 + r, -vm * 3 / 2 - 3 * r));
                positions.add(point(-hm / 2 - r, -vm / 2 - r));
                positions.add(point(hm / 2 + r, -vm / 2 - r));
                positions.add(point(-hm / 2 - r, vm / 2 + r));
                positions.add(point(hm / 2 + r, vm / 2 + r));
                positions.add(point(-hm / 2 - r, vm * 3 / 2 + 3 * r));
                positions.add(point(hm / 2 + r, vm * 3 / 2 + 3 * r));
                break;
            case DOT_9_1:
                positions.add(point(-hm - 2 * r, -vm - 2 * r));
                positions.add(point(0, -vm - 2 * r));
                positions.add(point(hm + 2 * r, -vm - 2 * r));
                positions.add(point(-hm - 2 * r, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + 2 * r, 0));
                positions.add(point(-hm - 2 * r, vm + 2 * r));
                positions.add(point(0, vm + 2 * r));
                positions.add(point(hm + 2 * r, vm + 2 * r));
                break;
            case DOT_9_2:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(0, -2 * a - 2 * c));
                positions.add(point(-hm / 2 - r, -a - c));
                positions.add(point(hm / 2 + r, -a - c));
                positions.add(point(-2 * b, 0));
                positions.add(point(0, 0));
                positions.add(point(2 * b, 0));
                positions.add(point(-hm / 2 - r, a + c));
                positions.add(point(hm / 2 + r, a + c));
                positions.add(point(0, 2 * a + 2 * c));
                break;
            case DOT_9_3:
                a = (float) ((2 * r + im) / Math.sqrt(2));
                positions.add(point(0, -2 * a));
                positions.add(point(a, -a));
                positions.add(point(2 * a, 0));
                positions.add(point(a, a));
                positions.add(point(0, 0));
                positions.add(point(0, 2 * a));
                positions.add(point(-a, a));
                positions.add(point(-2 * a, 0));
                positions.add(point(-a, -a));
                break;
            default:
                throw new RuntimeException("Unknown piece-place-enum!");
        }
        for (Point point : positions) point.offset(parentSize.x / 2 - dotRadius, parentSize.y / 2 - dotRadius);
        return positions;
    }

    public static ArrayList<Point> getHamPositions(PiecePlaceEnum piecePlaceEnum,
                                            Point parentSize,
                                            int hamWidth,
                                            int hamHeight,
                                            int hamVerticalMargin) {
        ArrayList<Point> positions = new ArrayList<>();
        float w = hamWidth, h = hamHeight, vm = hamVerticalMargin;
        int half = piecePlaceEnum.pieceNumber() / 2;
        if (piecePlaceEnum.pieceNumber() % 2 == 0) {
            for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h / 2 - vm / 2 - i * (h + vm)));
            for (int i = 0; i < h; i++) positions.add(point(0, h / 2 + vm / 2 + i * (h + vm)));
        } else {
            for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h - vm - i * (h + vm)));
            positions.add(point(0, 0));
            for (int i = 0; i < h; i++) positions.add(point(0, h + vm + i * (h + vm)));
        }
        for (Point point : positions) point.offset((int) (parentSize.x / 2 - w / 2), (int) (parentSize.y / 2 - h / 2));
        return positions;
    }

    public static ArrayList<Point> getShareDotPositions(Point parentSize,
                                                        int dotRadius,
                                                        int dotNumber,
                                                        int shareLineLength) {
        ArrayList<Point> positions = new ArrayList<>();
        float h = (float) (shareLineLength * Math.sqrt(3) / 3);
        for (int i = 0; i < dotNumber; i++) {
            switch (i % 3) {
                case 0: positions.add(point(h / 2, -shareLineLength / 2)); break;
                case 1: positions.add(point(-h, 0)); break;
                case 2: positions.add(point(h / 2, shareLineLength / 2)); break;
            }
        }
        Collections.sort(positions, new Comparator<Point>() {
            @Override
            public int compare(Point lhs, Point rhs) {
                return ((Integer)(lhs.y)).compareTo(rhs.y);
            }
        });
        for (Point point : positions) point.offset(parentSize.x / 2 - dotRadius, parentSize.y / 2 - dotRadius);
        return positions;
    }

    public static BoomPiece createPiece(Context context,
                                 PiecePlaceEnum piecePlaceEnum,
                                 int color) {
        switch (piecePlaceEnum) {
            case DOT_1:
            case DOT_2_1:
            case DOT_2_2:
            case DOT_3_1:
            case DOT_3_2:
            case DOT_3_3:
            case DOT_3_4:
            case DOT_4_1:
            case DOT_4_2:
            case DOT_5_1:
            case DOT_5_2:
            case DOT_5_3:
            case DOT_5_4:
            case DOT_6_1:
            case DOT_6_2:
            case DOT_6_3:
            case DOT_6_4:
            case DOT_6_5:
            case DOT_6_6:
            case DOT_7_1:
            case DOT_7_2:
            case DOT_7_3:
            case DOT_7_4:
            case DOT_7_5:
            case DOT_7_6:
            case DOT_8_1:
            case DOT_8_2:
            case DOT_8_3:
            case DOT_8_4:
            case DOT_8_5:
            case DOT_8_6:
            case DOT_8_7:
            case DOT_9_1:
            case DOT_9_2:
            case DOT_9_3:
            case Share:
                return createDot(context, color);
            case HAM_1:
            case HAM_2:
            case HAM_3:
            case HAM_4:
            case HAM_5:
            case HAM_6:
                return createHam(context, color);
        }
        throw new RuntimeException("Unknown button-enum!");
    }

    private static Dot createDot(Context context, int color) {
        Dot dot = new Dot(context);
        dot.init(color);
        return dot;
    }

    private static Ham createHam(Context context, int color) {
        Ham ham = new Ham(context);
        ham.init(color);
        return ham;
    }

    private static Point point(float x, float y) {
        return new Point((int) x, (int) y);
    }

    private static Point point(double x, double y) {
        return new Point((int) x, (int) y);
    }

    private static Point point(int x, int y) {
        return new Point(x, y);
    }

    private static PiecePlaceManager ourInstance = new PiecePlaceManager();

    public static PiecePlaceManager getInstance() {
        return ourInstance;
    }

    private PiecePlaceManager() {
    }
}
