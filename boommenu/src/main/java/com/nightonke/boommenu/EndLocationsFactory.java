package com.nightonke.boommenu;

import com.nightonke.boommenu.Types.PlaceType;

/**
 * Created by Weiping on 2016/4/1.
 */
public class EndLocationsFactory {
    
    public static int[][] getEndLocations(
            PlaceType placeType, 
            int screenWidth, 
            int screenHeight, 
            int buttonWidth, 
            int buttonHeight) {
        int[][] endLocations = new int[BoomMenuButton.MAX_CIRCLE_BUTTON_NUMBER][2];

        if (placeType.equals(PlaceType.CIRCLE_1_1)) {
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_2_1)) {
            endLocations[0][0] = screenWidth / 3 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth * 2 / 3 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_2_2)) {
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 3 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight * 2 / 3 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_3_1)) {
            int dis = buttonWidth * 9 / 8;
            endLocations[0][0] = screenWidth / 2 - dis - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_3_2)) {
            int dis = buttonWidth * 9 / 8;
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 + dis - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_3_3)) {
            int b = screenWidth / 6;
            int c = (int)(2 * b / Math.sqrt(3));
            int a = c / 2;
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - c - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - b - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 + a - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + b - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 + a - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_3_4)) {
            int b = screenWidth / 6;
            int c = (int)(2 * b / Math.sqrt(3));
            int a = c / 2;
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 + c - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - b - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - a - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + b - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - a - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_4_1)) {
            endLocations[0][0] = screenWidth / 3 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - screenWidth / 6 - buttonWidth / 2;
            endLocations[1][0] = screenWidth * 2 / 3 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - screenWidth / 6 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 3 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 + screenWidth / 6 - buttonWidth / 2;
            endLocations[3][0] = screenWidth * 2 / 3 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 + screenWidth / 6 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_4_2)) {
            double s2 = Math.sqrt(2);
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = (int) (screenHeight  / 2 - screenWidth / 3 / s2 - buttonWidth / 2);
            endLocations[1][0] = (int) (screenWidth  / 2 + screenWidth / 3 / s2 - buttonWidth / 2);
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[2][1] = (int) (screenHeight  / 2 + screenWidth / 3 / s2 - buttonWidth / 2);
            endLocations[3][0] = (int) (screenWidth  / 2 - screenWidth / 3 / s2 - buttonWidth / 2);
            endLocations[3][1] = screenHeight / 2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_5_1)) {
            double s3 = Math.sqrt(3);
            int h = screenHeight / 2;
            endLocations[0][0] = screenWidth / 4 - buttonWidth / 2;
            endLocations[0][1] = h - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[1][1] = h - buttonWidth / 2;
            endLocations[2][0] = screenWidth * 3 / 4 - buttonWidth / 2;
            endLocations[2][1] = h - buttonWidth / 2;
            endLocations[3][0] = screenWidth * 3 / 8 - buttonWidth / 2;
            endLocations[3][1] = (int) (h + s3 / 8 * screenWidth - buttonWidth / 2);
            endLocations[4][0] = screenWidth * 5 / 8 - buttonWidth / 2;
            endLocations[4][1] = (int) (h + s3 / 8 * screenWidth - buttonWidth / 2);
        }
        else if (placeType.equals(PlaceType.CIRCLE_5_2)) {
            double s3 = Math.sqrt(3);
            int h = screenHeight / 2;
            endLocations[0][0] = screenWidth * 3 / 8 - buttonWidth / 2;
            endLocations[0][1] = h - buttonWidth / 2;
            endLocations[1][0] = screenWidth * 5 / 8 - buttonWidth / 2;
            endLocations[1][1] = h - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 4 - buttonWidth / 2;
            endLocations[2][1] = (int) (h + s3 / 8 * screenWidth - buttonWidth / 2);
            endLocations[3][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[3][1] = (int) (h + s3 / 8 * screenWidth - buttonWidth / 2);
            endLocations[4][0] = screenWidth * 3 / 4 - buttonWidth / 2;
            endLocations[4][1] = (int) (h + s3 / 8 * screenWidth - buttonWidth / 2);
        }
        else if (placeType.equals(PlaceType.CIRCLE_5_3)) {
            int dis = (int) ((buttonWidth * 9 / 8) / Math.sqrt(2));
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - dis - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - dis - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - dis - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 + dis - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 + dis - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_5_4)) {
            int dis = buttonWidth * 9 / 8;
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - dis - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_1)) {
            int dis1 = buttonWidth * 9 / 8;
            int dis2 = buttonWidth * 9 / 16;
            endLocations[0][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_2)) {
            int dis1 = buttonWidth * 9 / 8;
            int dis2 = buttonWidth * 9 / 16;
            endLocations[0][0] = screenWidth / 2 - dis2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 + dis2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 - dis2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 + dis2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - dis2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 + dis2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_3)) {
            int dis1 = buttonWidth * 9 / 8;
            int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
            endLocations[0][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_4)) {
            int dis1 = buttonWidth * 9 / 8;
            int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 + dis2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - dis2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 - dis2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 - dis1 / 2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_5)) {
            int dis1 = buttonWidth * 9 / 16;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - dis1 * 2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 + dis1 * 2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_6_6)) {
            int dis1 = buttonWidth * 9 / 16;
            int dis2 = (int) (dis1 * Math.sqrt(3));
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - dis1 * 2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 + dis1 * 2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_7_1)) {
            int dis = buttonWidth * 9 / 8;
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 + dis - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - dis - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 + dis - buttonWidth / 2;
            endLocations[6][0] = screenWidth / 2 + dis - buttonWidth / 2;
            endLocations[6][1] = screenHeight / 2 + dis - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_7_2)) {
            int dis = buttonWidth * 9 / 8;
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 + dis - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 + dis - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - dis - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 - dis - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 - dis - buttonWidth / 2;
            endLocations[6][0] = screenWidth / 2 + dis - buttonWidth / 2;
            endLocations[6][1] = screenHeight / 2 - dis - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_7_3)) {
            int dis1 = buttonWidth * 9 / 8;
            int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
            endLocations[6][0] = screenWidth / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[6][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_7_4)) {
            int dis1 = buttonWidth * 9 / 8;
            int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 + dis2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 - dis2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[6][0] = screenWidth / 2 - dis2 - buttonWidth / 2;
            endLocations[6][1] = screenHeight / 2 - dis1 / 2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_8_1)) {
            int dis1 = buttonWidth * 9 / 8;
            int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
            endLocations[0][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - dis2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
            endLocations[6][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[6][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
            endLocations[7][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[7][1] = screenHeight / 2 + dis2 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_8_2)) {
            int dis1 = buttonWidth * 9 / 8;
            int dis2 = (int) (dis1 / 2 * Math.sqrt(3));
            endLocations[0][0] = screenWidth / 2 - dis2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 - dis2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 + dis2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[6][0] = screenWidth / 2 + dis2 - buttonWidth / 2;
            endLocations[6][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[7][0] = screenWidth / 2 + dis2 - buttonWidth / 2;
            endLocations[7][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_8_3)) {
            int dis1 = buttonWidth * 9 / 8;
            endLocations[0][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[6][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[6][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[7][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[7][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_9_1)) {
            int dis1 = buttonWidth * 9 / 8;
            endLocations[0][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
            endLocations[6][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[6][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[7][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[7][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[8][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[8][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.CIRCLE_9_2)) {
            int dis1 = (int) (buttonWidth * 8 / 8 * Math.sqrt(2));
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - dis1 - buttonWidth / 2;
            endLocations[1][0] = screenWidth / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[2][0] = screenWidth / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[3][0] = screenWidth / 2 - dis1 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[4][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[4][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[5][0] = screenWidth / 2 + dis1 - buttonWidth / 2;
            endLocations[5][1] = screenHeight / 2 - buttonWidth / 2;
            endLocations[6][0] = screenWidth / 2 - dis1 / 2 - buttonWidth / 2;
            endLocations[6][1] = screenHeight / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[7][0] = screenWidth / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[7][1] = screenHeight / 2 + dis1 / 2 - buttonWidth / 2;
            endLocations[8][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[8][1] = screenHeight / 2 + dis1 - buttonWidth / 2;
        }
        else if (placeType.equals(PlaceType.HAM_1_1)) {
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;    
            endLocations[0][1] = screenHeight / 2 - buttonHeight / 2;
        }
        else if (placeType.equals(PlaceType.HAM_2_1)) {
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonHeight;
            endLocations[1][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 + buttonHeight;
        }
        else if (placeType.equals(PlaceType.HAM_3_1)) {
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonHeight * 7 / 4;
            endLocations[1][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonHeight / 2;
            endLocations[2][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 + buttonHeight * 3 / 4;
        }
        else if (placeType.equals(PlaceType.HAM_4_1)) {
            endLocations[0][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[0][1] = screenHeight / 2 - buttonHeight * 23 / 10;
            endLocations[1][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[1][1] = screenHeight / 2 - buttonHeight * 11/ 10;
            endLocations[2][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[2][1] = screenHeight / 2 + buttonHeight / 10;
            endLocations[3][0] = screenWidth / 2 - buttonWidth / 2;
            endLocations[3][1] = screenHeight / 2 + buttonHeight * 13 / 10;
        } else if (PlaceType.SHARE_3_1.v <= placeType.v && placeType.v <= PlaceType.SHARE_9_2.v) {
            // the end locations of share is the same as the circle's
            endLocations = EndLocationsFactory.getEndLocations(
                    PlaceType.valueOf(placeType.v -
                            (PlaceType.SHARE_3_1.v - PlaceType.CIRCLE_3_1.v)),
                    screenWidth, screenHeight, buttonWidth, buttonWidth);
        }
        
        return endLocations;
    }
    
}
