package com.nightonke.boommenu.BoomButtons;

import android.graphics.Point;

import com.nightonke.boommenu.Util;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 20:26 on 16/11/19
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class ButtonPlaceManager {

    public static ArrayList<Point> getCircleButtonPositions(ButtonPlaceEnum placeEnum,
                                                            ButtonPlaceAlignmentEnum alignmentEnum,
                                                            Point parentSize,
                                                            float radius,
                                                            int buttonNumber,
                                                            float buttonHorizontalMargin,
                                                            float buttonVerticalMargin,
                                                            float buttonInclinedMargin,
                                                            float buttonTopMargin,
                                                            float buttonBottomMargin,
                                                            float buttonLeftMargin,
                                                            float buttonRightMargin) {

        ArrayList<Point> positions = new ArrayList<>(buttonNumber);
        float a, b, c, e, r = radius;
        float hm = buttonHorizontalMargin, vm = buttonVerticalMargin, im = buttonInclinedMargin;
        int h = buttonNumber / 2;

        switch (placeEnum) {
            case Horizontal:
                if (buttonNumber % 2 == 0) {
                    for (int i = h - 1; i >= 0; i--) positions.add(point(-r - hm / 2 - i * (2 * r + hm), 0));
                    for (int i = 0; i < h; i++) positions.add(point(r + hm / 2 + i * (2 * r + hm), 0));
                } else {
                    for (int i = h - 1; i >= 0; i--) positions.add(point(-2 * r - hm - i * (2 * r + hm), 0));
                    positions.add(point(0, 0));
                    for (int i = 0; i < h; i++) positions.add(point(2 * r + hm + i * (2 * r + hm), 0));
                }
                break;
            case Vertical:
                if (buttonNumber % 2 == 0) {
                    for (int i = h - 1; i >= 0; i--) positions.add(point(0, -r - vm / 2 - i * (2 * r + vm)));
                    for (int i = 0; i < h; i++) positions.add(point(0, r + vm / 2 + i * (2 * r + vm)));
                } else {
                    for (int i = h - 1; i >= 0; i--) positions.add(point(0, -2 * r - vm - i * (2 * r + vm)));
                    positions.add(point(0, 0));
                    for (int i = 0; i < h; i++) positions.add(point(0, 2 * r + vm + i * (2 * r + vm)));
                }
                break;
            case SC_1:
                positions.add(point(0, 0));
                break;
            case SC_2_1:
                positions.add(point(-hm / 2 - r, 0));
                positions.add(point(hm / 2 + r, 0));
                break;
            case SC_2_2:
                positions.add(point(0, -vm / 2 - r));
                positions.add(point(0, vm / 2 + r));
                break;
            case SC_3_1:
                positions.add(point(-hm - 2 * r, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + 2 * r, 0));
                break;
            case SC_3_2:
                positions.add(point(0, -vm - 2 * r));
                positions.add(point(0, 0));
                positions.add(point(0, vm + 2 * r));
                break;
            case SC_3_3:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-b, -a));
                positions.add(point(b, -a));
                positions.add(point(0, c));
                break;
            case SC_3_4:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(0, -c));
                positions.add(point(-b, a));
                positions.add(point(b, a));
                break;
            case SC_4_1:
                positions.add(point(-hm / 2 - r, -vm / 2 - r));
                positions.add(point(hm / 2 + r, -vm / 2 - r));
                positions.add(point(-hm / 2 - r, vm / 2 + r));
                positions.add(point(hm / 2 + r, vm / 2 + r));
                break;
            case SC_4_2:
                a = (float) ((2 * r + im) / Math.sqrt(2));
                positions.add(point(0, -a));
                positions.add(point(a, 0));
                positions.add(point(0, a));
                positions.add(point(-a, 0));
                break;
            case SC_5_1:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-2 * b, -c));
                positions.add(point(0, -c));
                positions.add(point(2 * b, -c));
                positions.add(point(-hm / 2 - r, a));
                positions.add(point(hm / 2 + r, a));
                break;
            case SC_5_2:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(hm / 2 + r, -a));
                positions.add(point(-hm / 2 - r, -a));
                positions.add(point(2 * b, c));
                positions.add(point(0, c));
                positions.add(point(-2 * b, c));
                break;
            case SC_5_3:
                positions.add(point(0, 0));
                positions.add(point(0, -vm - 2 * r));
                positions.add(point(hm + 2 * r, 0));
                positions.add(point(0, vm + 2 * r));
                positions.add(point(-hm - 2 * r, 0));
                break;
            case SC_5_4:
                a = (float) ((2 * r + im) / Math.sqrt(2));
                positions.add(point(0, 0));
                positions.add(point(a, -a));
                positions.add(point(a, a));
                positions.add(point(-a, a));
                positions.add(point(-a, -a));
                break;
            case SC_6_1:
                positions.add(point(-hm - 2 * r, -vm / 2 - r));
                positions.add(point(0, -vm / 2 - r));
                positions.add(point(hm + 2 * r, -vm / 2 - r));
                positions.add(point(-hm - 2 * r, vm / 2 + r));
                positions.add(point(0, vm / 2 + r));
                positions.add(point(hm + 2 * r, vm / 2 + r));
                break;
            case SC_6_2:
                positions.add(point(-hm / 2 - r, -vm - 2 * r));
                positions.add(point(-hm / 2 - r, 0));
                positions.add(point(-hm / 2 - r, vm + 2 * r));
                positions.add(point(hm / 2 + r, -vm - 2 * r));
                positions.add(point(hm / 2 + r, 0));
                positions.add(point(hm / 2 + r, vm + 2 * r));
                break;
            case SC_6_3:
                b = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(-b, -a - c));
                positions.add(point(b, -a - c));
                positions.add(point(2 * b, 0));
                positions.add(point(b, a + c));
                positions.add(point(-b, a + c));
                positions.add(point(-2 * b, 0));
                break;
            case SC_6_4:
                b  = hm / 2 + r;
                c = (float) (b / (Math.sqrt(3) / 2));
                a = c / 2;
                positions.add(point(0, -2 * b));
                positions.add(point(a + c, -b));
                positions.add(point(a + c, b));
                positions.add(point(0, 2 * b));
                positions.add(point(-a - c, b));
                positions.add(point(-a - c, -b));
                break;
            case SC_6_5:
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
            case SC_6_6:
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
            case SC_7_1:
                positions.add(point(-hm - 2 * r, -vm - 2 * r));
                positions.add(point(0, -vm - 2 * r));
                positions.add(point(hm + 2 * r, -vm - 2 * r));
                positions.add(point(-hm - 2 * r, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + 2 * r, 0));
                positions.add(point(0, vm + 2 * r));
                break;
            case SC_7_2:
                positions.add(point(0, -vm - 2 * r));
                positions.add(point(-hm - 2 * r, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + 2 * r, 0));
                positions.add(point(-hm - 2 * r, vm + 2 * r));
                positions.add(point(0, vm + 2 * r));
                positions.add(point(hm + 2 * r, vm + 2 * r));
                break;
            case SC_7_3:
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
            case SC_7_4:
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
            case SC_7_5:
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
            case SC_7_6:
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
            case SC_8_1:
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
            case SC_8_2:
                b = hm / 2 + r;
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
            case SC_8_3:
                positions.add(point(-hm - 2 * r, -vm - 2 * r));
                positions.add(point(0, -vm - 2 * r));
                positions.add(point(hm + 2 * r, -vm - 2 * r));
                positions.add(point(-hm - 2 * r, 0));
                positions.add(point(hm + 2 * r, 0));
                positions.add(point(-hm - 2 * r, vm + 2 * r));
                positions.add(point(0, vm + 2 * r));
                positions.add(point(hm + 2 * r, vm + 2 * r));
                break;
            case SC_8_4:
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
            case SC_8_5:
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
            case SC_8_6:
                positions.add(point(-hm * 3 / 2 - 3 * r, -vm / 2 - r));
                positions.add(point(-hm / 2 - r, -vm / 2 - r));
                positions.add(point(hm / 2 + r, -vm / 2 - r));
                positions.add(point(hm * 3 / 2 + 3 * r, -vm / 2 - r));
                positions.add(point(-hm * 3 / 2 - 3 * r, vm / 2 + r));
                positions.add(point(-hm / 2 - r, vm / 2 + r));
                positions.add(point(hm / 2 + r, vm / 2 + r));
                positions.add(point(hm * 3 / 2 + 3 * r, vm / 2 + r));
                break;
            case SC_8_7:
                positions.add(point(-hm / 2 - r, -vm * 3 / 2 - 3 * r));
                positions.add(point(hm / 2 + r, -vm * 3 / 2 - 3 * r));
                positions.add(point(-hm / 2 - r, -vm / 2 - r));
                positions.add(point(hm / 2 + r, -vm / 2 - r));
                positions.add(point(-hm / 2 - r, vm / 2 + r));
                positions.add(point(hm / 2 + r, vm / 2 + r));
                positions.add(point(-hm / 2 - r, vm * 3 / 2 + 3 * r));
                positions.add(point(hm / 2 + r, vm * 3 / 2 + 3 * r));
                break;
            case SC_9_1:
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
            case SC_9_2:
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
            case SC_9_3:
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
        }

        calculatePositionsInParent(positions, parentSize);

        calculateOffset(
                positions,
                alignmentEnum,
                parentSize,
                radius * 2,
                radius * 2,
                buttonTopMargin,
                buttonBottomMargin,
                buttonLeftMargin,
                buttonRightMargin);

        return positions;
    }

    public static ArrayList<Point> getCircleButtonPositions(ButtonPlaceEnum placeEnum,
                                                            ButtonPlaceAlignmentEnum alignmentEnum,
                                                            Point parentSize,
                                                            float buttonWidth,
                                                            float buttonHeight,
                                                            int buttonNumber,
                                                            float buttonHorizontalMargin,
                                                            float buttonVerticalMargin,
                                                            float buttonInclinedMargin,
                                                            float buttonTopMargin,
                                                            float buttonBottomMargin,
                                                            float buttonLeftMargin,
                                                            float buttonRightMargin) {

        ArrayList<Point> positions = new ArrayList<>(buttonNumber);
        float a, b, c, e, w = buttonWidth, h = buttonHeight;
        float hm = buttonHorizontalMargin, vm = buttonVerticalMargin, im = buttonInclinedMargin;
        int half = buttonNumber / 2;

        switch (placeEnum) {
            case Horizontal:
                if (buttonNumber % 2 == 0) {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(-w / 2 - hm / 2 - i * (w + hm), 0));
                    for (int i = 0; i < half; i++) positions.add(point(w / 2  + hm / 2 + i * (w + hm), 0));
                } else {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(-w - hm - i * (w + hm), 0));
                    positions.add(point(0, 0));
                    for (int i = 0; i < half; i++) positions.add(point(w + hm + i * (w + hm), 0));
                }
                break;
            case Vertical:
                if (buttonNumber % 2 == 0) {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h / 2 - vm / 2 - i * (h + vm)));
                    for (int i = 0; i < half; i++) positions.add(point(0, h / 2 + vm / 2 + i * (h + vm)));
                } else {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h - vm - i * (h + vm)));
                    positions.add(point(0, 0));
                    for (int i = 0; i < half; i++) positions.add(point(0, h + vm + i * (h + vm)));
                }
                break;
            case SC_1:
                positions.add(point(0, 0));
                break;
            case SC_2_1:
                positions.add(point(-hm / 2 - w / 2, 0));
                positions.add(point(hm / 2 + w / 2, 0));
                break;
            case SC_2_2:
                positions.add(point(0, -vm / 2 - h / 2));
                positions.add(point(0, vm / 2 + h / 2));
                break;
            case SC_3_1:
                positions.add(point(-hm - w, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + w, 0));
                break;
            case SC_3_2:
                positions.add(point(0, -vm - h));
                positions.add(point(0, 0));
                positions.add(point(0, vm + h));
                break;
            case SC_3_3:
                positions.add(point(-hm / 2 - w / 2, -vm / 2 - h / 2));
                positions.add(point(hm / 2 + w / 2, -vm / 2 - h / 2));
                positions.add(point(0, vm / 2 + h / 2));
                break;
            case SC_3_4:
                positions.add(point(0, -vm / 2 - h / 2));
                positions.add(point(-hm / 2 - w / 2, vm / 2 + h / 2));
                positions.add(point(hm / 2 + w / 2, vm / 2 + h / 2));
                break;
            case SC_4_1:
                positions.add(point(-hm / 2 - w / 2, -vm / 2 - h / 2));
                positions.add(point(hm / 2 + w / 2, -vm / 2 - h / 2));
                positions.add(point(-hm / 2 - w / 2, vm / 2 + h / 2));
                positions.add(point(hm / 2 + w / 2, vm / 2 + h / 2));
                break;
            case SC_4_2:
                positions.add(point(0, -vm / 2 - h / 2));
                positions.add(point(hm + w, 0));
                positions.add(point(0, vm / 2 + h / 2));
                positions.add(point(-hm - w, 0));
                break;
            case SC_5_1:
                positions.add(point(-hm - w, -vm / 2 - h / 2));
                positions.add(point(0, -vm / 2 - h / 2));
                positions.add(point(hm + w, -vm / 2 - h / 2));
                positions.add(point(-hm / 2 - w / 2, vm / 2 + h / 2));
                positions.add(point(hm / 2 + w / 2, vm / 2 + h / 2));
                break;
            case SC_5_2:
                positions.add(point(-hm / 2 - w / 2, -vm / 2 - h / 2));
                positions.add(point(hm / 2 + w / 2, -vm / 2 - h / 2));
                positions.add(point(-hm - w, vm / 2 + h / 2));
                positions.add(point(0, vm / 2 + h / 2));
                positions.add(point(hm + w, vm / 2 + h / 2));
                break;
            case SC_5_3:
                positions.add(point(0, -vm - h));
                positions.add(point(-hm - w, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + w, 0));
                positions.add(point(0, vm + h));
                break;
            case SC_5_4:
                positions.add(point(-hm - w, -vm / 2 - h / 2));
                positions.add(point(hm + w, -vm / 2 - h / 2));
                positions.add(point(0, 0));
                positions.add(point(-hm - w, vm / 2 + h / 2));
                positions.add(point(hm + w, vm / 2 + h / 2));
                break;
            case SC_6_1:
                positions.add(point(-hm - w, -vm / 2 - h / 2));
                positions.add(point(0, -vm / 2 - h / 2));
                positions.add(point(hm + w, -vm / 2 - h / 2));
                positions.add(point(-hm - w, vm / 2 + h / 2));
                positions.add(point(0, vm / 2 + h / 2));
                positions.add(point(hm + w, vm / 2 + h / 2));
                break;
            case SC_6_2:
                positions.add(point(-hm / 2 - w / 2, -vm - h));
                positions.add(point(-hm / 2 - w / 2, 0));
                positions.add(point(-hm / 2 - w / 2, vm + h));
                positions.add(point(hm / 2 + w / 2, -vm - h));
                positions.add(point(hm / 2 + w / 2, 0));
                positions.add(point(hm / 2 + w / 2, vm + h));
                break;
            case SC_6_3:
                positions.add(point(-hm / 2 - w / 2, -vm - h));
                positions.add(point(hm / 2 + w / 2, -vm - h));
                positions.add(point(-hm - w, 0));
                positions.add(point(hm + w, 0));
                positions.add(point(-hm / 2 - w / 2, vm + h));
                positions.add(point(hm / 2 + w / 2, vm + h));
                break;
            case SC_6_4:
                positions.add(point(0, -vm - h));
                positions.add(point(hm + w, -vm / 2 - h / 2));
                positions.add(point(hm + w, vm / 2 + h / 2));
                positions.add(point(0, vm + h));
                positions.add(point(-hm - w, -vm / 2 - h / 2));
                positions.add(point(-hm - w, vm / 2 + h / 2));
                break;
            case SC_6_5:
                positions.add(point(-hm - w, -vm - h));
                positions.add(point(0, -vm - h));
                positions.add(point(hm + w, -vm - h));
                positions.add(point(-hm / 2 - w / 2, 0));
                positions.add(point(hm / 2 + w / 2, 0));
                positions.add(point(0, vm + h));
                break;
            case SC_6_6:
                positions.add(point(0, -vm - h));
                positions.add(point(-hm / 2 - w / 2, 0));
                positions.add(point(hm / 2 + w / 2, 0));
                positions.add(point(-hm - w, vm + h));
                positions.add(point(0, vm + h));
                positions.add(point(hm + w, vm + h));
                break;
            case SC_7_1:
                positions.add(point(-hm - w, -vm - h));
                positions.add(point(0, -vm - h));
                positions.add(point(hm + w, -vm - h));
                positions.add(point(-hm - w, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + w, 0));
                positions.add(point(0, vm + h));
                break;
            case SC_7_2:
                positions.add(point(0, -vm - h));
                positions.add(point(-hm - w, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + w, 0));
                positions.add(point(-hm - w, vm + h));
                positions.add(point(0, vm + h));
                positions.add(point(hm + w, vm + h));
                break;
            case SC_7_3:
                positions.add(point(-hm / 2 - w / 2, -vm - h));
                positions.add(point(hm / 2 + w / 2, -vm - h));
                positions.add(point(-hm - w, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + w, 0));
                positions.add(point(-hm / 2 - w / 2, vm + h));
                positions.add(point(hm / 2 + w / 2, vm + h));
                break;
            case SC_7_4:
                positions.add(point(0, -vm - h));
                positions.add(point(hm + w, -vm / 2 - h / 2));
                positions.add(point(hm + w, vm / 2 + h / 2));
                positions.add(point(0, 0));
                positions.add(point(0, vm + h));
                positions.add(point(-hm - w, -vm / 2 - h / 2));
                positions.add(point(-hm - w, vm / 2 + h / 2));
                break;
            case SC_7_5:
                positions.add(point(-hm * 3 / 2 - w * 3 / 2, -vm / 2 - h / 2));
                positions.add(point(-hm / 2 - w / 2, -vm / 2 - h / 2));
                positions.add(point(hm / 2 + w / 2, -vm / 2 - h / 2));
                positions.add(point(hm * 3 / 2 + w * 3 / 2, -vm / 2 - h / 2));
                positions.add(point(-hm - w, vm / 2 + h / 2));
                positions.add(point(0, vm / 2 + h / 2));
                positions.add(point(hm + w, vm / 2 + h / 2));
                break;
            case SC_7_6:
                positions.add(point(-hm - w, -vm / 2 - h / 2));
                positions.add(point(0, -vm / 2 - h / 2));
                positions.add(point(hm + w, -vm / 2 - h / 2));
                positions.add(point(-hm * 3 / 2 - w * 3 / 2, vm / 2 + h / 2));
                positions.add(point(-hm / 2 - w / 2, vm / 2 + h / 2));
                positions.add(point(hm / 2 + w / 2, vm / 2 + h / 2));
                positions.add(point(hm * 3 / 2 + w * 3 / 2, vm / 2 + h / 2));
                break;
            case SC_8_1:
                positions.add(point(-hm - w, -vm - h));
                positions.add(point(0, -vm - h));
                positions.add(point(hm + w, -vm - h));
                positions.add(point(-hm / 2 - w / 2, 0));
                positions.add(point(hm / 2 + w / 2, 0));
                positions.add(point(-hm - w, vm + h));
                positions.add(point(0, vm + h));
                positions.add(point(hm + w, vm + h));
                break;
            case SC_8_2:
                positions.add(point(-hm - w, -vm - h));
                positions.add(point(-hm - w, 0));
                positions.add(point(-hm - w, vm + h));
                positions.add(point(0, -vm / 2 - h / 2));
                positions.add(point(0, vm / 2 + h / 2));
                positions.add(point(hm + w, -vm - h));
                positions.add(point(hm + w, 0));
                positions.add(point(hm + w, vm + h));
                break;
            case SC_8_3:
                positions.add(point(-hm - w, -vm - h));
                positions.add(point(0, -vm - h));
                positions.add(point(hm + w, -vm - h));
                positions.add(point(-hm - w, 0));
                positions.add(point(hm + w, 0));
                positions.add(point(-hm - w, vm + h));
                positions.add(point(0, vm + h));
                positions.add(point(hm + w, vm + h));
                break;
            case SC_8_4:
                positions.add(point(0, -vm * 2 - h * 2));
                positions.add(point(hm / 2 + w / 2, -vm - h));
                positions.add(point(hm + w, 0));
                positions.add(point(hm / 2 + w / 2, vm + h));
                positions.add(point(0, vm * 2 + h * 2));
                positions.add(point(-hm / 2 - w / 2, vm + h));
                positions.add(point(-hm - w, 0));
                positions.add(point(-hm / 2 - w / 2, -vm - h));
                break;
            case SC_8_5:
                positions.add(point(0, -vm - h));
                positions.add(point(hm + w, -vm / 2 - h / 2));
                positions.add(point(hm * 2 + w * 2, 0));
                positions.add(point(hm + w, vm / 2 + h / 2));
                positions.add(point(0, vm + h));
                positions.add(point(-hm - w, vm / 2 + h / 2));
                positions.add(point(-hm * 2 - w * 2, 0));
                positions.add(point(-hm - w, -vm / 2 - h / 2));
                break;
            case SC_8_6:
                positions.add(point(-hm * 3 / 2 - 3 * w / 2, -vm / 2 - h / 2));
                positions.add(point(-hm / 2 - w / 2, -vm / 2 - h / 2));
                positions.add(point(hm / 2 + w / 2, -vm / 2 - h / 2));
                positions.add(point(hm * 3 / 2 + 3 * w / 2, -vm / 2 - h / 2));
                positions.add(point(-hm * 3 / 2 - 3 * w / 2, vm / 2 + h / 2));
                positions.add(point(-hm / 2 - w / 2, vm / 2 + h / 2));
                positions.add(point(hm / 2 + w / 2, vm / 2 + h / 2));
                positions.add(point(hm * 3 / 2 + 3 * w / 2, vm / 2 + h / 2));
                break;
            case SC_8_7:
                positions.add(point(-hm / 2 - w / 2, -vm * 3 / 2 - 3 * h / 2));
                positions.add(point(hm / 2 + w / 2, -vm * 3 / 2 - 3 * h / 2));
                positions.add(point(-hm / 2 - w / 2, -vm / 2 - h / 2));
                positions.add(point(hm / 2 + w / 2, -vm / 2 - h / 2));
                positions.add(point(-hm / 2 - w / 2, vm / 2 + h / 2));
                positions.add(point(hm / 2 + w / 2, vm / 2 + h / 2));
                positions.add(point(-hm / 2 - w / 2, vm * 3 / 2 + 3 * h / 2));
                positions.add(point(hm / 2 + w / 2, vm * 3 / 2 + 3 * h / 2));
                break;
            case SC_9_1:
                positions.add(point(-hm - w, -vm - h));
                positions.add(point(0, -vm - h));
                positions.add(point(hm + w, -vm - h));
                positions.add(point(-hm - w, 0));
                positions.add(point(0, 0));
                positions.add(point(hm + w, 0));
                positions.add(point(-hm - w, vm + h));
                positions.add(point(0, vm + h));
                positions.add(point(hm + w, vm + h));
                break;
            case SC_9_2:
                positions.add(point(0, -vm * 2 - h * 2));
                positions.add(point(hm / 2 + w / 2, -vm - h));
                positions.add(point(hm + w, 0));
                positions.add(point(hm / 2 + w / 2, vm + h));
                positions.add(point(0, vm * 2 + h * 2));
                positions.add(point(-hm / 2 - w / 2, vm + h));
                positions.add(point(-hm - w, 0));
                positions.add(point(-hm / 2 - w / 2, -vm - h));
                positions.add(point(0, 0));
                break;
            case SC_9_3:
                positions.add(point(0, -vm - h));
                positions.add(point(hm + w, -vm / 2 - h / 2));
                positions.add(point(hm * 2 + w * 2, 0));
                positions.add(point(hm + w, vm / 2 + h / 2));
                positions.add(point(0, vm + h));
                positions.add(point(-hm - w, vm / 2 + h / 2));
                positions.add(point(-hm * 2 - w * 2, 0));
                positions.add(point(-hm - w, -vm / 2 - h / 2));
                positions.add(point(0, 0));
                break;
        }

        switch (placeEnum) {
            case SC_3_3:
                adjustOffset(positions, 0, calculateYOffsetToCenter(hm, vm, w, h));
                break;
            case SC_3_4:
                adjustOffset(positions, 0, -calculateYOffsetToCenter(hm, vm, w, h));
                break;
            case SC_4_2:
            case SC_5_1:
            case SC_5_2:
            case SC_5_3:
            case SC_5_4:
            case SC_6_1:
            case SC_6_2:
            case SC_6_3:
            case SC_6_4:
            case SC_6_5:
            case SC_6_6:
            case SC_7_1:
            case SC_7_2:
            case SC_7_3:
            case SC_7_4:
            case SC_7_5:
            case SC_7_6:
            case SC_8_1:
            case SC_8_2:
            case SC_8_3:
            case SC_8_4:
            case SC_8_5:
            case SC_8_6:
            case SC_8_7:
            case SC_9_1:
            case SC_9_2:
            case SC_9_3:
                adjustOffset(positions, 0, (h - w) / 2);
                break;
        }

        calculatePositionsInParent(positions, parentSize);

        calculateOffset(
                positions,
                alignmentEnum,
                parentSize,
                w,
                h,
                buttonTopMargin,
                buttonBottomMargin,
                buttonLeftMargin,
                buttonRightMargin);

        return positions;
    }

    public static ArrayList<Point> getHamButtonPositions(ButtonPlaceEnum placeEnum,
                                                         ButtonPlaceAlignmentEnum alignmentEnum,
                                                         Point parentSize,
                                                         float buttonWidth,
                                                         float buttonHeight,
                                                         int buttonNumber,
                                                         float buttonHorizontalMargin,
                                                         float buttonVerticalMargin,
                                                         float buttonTopMargin,
                                                         float buttonBottomMargin,
                                                         float buttonLeftMargin,
                                                         float buttonRightMargin,
                                                         float bottomHamButtonTopMargin) {
        ArrayList<Point> positions = new ArrayList<>(buttonNumber);
        float w = buttonWidth, h = buttonHeight;
        float hm = buttonHorizontalMargin, vm = buttonVerticalMargin;
        int half = buttonNumber / 2;

        switch (placeEnum) {
            case Horizontal:
                if (buttonNumber % 2 == 0) {
                    for (int i = half - 1; i >= 0; i--)
                        positions.add(point(-w / 2 - hm / 2 - i * (w + hm), 0));
                    for (int i = 0; i < half; i++)
                        positions.add(point(w / 2 + hm / 2 + i * (w + hm), 0));
                } else {
                    for (int i = half - 1; i >= 0; i--)
                        positions.add(point(-w - hm - i * (w + hm), 0));
                    positions.add(point(0, 0));
                    for (int i = 0; i < half; i++) positions.add(point(w + hm + i * (w + hm), 0));
                }
                break;
            case Vertical:
            case HAM_1:
            case HAM_2:
            case HAM_3:
            case HAM_4:
            case HAM_5:
            case HAM_6:
                if (buttonNumber % 2 == 0) {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h / 2 - vm / 2 - i * (h + vm)));
                    for (int i = 0; i < half; i++) positions.add(point(0, h / 2 + vm / 2 + i * (h + vm)));
                } else {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h - vm - i * (h + vm)));
                    positions.add(point(0, 0));
                    for (int i = 0; i < half; i++) positions.add(point(0, h + vm + i * (h + vm)));
                }
                if (buttonNumber >= 2 && bottomHamButtonTopMargin != Util.dp2px(-1))
                    positions.get(positions.size() - 1).offset(0, (int) (bottomHamButtonTopMargin - vm));
                break;
        }

        calculatePositionsInParent(positions, parentSize);

        calculateOffset(
                positions,
                alignmentEnum,
                parentSize,
                w,
                h,
                buttonTopMargin,
                buttonBottomMargin,
                buttonLeftMargin,
                buttonRightMargin);

        return positions;
    }

    private static void calculatePositionsInParent(ArrayList<Point> positions,
                                                   Point parentSize) {
        for (int i = 0; i < positions.size(); i++) {
            Point point = positions.get(i);
            positions.set(i, new Point(
                    (int) (point.x + parentSize.x / 2.0),
                    (int) (point.y + parentSize.y / 2.0)));
        }
    }

    private static float calculateYOffsetToCenter(float horizontalMargin,
                                                  float verticalMargin,
                                                  float width,
                                                  float height) {
        return (horizontalMargin / 2 + width / 2) * (horizontalMargin / 2 + width / 2) / (verticalMargin + height);
    }

    private static void adjustOffset(ArrayList<Point> position, float x, float y) {
        for (int i = 0; i < position.size(); i++) {
            position.set(i, point(position.get(i).x + x, position.get(i).y + y));
        }
    }

    private static void calculateOffset(ArrayList<Point> positions,
                                        ButtonPlaceAlignmentEnum alignmentEnum,
                                        Point parentSize,
                                        float width,
                                        float height,
                                        float buttonTopMargin,
                                        float buttonBottomMargin,
                                        float buttonLeftMargin,
                                        float buttonRightMargin) {
        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;
        int minWidth = Integer.MAX_VALUE;
        int maxWidth = Integer.MIN_VALUE;
        Point offset = new Point(0, 0);

        for (Point position : positions) {
            maxHeight = Math.max(maxHeight, position.y);
            minHeight = Math.min(minHeight, position.y);
            maxWidth = Math.max(maxWidth, position.x);
            minWidth = Math.min(minWidth, position.x);
        }

        switch (alignmentEnum) {
            case Center:
                break;
            case Top:
                offset.y = (int) (height / 2 + buttonTopMargin - minHeight);
                break;
            case Bottom:
                offset.y = (int) (parentSize.y - height / 2 - maxHeight - buttonBottomMargin);
                break;
            case Left:
                offset.x = (int) (width / 2 + buttonLeftMargin - minWidth);
                break;
            case Right:
                offset.x = (int) (parentSize.x - width / 2 - maxWidth - buttonRightMargin);
                break;
            case TL:
                offset.y = (int) (height / 2 + buttonTopMargin - minHeight);
                offset.x = (int) (width / 2 + buttonLeftMargin - minWidth);
                break;
            case TR:
                offset.y = (int) (height / 2 + buttonTopMargin - minHeight);
                offset.x = (int) (parentSize.x - width / 2 - maxWidth - buttonRightMargin);
                break;
            case BL:
                offset.y = (int) (parentSize.y - height / 2 - maxHeight - buttonBottomMargin);
                offset.x = (int) (width / 2 + buttonLeftMargin - minWidth);
                break;
            case BR:
                offset.y = (int) (parentSize.y - height / 2 - maxHeight - buttonBottomMargin);
                offset.x = (int) (parentSize.x - width / 2 - maxWidth - buttonRightMargin);
                break;
            case Unknown:
                throw new RuntimeException("Unknown button-place-alignment-enum!");
        }

        for (int i = 0; i < positions.size(); i++) {
            Point position = positions.get(i);
            positions.set(i, new Point(position.x + offset.x, position.y + offset.y));
        }
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

    private static ButtonPlaceManager ourInstance = new ButtonPlaceManager();

    public static ButtonPlaceManager getInstance() {
        return ourInstance;
    }

    private ButtonPlaceManager() {
    }
}
