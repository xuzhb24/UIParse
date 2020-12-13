package com.android.uiparse.ui.main

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.android.uiparse.R
import com.android.uiparse.base.BaseFragment
import com.android.uiparse.ui.home.FirstFragment
import com.android.uiparse.ui.home.SecondFragment
import com.android.uiparse.ui.home.ThirdFragment
import com.android.util.uiparse.Util
import kotlinx.android.synthetic.main.fragment_home.*
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView

/**
 * Created by xuzhb on 2020/12/13
 * Desc:
 */
class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun getLayoutId() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    private fun initViewPager() {
        val titleList: MutableList<String> = mutableListOf("第一页", "第二页", "第三页")
        val fragmentList: MutableList<Fragment> = mutableListOf(
            FirstFragment.newInstance(),
            SecondFragment.newInstance(),
            ThirdFragment.newInstance()
        )
        view_pager.adapter = object :
            FragmentPagerAdapter(childFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            override fun getItem(position: Int) = fragmentList[position]
            override fun getCount() = fragmentList.size
        }
        view_pager.offscreenPageLimit = fragmentList.size
        magic_indicator.navigator = CommonNavigator(context).apply {
            isAdjustMode = true
            adapter = object : CommonNavigatorAdapter() {
                override fun getTitleView(context: Context?, index: Int): IPagerTitleView =
                    ColorTransitionPagerTitleView(context).apply {
                        normalColor = Color.parseColor("#888888")
                        selectedColor = Color.parseColor("#000000")
                        text = titleList[index]
                        textSize = 16f
                        //设置左右间距
                        setPadding(0, 0, 0, 0)
                        setOnClickListener {
                            view_pager.currentItem = index
                        }
                    }

                override fun getCount() = titleList.size

                override fun getIndicator(context: Context?): IPagerIndicator =
                    LinePagerIndicator(context).apply {
                        mode = LinePagerIndicator.MODE_EXACTLY
                        lineWidth = Util.dp2px(context, 16f)
                        lineHeight = Util.dp2px(context, 4f)
                        roundRadius = Util.dp2px(context, 2.75f)
                        startInterpolator = AccelerateDecelerateInterpolator()
                        endInterpolator = DecelerateInterpolator(2.0f)
                        setColors(Color.BLACK)
                    }

            }
        }
        ViewPagerHelper.bind(magic_indicator, view_pager)
    }

}