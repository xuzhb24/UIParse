package com.android.util.uiparse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuzhb on 2020/12/12
 */
public class ActivityStructure {

    private static final String TAG = "ActivityStructure";

    private String packageName;   //应用包名
    private String activityName;  //当前Activity类名
    private String activityPath;  //当前Activity包路径
    private List<FragmentStructure> fragmentList;  //当前Activity包含的Fragment列表

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityPath() {
        return activityPath;
    }

    public void setActivityPath(String activityPath) {
        this.activityPath = activityPath;
    }

    public List<FragmentStructure> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<FragmentStructure> fragmentList) {
        this.fragmentList = fragmentList;
    }

    //获取处在前台的Fragment
    public FragmentStructure getTopFragment() {
        List<FragmentStructure> userVisibleHintList = new ArrayList<>();
        List<FragmentStructure> isResumedList = new ArrayList<>();
        for (FragmentStructure f : fragmentList) {
            if (f.isUserVisibleHint()) {
                userVisibleHintList.add(f);
            }
            if (f.isResumed()) {
                isResumedList.add(f);
            }
        }
        boolean isResumed = isResumedList.size() <= userVisibleHintList.size();
        List<FragmentStructure> firstList = new ArrayList<>();   //一级Fragment列表
        List<FragmentStructure> secondList = new ArrayList<>();  //二级Fragment列表
        for (FragmentStructure f : fragmentList) {
            if (isResumed ? f.isResumed() : f.isUserVisibleHint()) {
                if (!f.isChildFragment()) {
                    firstList.add(f);
                } else {
                    secondList.add(f);
                }
            }
        }
        Util.printObject("一级可见", firstList);
        Util.printObject("二级可见", secondList);
        if (secondList.size() > 0) {
            for (FragmentStructure f : secondList) {
                if (isFilter(f)) {
                    continue;
                }
                if (isResumed ? f.getParent().isResumed() : f.getParent().isUserVisibleHint()) {
                    return f;
                }
            }
        }
        if (firstList.size() > 0) {
            for (FragmentStructure f : firstList) {
                if (isFilter(f)) {
                    continue;
                }
                return f;
            }
        }
        return null;
    }

    //过滤掉一些特定的Fragment
    private boolean isFilter(FragmentStructure f) {
        if (f.getName().contains("ParseDialog")
                || f.getName().contains("SupportRequestManagerFragment")  //Glide添加的Fragment
        ) {
            return true;
        }
        return false;
    }

}
