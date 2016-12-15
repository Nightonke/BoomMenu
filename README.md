# BoomMenu 
[![Developer](https://img.shields.io/badge/Developer-Nightonke-red.svg)](https://github.com/Nightonke)
[![Demo](https://img.shields.io/badge/Demo-Download-orange.svg)](https://github.com/Nightonke/BoomMenu/blob/master/Apk/BoomMenu%20V2.0.3.apk?raw=true)
[![Download](https://api.bintray.com/packages/nightonke/maven/boommenu/images/download.svg)](https://bintray.com/nightonke/maven/boommenu)
[![Lisense](https://img.shields.io/badge/License-Apache%202-lightgrey.svg)](https://www.apache.org/licenses/LICENSE-2.0)

##2.0.0 Comes Finally

Approximately 8 months ago, I got an inspiration to creating something that can boom and show menu, which I named it Boom-Menu-Button, BMB. But at that time, I just a fresh-man in Android, knowing little about designing. The codes I wrote serveral months ago are ugly and performed low-efficient.

Between months, I always think about BMB and try to write a better design pattern for implements of BMB. My first try is [BMB-iOS](https://github.com/Nightonke/VHBoomMenuButton), which contains more family characteristics, for instance, buttons-alignment, text-inside/outside-button.

And now the BMB-Android 2.0.0 comes.

<img src="https://github.com/Nightonke/BoomMenu/blob/master/Pictures/text-inside-button.gif" width="250">
<img src="https://github.com/Nightonke/BoomMenu/blob/master/Pictures/ham-button.gif" width="250">
<img src="https://github.com/Nightonke/BoomMenu/blob/master/Pictures/text-outside-button.gif" width="250">
<img src="https://github.com/Nightonke/BoomMenu/blob/master/Pictures/actionbar-example.gif" width="250">
<img src="https://github.com/Nightonke/BoomMenu/blob/master/Pictures/list-example.gif" width="250">
<img src="https://github.com/Nightonke/BoomMenu/blob/master/Pictures/share-example.gif" width="250">

##Gradle & Maven
```
compile 'com.nightonke:boommenu:2.0.3'
```
```
<dependency>
  <groupId>com.nightonke</groupId>
  <artifactId>boommenu</artifactId>
  <version>2.0.3</version>
  <type>pom</type>
</dependency>
```

##Demo
<img src="https://github.com/Nightonke/BoomMenu/blob/master/Apk/BoomMenu.png" width="200">  
Or by link:  
[Boom V2.0.3 in Github](https://github.com/Nightonke/BoomMenu/blob/master/Apk/BoomMenu%20V2.0.3.apk?raw=true)  
[Boom V2.0.3 in Fir](http://fir.im/tv85)  

##Wiki
Check the [wiki](https://github.com/Nightonke/BoomMenu/wiki) to use BMB.

###Documentation Chapters

1. [Basic Usage](https://github.com/Nightonke/BoomMenu/wiki/Basic-Usage)  
How to use BMB in just several lines of code?  
2. [Simple Circle Button](https://github.com/Nightonke/BoomMenu/wiki/Simple-Circle-Button)  
Add simple circle buttons with just an image for each to BMB.  
<img src="https://github.com/Nightonke/BoomMenu/raw/master/Pictures/BoomButton/SimpleCircleButton.png" width="600">
3. [Text Inside Circle Button](https://github.com/Nightonke/BoomMenu/wiki/Text-Inside-Circle-Button)  
Add text inside circle buttons with a text and image inside for each to BMB.  
<img src="https://github.com/Nightonke/BoomMenu/raw/master/Pictures/BoomButton/TextInsideCircleButton.png" width="600">
4. [Text Outside Circle Button](https://github.com/Nightonke/BoomMenu/wiki/Text-Outside-Circle-Button)    
Add text outside circle buttons with a text and image outside for each to BMB.  
<img src="https://github.com/Nightonke/BoomMenu/raw/master/Pictures/BoomButton/TextOutsideCircleButton.png" width="600">
5. [Ham Button](https://github.com/Nightonke/BoomMenu/wiki/Ham-Button)  
Add ham buttons with with a title, subtitle and image inside for each to BMB.  
<img src="https://github.com/Nightonke/BoomMenu/raw/master/Pictures/BoomButton/HamButton.png" width="600">
6. [Share Style](https://github.com/Nightonke/BoomMenu/wiki/Share-Style)  
Make a share-style BMB.
7. [Button Place Alignments](https://github.com/Nightonke/BoomMenu/wiki/Button-Place-Alignments)  
Place all the buttons to anywhere on screen.  
<img src="https://github.com/Nightonke/BoomMenu/blob/master/Pictures/button-place-alignment-enum/button-place-alignment-1.png" width="200">
<img src="https://github.com/Nightonke/BoomMenu/blob/master/Pictures/button-place-alignment-enum/button-place-alignment-2.png" width="200">
<img src="https://github.com/Nightonke/BoomMenu/blob/master/Pictures/button-place-alignment-enum/button-place-alignment-3.png" width="200">
8. [Different Ways to Boom](https://github.com/Nightonke/BoomMenu/wiki/Different-Ways-to-Boom)  
Different animations when the buttons boom or re-boom.  
<img src="https://github.com/Nightonke/BoomMenu/raw/master/Pictures/boom-enum/boom-enum-parabola-2.png" width="400">
<img src="https://github.com/Nightonke/BoomMenu/raw/master/Pictures/boom-enum/boom-enum-parabola-3.png" width="400">
9. [Ease Animations for Buttons](https://github.com/Nightonke/BoomMenu/wiki/Ease-Animations-for-Buttons)  
Use different and cute ease-animations for buttons.
1. [Different Order for Buttons](https://github.com/Nightonke/BoomMenu/wiki/Different-Order-for-Buttons)  
Different order enum for boom-buttons.
10. [Other Animations Attributes for Buttons](https://github.com/Nightonke/BoomMenu/wiki/Other-Animations-Attributes-for-Buttons)  
Delay, duration, rotate-degrees, frames...
11. [Click Event and Listener](https://github.com/Nightonke/BoomMenu/wiki/Click-Event-and-Listener)  
Listener for clicking each button or animation-states.
12. [Control BMB](https://github.com/Nightonke/BoomMenu/wiki/Control-BMB)  
Boom or re-boom BMB programmatically.
13. [Use BMB in Action Bar](https://github.com/Nightonke/BoomMenu/wiki/Use-BMB-in-Action-Bar)  
How to put BMB in action bar?
14. [Use BMB in List](https://github.com/Nightonke/BoomMenu/wiki/Use-BMB-in-List)  
Matters need attention when you need a BMB in list-view or recycler-view.
15. [Use BMB in Fragment](https://github.com/Nightonke/BoomMenu/wiki/Use-BMB-in-Fragment)  
Example for use BMB in fragment.
16. [Attributes for BMB or Pieces on BMB](https://github.com/Nightonke/BoomMenu/wiki/Attributes-for-BMB-or-Pieces-on-BMB)  
How to change the size or margins of dots on BMB?
17. [Cache Optimization & Boom Area](https://github.com/Nightonke/BoomMenu/wiki/Cache-Optimization-&-Boom-Area)  
What if I want BMB to boom in just its parent-view?
1. [Version History](https://github.com/Nightonke/BoomMenu/wiki/Version-History)  
What's more for every version?  
18. [Structure for BMB](https://github.com/Nightonke/BoomMenu/wiki/Structure-for-BMB)  
Structure for BMB when I designed it, for sharing and communicating.

##Issues & Feedbacks
Try to tell me the bugs or enhancements about BMB, or contact me with Nightonke@outlook.com / 2584541288@qq.com. Before doing that, having a careful read on [readme](https://github.com/Nightonke/BoomMenu), [wiki](https://github.com/Nightonke/BoomMenu/wiki) and issues is really helpful. 

##ReadMe for Version 1.0.9 or Below
If you still wanna use version 1.0.9 or below, you can find the README below:

[English README](https://github.com/Nightonke/BoomMenu/blob/master/README-EN.md) 
[中文文档](https://github.com/Nightonke/BoomMenu/blob/master/README-ZH.md)

But I strongly suggest you to use the newest version.

##What I'm Doing
[![WoWoViewPager](https://img.shields.io/badge/%20WoWoViewPager%20-%20Android%20Library%20-brightgreen.svg)](https://github.com/Nightonke/WoWoViewPager)  
[![BoomMenuButton-Android](https://img.shields.io/badge/%20BoomMenuButton%20-%20Android%20%20Library%20-green.svg)](https://github.com/Nightonke/BoomMenu)  
[![CoCoin](https://img.shields.io/badge/%20CoCoin%20-%20Android%20App%20-yellowgreen.svg)](https://github.com/Nightonke/CoCoin)  
[![BlurLockView](https://img.shields.io/badge/%20BlurLockView%20-%20Android%20Library%20-yellowgreen.svg)](https://github.com/Nightonke/BlurLockView)  
[![LeeCo](https://img.shields.io/badge/%20LeeCo%20-%20Android%20App%20-yellow.svg)](https://github.com/Nightonke/LeeCo)  
[![GithubWidget](https://img.shields.io/badge/%20GithubWidget%20-%20Android%20App%20-orange.svg)](https://github.com/Nightonke/GithubWidget)  
[![JellyToggleButton](https://img.shields.io/badge/%20JellyToggleButton%20-%20Android%20Library%20-red.svg)](https://github.com/Nightonke/JellyToggleButton)  
[![FaceOffToggleButton](https://img.shields.io/badge/%20FaceOffToggleButton%20-%20Android%20Library%20-lightgrey.svg)](https://github.com/Nightonke/FaceOffToggleButton)  
[![BoomMenuButton-iOS](https://img.shields.io/badge/%20BoomMenuButton%20-%20iOS%20Library%20-blue.svg)](https://github.com/Nightonke/VHBoomMenuButton)  
