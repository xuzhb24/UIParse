package com.android.uiparse.ui.video

import com.android.uiparse.R
import com.android.uiparse.base.BaseFragment

/**
 * Created by xuzhb on 2020/12/13
 * Desc:
 */
class VideoFirstFragment : BaseFragment() {

    companion object {
        fun newInstance() = VideoFirstFragment()
    }

    override fun getLayoutId() = R.layout.fragment_video_first
}