# Bottom Navigation View

 <img src="https://github.com/Nucleus-Inc/NavigationBottomBar/blob/master/graphics/image_demo2.png" width="33%" /> <img src="https://github.com/Nucleus-Inc/NavigationBottomBar/blob/master/graphics/image_demo1.png" width="33%" /> <img src="https://github.com/Nucleus-Inc/NavigationBottomBar/blob/master/graphics/image_demo3.png" width="33%" />
 
## Introduction

The Bottom Navigation View has been in the material design guidelines for some time, but it hasn’t been easy for us to implement it into our apps. Some applications have built their own solutions, whilst others have relied on third-party open-source libraries to get the job done. Now the design support library is seeing the addition of this bottom navigation bar, let’s take a dive into how we can use it!

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

   * **inflateMenu(int menuResource):** Inflate a menu for the bottom navigation view using a menu resource identifier
   * **setItemBackgroundResource(int backgroundResource):** The background to be used for the menu items.
   * **setItemTextColor(ColorStateList colorStateList):** A ColorStateList used to color the text used for the menu items
   * **setItemIconTintList(ColorStateList colorStateList):** A ColorStateList used to tint the icons used for the menu items

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
