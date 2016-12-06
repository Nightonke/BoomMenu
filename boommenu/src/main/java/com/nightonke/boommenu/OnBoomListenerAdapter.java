package com.nightonke.boommenu;

import com.nightonke.boommenu.BoomButtons.BoomButton;

/**
 * Created by Weiping Huang at 00:23 on 16/11/29
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class OnBoomListenerAdapter implements OnBoomListener {

    /**
     * When one of the boom-button is clicked.
     *
     * @param index index of the clicked boom-button
     * @param boomButton the clicked boom-button
     */
    @Override
    public void onClicked(int index, BoomButton boomButton) {

    }

    /**
     * When the background of boom-buttons is clicked.
     */
    @Override
    public void onBackgroundClick() {

    }

    /**
     * When the BMB is going to hide its boom-buttons.
     */
    @Override
    public void onBoomWillHide() {

    }

    /**
     * When the BMB finishes hide animations.
     */
    @Override
    public void onBoomDidHide() {

    }

    /**
     * When the BMB is going to show its boom-buttons.
     */
    @Override
    public void onBoomWillShow() {

    }

    /**
     * When the BMB finished boom animations.
     */
    @Override
    public void onBoomDidShow() {

    }

}
