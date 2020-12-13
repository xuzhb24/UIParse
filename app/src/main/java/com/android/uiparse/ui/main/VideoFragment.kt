package com.android.uiparse.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.uiparse.R
import com.android.uiparse.base.BaseFragment
import com.android.uiparse.ui.video.VideoActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_video.*

/**
 * Created by xuzhb on 2020/12/13
 * Desc:
 */
class VideoFragment : BaseFragment() {

    companion object {
        fun newInstance() = VideoFragment()
    }

    override fun getLayoutId() = R.layout.fragment_video

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn.setOnClickListener {
            activity?.let {
                it.startActivity(Intent(it, VideoActivity::class.java))
            }
        }
        Glide.with(this)
            .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg")
            .into(iv)
    }

}