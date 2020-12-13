package com.android.uiparse.base

import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.android.util.uiparse.ParseUtil

/**
 * Created by xuzhb on 2019/8/31
 * Desc:基类Activity
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    //获取ViewBinding
    abstract fun getLayoutId(): Int

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        //屏幕顶部中间区域双击获取当前Activity类名
        ParseUtil.showTopActivityInfo(this, ev)
        return super.dispatchTouchEvent(ev)
    }
}