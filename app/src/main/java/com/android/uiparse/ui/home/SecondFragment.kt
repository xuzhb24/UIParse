package com.android.uiparse.ui.home

import com.android.uiparse.R
import com.android.uiparse.base.BaseFragment

/**
 * Created by xuzhb on 2020/12/13
 * Desc:
 */
class SecondFragment : BaseFragment() {

    companion object {
        fun newInstance() = SecondFragment()
    }

    override fun getLayoutId() = R.layout.fragment_second
}