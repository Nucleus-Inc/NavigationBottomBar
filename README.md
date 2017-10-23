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

    <android.support.v7.widget.Toolbar
        android:id="@+id/toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/white">

        <RelativeLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <ImageView
                android:id="@+id/toolbar_img"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentTop="true"
                android:layout_centerHorizontal="true"
                android:scaleType="centerCrop"
                android:src="@drawable/nucleus" />

        </RelativeLayout>

    </android.support.v7.widget.Toolbar>

    <FrameLayout
        android:id="@+id/framelayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/toolbar"/>

    <android.support.design.widget.BottomNavigationView
        android:id="@+id/navigation"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:background="@android:color/white"
        app:itemIconTint="@color/tinting_selected_items"
        app:itemTextColor="@color/tinting_selected_items"
        app:menu="@menu/bottombar_items"/>

</RelativeLayout>
```
