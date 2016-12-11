package com.nightonke.boommenu;

import com.nightonke.boommenu.Animation.BoomEnum;
import com.nightonke.boommenu.BoomButtons.BoomButtonBuilder;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 22:51 on 16/11/13
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */
class ExceptionManager {

    static void judge(PiecePlaceEnum piecePlaceEnum) {
        if (piecePlaceEnum == null || piecePlaceEnum == PiecePlaceEnum.Unknown)
            throw new RuntimeException("Unknown piece-place-enum!");
    }

    static void judge(ButtonPlaceEnum buttonPlaceEnum) {
        if (buttonPlaceEnum == null || buttonPlaceEnum == ButtonPlaceEnum.Unknown)
            throw new RuntimeException("Unknown button-place-enum!");
    }

    static void judge(ButtonEnum buttonEnum) {
        if (buttonEnum == null || buttonEnum == ButtonEnum.Unknown)
            throw new RuntimeException("Unknown button-enum!");
    }

    static void judge(BoomEnum boomEnum) {
        if (boomEnum == null || boomEnum == BoomEnum.Unknown)
            throw new RuntimeException("Unknown boom-enum!");
    }

    static void judge(ButtonEnum buttonEnum, PiecePlaceEnum piecePlaceEnum) {
        if (piecePlaceEnum == PiecePlaceEnum.Share && buttonEnum == ButtonEnum.Ham)
            throw new RuntimeException("Share style BMB is not support ham-boom-buttons");
    }

    static void judge(PiecePlaceEnum piecePlaceEnum,
                      ButtonPlaceEnum buttonPlaceEnum,
                      ArrayList<BoomButtonBuilder> builders) {
        judge(piecePlaceEnum);
        judge(buttonPlaceEnum);
        int piecePlaceNumber = piecePlaceEnum.pieceNumber();
        int buttonPlaceNumber = buttonPlaceEnum.buttonNumber();
        if (piecePlaceNumber == -1) {
            if (piecePlaceEnum == PiecePlaceEnum.Share) {
                int minPieceNumber = piecePlaceEnum.minPieceNumber();
                int maxPieceNumber = piecePlaceEnum.maxPieceNumber();
                if (buttonPlaceEnum.buttonNumber() < minPieceNumber
                        || buttonPlaceEnum.buttonNumber() > maxPieceNumber) {
                    throw new RuntimeException("ButtonPlaceEnum(" + buttonPlaceEnum + ") is not match for PiecePlaceEnum(" + piecePlaceEnum + ")!");
                } else if (builders.size() < minPieceNumber || builders.size() > maxPieceNumber) {
                    throw new RuntimeException("Number of builders(" + builders.size() + ") is not match for PiecePlaceEnum(" + piecePlaceEnum + ")!");
                }
            } else {
                throw new RuntimeException("Unknown piece-place-enum!");
            }
        }
        if (buttonPlaceNumber == -1)
            throw new RuntimeException("Unknown button-place-enum!");
        if (piecePlaceNumber != buttonPlaceNumber
                && buttonPlaceEnum != ButtonPlaceEnum.Horizontal
                && buttonPlaceEnum != ButtonPlaceEnum.Vertical
                && piecePlaceEnum != PiecePlaceEnum.Share)
            throw new RuntimeException("Number of pieces is not equal to buttons'!");
        if (builders == null || builders.size() == 0)
            throw new RuntimeException("Empty builders!");
        if (piecePlaceNumber != builders.size() && piecePlaceEnum != PiecePlaceEnum.Share)
            throw new RuntimeException("Number of builders is not equal to buttons'!");
    }

    private static ExceptionManager ourInstance = new ExceptionManager();

    public static ExceptionManager getInstance() {
        return ourInstance;
    }

    private ExceptionManager() {
    }
}
