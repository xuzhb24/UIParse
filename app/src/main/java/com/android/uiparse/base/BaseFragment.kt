package com.android.uiparse.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * Created by xuzhb on 2019/9/7
 * Desc:基类Fragment
 */
abstract class BaseFragment : Fragment() {

    companion object {
        private const val TAG = "BaseFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    //获取ViewBinding
    abstract fun getLayoutId(): Int

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.i(TAG, "setUserVisibleHint ${javaClass.name} $isVisibleToUser")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume ${javaClass.name}")
    }

}