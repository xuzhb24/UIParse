解析页面结构，辅助获取前台Activity类名和前台Fragment类名，适用于AppCompatActivity  
导入：  
implementation 'com.github.xuzhb24:uiparse:1.0.1'  
使用：  
在根Activity或具体Activity中重写dispatchTouchEvent，加入ParseUtil.showTopActivityInfo(this, ev)这一行代码即可  
Activity需继承AppCompatActivity，ViewPager的adapter建议使用BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT标识  