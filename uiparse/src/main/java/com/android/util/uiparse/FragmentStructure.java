package com.android.util.uiparse;

/**
 * Created by xuzhb on 2020/12/12
 */
public class FragmentStructure {

    private String name;               //Fragment名称
    private boolean userVisibleHint;   //是否可见
    private boolean isResumed;         //是否处在前台
    private FragmentStructure parent;  //父Fragment，该值为null的是一级Fragment

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUserVisibleHint() {
        return userVisibleHint;
    }

    public void setUserVisibleHint(boolean userVisibleHint) {
        this.userVisibleHint = userVisibleHint;
    }

    public boolean isResumed() {
        return isResumed;
    }

    public void setResumed(boolean resumed) {
        isResumed = resumed;
    }

    public FragmentStructure getParent() {
        return parent;
    }

    public void setParent(FragmentStructure parent) {
        this.parent = parent;
    }

    //是否是二级Fragment，一级Fragment该属性为空
    public boolean isChildFragment() {
        return parent != null;
    }

}
