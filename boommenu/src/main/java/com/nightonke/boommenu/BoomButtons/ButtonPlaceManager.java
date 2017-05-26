package com.nightonke.boommenu.BoomButtons;

import android.graphics.Point;
import android.graphics.PointF;

import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Weiping Huang at 20:26 on 16/11/19
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class ButtonPlaceManager {

    public static ArrayList<PointF> getPositions(Point parentSize,
                                                 float w,
                                                 float h,
                                                 int buttonNumber,
                                                 BoomMenuButton bmb) {
        ArrayList<PointF> ps = new ArrayList<>(buttonNumber);

        int halfButtonNumber = buttonNumber / 2;

        float hm = bmb.getButtonHorizontalMargin();
        float hm_0_5 = hm / 2;
        float hm_1_5 = hm * 1.5f;
        float hm_2_0 = hm * 2;
        float vm = bmb.getButtonVerticalMargin();
        float vm_0_5 = vm / 2;
        float vm_1_5 = vm * 1.5f;
        float vm_2_0 = vm * 2;
        float w_0_5 = w / 2;
        float w_1_5 = w * 1.5f;
        float w_2_0 = w * 2;
        float h_0_5 = h / 2;
        float h_1_5 = h * 1.5f;
        float h_2_0 = h * 2;

        switch (bmb.getButtonPlaceEnum()) {
            case Horizontal:
                if (buttonNumber % 2 == 0) {
                    for (int i = halfButtonNumber - 1; i >= 0; i--)
                        ps.add(point(-w_0_5 - hm_0_5 - i * (w + hm), 0));
                    for (int i = 0; i < halfButtonNumber; i++)
                        ps.add(point(+w_0_5 + hm_0_5 + i * (w + hm), 0));
                } else {
                    for (int i = halfButtonNumber - 1; i >= 0; i--)
                        ps.add(point(-w - hm - i * (w + hm), 0));
                    ps.add(point(0, 0));
                    for (int i = 0; i < halfButtonNumber; i++)
                        ps.add(point(+w + hm + i * (w + hm), 0));
                }
                break;
            case Vertical: case HAM_1: case HAM_2: case HAM_3: case HAM_4: case HAM_5: case HAM_6:
                if (buttonNumber % 2 == 0) {
                    for (int i = halfButtonNumber - 1; i >= 0; i--)
                        ps.add(point(0, -h_0_5 - vm_0_5 - i * (h + vm)));
                    for (int i = 0; i < halfButtonNumber; i++)
                        ps.add(point(0, +h_0_5 + vm_0_5 + i * (h + vm)));
                } else {
                    for (int i = halfButtonNumber - 1; i >= 0; i--)
                        ps.add(point(0, -h - vm - i * (h + vm)));
                    ps.add(point(0, 0));
                    for (int i = 0; i < halfButtonNumber; i++)
                        ps.add(point(0, +h + vm + i * (h + vm)));
                }
                break;
            case SC_1:
                ps.add(point(0, 0));
                break;
            case SC_2_1:
                ps.add(point(-hm_0_5 - w_0_5, 0));
                ps.add(point(+hm_0_5 + w_0_5, 0));
                break;
            case SC_2_2:
                ps.add(point(0, -vm_0_5 - h_0_5));
                ps.add(point(0, +vm_0_5 + h_0_5));
                break;
            case SC_3_1:
                ps.add(point(-hm - w, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + w, 0));
                break;
            case SC_3_2:
                ps.add(point(0, -vm - h));
                ps.add(point(0, 0));
                ps.add(point(0, +vm + h));
                break;
            case SC_3_3:
                ps.add(point(-hm_0_5 - w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(0, vm_0_5 + h_0_5));
                break;
            case SC_3_4:
                ps.add(point(0, -vm_0_5 - h_0_5));
                ps.add(point(-hm_0_5 - w_0_5, +vm_0_5 + h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, +vm_0_5 + h_0_5));
                break;
            case SC_4_1:
                ps.add(point(-hm_0_5 - w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(-hm_0_5 - w_0_5, +vm_0_5 + h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, +vm_0_5 + h_0_5));
                break;
            case SC_4_2:
                ps.add(point(0, -vm_0_5 - h_0_5));
                ps.add(point(-hm - w, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(0, +vm_0_5 + h_0_5));
                break;
            case SC_5_1:
                ps.add(point(-hm - w, -vm_0_5 - h_0_5));
                ps.add(point(0, -vm_0_5 - h_0_5));
                ps.add(point(+hm + w, -vm_0_5 - h_0_5));
                ps.add(point(-hm_0_5 - w_0_5, +vm_0_5 + h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, +vm_0_5 + h_0_5));
                break;
            case SC_5_2:
                ps.add(point(-hm_0_5 - w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(-hm - w, +vm_0_5 + h_0_5));
                ps.add(point(0, +vm_0_5 + h_0_5));
                ps.add(point(+hm + w, +vm_0_5 + h_0_5));
                break;
            case SC_5_3:
                ps.add(point(0, -vm - h));
                ps.add(point(-hm - w, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(0, +vm + h));
                break;
            case SC_5_4:
                ps.add(point(-hm - w, -vm_0_5 - h_0_5));
                ps.add(point(+hm + w, -vm_0_5 - h_0_5));
                ps.add(point(0, 0));
                ps.add(point(-hm - w, +vm_0_5 + h_0_5));
                ps.add(point(+hm + w, +vm_0_5 + h_0_5));
                break;
            case SC_6_1:
                ps.add(point(-hm - w, -vm_0_5 - h_0_5));
                ps.add(point(0, -vm_0_5 - h_0_5));
                ps.add(point(+hm + w, -vm_0_5 - h_0_5));
                ps.add(point(-hm - w, +vm_0_5 + h_0_5));
                ps.add(point(0, +vm_0_5 + h_0_5));
                ps.add(point(+hm + w, +vm_0_5 + h_0_5));
                break;
            case SC_6_2:
                ps.add(point(-hm_0_5 - w_0_5, -vm - h));
                ps.add(point(+hm_0_5 + w_0_5, -vm - h));
                ps.add(point(-hm_0_5 - w_0_5, 0));
                ps.add(point(+hm_0_5 + w_0_5, 0));
                ps.add(point(-hm_0_5 - w_0_5, +vm + h));
                ps.add(point(+hm_0_5 + w_0_5, +vm + h));
                break;
            case SC_6_3:
                ps.add(point(-hm_0_5 - w_0_5, -vm - h));
                ps.add(point(+hm_0_5 + w_0_5, -vm - h));
                ps.add(point(-hm - w, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(-hm_0_5 - w_0_5, +vm + h));
                ps.add(point(+hm_0_5 + w_0_5, +vm + h));
                break;
            case SC_6_4:
                ps.add(point(0, -vm - h));
                ps.add(point(-hm - w, -vm_0_5 - h_0_5));
                ps.add(point(+hm + w, -vm_0_5 - h_0_5));
                ps.add(point(-hm - w, +vm_0_5 + h_0_5));
                ps.add(point(+hm + w, +vm_0_5 + h_0_5));
                ps.add(point(0, +vm + h));
                break;
            case SC_6_5:
                ps.add(point(-hm - w, -vm - h));
                ps.add(point(0, -vm - h));
                ps.add(point(+hm + w, -vm - h));
                ps.add(point(-hm_0_5 - w_0_5, 0));
                ps.add(point(+hm_0_5 + w_0_5, 0));
                ps.add(point(0, vm + h));
                break;
            case SC_6_6:
                ps.add(point(0, -vm - h));
                ps.add(point(-hm_0_5 - w_0_5, 0));
                ps.add(point(+hm_0_5 + w_0_5, 0));
                ps.add(point(-hm - w, vm + h));
                ps.add(point(0, vm + h));
                ps.add(point(+hm + w, vm + h));
                break;
            case SC_7_1:
                ps.add(point(-hm - w, -vm - h));
                ps.add(point(0, -vm - h));
                ps.add(point(+hm + w, -vm - h));
                ps.add(point(-hm - w, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(0, vm + h));
                break;
            case SC_7_2:
                ps.add(point(-hm - w, -vm - h));
                ps.add(point(0, -vm - h));
                ps.add(point(+hm + w, -vm - h));
                ps.add(point(-hm - w, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(0, vm + h));
                break;
            case SC_7_3:
                ps.add(point(-hm_0_5 - w_0_5, -vm - h));
                ps.add(point(+hm_0_5 + w_0_5, -vm - h));
                ps.add(point(-hm - w, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(-hm_0_5 - w_0_5, +vm + h));
                ps.add(point(+hm_0_5 + w_0_5, +vm + h));
                break;
            case SC_7_4:
                ps.add(point(0, -vm - h));
                ps.add(point(-hm - w, -vm_0_5 - h_0_5));
                ps.add(point(+hm + w, -vm_0_5 - h_0_5));
                ps.add(point(0, 0));
                ps.add(point(-hm - w, +vm_0_5 + h_0_5));
                ps.add(point(+hm + w, +vm_0_5 + h_0_5));
                ps.add(point(0, +vm + h));
                break;
            case SC_7_5:
                ps.add(point(-hm_1_5 - w_1_5, -vm_0_5 - h_0_5));
                ps.add(point(-hm_0_5 - w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(+hm_1_5 + w_1_5, -vm_0_5 - h_0_5));
                ps.add(point(-hm - w, vm_0_5 + h_0_5));
                ps.add(point(0, vm_0_5 + h_0_5));
                ps.add(point(+hm + w, vm_0_5 + h_0_5));
                break;
            case SC_7_6:
                ps.add(point(-hm - w, -vm_0_5 - h_0_5));
                ps.add(point(0, -vm_0_5 - h_0_5));
                ps.add(point(+hm + w, -vm_0_5 - h_0_5));
                ps.add(point(-hm_1_5 - w_1_5, +vm_0_5 + h_0_5));
                ps.add(point(-hm_0_5 - w_0_5, +vm_0_5 + h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, +vm_0_5 + h_0_5));
                ps.add(point(+hm_1_5 + w_1_5, +vm_0_5 + h_0_5));
                break;
            case SC_8_1:
                ps.add(point(-hm - w, -vm - h));
                ps.add(point(0, -vm - h));
                ps.add(point(+hm + w, -vm - h));
                ps.add(point(-hm_0_5 - w_0_5, 0));
                ps.add(point(+hm_0_5 + w_0_5, 0));
                ps.add(point(-hm - w, +vm + h));
                ps.add(point(0, +vm + h));
                ps.add(point(+hm + w, +vm + h));
                break;
            case SC_8_2:
                ps.add(point(-hm - w, -vm - h));
                ps.add(point(+hm + w, -vm - h));
                ps.add(point(0, -vm_0_5 - h_0_5));
                ps.add(point(-hm - w, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(0, +vm_0_5 + h_0_5));
                ps.add(point(-hm - w, +vm + h));
                ps.add(point(+hm + w, +vm + h));
                break;
            case SC_8_3:
                ps.add(point(-hm - w, -vm - h));
                ps.add(point(0, -vm - h));
                ps.add(point(+hm + w, -vm - h));
                ps.add(point(-hm - w, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(-hm - w, +vm + h));
                ps.add(point(0, +vm + h));
                ps.add(point(+hm + w, +vm + h));
                break;
            case SC_8_4:
                ps.add(point(0, -vm_2_0 - h_2_0));
                ps.add(point(-hm_0_5 - w_0_5, -vm - h));
                ps.add(point(+hm_0_5 + w_0_5, -vm - h));
                ps.add(point(-hm - w, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(-hm_0_5 - w_0_5, +vm + h));
                ps.add(point(+hm_0_5 + w_0_5, +vm + h));
                ps.add(point(0, +vm_2_0 + h_2_0));
                break;
            case SC_8_5:
                ps.add(point(0, -vm - h));
                ps.add(point(-hm - w, -vm_0_5 - h_0_5));
                ps.add(point(+hm + w, -vm_0_5 - h_0_5));
                ps.add(point(-hm_2_0 - w_2_0, 0));
                ps.add(point(+hm_2_0 + w_2_0, 0));
                ps.add(point(-hm - w, +vm_0_5 + h_0_5));
                ps.add(point(+hm + w, +vm_0_5 + h_0_5));
                ps.add(point(0, +vm + h));
                break;
            case SC_8_6:
                ps.add(point(-hm_1_5 - w_1_5, -vm_0_5 - h_0_5));
                ps.add(point(-hm_0_5 - w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(+hm_1_5 + w_1_5, -vm_0_5 - h_0_5));
                ps.add(point(-hm_1_5 - w_1_5, +vm_0_5 + h_0_5));
                ps.add(point(-hm_0_5 - w_0_5, +vm_0_5 + h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, +vm_0_5 + h_0_5));
                ps.add(point(+hm_1_5 + w_1_5, +vm_0_5 + h_0_5));
                break;
            case SC_8_7:
                ps.add(point(-hm_0_5 - w_0_5, -vm_1_5 - h_1_5));
                ps.add(point(+hm_0_5 + w_0_5, -vm_1_5 - h_1_5));
                ps.add(point(-hm_0_5 - w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, -vm_0_5 - h_0_5));
                ps.add(point(-hm_0_5 - w_0_5, +vm_0_5 + h_0_5));
                ps.add(point(+hm_0_5 + w_0_5, +vm_0_5 + h_0_5));
                ps.add(point(-hm_0_5 - w_0_5, +vm_1_5 + h_1_5));
                ps.add(point(+hm_0_5 + w_0_5, +vm_1_5 + h_1_5));
                break;
            case SC_9_1:
                ps.add(point(-hm - w, -vm - h));
                ps.add(point(0, -vm - h));
                ps.add(point(+hm + w, -vm - h));
                ps.add(point(-hm - w, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(-hm - w, +vm + h));
                ps.add(point(0, +vm + h));
                ps.add(point(+hm + w, +vm + h));
                break;
            case SC_9_2:
                ps.add(point(0, -vm_2_0 - h_2_0));
                ps.add(point(-hm_0_5 - w_0_5, -vm - h));
                ps.add(point(+hm_0_5 + w_0_5, -vm - h));
                ps.add(point(-hm - w, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + w, 0));
                ps.add(point(-hm_0_5 - w_0_5, +vm + h));
                ps.add(point(+hm_0_5 + w_0_5, +vm + h));
                ps.add(point(0, +vm_2_0 + h_2_0));
                break;
            case SC_9_3:
                ps.add(point(0, -vm - h));
                ps.add(point(-hm - w, -vm_0_5 - h_0_5));
                ps.add(point(+hm + w, -vm_0_5 - h_0_5));
                ps.add(point(-hm_2_0 - w_2_0, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm_2_0 + w_2_0, 0));
                ps.add(point(-hm - w, +vm_0_5 + h_0_5));
                ps.add(point(+hm + w, +vm_0_5 + h_0_5));
                ps.add(point(0, +vm + h));
                break;
            case Custom:
                for (PointF p : bmb.getCustomButtonPlacePositions()) ps.add(point(p.x, p.y));
                break;
            default:
                throw new RuntimeException("Button place enum not found!");
        }

        switch (bmb.getButtonPlaceEnum()) {
            case SC_3_3:
                adjust(ps, 0, (float) (+pow(hm_0_5 + w_0_5, 2) / (vm + h)));
                break;
            case SC_3_4:
                adjust(ps, 0, (float) (-pow(hm_0_5 + w_0_5, 2) / (vm + h)));
                break;
            case SC_4_2: case SC_5_1: case SC_5_2: case SC_5_3: case SC_5_4: case SC_6_1:
            case SC_6_2: case SC_6_3: case SC_6_4: case SC_6_5: case SC_6_6: case SC_7_1:
            case SC_7_2: case SC_7_3: case SC_7_4: case SC_7_5: case SC_7_6: case SC_8_1:
            case SC_8_2: case SC_8_3: case SC_8_4: case SC_8_5: case SC_8_6: case SC_8_7:
            case SC_9_1: case SC_9_2: case SC_9_3:
                adjust(ps, 0, h_0_5 - w_0_5);
                break;
            default:
                if (buttonNumber >= 2
                        && bmb.getButtonEnum().equals(ButtonEnum.Ham)
                        && bmb.getBottomHamButtonTopMargin() > 0
                        && !bmb.getButtonPlaceEnum().equals(ButtonPlaceEnum.Horizontal))
                    ps.get(ps.size() - 1).offset(0, bmb.getBottomHamButtonTopMargin() - vm);
        }

        adjust(ps, parentSize.x / 2, parentSize.y / 2);
        adjust(ps, parentSize, w_0_5, h_0_5, bmb);

        return ps;
    }

    public static ArrayList<PointF> getPositions(Point parentSize,
                                                 float r,
                                                 int buttonNumber,
                                                 BoomMenuButton bmb) {
        ArrayList<PointF> ps = new ArrayList<>(buttonNumber);

        int halfButtonNumber = buttonNumber / 2;

        float hm = bmb.getButtonHorizontalMargin();
        float hm_0_5 = hm / 2;
        float hm_1_5 = hm * 1.5f;

        float vm = bmb.getButtonVerticalMargin();
        float vm_0_5 = vm / 2;
        float vm_1_5 = vm * 1.5f;

        float im = bmb.getButtonInclinedMargin();

        float r_2_0 = 2 * r;
        float r_3_0 = 3 * r;

        float a, b, c, e;
        b = hm_0_5 + r;
        c = (float) (b / (sqrt(3) / 2));
        a = c / 2;
        e = c - a;

        switch (bmb.getButtonPlaceEnum()) {
            case SC_4_2: case SC_5_4: case SC_8_5: case SC_9_3:
                a = (float) ((r_2_0 + im) / sqrt(2));
                break;
            case SC_8_2:
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

        switch (bmb.getButtonPlaceEnum()) {
            case Horizontal:
                if (buttonNumber % 2 == 0) {
                    for (int i = halfButtonNumber - 1; i >= 0; i--)
                        ps.add(point(-r - hm_0_5 - i * (r_2_0 + hm), 0));
                    for (int i = 0; i < halfButtonNumber; i++)
                        ps.add(point(+r + hm_0_5 + i * (r_2_0 + hm), 0));
                } else {
                    for (int i = halfButtonNumber - 1; i >= 0; i--)
                        ps.add(point(-r_2_0 - hm - i * (r_2_0 + hm), 0));
                    ps.add(point(0, 0));
                    for (int i = 0; i < halfButtonNumber; i++)
                        ps.add(point(+r_2_0 + hm + i * (r_2_0 + hm), 0));
                }
                break;
            case Vertical:
                if (buttonNumber % 2 == 0) {
                    for (int i = halfButtonNumber - 1; i >= 0; i--)
                        ps.add(point(0, -r - vm_0_5 - i * (r_2_0 + vm)));
                    for (int i = 0; i < halfButtonNumber; i++)
                        ps.add(point(0, +r + vm_0_5 + i * (r_2_0 + vm)));
                } else {
                    for (int i = halfButtonNumber - 1; i >= 0; i--)
                        ps.add(point(0, -r_2_0 - vm - i * (r_2_0 + vm)));
                    ps.add(point(0, 0));
                    for (int i = 0; i < halfButtonNumber; i++)
                        ps.add(point(0, +r_2_0 + vm + i * (r_2_0 + vm)));
                }
                break;
            case SC_1:
                ps.add(point(0, 0));
                break;
            case SC_2_1:
                ps.add(point(-hm_0_5 - r, 0));
                ps.add(point(+hm_0_5 + r, 0));
                break;
            case SC_2_2:
                ps.add(point(0, -vm_0_5 - r));
                ps.add(point(0, +vm_0_5 + r));
                break;
            case SC_3_1:
                ps.add(point(-hm - r_2_0, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + r_2_0, 0));
                break;
            case SC_3_2:
                ps.add(point(0, -vm - r_2_0));
                ps.add(point(0, 0));
                ps.add(point(0, +vm + r_2_0));
                break;
            case SC_3_3:
                ps.add(point(-b, -a));
                ps.add(point(+b, -a));
                ps.add(point(0, c));
                break;
            case SC_3_4:
                ps.add(point(0, -c));
                ps.add(point(-b, a));
                ps.add(point(+b, a));
                break;
            case SC_4_1:
                ps.add(point(-hm_0_5 - r, -vm_0_5 - r));
                ps.add(point(+hm_0_5 + r, -vm_0_5 - r));
                ps.add(point(-hm_0_5 - r, +vm_0_5 + r));
                ps.add(point(+hm_0_5 + r, +vm_0_5 + r));
                break;
            case SC_4_2:
                ps.add(point(0, -a));
                ps.add(point(-a, 0));
                ps.add(point(+a, 0));
                ps.add(point(0, +a));
                break;
            case SC_5_1:
                ps.add(point(-b_2_0, -c));
                ps.add(point(0, -c));
                ps.add(point(+b_2_0, -c));
                ps.add(point(-hm_0_5 - r, a));
                ps.add(point(+hm_0_5 + r, a));
                break;
            case SC_5_2:
                ps.add(point(-hm_0_5 - r, -a));
                ps.add(point(+hm_0_5 + r, -a));
                ps.add(point(-b_2_0, c));
                ps.add(point(0, c));
                ps.add(point(+b_2_0, c));
                break;
            case SC_5_3:
                ps.add(point(0, -vm - r_2_0));
                ps.add(point(-hm - r_2_0, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + r_2_0, 0));
                ps.add(point(0, +vm + r_2_0));
                break;
            case SC_5_4:
                ps.add(point(-a, -a));
                ps.add(point(+a, -a));
                ps.add(point(0, 0));
                ps.add(point(-a, +a));
                ps.add(point(+a, +a));
                break;
            case SC_6_1:
                ps.add(point(-hm - r_2_0, -vm_0_5 - r));
                ps.add(point(0, -vm_0_5 - r));
                ps.add(point(+hm + r_2_0, -vm_0_5 - r));
                ps.add(point(-hm - r_2_0, +vm_0_5 + r));
                ps.add(point(0, +vm_0_5 + r));
                ps.add(point(+hm + r_2_0, +vm_0_5 + r));
                break;
            case SC_6_2:
                ps.add(point(-hm_0_5 - r, -vm - r_2_0));
                ps.add(point(+hm_0_5 + r, -vm - r_2_0));
                ps.add(point(-hm_0_5 - r, 0));
                ps.add(point(+hm_0_5 + r, 0));
                ps.add(point(-hm_0_5 - r, +vm + r_2_0));
                ps.add(point(+hm_0_5 + r, +vm + r_2_0));
                break;
            case SC_6_3:
                ps.add(point(-b, -a - c));
                ps.add(point(+b, -a - c));
                ps.add(point(-b_2_0, 0));
                ps.add(point(+b_2_0, 0));
                ps.add(point(-b, +a + c));
                ps.add(point(+b, +a + c));
                break;
            case SC_6_4:
                ps.add(point(0, -b_2_0));
                ps.add(point(-a - c, -b));
                ps.add(point(+a + c, -b));
                ps.add(point(-a - c, +b));
                ps.add(point(+a + c, +b));
                ps.add(point(0, +b_2_0));
                break;
            case SC_6_5:
                ps.add(point(-b_2_0, -a - c + e));
                ps.add(point(0, -a - c + e));
                ps.add(point(+b_2_0, -a - c + e));
                ps.add(point(-hm_0_5 - r, +e));
                ps.add(point(+hm_0_5 + r, +e));
                ps.add(point(0, +a + c + e));
                break;
            case SC_6_6:
                ps.add(point(0, -a - c - e));
                ps.add(point(-hm_0_5 - r, -e));
                ps.add(point(+hm_0_5 + r, -e));
                ps.add(point(-b_2_0, +a + c - e));
                ps.add(point(0, +a + c - e));
                ps.add(point(+b_2_0, +a + c - e));
                break;
            case SC_7_1:
                ps.add(point(-hm - r_2_0, -vm - r_2_0));
                ps.add(point(0, -vm - r_2_0));
                ps.add(point(+hm + r_2_0, -vm - r_2_0));
                ps.add(point(-hm - r_2_0, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + r_2_0, 0));
                ps.add(point(0, vm + r_2_0));
                break;
            case SC_7_2:
                ps.add(point(0, -vm - r_2_0));
                ps.add(point(-hm - r_2_0, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + r_2_0, 0));
                ps.add(point(-hm - r_2_0, vm + r_2_0));
                ps.add(point(0, vm + r_2_0));
                ps.add(point(+hm + r_2_0, vm + r_2_0));
                break;
            case SC_7_3:
                ps.add(point(-b, -a - c));
                ps.add(point(+b, -a - c));
                ps.add(point(-b_2_0, 0));
                ps.add(point(0, 0));
                ps.add(point(+b_2_0, 0));
                ps.add(point(-b, +a + c));
                ps.add(point(+b, +a + c));
                break;
            case SC_7_4:
                ps.add(point(0, -b_2_0));
                ps.add(point(-a - c, -b));
                ps.add(point(+a + c, -b));
                ps.add(point(0, 0));
                ps.add(point(-a - c, +b));
                ps.add(point(+a + c, +b));
                ps.add(point(0, +b_2_0));
                break;
            case SC_7_5:
                ps.add(point(-b_3_0, -a));
                ps.add(point(-b, -a));
                ps.add(point(+b, -a));
                ps.add(point(+b_3_0, -a));
                ps.add(point(-b_2_0, c));
                ps.add(point(0, c));
                ps.add(point(+b_2_0, c));
                break;
            case SC_7_6:
                ps.add(point(-b_2_0, -c));
                ps.add(point(0, -c));
                ps.add(point(+b_2_0, -c));
                ps.add(point(-b_3_0, a));
                ps.add(point(-b, a));
                ps.add(point(+b, a));
                ps.add(point(+b_3_0, a));
                break;
            case SC_8_1:
                ps.add(point(-b_2_0, -a - c));
                ps.add(point(0, -a - c));
                ps.add(point(+b_2_0, -a - c));
                ps.add(point(-hm_0_5 - r, 0));
                ps.add(point(+hm_0_5 + r, 0));
                ps.add(point(-b_2_0, +a + c));
                ps.add(point(0, +a + c));
                ps.add(point(+b_2_0, +a + c));
                break;
            case SC_8_2:
                ps.add(point(-a - c, -b_2_0));
                ps.add(point(+a + c, -b_2_0));
                ps.add(point(0, -vm_0_5 - r));
                ps.add(point(-a - c, 0));
                ps.add(point(+a + c, 0));
                ps.add(point(0, +vm_0_5 + r));
                ps.add(point(-a - c, +b_2_0));
                ps.add(point(+a + c, +b_2_0));
                break;
            case SC_8_3:
                ps.add(point(-hm - r_2_0, -vm - r_2_0));
                ps.add(point(0, -vm - r_2_0));
                ps.add(point(+hm + r_2_0, -vm - r_2_0));
                ps.add(point(-hm - r_2_0, 0));
                ps.add(point(+hm + r_2_0, 0));
                ps.add(point(-hm - r_2_0, +vm + r_2_0));
                ps.add(point(0, +vm + r_2_0));
                ps.add(point(+hm + r_2_0, +vm + r_2_0));
                break;
            case SC_8_4:
                ps.add(point(0, -a_2_0 - c_2_0));
                ps.add(point(-hm_0_5 - r, -a - c));
                ps.add(point(+hm_0_5 + r, -a - c));
                ps.add(point(-b_2_0, 0));
                ps.add(point(+b_2_0, 0));
                ps.add(point(-hm_0_5 - r, +a + c));
                ps.add(point(+hm_0_5 + r, +a + c));
                ps.add(point(0, +a_2_0 + c_2_0));
                break;
            case SC_8_5:
                ps.add(point(0, -a_2_0));
                ps.add(point(-a, -a));
                ps.add(point(+a, -a));
                ps.add(point(-a_2_0, 0));
                ps.add(point(+a_2_0, 0));
                ps.add(point(-a, +a));
                ps.add(point(+a, +a));
                ps.add(point(0, +a_2_0));
                break;
            case SC_8_6:
                ps.add(point(-hm_1_5 - r_3_0, -vm_0_5 - r));
                ps.add(point(-hm_0_5 - r, -vm_0_5 - r));
                ps.add(point(+hm_0_5 + r, -vm_0_5 - r));
                ps.add(point(+hm_1_5 + r_3_0, -vm_0_5 - r));
                ps.add(point(-hm_1_5 - r_3_0, +vm_0_5 + r));
                ps.add(point(-hm_0_5 - r, +vm_0_5 + r));
                ps.add(point(+hm_0_5 + r, +vm_0_5 + r));
                ps.add(point(+hm_1_5 + r_3_0, +vm_0_5 + r));
                break;
            case SC_8_7:
                ps.add(point(-hm_0_5 - r, -vm_1_5 - r_3_0));
                ps.add(point(+hm_0_5 + r, -vm_1_5 - r_3_0));
                ps.add(point(-hm_0_5 - r, -vm_0_5 - r));
                ps.add(point(+hm_0_5 + r, -vm_0_5 - r));
                ps.add(point(-hm_0_5 - r, +vm_0_5 + r));
                ps.add(point(+hm_0_5 + r, +vm_0_5 + r));
                ps.add(point(-hm_0_5 - r, +vm_1_5 + r_3_0));
                ps.add(point(+hm_0_5 + r, +vm_1_5 + r_3_0));
                break;
            case SC_9_1:
                ps.add(point(-hm - r_2_0, -vm - r_2_0));
                ps.add(point(0, -vm - r_2_0));
                ps.add(point(+hm + r_2_0, -vm - r_2_0));
                ps.add(point(-hm - r_2_0, 0));
                ps.add(point(0, 0));
                ps.add(point(+hm + r_2_0, 0));
                ps.add(point(-hm - r_2_0, +vm + r_2_0));
                ps.add(point(0, +vm + r_2_0));
                ps.add(point(+hm + r_2_0, +vm + r_2_0));
                break;
            case SC_9_2:
                ps.add(point(0, -a_2_0 - c_2_0));
                ps.add(point(-hm_0_5 - r, -a - c));
                ps.add(point(+hm_0_5 + r, -a - c));
                ps.add(point(-b_2_0, 0));
                ps.add(point(0, 0));
                ps.add(point(+b_2_0, 0));
                ps.add(point(-hm_0_5 - r, +a + c));
                ps.add(point(+hm_0_5 + r, +a + c));
                ps.add(point(0, +a_2_0 + c_2_0));
                break;
            case SC_9_3:
                ps.add(point(0, -a_2_0));
                ps.add(point(-a, -a));
                ps.add(point(+a, -a));
                ps.add(point(-a_2_0, 0));
                ps.add(point(0, 0));
                ps.add(point(+a_2_0, 0));
                ps.add(point(-a, +a));
                ps.add(point(+a, +a));
                ps.add(point(0, +a_2_0));
                break;
            case Custom:
                for (PointF p : bmb.getCustomButtonPlacePositions()) ps.add(point(p.x, p.y));
                break;
            default:
                throw new RuntimeException("Button place enum not found!");
        }

        adjust(ps, parentSize.x / 2, parentSize.y / 2);
        adjust(ps, parentSize, r, r, bmb);

        return ps;
    }

    private static void adjust(ArrayList<PointF> ps, Point parentSize, float halfWidth,
                               float halfHeight, BoomMenuButton bmb) {
        float minY = Float.MAX_VALUE;
        float maxY = Float.MIN_VALUE;
        float minX = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;

        for (PointF position : ps) {
            maxY = Math.max(maxY, position.y);
            minY = Math.min(minY, position.y);
            maxX = Math.max(maxX, position.x);
            minX = Math.min(minX, position.x);
        }

        float yOffset = 0;
        float xOffset = 0;
        switch (bmb.getButtonPlaceAlignmentEnum()) {
            case Center:
                break;
            case Top:
                yOffset = halfHeight + bmb.getButtonTopMargin() - minY;
                break;
            case Bottom:
                yOffset = parentSize.y - halfHeight - maxY - bmb.getButtonBottomMargin();
                break;
            case Left:
                xOffset = halfWidth + bmb.getButtonLeftMargin() - minY;
                break;
            case Right:
                xOffset = parentSize.y - halfHeight - maxY - bmb.getButtonRightMargin();
                break;
            case TL:
                yOffset = halfHeight + bmb.getButtonTopMargin() - minY;
                xOffset = halfWidth + bmb.getButtonLeftMargin() - minY;
                break;
            case TR:
                yOffset = halfHeight + bmb.getButtonTopMargin() - minY;
                xOffset = parentSize.y - halfHeight - maxY - bmb.getButtonRightMargin();
                break;
            case BL:
                yOffset = parentSize.y - halfHeight - maxY - bmb.getButtonBottomMargin();
                xOffset = halfWidth + bmb.getButtonLeftMargin() - minY;
                break;
            case BR:
                yOffset = parentSize.y - halfHeight - maxY - bmb.getButtonBottomMargin();
                xOffset = parentSize.y - halfHeight - maxY - bmb.getButtonRightMargin();
                break;
        }

        adjust(ps, xOffset, yOffset);
    }

    private static void adjust(ArrayList<PointF> ps, float offsetX, float offsetY) {
        for (int i = 0; i < ps.size(); i++) ps.get(i).offset(offsetX, offsetY);
    }

    private static PointF point(float x, float y) {
        return new PointF(x, y);
    }
}
