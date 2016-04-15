package com.nightonke.boommenu;

import android.view.Gravity;
import android.widget.FrameLayout;

import com.nightonke.boommenu.Types.PlaceType;

/**
 * Created by Weiping on 2016/4/1.
 */
public class PlaceParamsFactory {

    public static FrameLayout.LayoutParams[] getDotParams(
            PlaceType placeType,
            int buttonWidth,
            int buttonHeight,
            int dotWidth,
            int dotHeight) {
        FrameLayout.LayoutParams[] ps = null;
        if (placeType.equals(PlaceType.CIRCLE_1_1)) {
            ps = new FrameLayout.LayoutParams[1];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_2_1)) {
            ps = new FrameLayout.LayoutParams[2];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_2_2)) {
            ps = new FrameLayout.LayoutParams[2];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_3_1)) {
            ps = new FrameLayout.LayoutParams[3];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_3_2)) {
            ps = new FrameLayout.LayoutParams[3];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[2].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_3_3)) {
            int c = buttonWidth / 6;
            int a = c / 2;
            int b = (int)Math.sqrt(c * c - a * a);
            ps = new FrameLayout.LayoutParams[3];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - c - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - b - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 + a - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 + b - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 + a - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_3_4)) {
            int c = buttonWidth / 6;
            int a = c / 2;
            int b = (int)Math.sqrt(c * c - a * a);
            ps = new FrameLayout.LayoutParams[3];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 + c - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - b - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - a - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 + b - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - a - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_4_1)) {
            ps = new FrameLayout.LayoutParams[4];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth * 3 / 8 - dotWidth / 2;
            ps[0].topMargin = buttonHeight * 3 / 8 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth * 5 / 8 - dotWidth / 2;
            ps[1].topMargin = buttonHeight * 3 / 8 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth * 3 / 8 - dotWidth / 2;
            ps[2].topMargin = buttonHeight * 5 / 8 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth * 5 / 8 - dotWidth / 2;
            ps[3].topMargin = buttonHeight * 5 / 8 - dotWidth / 2;
        }
        if (placeType.equals(PlaceType.CIRCLE_4_2)) {
            double s2 = Math.sqrt(2);
            ps = new FrameLayout.LayoutParams[4];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = (int) (buttonWidth / 2 - buttonWidth / 4 / s2 - dotWidth / 2);
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = (int) (buttonWidth / 2 - buttonWidth / 4 / s2 - dotWidth / 2);
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[2].topMargin = (int) (buttonWidth / 2 + buttonWidth / 4 / s2 - dotWidth / 2);
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = (int) (buttonWidth / 2 + buttonWidth / 4 / s2 - dotWidth / 2);
            ps[3].topMargin = buttonHeight / 2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_5_1)) {
            ps = new FrameLayout.LayoutParams[5];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[0].topMargin = (int) (buttonWidth * 5.5 / 12 - dotWidth / 2);
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[1].topMargin = (int) (buttonWidth * 5.5 / 12 - dotWidth / 2);
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[2].topMargin = (int) (buttonWidth * 5.5 / 12 - dotWidth / 2);
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth * 5 / 12 - dotWidth / 2;
            ps[3].topMargin = (int) (buttonWidth * 7.5 / 12 - dotWidth / 2);
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth * 7 / 12 - dotWidth / 2;
            ps[4].topMargin = (int) (buttonWidth * 7.5 / 12 - dotWidth / 2);
        }
        else if (placeType.equals(PlaceType.CIRCLE_5_2)) {
            ps = new FrameLayout.LayoutParams[5];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth * 5 / 12 - dotWidth / 2;
            ps[0].topMargin = buttonHeight * 5 / 12 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth * 7 / 12 - dotWidth / 2;
            ps[1].topMargin = buttonHeight * 5 / 12 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[2].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[3].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[4].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_5_3)) {
            ps = new FrameLayout.LayoutParams[5];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth * 3 / 8 - dotWidth / 2;
            ps[1].topMargin = buttonHeight * 3 / 8 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth * 5 / 8 - dotWidth / 2;
            ps[2].topMargin = buttonHeight * 3 / 8 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth * 3 / 8 - dotWidth / 2;
            ps[3].topMargin = buttonHeight * 5 / 8 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth * 5 / 8 - dotWidth / 2;
            ps[4].topMargin = buttonHeight * 5 / 8 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_5_4)) {
            double s2 = Math.sqrt(2);
            ps = new FrameLayout.LayoutParams[5];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[1].topMargin = (int) (buttonWidth / 2 - buttonWidth / 4 / s2 - dotWidth / 2);
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = (int) (buttonWidth / 2 - buttonWidth / 4 / s2 - dotWidth / 2);
            ps[2].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[3].topMargin = (int) (buttonWidth / 2 + buttonWidth / 4 / s2 - dotWidth / 2);
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = (int) (buttonWidth / 2 + buttonWidth / 4 / s2 - dotWidth / 2);
            ps[4].topMargin = buttonHeight / 2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_1)) {
            ps = new FrameLayout.LayoutParams[6];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[0].topMargin = buttonHeight * 5 / 12 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight * 5 / 12 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[2].topMargin = buttonHeight * 5 / 12 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[3].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[4].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[5].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_2)) {
            ps = new FrameLayout.LayoutParams[6];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth * 5 / 12 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth * 7 / 12 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth * 5 / 12 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth * 7 / 12 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth * 5 / 12 - dotWidth / 2;
            ps[4].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth * 7 / 12 - dotWidth / 2;
            ps[5].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_3)) {
            int dis1 = buttonWidth / 12;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            ps = new FrameLayout.LayoutParams[6];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth * 5 / 12 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth * 7 / 12 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth * 7 / 12 - dotWidth / 2;
            ps[4].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth * 5 / 12 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_4)) {
            int dis1 = buttonWidth / 12;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            ps = new FrameLayout.LayoutParams[6];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight * 5 / 12 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[2].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[3].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[4].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[5].topMargin = buttonHeight * 5 / 12 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_5)) {
            int dis1 = buttonWidth / 12;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            ps = new FrameLayout.LayoutParams[6];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dis1 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 + dis1 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 - dis1 * 2 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[4].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 + dis1 * 2 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_6)) {
            int dis1 = buttonWidth / 12;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            ps = new FrameLayout.LayoutParams[6];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dis1 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 + dis1 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 - dis1 * 2 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[4].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 + dis1 * 2 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_7_1)) {
            ps = new FrameLayout.LayoutParams[7];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[4].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[5].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
            ps[6] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[6].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[6].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_7_2)) {
            ps = new FrameLayout.LayoutParams[7];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[4].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[6] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[6].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[6].topMargin = buttonHeight / 3 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_7_3)) {
            int dis1 = buttonWidth / 12;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            ps = new FrameLayout.LayoutParams[7];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth * 5 / 12 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth * 7 / 12 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[4].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth * 7 / 12 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[6] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[6].leftMargin = buttonWidth * 5 / 12 - dotWidth / 2;
            ps[6].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_7_4)) {
            int dis1 = buttonWidth / 12;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            ps = new FrameLayout.LayoutParams[7];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[2].topMargin = buttonHeight * 5 / 12 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[3].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[4].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[5].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
            ps[6] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[6].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[6].topMargin = buttonHeight * 5 / 12 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_8_1)) {
            int dis1 = buttonWidth / 12;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            ps = new FrameLayout.LayoutParams[8];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 - dis1 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 + dis1 - dotWidth / 2;
            ps[4].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 3 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[6] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[6].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[6].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[7] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[7].leftMargin = buttonWidth * 2 / 3 - dotWidth / 2;
            ps[7].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_8_2)) {
            int dis1 = buttonWidth / 12;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            ps = new FrameLayout.LayoutParams[8];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[2].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[3].topMargin = buttonHeight * 5 / 12 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[4].topMargin = buttonHeight * 7 / 12 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 3 - dotWidth / 2;
            ps[6] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[6].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[6].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[7] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[7].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[7].topMargin = buttonHeight * 2 / 3 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_8_3)) {
            int dis1 = buttonWidth / 12;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            ps = new FrameLayout.LayoutParams[8];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[4].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[6] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[6].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[6].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[7] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[7].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[7].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_9_1)) {
            int dis1 = buttonWidth / 12;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            ps = new FrameLayout.LayoutParams[9];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 - dis2 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[4].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[6] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[6].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[6].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[7] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[7].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[7].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[8] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[8].leftMargin = buttonWidth / 2 + dis2 - dotWidth / 2;
            ps[8].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_9_2)) {
            int dis1 = (int) (buttonWidth / 12 * Math.sqrt(6));
            ps = new FrameLayout.LayoutParams[9];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dis1 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dis1 / 2 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dis1 / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 + dis1 / 2 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 - dis1 / 2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 - dis1 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[4].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 + dis1 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[6] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[6].leftMargin = buttonWidth / 2 - dis1 / 2 - dotWidth / 2;
            ps[6].topMargin = buttonHeight / 2 + dis1 / 2 - dotWidth / 2;
            ps[7] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[7].leftMargin = buttonWidth / 2 + dis1 / 2 - dotWidth / 2;
            ps[7].topMargin = buttonHeight / 2 + dis1 / 2 - dotWidth / 2;
            ps[8] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[8].leftMargin = buttonWidth / 2 - dotWidth / 2;
            ps[8].topMargin = buttonHeight / 2 + dis1 - dotWidth / 2;
        } else if (PlaceType.SHARE_3_1.v <= placeType.v && placeType.v <= PlaceType.SHARE_9_2.v) {
            int dis1 = buttonWidth / 6;
            int dis2 = (int) (Math.sqrt(3) / 2 * dis1);
            ps = new FrameLayout.LayoutParams[9];
            ps[0] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[0].leftMargin = buttonWidth / 2 + dis1 / 2 - dotWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[1] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[1].leftMargin = buttonWidth / 2 - dis1 - dotWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[2] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[2].leftMargin = buttonWidth / 2 + dis1 / 2 - dotWidth / 2;
            ps[2].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[3] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[3].leftMargin = buttonWidth / 2 + dis1 / 2 - dotWidth / 2;
            ps[3].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[4] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[4].leftMargin = buttonWidth / 2 - dis1 - dotWidth / 2;
            ps[4].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[5] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[5].leftMargin = buttonWidth / 2 + dis1 / 2 - dotWidth / 2;
            ps[5].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
            ps[6] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[6].leftMargin = buttonWidth / 2 + dis1 / 2 - dotWidth / 2;
            ps[6].topMargin = buttonHeight / 2 - dis2 - dotWidth / 2;
            ps[7] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[7].leftMargin = buttonWidth / 2 - dis1 - dotWidth / 2;
            ps[7].topMargin = buttonHeight / 2 - dotWidth / 2;
            ps[8] = new FrameLayout.LayoutParams(dotWidth, dotHeight);
            ps[8].leftMargin = buttonWidth / 2 + dis1 / 2 - dotWidth / 2;
            ps[8].topMargin = buttonHeight / 2 + dis2 - dotWidth / 2;
        }
        // to support RTL mode
        for (int i = 0; i < ps.length; i++) ps[i].gravity = Gravity.TOP | Gravity.LEFT;
        return ps;
    }

    public static FrameLayout.LayoutParams[] getBarParams(
            PlaceType placeType,
            int buttonWidth,
            int buttonHeight,
            int barWidth,
            int barHeight) {
        FrameLayout.LayoutParams[] ps = null;
        if (placeType.equals(PlaceType.HAM_1_1)) {
            ps = new FrameLayout.LayoutParams[1];
            ps[0] = new FrameLayout.LayoutParams(barWidth, barHeight);
            ps[0].leftMargin = buttonWidth / 2 - barWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - barHeight / 2;
        }
        else if (placeType.equals(PlaceType.HAM_2_1)) {
            ps = new FrameLayout.LayoutParams[2];
            ps[0] = new FrameLayout.LayoutParams(barWidth, barHeight);
            ps[0].leftMargin = buttonWidth / 2 - barWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - barHeight * 3 / 2;
            ps[1] = new FrameLayout.LayoutParams(barWidth, barHeight);
            ps[1].leftMargin = buttonWidth / 2 - barWidth / 2;
            ps[1].topMargin = buttonHeight / 2 + barHeight / 2;
        }
        else if (placeType.equals(PlaceType.HAM_3_1)) {
            ps = new FrameLayout.LayoutParams[3];
            ps[0] = new FrameLayout.LayoutParams(barWidth, barHeight);
            ps[0].leftMargin = buttonWidth / 2 - barWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - barHeight * 13 / 6;
            ps[1] = new FrameLayout.LayoutParams(barWidth, barHeight);
            ps[1].leftMargin = buttonWidth / 2 - barWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - barHeight / 2;
            ps[2] = new FrameLayout.LayoutParams(barWidth, barHeight);
            ps[2].leftMargin = buttonWidth / 2 - barWidth / 2;
            ps[2].topMargin = buttonHeight / 2 + barHeight * 7 / 6;
        }
        else if (placeType.equals(PlaceType.HAM_4_1)) {
            ps = new FrameLayout.LayoutParams[4];
            ps[0] = new FrameLayout.LayoutParams(barWidth, barHeight);
            ps[0].leftMargin = buttonWidth / 2 - barWidth / 2;
            ps[0].topMargin = buttonHeight / 2 - barHeight * 11 / 4;
            ps[1] = new FrameLayout.LayoutParams(barWidth, barHeight);
            ps[1].leftMargin = buttonWidth / 2 - barWidth / 2;
            ps[1].topMargin = buttonHeight / 2 - barHeight * 5 / 4;
            ps[2] = new FrameLayout.LayoutParams(barWidth, barHeight);
            ps[2].leftMargin = buttonWidth / 2 - barWidth / 2;
            ps[2].topMargin = buttonHeight / 2 + barHeight / 4;
            ps[3] = new FrameLayout.LayoutParams(barWidth, barHeight);
            ps[3].leftMargin = buttonWidth / 2 - barWidth / 2;
            ps[3].topMargin = buttonHeight / 2 + barHeight * 7 / 4;
        }
        // to support RTL mode
        for (int i = 0; i < ps.length; i++) ps[i].gravity = Gravity.TOP | Gravity.LEFT;
        return ps;
    }
}
