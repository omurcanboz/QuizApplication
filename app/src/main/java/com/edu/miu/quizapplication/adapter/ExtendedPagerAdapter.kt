package com.edu.miu.quizapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class ExtendedPagerAdapter(
    context: Context?,
    private var layouts: IntArray
) : PagerAdapter() {

    private var layoutInflater: LayoutInflater?=null

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View?=layoutInflater?.inflate(layouts[position], container, false)
        container.addView(view)
        return view!!
    }

    init {
        layoutInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
    }

    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        val view = obj as View
        container.removeView(view)
    }

}