package com.android.util.uiparse;

import android.app.ActivityManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhb on 2020/12/12
 * Desc:页面解析工具
 */
public class ParseUtil {

    //双击屏幕顶部中间区域获取当前页面的Activity信息
    public static void showTopActivityInfo(AppCompatActivity activity, MotionEvent ev) {
        WindowManager manager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        float width = Util.dp2px(activity, 40);
        float height = Util.dp2px(activity, 80);
        float left = (screenWidth - width) / 2f;
        float right = left + width;
        if (ev.getAction() == MotionEvent.ACTION_DOWN && ev.getRawY() < height
                && ev.getRawX() > left && ev.getRawX() < right) {
            CheckFastClickUtil.setOnMultiClickListener(600, clickCount -> {
                if (clickCount == 2) {
                    ParseDialog.newInstance().show(activity.getSupportFragmentManager());
                }
            });
        }
    }

    public static ActivityStructure getActivityStructure(FragmentActivity activity) {
        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        String packageName = info.topActivity.getPackageName();
        String className = info.topActivity.getClassName();
        String activityName = className.substring(className.lastIndexOf(".") + 1);
        String activityPath = className.substring(0, className.lastIndexOf("."));
        List<FragmentStructure> fragmentList = new ArrayList<>();
        for (Fragment fragment : activity.getSupportFragmentManager().getFragments()) {
            if (fragment instanceof ParseDialog) {
                continue;
            }
            FragmentStructure parent = new FragmentStructure();
            parent.setName(getFragmentName(fragment));
            parent.setParent(null);
            parent.setResumed(fragment.isResumed());
            parent.setUserVisibleHint(fragment.getUserVisibleHint());
            fragmentList.add(parent);
            fragmentList.addAll(getChildFragments(fragment, parent));
        }
        ActivityStructure structure = new ActivityStructure();
        structure.setPackageName(packageName);
        structure.setActivityName(activityName);
        structure.setActivityPath(activityPath);
        structure.setFragmentList(fragmentList);
        return structure;
    }

    private static List<FragmentStructure> getChildFragments(Fragment fragment, FragmentStructure parent) {
        List<FragmentStructure> list = new ArrayList<>();
        for (Fragment f : fragment.getChildFragmentManager().getFragments()) {
            FragmentStructure structure = new FragmentStructure();
            structure.setName(getFragmentName(f));
            structure.setParent(parent);
            structure.setResumed(f.isResumed());
            structure.setUserVisibleHint(f.getUserVisibleHint());
            list.add(structure);
        }
        return list;
    }

    private static String getFragmentName(Fragment fragment) {
        String name = fragment.getClass().getName();
        if (name.contains(".")) {
            return name.substring(name.lastIndexOf(".") + 1);
        }
        return name;
    }

}
