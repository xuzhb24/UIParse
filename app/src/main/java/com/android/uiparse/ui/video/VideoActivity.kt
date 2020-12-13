package com.android.uiparse.ui.video

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.android.uiparse.R
import com.android.uiparse.base.BaseActivity
import com.android.util.uiparse.Util
import kotlinx.android.synthetic.main.activity_video.*
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
class VideoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
    }

    override fun getLayoutId() = R.layout.activity_video

    private fun initViewPager() {
        val titleList: MutableList<String> = mutableListOf("视频第一页", "视频第二页")
        val fragmentList: MutableList<Fragment> = mutableListOf(
            VideoFirstFragment.newInstance(),
            VideoSecondFragment.newInstance()
        )
        view_pager.adapter = object :
            FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) = fragmentList[position]
            override fun getCount() = fragmentList.size
        }
        view_pager.offscreenPageLimit = fragmentList.size
        magic_indicator.navigator = CommonNavigator(this).apply {
            isAdjustMode = true
            adapter = object : CommonNavigatorAdapter() {
                override fun getTitleView(context: Context?, index: Int): IPagerTitleView =
                    ColorTransitionPagerTitleView(this@VideoActivity).apply {
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
                    LinePagerIndicator(this@VideoActivity).apply {
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