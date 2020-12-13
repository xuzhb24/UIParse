package com.android.uiparse.ui.home

import android.os.Bundle
import android.view.View
import com.android.uiparse.R
import com.android.uiparse.base.BaseFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * Created by xuzhb on 2020/12/13
 * Desc:
 */
class FirstFragment : BaseFragment() {

    companion object {
        fun newInstance() = FirstFragment()
    }

    override fun getLayoutId() = R.layout.fragment_first

}