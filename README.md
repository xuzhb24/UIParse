详细介绍参见链接：https://blog.csdn.net/xuzhb_blog/article/details/124478701  
解析页面结构，双击屏幕顶部中间区域获取前台Activity类名和前台Fragment类名，适用于AppCompatActivity  
导入：  
implementation 'com.github.xuzhb24:uiparse:1.0.3'  
同时需要在Project的build.gradle的repositories下加入maven { url "https://jitpack.io" }  
使用：  
在根Activity或具体Activity中重写dispatchTouchEvent，加入ParseUtil.showTopActivityInfo(this, ev)这一行代码即可  
Activity需继承AppCompatActivity，ViewPager的adapter建议使用BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT标识  
