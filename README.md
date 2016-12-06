# BoomMenu 
[![WoWoViewPager](https://github.com/Nightonke/WoWoViewPager/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/WoWoViewPager)
[![BoomMenu](https://github.com/Nightonke/BoomMenu/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/BoomMenu)
[![CoCoin](https://github.com/Nightonke/CoCoin/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/CoCoin)
[![BlurLockView](https://github.com/Nightonke/BlurLockView/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/BlurLockView)
[![LeeCo](https://github.com/Nightonke/LeeCo/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/LeeCo)
[![GithubWidget](https://github.com/Nightonke/GithubWidget/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/GithubWidget)
[![JellyToggleButton](https://github.com/Nightonke/JellyToggleButton/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/JellyToggleButton)
[![FaceOffToggleButton](https://github.com/Nightonke/FaceOffToggleButton/blob/master/app/src/main/res/mipmap-hdpi/ic_launcher.png?raw=true)](https://github.com/Nightonke/FaceOffToggleButton)

Tired of these menu buttons?

![Old Menu Buttons](https://github.com/Nightonke/BoomMenu/raw/master/Pictures/old_action_bar_menu.png)

Why not try these:

![Circle](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/show_circle.gif?raw=true)
![Ham](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/show_ham.gif?raw=true)

![List](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/show_list.gif?raw=true)
![Share](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/show_share.gif?raw=true)

Yes, this library is about a menu which can ... BOOM!  
Looking for iOS version? Check [here](https://github.com/Nightonke/VHBoomMenuButton)

# Guide

[中文文档](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md)  
[Gradle & Maven](https://github.com/Nightonke/BoomMenu#gradle-and-maven)  
[Note](https://github.com/Nightonke/BoomMenu#note)  
[Demo](https://github.com/Nightonke/BoomMenu#demo)  

###Usage

1. [Easy to Use in 3 Steps](https://github.com/Nightonke/BoomMenu#easy-to-use-in-3-steps)
2. [Use in Action Bar](https://github.com/Nightonke/BoomMenu#use-in-action-bar)
3. [Use in Floating Action Button](https://github.com/Nightonke/BoomMenu#use-in-floating-action-button)
4. [Use in List](https://github.com/Nightonke/BoomMenu#use-in-list)
5. [Use in Share Style](https://github.com/Nightonke/BoomMenu#use-in-share-style)
6. [Use with Builder](https://github.com/Nightonke/BoomMenu#use-with-builder)
4. [Hamburger Button and Circle Button](https://github.com/Nightonke/BoomMenu#hamburger-button-and-circle-button)
5. [Number of Sub Buttons](https://github.com/Nightonke/BoomMenu#number-of-sub-buttons)
6. [Boom Types](https://github.com/Nightonke/BoomMenu#boom-types)
7. [Place Types](https://github.com/Nightonke/BoomMenu#place-types)
8. [Ease Types](https://github.com/Nightonke/BoomMenu#ease-types)
9. [Boom Animation Duration](https://github.com/Nightonke/BoomMenu#boom-animation-duration)
10. [Animation Start Delay](https://github.com/Nightonke/BoomMenu#animation-start-delay)
11. [Rotation Degree](https://github.com/Nightonke/BoomMenu#rotation-degree)
12. [Auto Dismiss](https://github.com/Nightonke/BoomMenu#auto-dismiss)
13. [Cancelable](https://github.com/Nightonke/BoomMenu#cancelable)
14. [Show Order and Hide Order](https://github.com/Nightonke/BoomMenu#show-order-and-hide-order)
15. [Sub Buttons Click Listener](https://github.com/Nightonke/BoomMenu#sub-buttons-click-listener)
16. [Animation Listener](https://github.com/Nightonke/BoomMenu#animation-listener)
17. [Click Effects](https://github.com/Nightonke/BoomMenu#click-effects)
18. [Sub Button Texts Color](https://github.com/Nightonke/BoomMenu#sub-button-texts-color)
19. [Dim Types](https://github.com/Nightonke/BoomMenu#dim-types)
20. [Shadow of Sub Buttons and Boom Button](https://github.com/Nightonke/BoomMenu#shadow-of-sub-buttons-and-boom-button)
21. [Get States and Dismiss](https://github.com/Nightonke/BoomMenu#get-states-and-dismiss)
22. [Get Sub Views of Sub Button](https://github.com/Nightonke/BoomMenu#get-sub-views-of-sub-button)

[Versions](https://github.com/Nightonke/BoomMenu#versions)  
[Todo](https://github.com/Nightonke/BoomMenu#todo)  
[License](https://github.com/Nightonke/BoomMenu#license)  

# Gradle and Maven
Just add the "compile 'com.nightonke:BoomMenu:1.0.9'" in your build.gradle of your module.  
```
dependencies {
    ...
    compile 'com.nightonke:boommenu:1.0.9'
    ...
}
```
Or maven:
```maven
<dependency>
  <groupId>com.nightonke</groupId>
  <artifactId>boommenu</artifactId>
  <version>1.0.9</version>
  <type>pom</type>
</dependency>
```

# Note
1. I use the ShadowLayout from [dmytrodanylyk-ShadowLayout](https://github.com/dmytrodanylyk/shadow-layout) to create the shadow effect of the buttons.
2. Boom menu button can be used in list since version 1.0.4.

# Demo
You can check most of the options that you can set when using boom menu button in this demo. When you read the code of the demo, don't be afraid of the length of the code in MainActivity.class. Most of codes are for the logic of the RadioGroups.  
![Boom V1.0.9](https://github.com/Nightonke/BoomMenu/blob/master/Apk/BoomMenu%20V1.0.9.png)  
Or by link:  
[Boom V1.0.9 in Github](https://github.com/Nightonke/BoomMenu/blob/master/Apk/BoomMenu%20V1.0.9.apk?raw=true)  
[Boom V1.0.9 in Fir](http://fir.im/tv85)  

# Usage

### Easy to Use in 3 Steps
Check the code in [EaseUseActivity](https://github.com/Nightonke/BoomMenu/blob/master/app/src/main/java/com/nightonke/boommenusample/EasyUseActivity.java) and you will found out all to do are 3 steps:

**1**.Add BoomMenuButton in xml file:
```xml
<com.nightonke.boommenu.BoomMenuButton
    android:id="@+id/boom"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_alignParentBottom="true"
    android:layout_alignParentRight="true"
    android:layout_alignParentEnd="true"
    android:layout_margin="20dp"
    app:boom_inActionBar="false"
    app:boom_button_color="@color/colorPrimary"
    app:boom_button_pressed_color="@color/colorPrimary"
    />
```

**2.**Get the view in xml in onCreate() method:
```java
boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);
```

**3.**Initialize the boom menu button in the onWindowFocusChanged() method in activity:
```java
@Override
public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    
    boomMenuButton.init(
            subButtonDrawables, // The drawables of images of sub buttons. Can not be null.
            subButtonTexts,     // The texts of sub buttons, ok to be null.
            subButtonColors,    // The colors of sub buttons, including pressed-state and normal-state.
            ButtonType.HAM,     // The button type.
            BoomType.PARABOLA,  // The boom type.
            PlaceType.HAM_3_1,  // The place type.
            null,               // Ease type to move the sub buttons when showing.
            null,               // Ease type to scale the sub buttons when showing.
            null,               // Ease type to rotate the sub buttons when showing.
            null,               // Ease type to move the sub buttons when dismissing.
            null,               // Ease type to scale the sub buttons when dismissing.
            null,               // Ease type to rotate the sub buttons when dismissing.
            null                // Rotation degree.
    ); 
}
```

### Use in Action Bar

To add boom menu button in action bar just:

**1**.Create your own action bar layout, custom_actionbar.xml:
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:background="@color/transparent">

    <com.nightonke.boommenu.BoomMenuButton
        android:id="@+id/boom"
        android:layout_width="56dp"
        android:layout_height="match_parent"
        android:layout_alignParentStart="true"
        android:layout_alignParentLeft="true"
        android:layout_centerVertical="true"
        android:background="?android:actionBarItemBackground"
        app:boom_inActionBar="true"
        />

    <TextView
        android:id="@+id/title_text"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerVertical="true"
        android:textAllCaps="true"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:textColor="#fff"
        android:layout_toRightOf="@+id/boom"
        android:layout_toEndOf="@+id/boom"
        />

</RelativeLayout>
```
**2**.Custom the default action bar in onCreate() method:
```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ActionBar mActionBar = getSupportActionBar();
    mActionBar.setDisplayShowHomeEnabled(false);
    mActionBar.setDisplayShowTitleEnabled(false);
    LayoutInflater mInflater = LayoutInflater.from(this);

    mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
    TextView mTitleTextView = (TextView) mCustomView.findViewById(R.id.title_text);
    mTitleTextView.setText(R.string.app_name);

    boomMenuButtonInActionBar = (BoomMenuButton) mCustomView.findViewById(R.id.boom);
    boomMenuButtonInActionBar.setOnSubButtonClickListener(this);
    boomMenuButtonInActionBar.setAnimatorListener(this);

    mActionBar.setCustomView(mCustomView);
    mActionBar.setDisplayShowCustomEnabled(true);

    ((Toolbar) mCustomView.getParent()).setContentInsetsAbsolute(0,0);
}
```
**3**.Initialize the boom menu button in the onWindowFocusChanged() method in activity. Just like what we do in the step3 in [Easy to Use in 3 Steps](https://github.com/Nightonke/BoomMenu#easy-to-use-in-3-steps)

###Use in Floating Action Button

Similar with above. But just add some params in xml:
```xml
<com.nightonke.boommenu.BoomMenuButton
    android:id="@+id/boom"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:boom_inActionBar="false"
    app:boom_button_color="@color/colorPrimary"
    app:boom_button_pressed_color="@color/colorPrimary"
    />
```

| Param    | Type    | What It Does   |
| ------------- |-------------| -----|
| app:boom_inActionBar | boolean | true for boom menu button in action bar |
| app:boom_inList | boolean | true for boom menu button in list |
| app:boom_button_color | color | the color of the boom menu button, only work in floating action button |
| app:boom_button_pressed_color | color | the color when pressing the boom menu button, only work when the ClickEffect is normal |

### Use in List

To add boom menu button in list just:

**1**.Create your list-item layout, notice that the app:boom_inList value should be true:
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="horizontal"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/text_view"
        android:layout_width="0dp"
        android:layout_height="56dp"
        android:layout_weight="1"
        android:layout_marginStart="15dp"
        android:layout_marginLeft="15dp"
        android:gravity="center_vertical"
        />

    <com.nightonke.boommenu.BoomMenuButton
        android:id="@+id/boom_circle"
        android:layout_width="56dp"
        android:layout_height="match_parent"
        app:boom_inList="true"
        />

    <com.nightonke.boommenu.BoomMenuButton
        android:id="@+id/boom_ham"
        android:layout_width="56dp"
        android:layout_height="match_parent"
        app:boom_inList="true"
        />

</LinearLayout>
```
**2**.Init the boom menu button with delays in adapter:
```java
@Override
public View getView(int position, View convertView, final ViewGroup parent) {

    ...

    viewHolder.circleBoomMenuButton.postDelayed(new Runnable() {
        @Override
        public void run() {
            viewHolder.circleBoomMenuButton.init(
                    circleSubButtonDrawables, // The drawables of images of sub buttons. Can not be null.
                    circleSubButtonTexts,     // The texts of sub buttons, ok to be null.
                    subButtonColors,          // The colors of sub buttons, including pressed-state and normal-state.
                    ButtonType.CIRCLE,        // The button type.
                    BoomType.PARABOLA,        // The boom type.
                    PlaceType.CIRCLE_3_1,     // The place type.
                    null,                     // Ease type to move the sub buttons when showing.
                    null,                     // Ease type to scale the sub buttons when showing.
                    null,                     // Ease type to rotate the sub buttons when showing.
                    null,                     // Ease type to move the sub buttons when dismissing.
                    null,                     // Ease type to scale the sub buttons when dismissing.
                    null,                     // Ease type to rotate the sub buttons when dismissing.
                    null                      // Rotation degree.
            );
            viewHolder.hamBoomMenuButton.setSubButtonShadowOffset(
                    Util.getInstance().dp2px(2), Util.getInstance().dp2px(2));
            viewHolder.circleBoomMenuButton.setSubButtonShadowOffset(
                    Util.getInstance().dp2px(2), Util.getInstance().dp2px(2));
        }
    }, 1);

    ...
    
}
```
For more information, please check [ListViewActivity.class](https://github.com/Nightonke/BoomMenu/blob/master/app/src/main/java/com/nightonke/boommenusample/ListViewActivity.java)

### Use in Share Style

Share style is the new place type of BMB in version 1.0.6. You can see the effect in the gifs. To use this, just change the place type as ```PlaceType.SHARE_X_X```. Just try it in the demo.

You can set the width of lines in share icon with ```setShareLineWidth(float width)``` and change the lines colors with ```setShareLine1Color(int color)``` and ```setShareLine2Color(int color)```.

For more information, please check [ShareActivity.class](https://github.com/Nightonke/BoomMenu/blob/master/app/src/main/java/com/nightonke/boommenusample/ShareActivity.java)

### Use with Builder

Thanks @demolot to remind me of using Builder. Now you can use Builder to initialize the BMB more convenient.
```
@Override
public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);

    // this is an example to show how to use the builder
    new BoomMenuButton.Builder()
            // set all sub buttons with subButtons method
            //.subButtons(subButtonDrawables, subButtonColors, subButtonTexts)
            // or add each sub button with addSubButton method
            .addSubButton(this, R.drawable.boom, subButtonColors[0], "BoomMenuButton")
            .addSubButton(this, R.drawable.java, subButtonColors[1], "View source code")
            .addSubButton(this, R.drawable.github, subButtonColors[2], "Follow me")
            .frames(80)
            .duration(800)
            .delay(100)
            .showOrder(OrderType.RANDOM)
            .hideOrder(OrderType.RANDOM)
            .button(ButtonType.HAM)
            .boom(BoomType.PARABOLA_2)
            .place(PlaceType.HAM_3_1)
            .showMoveEase(EaseType.EaseOutBack)
            .hideMoveEase(EaseType.EaseOutCirc)
            .showScaleEase(EaseType.EaseOutBack)
            .hideScaleType(EaseType.EaseOutCirc)
            .rotateDegree(720)
            .showRotateEase(EaseType.EaseOutBack)
            .hideRotateType(EaseType.Linear)
            .autoDismiss(true)
            .cancelable(true)
            .dim(DimType.DIM_6)
            .clickEffect(ClickEffectType.RIPPLE)
            .boomButtonShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
            .subButtonsShadow(Util.getInstance().dp2px(2), Util.getInstance().dp2px(2))
            .subButtonTextColor(Color.BLACK)
            .onBoomButtonBlick(null)
            .animator(null)
            .onSubButtonClick(null)
            // this only work when the place type is SHARE_X_X
            .shareStyle(0, 0, 0)
            .init(boomMenuButton);
}
```

For more information, please check [BuilderActivity.class](https://github.com/Nightonke/BoomMenu/blob/master/app/src/main/java/com/nightonke/boommenusample/BuilderActivity.java)

### Hamburger Button and Circle Button
There are 2 types of sub buttons in BMB(boom menu button). Hamburger and circle. You can use ```ButtonType.HAM``` and ```ButtonType.CIRCLE``` to initialize the BMB. 

### Number of Sub Buttons
For hamberger type, there are [1, 4] sub buttons. For circle type, there are [1, 9] sub buttons.

### Boom Types
There are 5 boom types provided in this version. They are ```BoomType.LINE```, ```BoomType.PARABOLA_2``` , ```BoomType.HORIZONTAL_THROW```, ```BoomType.PARABOLA_2``` and ```BoomType.HORIZONTAL_THROW_2```. Just try them for fun in the demo. You can choose your favorite BoomType when initializing the BMB, or set it:
```java
setBoomType(newBoomType);
```

### Place Types
There are 32 types for placing the sub buttons in BMB or in the screen. You can use ```PlaceType.CIRCLE_X_X``` and ```PlaceType.HAM_X_X``` (the former X is the number of sub buttons and the latter X is type number) to initialize BMB.  
![PlayType 1~8](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/place_type_1.png)  
![PlayType 9~16](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/place_type_2.png)  
![PlayType 17~24](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/place_type_3.png)  
![PlayType 25~32](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/place_type_4.png)  
For more information for Place type, please check [PlaceType.class](https://github.com/Nightonke/BoomMenu/blob/master/boommenu/src/main/java/com/nightonke/boommenu/Types/PlaceType.java)

### Ease Types
You can set 30 ease types for 6 part of animations when the BMB is showing or hiding. You can set the moving, scaling and rotating or showing and hiding animation when initializing the BMB, or set them to null to use the default ease types. And also, you can set 6 types by the setters:
```java
setShowMoveEaseType(showMoveEaseType);

setShowScaleEaseType(showScaleEaseType);

setShowRotateEaseType(showRotateEaseType);

setHideMoveEaseType(hideMoveEaseType);

setHideScaleEaseType(hideScaleEaseType);

setHideRotateEaseType(hideRotateEaseType);
```
For more ease types, you can check [Ease Type Package](https://github.com/Nightonke/BoomMenu/tree/master/boommenu/src/main/java/com/nightonke/boommenu/Eases). Or check the [library](https://github.com/Nightonke/WoWoViewPager#ease) that I made(This part is the same).

### Boom Animation Duration
Set the duration of the boom animation by ```setDuration(duration)``` (in ms). The default duration is 800ms.

### Animation Start Delay
Use ```setDelay(delay)``` to set the delay between each 2 sub buttons(in ms). For instance, if the delay is 0, then all the sub buttons will boom out at the same time. The default delay is 100ms.

### Rotation Degree
You can set the degree to rotate the sub button. But only for circle types, I forbade applying rotation animation to hamburger types button because the shadow or a rolling rectangle is hard to display. The default rotation degree is 720. Set the degree when initializing BMB or use ```setRotateDegree(rotateDegree);```

### Auto Dismiss
When you click a sub button, the BMB will automatically hide all the sub buttons. If you want to stop this, just use ```setAutoDismiss(autoDismiss)``` to set autoDismiss to false.

### Cancelable
When click other place except the sub buttons, the BMB will hide all the sub buttons. You can use ```setCancelable(cancelable)``` to set the value to false. But remember to set one button to perform the cancelable job, otherwise the BMB will be uncancelable.

### Show Order and Hide Order
Set the order of showing or hiding sub buttons with ```OrderType.DEFAULT```, ```OrderType.REVERSE``` and ```OrderType.RANDOM```. Use ```setShowOrderType(showOrderType)``` and ```setHideOrderType(hideOrderType)``` to set these values.

### Sub Buttons Click Listener
```java
boomMenuButton.setOnSubButtonClickListener(new BoomMenuButton.OnSubButtonClickListener() {
    @Override
    public void onClick(int buttonIndex) {
        // return the index of the sub button clicked
    }
});
```

### Animation Listener
```java
boomInfo.setAnimatorListener(new BoomMenuButton.AnimatorListener() {
    @Override
    public void toShow() {
        // the moment when the BMB is clicked and the showing animation is about to start
    }

    @Override
    public void showing(float fraction) {
        // the showing animation is playing, the fraction is the process of animation
    }

    @Override
    public void showed() {
        // the showing animation is just played 
    }

    @Override
    public void toHide() {
        // the BMB is about to play the hiding animation
    }

    @Override
    public void hiding(float fraction) {
        // the hiding animation is playing
    }

    @Override
    public void hided() {
        // the hiding animation is just played
    }
});
```

### Click Effects
Use ```setClickEffectType(clickEffectType)``` set click effect of all the buttons of BMB(including itself). Use ```ClickEffectType.RIPPLE``` to set the ripple effect(only work at or after Android 5.0) or ```ClickEffectType.NORMAL``` to set the normal effect of buttons.

### Sub Button Texts Color
Use ```setTextViewColor(int color)``` to set the color of all the textviews of sub buttons. Or use ```setTextViewColor(int[] colors)``` to set different color of textviews of sub buttons.

### Dim Types
You can use DimType to control the dim degree when showing the sub buttons. You may notice that in the demo, when you click the floating action button, the background would not be dim. But, when you click the BMB in the action bar, the background would be dim. There are 10 values of dim degree:
```java
public enum DimType {
    DIM_0(Color.parseColor("#00000000")),
    DIM_1(Color.parseColor("#11000000")),
    DIM_2(Color.parseColor("#22000000")),
    DIM_3(Color.parseColor("#33000000")),
    DIM_4(Color.parseColor("#44000000")),
    DIM_5(Color.parseColor("#55000000")),
    DIM_6(Color.parseColor("#66000000")),
    DIM_7(Color.parseColor("#77000000")),
    DIM_8(Color.parseColor("#88000000")),
    DIM_9(Color.parseColor("#99000000"));
    
    public int value;

    DimType(int value) {
        this.value = value;
    }
}
```
Use ```setDimType(DimType dimType)``` to set the dim degree of BMB. For instance, you can use ```boomMenuButton.setDimType(DimType.DIM_0);``` to keep the background light when showing sub buttons.

### Shadow of Sub Buttons and Boom Button
You can set the offset of the shadow of sub buttons and the BMB. Use ```setSubButtonShadowOffset(float xOffset, float yOffset)``` (in pixel)to set the offset of sub buttons and ```setBoomButtonShadowOffset(float xOffset, float yOffset)``` to the BMB's.

### Get States and Dismiss
You can get the current state of BMB with:
```java
boolean isClosed();

boolean isClosing();

boolean isOpen();

boolean isOpening();
```

And use ```boolean dismiss()``` to force the BMB to play hiding animation. Returns true if the BMB is going to hide, returns false if the BMB cannot hide right now(because it is showing, hiding or hided).

### Get Sub Views of Sub Button
If you wanna modify the sub views in the sub button. There are several methods to do that:
```java
ImageView[] getImageViews();

TextView[] getTextViews();
```

# Versions
### 1.0.1  
First version.
### 1.0.2  
Fix a bug that now BMB should be able to used in version below lollipop.
### 1.0.3
Fix the bug that's in Android 4.0, the image of the circle button is black.
### 1.0.4
Now the BMB can be used in list.
### 1.0.5
Share style.
### 1.0.6
Setters for share style.
### 1.0.7
Thanks @hisham2007 to remind me of the RTL bug. Now BMB is able to support RTL mode.  
Thanks @demolot to remind me use the Builder to init the BMB. For more information, please check [Use with BMB](https://github.com/Nightonke/BoomMenu#use-with-builder).
### 1.0.8
Try to fix the memory optimization issue, but I'm not sure whether it works.
### 1.0.9
Try to fix the null-pointer exception.

# Todo
1. Particle effects are coming soon.
2. ~~Make BMB ready for using in listview~~.  
3. Blur background.

# License

    Copyright 2016 Nightonke

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
