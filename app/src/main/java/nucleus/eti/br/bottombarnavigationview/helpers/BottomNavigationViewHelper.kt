package nucleus.eti.br.bottombarnavigationview.helpers

import android.support.annotation.IdRes
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import nucleus.eti.br.bottombarnavigationview.R
import android.view.ViewGroup
import android.support.v7.view.menu.MenuView
import android.view.LayoutInflater
import android.util.TypedValue
import android.util.DisplayMetrics

object BottomNavigationViewHelper {

    fun disableShiftMode(view: BottomNavigationView) {
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item = menuView.getChildAt(i) as BottomNavigationItemView
                item.setShiftingMode(false)
                // set once again checked value, so view will be updated
                item.setChecked(item.itemData.isChecked)
            }
        } catch (e: NoSuchFieldException) {
            Log.e("BNVHelper", "Unable to get shift mode field", e)
        } catch (e: IllegalAccessException) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e)
        }
    }

    fun removeTextLabel(view: BottomNavigationView){
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        for(i in 0 until menuView.childCount){
            val item = menuView.getChildAt(i) as BottomNavigationItemView
            val itemView: View = view.findViewById(item.id)
            if(itemView is MenuView.ItemView){
                val itemViewGroup = itemView as ViewGroup
                for(j in 0 until itemViewGroup.childCount){
                    val v = itemViewGroup.getChildAt(j)
                    if(v is ViewGroup) {
                        itemViewGroup.removeViewAt(j)
                    }
                }
                itemView.setPadding(itemView.paddingLeft,24,itemView.paddingRight,24)
            }
        }
    }

    fun addBadge(view: BottomNavigationView, activity : AppCompatActivity, field: Int) {
        val bottomNavigationMenuView = view.getChildAt(0) as BottomNavigationMenuView
        val v = bottomNavigationMenuView.getChildAt(field)
        val itemView = v as BottomNavigationItemView
        val badge = LayoutInflater.from(activity).inflate(R.layout.badge, bottomNavigationMenuView, false)
        itemView.addView(badge)
    }

    fun sizeIcon(view: BottomNavigationView, metrics: DisplayMetrics, float: Float){
        val menuView = view.getChildAt(0) as BottomNavigationMenuView
        for (i in 0 until menuView.childCount) {
            val iconView = menuView.getChildAt(i).findViewById<View>(android.support.design.R.id.icon)
            val layoutParams = iconView.layoutParams
            layoutParams.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, float, metrics).toInt()
            layoutParams.width = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, float, metrics).toInt()
            iconView.layoutParams = layoutParams
        }
    }

}