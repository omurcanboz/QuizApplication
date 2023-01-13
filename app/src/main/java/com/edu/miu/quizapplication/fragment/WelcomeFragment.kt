package com.edu.miu.quizapplication.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.edu.miu.quizapplication.R
import com.edu.miu.quizapplication.adapter.ExtendedPagerAdapter
import com.edu.miu.quizapplication.utility.PreferencesManager

class WelcomeFragment : BaseFragment() {

    private var viewPager: ViewPager?=null
    private var layout : LinearLayout?=null
    private var skipButton : Button?=null
    private var nextButton : Button?=null
    private var preferencesManager : PreferencesManager?=null

    private lateinit var dots: Array<TextView?>
    private lateinit var layouts: IntArray

    private var extendedPagerAdapter: ExtendedPagerAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.welcome_fragment, container, false)
        preferencesManager = PreferencesManager(context)

        viewPager = view.findViewById(R.id.view_pager) as ViewPager
        layout = view.findViewById(R.id.layoutDots) as LinearLayout
        skipButton = view.findViewById(R.id.btn_skip) as Button
        nextButton = view.findViewById(R.id.btn_next) as Button

        layouts = intArrayOf(
            R.layout.slide,
            R.layout.slide,
            R.layout.slide,
            R.layout.slide
        )

        addDots(0)
        colorChange()

        extendedPagerAdapter = ExtendedPagerAdapter(context, layouts)
        viewPager?.adapter = extendedPagerAdapter
        viewPager?.addOnPageChangeListener(viewPageChangeListener)

        skipButton?.setOnClickListener {
            goHome()
        }

        nextButton?.setOnClickListener {
            val current : Int = getItem(+1)
            if(current < layouts.size) viewPager?.currentItem = current
            else goHome()
        }

        return view
    }

    private fun goHome(view: View?=null) {
        preferencesManager!!.setFirstTime(false)
        Navigation.findNavController(requireView()).navigate(R.id.action_welcomeFragment_to_homeFragment)
    }

    private fun getItem(i : Int): Int {
        return viewPager!!.currentItem + i
    }

    private fun addDots(current: Int) {
        dots = arrayOfNulls(layouts.size)
        val activeColors = resources.getIntArray(R.array.array_dot_active)
        val inactiveColors = resources.getIntArray(R.array.array_dot_inactive)
        layout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(context)
            dots[i]?.text = Html.fromHtml("&#8226;")
            dots[i]?.textSize = 35F
            dots[i]?.setTextColor(inactiveColors[current])
            layout!!.addView(dots[i])
        }

        if(dots.isNotEmpty()) dots[current]?.setTextColor(activeColors[current])

    }

    private fun colorChange() {
        val window: Window?=activity?.window
        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.statusBarColor = Color.TRANSPARENT
    }

    var viewPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            TODO("Not yet implemented")
        }

        override fun onPageScrollStateChanged(state: Int) {
            TODO("Not yet implemented")
        }

        override fun onPageSelected(position: Int) {
            addDots(position)

            if(position == layouts.size-1) {
                nextButton?.text = getString(R.string.start)
                skipButton!!.visibility = View.GONE
            } else {
                nextButton?.text = getString(R.string.next)
                skipButton!!.visibility = View.VISIBLE
            }
        }


    }


}