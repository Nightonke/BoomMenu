# BoomMenu

是否觉得这种菜单按钮有点无聊？

![Old Menu Buttons](https://github.com/Nightonke/BoomMenu/raw/master/Pictures/old_action_bar_menu.png)

要不，试试这个：

![Circle](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/show_circle.gif?raw=true)
![Ham](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/show_ham.gif?raw=true)

BoomMenu可以让你的菜单按钮 ... 瞬间爆炸！

# Guide

[English Version](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md)  
[Gradle & Maven](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#gradle-and-maven)  
[Note](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#note)  
[Demo](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#demo)  

###Usage

1. [Easy to Use in 3 Steps](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#easy-to-use-in-3-steps)
2. [Use in Action Bar](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#use-in-action-bar)
3. [Use in Floating Action Button](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#use-in-floating-action-button)
4. [Hamburger Button and Circle Button](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#hamburger-button-and-circle-button)
5. [Number of Sub Buttons](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#number-of-sub-buttons)
6. [Boom Types](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#boom-types)
7. [Place Types](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#place-types)
8. [Ease Types](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#ease-types)
9. [Boom Animation Duration](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#boom-animation-duration)
10. [Animation Start Delay](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#animation-start-delay)
11. [Rotation Degree](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#rotation-degree)
12. [Auto Dismiss](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#auto-dismiss)
13. [Cancelable](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#cancelable)
14. [Show Order and Hide Order](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#show-order-and-hide-order)
15. [Sub Buttons Click Listener](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#sub-buttons-click-listener)
16. [Animation Listener](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#animation-listener)
17. [Click Effects](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#click-effects)
18. [Sub Button Texts Color](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#sub-button-texts-color)
19. [Dim Types](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#dim-types)
20. [Shadow of Sub Buttons and Boom Button](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#shadow-of-sub-buttons-and-boom-button)
21. [Get States and Dismiss](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#get-states-and-dismiss)
22. [Get Sub Views of Sub Button](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#get-sub-views-of-sub-button)

[Versions](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#versions)  
[Todo](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#todo)  
[License](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#license)  

# Gradle and Maven
添加 "compile 'com.nightonke:boommenu:1.0.1'" 到app模块中的build.gradle即可：  
```
dependencies {
    ...
    compile 'com.nightonke:boommenu:1.0.1'
    ...
}
```
或者通过 maven:
```maven
<dependency>
  <groupId>com.nightonke</groupId>
  <artifactId>boommenu</artifactId>
  <version>1.0.1</version>
  <type>pom</type>
</dependency>
```

# Note
1. 使用了 [dmytrodanylyk-ShadowLayout](https://github.com/dmytrodanylyk/shadow-layout) 来为按钮产生阴影效果。
2. BoomMenuButton现在还**没可以**用于listview中，因为bitmap回收处理没有弄好。但是这并**不影响**在ActionBar或者FloatingActionButton里面使用。如果有人知道这个bug怎么解决，请提交你的代码，万分感谢！

# Demo
你可以在这个demo中选择绝大部分的BoomMenuButton的选项来查看其不同的效果。当你查看demo的MainActivity的时候，不用担心里面过长的代码，很多代码都是为了处理RadioGroup的逻辑。   

![Boom V1.0.1](https://github.com/Nightonke/BoomMenu/blob/master/Apk/BoomMenu%20V1.0.1.png)  

通过链接下载：  

[Boom V1.0.1 in Github](https://github.com/Nightonke/BoomMenu/blob/master/Apk/BoomMenu%20V1.0.1.apk?raw=true)  
[Boom V1.0.1 in Fir](http://fir.im/tv85)  

# Usage

### Easy to Use in 3 Steps
查看demo中的一个 [简单示例](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md/blob/master/app/src/main/java/com/nightonke/boommenusample/EasyUseActivity.java) ，你就会发现，其实只需要三步即可使用：

**1**.添加BoomMenuButton到xml中：
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

**2.**在onCreate()方法中findView：  
```java
boomMenuButton = (BoomMenuButton)findViewById(R.id.boom);
```

**3.**在onWindowFocusChanged()方法中初始化BoomMenuButton：
```java
@Override
onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    
    boomMenuButton.init(
            subButtonDrawables, // 子按钮的图标Drawable数组，不可以为null
            subButtonTexts,     // 子按钮的文本String数组，可以为null
            subButtonColors,    // 子按钮的背景颜色color二维数组，包括按下和正常状态的颜色，不可为null
            ButtonType.HAM,     // 子按钮的类型
            BoomType.PARABOLA,  // 爆炸类型
            PlaceType.HAM_3_1,  // 排列类型
            null,               // 展开时子按钮移动的缓动函数类型
            null,               // 展开时子按钮放大的缓动函数类型
            null,               // 展开时子按钮旋转的缓动函数类型
            null,               // 隐藏时子按钮移动的缓动函数类型
            null,               // 隐藏时子按钮缩小的缓动函数类型
            null,               // 隐藏时子按钮旋转的缓动函数类型
            null                // 旋转角度
    ); 
}
```

### Use in Action Bar

如何在ActionBar中使用BoomMenuButton：

**1**.自定义一个ActionBar的layout，比如custom_actionbar.xml：
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
**2**.在onCreate()方法中应用你的自定义ActionBar：
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
**3**.在onWindowFocusChanged()方法中初始化BoomMenuButton。就像在[简单使用](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md#easy-to-use-in-3-steps)中介绍的一样。

###Use in Floating Action Button

如何在FloatingActionButton中使用？与上述类似，稍微修改一下xml文件：
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

| 参数    | 类型    | 干啥的   |
| ------------- |-------------| -----|
| app:boom_inActionBar | boolean |真，表示应用在ActionBar中 |
| app:boom_button_color | color | BoomMenuButton的背景色，仅仅在FloatingActionButton中有效 |
| app:boom_button_pressed_color | color | BoomMenuButton的按下背景颜色，仅仅在点击效果为Normal时有效 |

### Hamburger Button and Circle Button
在BMB(BoomMenuButton)中有两种子按钮类型， Hamburger 和 circle. You can use ```ButtonType.HAM``` and ```ButtonType.CIRCLE``` to initialize the BMB. 

### Number of Sub Buttons
For hamberger type, there are [1, 4] sub buttons. For circle type, there are [1, 9] sub buttons.

### Boom Types
There are 5 boom types provided in this version. They are ```BoomType.LINE```, ```BoomType.PARABOLA_2``` , ```BoomType.HORIZONTAL_THROW```, ```BoomType.PARABOLA_2``` and ```BoomType.HORIZONTAL_THROW_2```. Just try them for fun in the demo. You can choose your favorite BoomType when initializing the BMB, or set it:
```java
setBoomType(newBoomType);
```

### Place Types
There are 32 types for placing the sub buttons in BMB or in the screen. You can use ```PlaceType.CIRCLE_X_X``` and ```PlaceType.HAM_X_X``` (the former X is the number of sub buttons and the latter X is type number) to initialize BMB.  
![PlayType 1~8](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md/blob/master/Pictures/place_type_1.png)  
![PlayType 9~16](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md/blob/master/Pictures/place_type_2.png)  
![PlayType 17~24](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md/blob/master/Pictures/place_type_3.png)  
![PlayType 25~32](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md/blob/master/Pictures/place_type_4.png)  
For more information for Place type, please check [PlaceType.class](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md/blob/master/boommenu/src/main/java/com/nightonke/boommenu/Types/PlaceType.java)

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
For more ease types, you can check [Ease Type Package](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md/tree/master/boommenu/src/main/java/com/nightonke/boommenu/Eases). Or check the [library](https://github.com/Nightonke/WoWoViewPager#ease) that I made(This part is the same).

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
Use ```setClickEffectType(clickEffectType)``` set click effect of all the buttons of BMB(including itself). Use ```ClickEffectType.RIPPLE``` to set the ripple effect(only word after Android 5.0) or ```ClickEffectType.NORMAL``` to set the normal effect of buttons with ```setClickEffectType(clickEffectType)``` method.

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

# Todo
1. Particle effects are coming soon.
2. Make BMB ready for using in listview.  

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
