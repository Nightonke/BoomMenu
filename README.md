# BoomMenu

Tired of these menu buttons?

![Old Menu Buttons](https://github.com/Nightonke/BoomMenu/raw/master/Pictures/old_action_bar_menu.png)

Why not try these:

![Circle](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/show_circle.gif?raw=true)
![Ham](https://github.com/Nightonke/BoomMenu/blob/master/Pictures/show_ham.gif?raw=true)

Yes, this library is about a menu which can ... BOOM!

# Guide

[中文文档](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md)  
[Gradle](https://github.com/Nightonke/BoomMenu#gradle)  
[Note](https://github.com/Nightonke/BoomMenu#note)  
[Demo](https://github.com/Nightonke/BoomMenu#demo)  

###Usage

1. [Easy to Use in 3 Steps]()
2. [Use in Action Bar]()
3. [Use in Floating Action Button]()
4. [Hamburger Button and Circle Button]()
5. [Number of Sub Buttons]()
6. [Boom Types]()
7. [Place Types]()
8. [Ease Types]()
9. [Boom Animation Duration]()
10. [Animation Start Delay]()
11. [Rotation Degree]()
12. [Auto Dismiss]()
13. [Cancelable]()
14. [Show Order and Hide Order]()
15. [Sub Buttons Click Listener]()
16. [Animation Listener]()
17. [Click Effects]()
18. [Sub Button Texts Color]()
19. [Dim Types]()
20. [Shadow of Sub Buttons and Boom Button]()

[Versions](https://github.com/Nightonke/BoomMenu#versions)  
[Todo](https://github.com/Nightonke/BoomMenu#todo)  
[License](https://github.com/Nightonke/BoomMenu#license)  

# Gradle
Just add the "compile 'com.nightonke:BoomMenu:1.0.0'" in your build.gradle of your module.  
```
dependencies {
    ...
    compile 'com.nightonke:BoomMenu:1.0.0'
    ...
}
```

# Note
1. I use the ShadowLayout from [dmytrodanylyk-ShadowLayout](https://github.com/dmytrodanylyk/shadow-layout) to create the shadow effect of the buttons.
2. The boom menu button is **NOT** ready to be used in list item yet. Because the memory-leak when creating the bitmaps(I guess). But **don't worry** about using it in action bar or in floating action bar. If somebody can help me to fix the memory-leak bug, that would be so helpful and appreciated.

# Demo
You can check most of the options that you can set when using boom menu button in this demo. When you read the code of the demo, don't be afraid of the length of the code in MainActivity.class. Most of codes are for the logic of the RadioGroups.

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
| app:boom_button_color | color | the color of the boom menu button, only work in floating action button |
| app:boom_button_pressed_color | color | the color when pressing the boom menu button, only work when the ClickEffect is normal |

### Hamburger Button and Circle Button
There are 2 types of sub buttons in BMB(boom menu button). Hamburger and circle. You can use ```ButtonType.HAM``` and ```ButtonType.CIRCLE``` to initialize the BMB. 

### Number of Sub Buttons
For hamberger type, there are [1, 4] sub buttons. For circle type, there are [1, 9] sub buttons.

### Boom Types
There are 5 boom types provided in this version. They are ```BoomType.LINE```, ```BoomType.PARABOLA_2``` and ``````, ```BoomType.HORIZONTAL_THROW```, ```BoomType.PARABOLA_2``` and ```BoomType.HORIZONTAL_THROW_2```. Just try them for fun in the demo. You can choose your favorite BoomType when initializing the BMB, or set it:
```java
setBoomType(newBoomType);
```


