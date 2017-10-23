# Bottom Navigation View

 <img src="https://github.com/Nucleus-Inc/NavigationBottomBar/blob/master/graphics/image_demo2.png" width="33%" /> <img src="https://github.com/Nucleus-Inc/NavigationBottomBar/blob/master/graphics/image_demo1.png" width="33%" /> <img src="https://github.com/Nucleus-Inc/NavigationBottomBar/blob/master/graphics/image_demo4.png" width="33%" />
 
## Introduction

The Bottom Navigation View has been in the material design guidelines for some time, but it hasn’t been easy for us to implement it into our apps. Some applications have built their own solutions, whilst others have relied on third-party open-source libraries to get the job done. Now the design support library is seeing the addition of this bottom navigation bar, let’s take a dive into how we can use it and modify!

## How to use?

### To begin with we need to update our dependancy

```groovy
compile 'com.android.support:appcompat-v7:26.+'
compile 'com.android.support:design:26.+'
compile 'com.android.support:support-v4:26.+'
```
### Add the Bottom Navigation View widget

**layout/activity_main.xml**

```xml
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    tools:context="com.eti.nucleus.bottombarnavigationview.MainActivity">

    <!-- Content Container -->
 
    <android.support.design.widget.BottomNavigationView
        android:id="@+id/navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        app:itemBackground="@android:color/white"
        app:itemIconTint="@color/tinting_selected_items"
        app:itemTextColor="@color/tinting_selected_items"
        app:menu="@menu/bottombar_items"/>

</RelativeLayout>
```

You’ll notice that the widget has a couple of attributes set on it:

   * **app:itemBackground:** The background color to be used for the bottom navigation menu
   * **app:itemIconTint:** The tint to be used for the icons in the bottom navigation menu
   * **app:itemTextColor:** The color to be used for the text in the bottom navigation menu
   * **app:menu:** The menu resource to be used to display items in the bottom navigation menu

We can also set these values programatically by using the following methods:

   * **inflateMenu(int menuResource):**  Inflate a menu for the bottom navigation view using a menu resource identifier
   * **setItemBackgroundResource(int backgroundResource):**  The background to be used for the menu items.
   * **setItemTextColor(ColorStateList colorStateList):**  A ColorStateList used to color the text used for the menu items
   * **setItemIconTintList(ColorStateList colorStateList):**  A ColorStateList used to tint the icons used for the menu items

### Create a menu to display

**menu/bottombar_itens.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:id="@+id/menu_item1"
        android:icon="@drawable/home"
        android:title="@string/menu_item1"/>
    <item
        android:id="@+id/menu_item2"
        android:icon="@drawable/search"
        android:title="@string/menu_item2"/>
    <item
        android:id="@+id/menu_item3"
        android:icon="@drawable/play"
        android:title="@string/menu_item3"/>
    <item
        android:id="@+id/menu_item4"
        android:icon="@drawable/favorite"
        android:title="@string/menu_item4"/>
    <item
        android:id="@+id/menu_item5"
        android:icon="@drawable/user"
        android:title="@string/menu_item5"/>
</menu>
```
It’s important to note that the maximum number of items we can display is 5.

### Handling Enabled / Disabled states

To make the view handle these cases we only simply need to create a selector file for our enabled / disabled colors.

**color/handling_states.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item
        android:color="@color/colorAccent"
        android:state_checked="false"/>
    <item
        android:color="@color/colorPrimary"
        android:state_checked="true"/>
</selector>
```
### Listen for click events

Now we’ve implemented our menu we need to be able to react when it’s interacted with. We can use the BottomNavigationView **setOnNavigationItemSelectedListener** method to set a listener for menu item events:

**MainActivity.kt**

```kotlin
private lateinit var bottomNavigationView: BottomNavigationView

bottomNavigationView = findViewById(R.id.navigation)

supportFragmentManager.beginTransaction().replace(R.id.framelayout,Fragment3()).commit()
bottomNavigationView.selectedItemId = R.id.menu_item3

bottomNavigationView.setOnNavigationItemSelectedListener { item ->
    var selectedFragment: Fragment? = null
    when (item.itemId) {
        R.id.menu_item1 -> selectedFragment = Fragment1()
        R.id.menu_item2 -> selectedFragment = Fragment2()
        R.id.menu_item3 -> selectedFragment = Fragment3()
        R.id.menu_item4 -> selectedFragment = Fragment4()
        R.id.menu_item5 -> selectedFragment = Fragment5()
    }
    val transaction = supportFragmentManager.beginTransaction()
    transaction.replace(R.id.framelayout, selectedFragment)
    transaction.commit()
    true
}
```

## Features Extras

We created a file called **BottomNavigationViewHelper.kt** where all the features are implemented. 

### Remove Animation

To remove the animation, simply call the **disableShiftMode** function by passing an instance of the bottomNavigationView per parameter:

**MainActivity.kt**

```kotlin

BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)

```

### Remove Text Label

To remove the text label, simply call the **removeTextLabel** function by passing an instance of the bottomNavigationView per parameter:

**MainActivity.kt**

```kotlin

BottomNavigationViewHelper.removeTextLabel(bottomNavigationView)

```

### Modify Size Icon

To modify size icon, simply call the **sizeIcon** function by passing as instance of the bottomNavigationView, an resource display metrics and a floating value that you want to change the icon:

**MainActivity.kt**

```kotlin

BottomNavigationViewHelper.sizeIcon(bottomNavigationView,resources.displayMetrics,28f)

```

### Badges

To add a badge to the bottom bar the first step is to create a layout for it:

**layout/badge.xml**

```xml
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <TextView
        android:id="@+id/notifications.badge"
        android:layout_width="wrap_content"
        android:minWidth="20dp"
        android:layout_height="wrap_content"
        android:minHeight="20dp"
        android:layout_gravity="top|center_horizontal"
        android:layout_marginStart="14dp"
        android:layout_marginTop="2dp"
        android:background="@drawable/notification_badge"
        android:gravity="center"
        android:text="@string/notification_badge"
        android:textColor="@android:color/white"
        android:textSize="12sp" />
</FrameLayout>
```

After, to add badge, simply call the **addBadge** function by passing as instance of the bottomNavigationView, an activity and a position value that you want to add your badge:

**MainActivity.kt**

```kotlin

BottomNavigationViewHelper.addBadge(bottomNavigationView,this,3)

```

## Contributions

Pull requests for new features, bug fixes, and suggestions are welcome!

## License

The MIT License (MIT)

Copyright (c) 2017 Nucleus.eti

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

## Contact

Nucleus.eti <suporte@nucleus.eti.br>
+55 85 3025-4949 <phone number>
+55 85 99872-6251 <whatsapp>
@nucleus.eti <Instagram>

