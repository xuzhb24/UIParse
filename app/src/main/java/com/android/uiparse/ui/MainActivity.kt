package com.android.uiparse.ui

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.android.uiparse.R
import com.android.uiparse.base.BaseActivity
import com.android.uiparse.ui.main.HomeFragment
import com.android.uiparse.ui.main.MineFragment
import com.android.uiparse.ui.main.VideoFragment
import com.android.util.uiparse.Util
import kotlinx.android.synthetic.main.activity_main.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    private fun initViewPager() {
        val titleList: MutableList<String> = mutableListOf("首页", "视频", "我的")
        val fragmentList: MutableList<Fragment> = mutableListOf(
            HomeFragment.newInstance(),
            VideoFragment.newInstance(),
            MineFragment.newInstance()
        )
        view_pager.adapter = object :
            FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int) = fragmentList[position]
            override fun getCount() = fragmentList.size
        }
        view_pager.offscreenPageLimit = fragmentList.size
        magic_indicator.navigator = CommonNavigator(this).apply {
            isAdjustMode = true
            adapter = object : CommonNavigatorAdapter() {
                override fun getTitleView(context: Context?, index: Int): IPagerTitleView =
                    ColorTransitionPagerTitleView(this@MainActivity).apply {
                        normalColor = Color.parseColor("#888888")
                        selectedColor = Color.parseColor("#000000")
                        text = titleList[index]
                        textSize = 20f
                        //设置左右间距
                        setPadding(0, 0, 0, 0)
                        setOnClickListener {
                            view_pager.currentItem = index
                        }
                    }

                override fun getCount() = titleList.size

                override fun getIndicator(context: Context?): IPagerIndicator =
                    LinePagerIndicator(this@MainActivity).apply {
                        mode = LinePagerIndicator.MODE_EXACTLY
                        lineWidth = Util.dp2px(applicationContext, 16f)
                        lineHeight = Util.dp2px(applicationContext, 4f)
                        roundRadius = Util.dp2px(applicationContext, 2.75f)
                        startInterpolator = AccelerateDecelerateInterpolator()
                        endInterpolator = DecelerateInterpolator(2.0f)
                        setColors(Color.BLACK)
                    }

            }
        }
        ViewPagerHelper.bind(magic_indicator, view_pager)
    }

}